package org.uestc.serviceImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.uestc.service.AuditOrderService;
import org.uestc.util.PageBean;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.uestc.bean.Booking;
import com.uestc.bean.OrderItem;

public class AuditOrderServiceImp implements AuditOrderService {

	@Override
	public PageBean<Booking> findAllBookingByUid(Long uId, int pc) {
		try {
			return auditOrderDao.findAllBookingByUid(uId, pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<String> findAllDate() {
		try {
			return auditOrderDao.findAllDate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageBean<Booking> findByCombination(String bookingNo, String date, String storeName, String status, Long uId,
			int pc) {
		try {
			return auditOrderDao.findByCombination(bookingNo, date, storeName, status, uId ,pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<OrderItem> findByBNo(String bno) {
		try {
			return auditOrderDao.findByBNo(bno);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateBookingByBNo(String status, String quantity, String description, String gIndex, String bno) {
		try {
			auditOrderDao.updateBookingByBNo(status, quantity, description, gIndex, bno);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(String bno) {
		try {
			auditOrderDao.delete(bno);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<OrderItem> findByBNos(String bnos) {
		List<OrderItem> list = new ArrayList<OrderItem>();
		if (bnos != null && !bnos.trim().isEmpty()) {
			String[] bnoArr = bnos.split(","); // 页面传递的数据以","分隔
			for (int i = 0; i < bnoArr.length; i++) {
				try {
					List<OrderItem> list1 = auditOrderDao.findByBNo(bnoArr[i]);
					list = merge(list, list1);
					
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			
			}
		}
		return list;
	}
	
	public List<OrderItem> merge(List<OrderItem> list1, List<OrderItem> list2) {
		Set<Integer> set = new HashSet<Integer>(); // 记录合并的下标

		if (list1 != null) { // list1 != null && list1.size() != 0
			for (int i = 0; i < list1.size(); i++) {
				OrderItem item1 = list1.get(i);
				String code = item1.getBarcode();
				if (list2 != null) { // list2 != null && list2.size() != 0
					for (int j = 0; j < list2.size(); j++) {
						OrderItem item2 = list2.get(j);
						if (code.equals(item2.getBarcode())) {
							String num1 = item1.getgNum();
							String num2 = item2.getgNum();
							Integer num = Integer.parseInt(num1) + Integer.parseInt(num2);
							item1.setgNum(num.toString());
							set.add(j);
						}
					}
				}
			}
//			System.out.println(set);
			for (int i = 0; i < list2.size(); i++) {
				if (!set.contains(i)) {
					list1.add(list2.get(i));
				}
			}
		}
		return list1;
	}

}
