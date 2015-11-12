package org.uestc.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.uestc.util.JdbcUtils;
import org.uestc.util.PageBean;

import com.uestc.bean.Booking;
import com.uestc.bean.OrderItem;

public interface AuditOrderDao {

	public QueryRunner qr = new QueryRunner(JdbcUtils.getInstance().getDataSource());

	PageBean<Booking> findAllBookingByUid(Long uId, int pc) throws SQLException;

	List<String> findAllDate()throws SQLException;

	PageBean<Booking> findByCombination(String bookingNo, String date, String storeName, String status, Long uId, int pc) throws SQLException;

	List<OrderItem> findByBNo(String bno)throws SQLException;
}
