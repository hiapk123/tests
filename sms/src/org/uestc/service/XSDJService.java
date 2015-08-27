package org.uestc.service;

import org.uestc.dao.XSDJDao;
import org.uestc.daoImp.XSDJDaoImp;
import org.uestc.util.PageBean;

import com.uestc.bean.XSDJBean;

public interface XSDJService {
	XSDJDao xsdjDao = new XSDJDaoImp();

	PageBean<XSDJBean> findAllByUid(Long uId, int pc);

	PageBean<XSDJBean> findByCombination(String storeName, String receiptType, String beginTime, String endTime,
			String seriNum, Long uId, int pc);
}
