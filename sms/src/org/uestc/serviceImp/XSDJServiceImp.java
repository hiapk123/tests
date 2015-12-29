package org.uestc.serviceImp;

import java.sql.SQLException;
import java.text.ParseException;

import org.uestc.service.XSDJService;
import org.uestc.util.PageBean;

import com.uestc.bean.XSDJBean;

public class XSDJServiceImp implements XSDJService {

	@Override
	public PageBean<XSDJBean> findAllByUid(Long uId, int pc) {
		try {
			return xsdjDao.findAllByUid(uId, pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageBean<XSDJBean> findByCombination(String storeName, String receiptType, String beginTime, String endTime,
			String seriNum, Long uId, int pc) {
		try {
			return xsdjDao.findByCombination(storeName, receiptType, beginTime, endTime, seriNum, uId, pc);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageBean<XSDJBean> findAll(int pc) {
		try {
			return xsdjDao.findAll(pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageBean<XSDJBean> findAllByCombination(String storeName, String receiptType, String beginTime,
			String endTime, String seriNum, int pc) {
		try {
			return xsdjDao.findAllByCombination(storeName, receiptType, beginTime, endTime, seriNum, pc);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
