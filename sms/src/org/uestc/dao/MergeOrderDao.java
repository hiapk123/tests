package org.uestc.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.uestc.util.JdbcUtils;
import org.uestc.util.PageBean;

import com.uestc.bean.OrderItem;
import com.uestc.bean.TbOrder;

public interface MergeOrderDao {

	public QueryRunner qr = new QueryRunner(JdbcUtils.getInstance().getDataSource());
	
	List<String> findAllDate() throws SQLException;  
	
	PageBean<TbOrder> findAllOrder(int pc) throws SQLException;
	
	PageBean<TbOrder> findByCombination(String bookingNo, String date, String storeName, int pc) throws SQLException;

	List<OrderItem> findByBNo(String bno)throws SQLException;
}
