package org.uestc.daoImp;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.uestc.dao.MergeOrderDao;
import org.uestc.util.PageBean;
import org.uestc.util.PageConstants;

import com.uestc.bean.OrderItem;
import com.uestc.bean.Store;
import com.uestc.bean.TbOrder;

public class MergeOrderDaoImp implements MergeOrderDao {

	@Override
	public List<String> findAllDate() throws SQLException {
		String sql = "SELECT DISTINCT(FROM_UNIXTIME(b_date/1000,'%Y-%m-%d')) FROM tb_order ORDER BY b_date";
		List<Object[]> dateList = qr.query(sql, new ArrayListHandler());
		List<String> list = new ArrayList<String>();
		for (Object[] obj : dateList) {
			if (obj[0] != null) {
				list.add(obj[0].toString());
			}
		}
		return list;
	}

	@Override
	public PageBean<TbOrder> findAllOrder(int pc) throws SQLException {
		int ps = PageConstants.ADMIN_PAGE_SIZE;

		String sql = "select count(DISTINCT(b_no)) from tb_order";
		Number number = qr.query(sql, new ScalarHandler());
		int tr = number.intValue();

		sql = "SELECT b_no,s_id,b_date,b_info FROM tb_order order by b_date limit ?,?;";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), (pc - 1) * ps, ps);
		List<TbOrder> orderList = new ArrayList<TbOrder>();
		for (Object[] obj : list) {
			TbOrder order = new TbOrder();
			if (obj[0] != null) {
				order.setbNo(obj[0].toString());
			}
			if (obj[1] != null) {
				order.setStore(findStoreByStoreId(Long.valueOf(obj[1].toString())));
			}
			if (obj[2] != null) {
				order.setbDate(msecToDateTimeStr(obj[2].toString()));
			}
			if (obj[3] != null) {
				order.setbInfo(obj[3].toString());
			}
			orderList.add(order);
		}

		PageBean<TbOrder> pb = new PageBean<TbOrder>();
		pb.setBeanList(orderList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);

		return pb;
	}

	@Override
	public PageBean<TbOrder> findByCombination(String bookingNo, String date, String storeName, int pc)
			throws SQLException {
		int ps = PageConstants.ADMIN_PAGE_SIZE;

		String sql = "select count(distinct(b_no)) from tb_order where b_no like '%" + bookingNo + "%' AND FROM_UNIXTIME(b_date/1000,'%Y-%m-%d') between ? and ? AND s_id=?";
		Number number = qr.query(sql, new ScalarHandler(), date, date, findStoreIdByStoreName(storeName));
		int tr = number.intValue();

		sql = "SELECT b_no,s_id,b_date,b_info FROM tb_order where b_no like '%" + bookingNo + "%' AND FROM_UNIXTIME(b_date/1000,'%Y-%m-%d') between ? and ? AND s_id=? order by b_date limit ?,?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), date, date, findStoreIdByStoreName(storeName), (pc - 1) * ps, ps);
		List<TbOrder> orderList = new ArrayList<TbOrder>();
		for (Object[] obj : list) {
			TbOrder order = new TbOrder();
			if (obj[0] != null) {
				order.setbNo(obj[0].toString());
			}
			if (obj[1] != null) {
				order.setStore(findStoreByStoreId(Long.valueOf(obj[1].toString())));
			}
			if (obj[2] != null) {
				order.setbDate(msecToDateTimeStr(obj[2].toString()));
			}
			if (obj[3] != null) {
				order.setbInfo(obj[3].toString());
			}
			orderList.add(order);
		}

		PageBean<TbOrder> pb = new PageBean<TbOrder>();
		pb.setBeanList(orderList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);

		return pb;
	}

	@Override
	public List<OrderItem> findByBNo(String bno) throws SQLException {
		String sql = "select g_id, b_num, b_info from tb_order where b_no=?";
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
		if (!gIds.equals("")) { // 
			gIdArray = gIds.split(";");
			if (gIdArray.length > 0) {
				for (int i = 0; i < gIdArray.length; i++) {
					sql = "select g_barcode,g_name,g_pur_price from goods where g_barcode=?";
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
