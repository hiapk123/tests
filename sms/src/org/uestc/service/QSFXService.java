package org.uestc.service;

import org.uestc.dao.QSFXDao;
import org.uestc.daoImp.QSFXDaoImp;
import org.uestc.util.PageBean;

import com.uestc.bean.Sale;

public interface QSFXService {

	public QSFXDao qsfxDao = new QSFXDaoImp();

	PageBean<Sale> findAllSalesByUid(Long uId, int pc);

	PageBean<Sale> findByCombination(String storeName, String beginTime, String endTime, String condition, Long uId,
			int pc);

	PageBean<Sale> findAll(int pc);

	PageBean<Sale> findAllByCombination(String storeName, String beginTime, String endTime, String condition, int pc);
}
