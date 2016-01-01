package org.uestc.service;

import java.util.List;

import org.uestc.dao.ZBTJDao;
import org.uestc.daoImp.ZBTJDaoImp;
import org.uestc.util.PageBean;

import com.uestc.bean.Sale;

public interface ZBTJService {

	public ZBTJDao zbtjDao = new ZBTJDaoImp();

	PageBean<Sale> findAllSalesByUid(Long uId, int pc);

	PageBean<Sale> findByCombination(String storeName, String beginTime, String endTime, String condition, Long uId,
			int pc);

	PageBean<Sale> findAll(int pc);

	PageBean<Sale> findAllByCombination(String storeName, String beginTime, String endTime, String condition, int pc);
}

