package org.uestc.service;

import java.util.List;

import org.uestc.dao.AuditOrderDao;
import org.uestc.daoImp.AuditOrderDaoImp;
import org.uestc.util.PageBean;

import com.uestc.bean.Booking;
import com.uestc.bean.OrderItem;

public interface AuditOrderService {

	public AuditOrderDao auditOrderDao = new AuditOrderDaoImp();
	
	PageBean<Booking> findAllBookingByUid(Long uId, int pc);

	List<String> findAllDate();

	PageBean<Booking> findByCombination(String bookingNo, String date, String storeName, String status, Long uId,
			int pc);

	List<OrderItem> findByBNo(String bno);

	void updateBookingByBNo(String status, String quantity, String description, String gIndex, String bno);

	void delete(String bno);

}
