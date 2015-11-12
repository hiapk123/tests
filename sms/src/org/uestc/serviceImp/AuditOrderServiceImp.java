package org.uestc.serviceImp;

import java.sql.SQLException;
import java.util.List;

import org.uestc.service.AuditOrderService;
import org.uestc.util.PageBean;

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

}
