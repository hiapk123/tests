package org.uestc.daoImp;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;
import org.uestc.dao.AuditOrderDao;
import org.uestc.util.PageBean;
import org.uestc.util.PageConstants;

import com.uestc.bean.Booking;
import com.uestc.bean.OrderItem;
import com.uestc.bean.Store;
import com.uestc.bean.TbOrder;

public class AuditOrderDaoImp implements AuditOrderDao {
	
	@Override
	public void delete(String bno) throws SQLException {
		String sql = "delete from booking where b_no=?";
		qr.update(sql, bno);
	}

	@Override
	public void updateBookingByBNo(String status, String quantity, String description, String gIndex, String bno) throws SQLException {
		/**
		 * 修改 booking的b_status字段（其他字段不能修改，该表是记录原始订单的信息）
		 * 订单状态是前台隐藏(hidden)字段，值为"已处理"
		 */
		String sql = "update booking set b_status=? where b_no=?";
		qr.update(sql, status, bno); // booking表只能修改b_status字段
		
		
		/**
		 * tb_order操作策略：
		 * 查询tb_order表是否存在该订单记录
		 * 		1）如果存在该订单记录，就修改该记录
		 * 		2）如果不存在该记录，就插入新纪录
		 */
		/* 查询tb_order表是否存在该订单记录 */
		sql = "select count(1) from tb_order where b_no=?";
		Number number = qr.query(sql, new ScalarHandler(), bno);
		String bNums = "";
		String bInfos = "";
		String[] bNumArray = null;
		String[] bInfoArray = null;
		if (number.intValue() == 1) { /* 1）如果存在该订单记录，就修改该记录(update) */
			sql = "select b_num, b_info from booking where b_no=?"; // 查询出数据库之前的b_num信息，组装拼接再插入
			List<Object[]> list = qr.query(sql, new ArrayListHandler(), bno);
			if (list.size() > 0) {
				Object[] obj = list.get(0);
				if (obj[0] != null) {
					bNums = obj[0].toString();
					bNumArray = bNums.split(";");
					for (int i = 0; i < bNumArray.length; i++) {
						if (Integer.parseInt(gIndex) == i) {
							bNumArray[i] = quantity;
						}
					}
					bNums = ""; // 给bNums置为""
					for (int i = 0; i < bNumArray.length; i++) {
						bNums += bNumArray[i]+";";
					}
				}
				if (obj[1] != null) {
					bInfos = obj[1].toString();
					bInfoArray = bInfos.split(";");
					for (int i = 0; i < bInfoArray.length; i++) {
						if (Integer.parseInt(gIndex) == i) {
							bInfoArray[i] = description;
						}
					}
					bInfos = ""; // 给bInfos置为""
					for (int i = 0; i < bInfoArray.length; i++) {
						bInfos += bInfoArray[i]+";";
					}
				}
			}
			
			sql = "update tb_order set b_num=?,b_status=?,b_info=? where b_no=?"; // 只有这三个字段可以修改，其他字段保持不变
			qr.update(sql, bNums, status, bInfos, bno);
			
		} else { // 2）如果不存在该记录，就插入新纪录(insert)  
			sql = "select * from booking where b_no=?";
			List<Object[]> list = qr.query(sql, new ArrayListHandler(), bno);
			TbOrder tbOrder = new TbOrder();
			if (list.size() > 0) {
				Object[] obj = list.get(0);
				if (obj[1] != null) {
					tbOrder.setbNo(obj[1].toString());
				}
				if (obj[2] != null) {
					tbOrder.setgId(obj[2].toString());
				}
				if (obj[3] != null) {
					bNums = obj[3].toString();
					bNumArray = bNums.split(";");
					for (int i = 0; i < bNumArray.length; i++) {
						if (Integer.parseInt(gIndex) == i) {
							bNumArray[i] = quantity;
						}
					}
					bNums = ""; // 给bNums置为""
					for (int i = 0; i < bNumArray.length; i++) {
						bNums += bNumArray[i]+";";
					}
					tbOrder.setbNum(bNums); // 编辑页面中的数量
				}
				if (obj[4] != null) {
					tbOrder.setsId(obj[4].toString());
				}
				if (obj[5] != null) {
					tbOrder.setbStatus(status); // 编辑页面中的状态
				}
				if (obj[6] != null) {
					bInfos = obj[6].toString();
					bInfoArray = bInfos.split(";");
					for (int i = 0; i < bInfoArray.length; i++) {
						if (Integer.parseInt(gIndex) == i) {
							bInfoArray[i] = description;
						}
					}
					bInfos = ""; // 给bInfos置为""
					for (int i = 0; i < bInfoArray.length; i++) {
						bInfos += bInfoArray[i]+";";
					}
					tbOrder.setbInfo(description); // 编辑页面中的备注信息
				}
				if (obj[7] != null) {
//					tbOrder.setbDate(obj[7].toString()); // 原始订单的时间
					tbOrder.setbDate(new Date().getTime()+""); // 已处理的时间为当前系统时间
					
				}
				if (obj[8] != null) {
					tbOrder.setsDel(Integer.valueOf(obj[8].toString()));
				}
			}
			
			sql = "insert into tb_order(b_no,o_no,g_id,b_num,s_id,b_status,b_info,b_date,s_del) values(?,?,?,?,?,?,?,?,?)";
			Object[] params = {
				tbOrder.getbNo(),
				tbOrder.getbNo(), // 暂时让o_no和b_no相等
				tbOrder.getgId(),
				tbOrder.getbNum(),
				tbOrder.getsId(),
				tbOrder.getbStatus(),
				tbOrder.getbInfo(),
				tbOrder.getbDate(),
				tbOrder.getsDel()
			};
			qr.update(sql, params);
		}
	}

