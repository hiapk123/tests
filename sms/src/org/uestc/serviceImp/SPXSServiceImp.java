package org.uestc.serviceImp;

import java.sql.SQLException;
import java.text.ParseException;

import org.uestc.service.SPXSService;
import org.uestc.util.PageBean;

import com.uestc.bean.SPXSBean;
import com.uestc.bean.Sale;

public class SPXSServiceImp implements SPXSService {

	@Override
	public PageBean<SPXSBean> findAllByUid(Long uId, int pc) {
		try {
			return spxsDao.findAllByUid(uId, pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageBean<SPXSBean> findByCombination(String storeName, String beginTime, String endTime, String condition,
			Long uId, int pc) {
		try {
			return spxsDao.findByCombination(storeName, beginTime, endTime, condition, uId, pc);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
