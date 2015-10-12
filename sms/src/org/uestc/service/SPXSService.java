package org.uestc.service;

import org.uestc.dao.SPXSDao;
import org.uestc.daoImp.SPXSDaoImp;
import org.uestc.util.PageBean;

import com.uestc.bean.SPXSBean;

public interface SPXSService {
	SPXSDao spxsDao = new SPXSDaoImp();

	PageBean<SPXSBean> findAllByUid(Long uId, int pc);

	PageBean<SPXSBean> findByCombination(String storeName, String beginTime, String endTime, String condition, Long uId,
			int pc);
}
