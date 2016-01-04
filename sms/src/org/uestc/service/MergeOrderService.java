package org.uestc.service;

import java.util.List;

import org.uestc.dao.MergeOrderDao;
import org.uestc.daoImp.MergeOrderDaoImp;
import org.uestc.util.PageBean;

import com.uestc.bean.OrderItem;
import com.uestc.bean.TbOrder;

public interface MergeOrderService {

	public MergeOrderDao mergeOrderDao = new MergeOrderDaoImp();
	
	PageBean<TbOrder> findAllOrder(int pc);

	List<String> findAllDate();

	PageBean<TbOrder> findByCombination(String bookingNo, String date, String storeName, int pc);

	List<OrderItem> findByBNo(String bno);

	List<OrderItem> findByBNos(String bnos);
}
