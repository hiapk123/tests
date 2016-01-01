package org.uestc.service;

import org.uestc.dao.RJJLDao;
import org.uestc.daoImp.RJJLDaoImp;
import org.uestc.util.PageBean;

import com.uestc.bean.Sale;

public interface RJJLService {

	public RJJLDao rjjlDao = new RJJLDaoImp();

	PageBean<Sale> findAllSalesByUid(Long uId, int pc);

	PageBean<Sale> findByCombination(String storeName, String beginTime, String endTime, Long uId, int pc);

	PageBean<Sale> findAll(int pc);

	PageBean<Sale> findAllByCombination(String storeName, String beginTime, String endTime, int pc);
}