	@Override
	public List<OrderItem> findByBNo(String bno) throws SQLException {
		String sql = "select g_id, b_num, b_info from booking where b_no=?";
		String gIds = "";
		String bNums = "";
		String bInfos = "";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), bno);
		if (list.size() > 0) {
			Object[] obj = list.get(0);
			if (obj[0] != null) {
				gIds = obj[0].toString();
			}
			if (obj[1] != null) {
				bNums = obj[1].toString();
			}
			if (obj[2] != null) {
				bInfos = obj[2].toString();
			}
		}
		String[] bInfoArray = null;
		String[] bNumArray = null;
		String[] gIdArray = null;
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		if (!bNums.equals("")) { // 100;50;
			bNumArray = bNums.split(";");
		}
		if (!bInfos.equals("")) { // 无;无;
			bInfoArray = bInfos.split(";");
		}
		if (!gIds.equals("")) { // 10004;10003;
			gIdArray = gIds.split(";");
			if (gIdArray.length > 0) {
				for (int i = 0; i < gIdArray.length; i++) {
//					sql = "select g_barcode,g_name,g_pur_price from goods where g_id=?"; // 还差商品数量，来源于bNums中截取
					sql = "select g_barcode,g_name,g_pur_price from goods where g_barcode=?"; // booking表中的g_id实际代表goods表中的g_barcode，而不是主键g_id
					list = qr.query(sql, new ArrayListHandler(), gIdArray[i]);
					if (list.size() > 0) {
						Object[] obj = list.get(0);
						OrderItem orderItem = new OrderItem();
						if (obj[0] != null) {
							orderItem.setBarcode(obj[0].toString());
						}
						if (obj[1] != null) {
							orderItem.setgName(obj[1].toString());
						}
						if (obj[2] != null) {
							orderItem.setPrice(obj[2].toString());
						}
						orderItem.setgInfo(bInfoArray[i]);
						orderItem.setgNum(bNumArray[i]);
						orderItemList.add(orderItem);
					}
						
				}
			}
		}
		
		return orderItemList;
	}
	
	@Test
	public void testFindByBNo() throws Exception {
		List<OrderItem> list = findByBNo("ORDER20151016953452421");
		System.out.println("heheda");
	}

	@Override
	public PageBean<Booking> findAllBookingByUid(Long uId, int pc) throws SQLException {
		int ps = PageConstants.ADMIN_PAGE_SIZE;

		String sql = "select count(DISTINCT(b_no)) from booking where s_id in(select s_id from store where u_id=?)";
		Number number = qr.query(sql, new ScalarHandler(), uId);
		int tr = number.intValue();

		sql = "SELECT b_no,s_id,b_date,b_status,b_info FROM booking WHERE s_id in(select s_id from store where u_id=?) order by b_date limit ?,?;";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), uId, (pc - 1) * ps, ps);
		List<Booking> bookingList = new ArrayList<Booking>();
		for (Object[] obj : list) {
			Booking booking = new Booking();
			if (obj[0] != null) {
				booking.setBNo(obj[0].toString());
			}
			if (obj[1] != null) {
				booking.setStore(findStoreByStoreId(Long.valueOf(obj[1].toString())));
			}
			if (obj[2] != null) {
				booking.setBDate(msecToDateTimeStr(obj[2].toString()));
			}
			if (obj[3] != null) {
				booking.setBStatus(obj[3].toString());
			}
			if (obj[4] != null) {
				booking.setBInfo(obj[4].toString());
			}
			bookingList.add(booking);
		}

		PageBean<Booking> pb = new PageBean<Booking>();
		pb.setBeanList(bookingList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);

		return pb;
	}
	
	@Override
	public PageBean<Booking> findByCombination(String bookingNo, String date, String storeName, String status, Long uId,
			int pc) throws SQLException {
		int ps = PageConstants.ADMIN_PAGE_SIZE;

		String sql = "select count(distinct(b_no)) from booking where b_no like '%" + bookingNo + "%' AND FROM_UNIXTIME(b_date/1000,'%Y-%m-%d') between ? and ? AND s_id=? AND b_status=? AND s_id in(select s_id from store where u_id=?)";
//		String sql = "select count(distinct(b_no)) from booking where b_no=? AND FROM_UNIXTIME(b_date/1000,'%Y-%m-%d') between ? and ? AND s_id=? AND b_status=? AND s_id in(select s_id from store where u_id=?)";
		Number number = qr.query(sql, new ScalarHandler(), date, date, findStoreIdByStoreName(storeName), status, uId);
		int tr = number.intValue();

		sql = "select b_no,s_id,b_date,b_status,b_info from booking where b_no like '%" + bookingNo + "%' AND FROM_UNIXTIME(b_date/1000,'%Y-%m-%d') between ? and ? AND s_id=? AND b_status=? AND s_id in(select s_id from store where u_id=?) order by b_date limit ?,?";
//		sql = "select b_no,s_id,b_date,b_status,b_info from booking where b_no=? AND FROM_UNIXTIME(b_date/1000,'%Y-%m-%d') between ? and ? AND s_id=? AND b_status=? AND s_id in(select s_id from store where u_id=?) order by b_date limit ?,?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), date, date, findStoreIdByStoreName(storeName), status, uId, (pc - 1) * ps, ps);
		List<Booking> bookingList = new ArrayList<Booking>();
		for (Object[] obj : list) {
			Booking booking = new Booking();
			if (obj[0] != null) {
				booking.setBNo(obj[0].toString());
			}
			if (obj[1] != null) {
				booking.setStore(findStoreByStoreId(Long.valueOf(obj[1].toString())));
			}
			if (obj[2] != null) {
				booking.setBDate(msecToDateTimeStr(obj[2].toString()));
			}
			if (obj[3] != null) {
				booking.setBStatus(obj[3].toString());
			}
			if (obj[4] != null) {
				booking.setBInfo(obj[4].toString());
			}
			bookingList.add(booking);
		}

		PageBean<Booking> pb = new PageBean<Booking>();
		pb.setBeanList(bookingList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);

		return pb;
	}
	
	@Override
	public List<String> findAllDate() throws SQLException {
		String sql = "SELECT DISTINCT(FROM_UNIXTIME(b_date/1000,'%Y-%m-%d')) FROM booking ORDER BY b_date";
		List<Object[]> dateList = qr.query(sql, new ArrayListHandler());
		List<String> list = new ArrayList<String>();
		for (Object[] obj : dateList) {
			if (obj[0] != null) {
				list.add(obj[0].toString());
			}
		}
		return list;
	}
	
	private Store findStoreByStoreId(Long id) throws SQLException {
		String sql = "select s_name from store where s_id=?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), id);
		if (list.size() > 0) {
			Store store = new Store();
			Object[] obj = list.get(0);
			if (obj[0] != null) {
				store.setSName(obj[0].toString());
			}
			return store;
		}
		return null;
	}
	
	private String msecToDateTimeStr(String msecStr) {
		Long msecLong = Long.valueOf(msecStr);
		Date dat = new Date(msecLong);
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dat);
		SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str = format.format(gc.getTime());
		return str;
	}
	
	private Long findStoreIdByStoreName(String storeName) throws SQLException {
		String sql = "select s_id from store where s_name=?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), storeName);
		if (list.size() > 0) {
			Store store = new Store();
			Object[] obj = list.get(0);
			return Long.valueOf(obj[0].toString());
		}
		return null;
	}

}
