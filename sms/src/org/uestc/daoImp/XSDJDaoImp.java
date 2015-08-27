package org.uestc.daoImp;

import java.sql.SQLException;
import java.text.ParseException;

import org.uestc.dao.XSDJDao;
import org.uestc.util.PageBean;

import com.uestc.bean.XSDJBean;

public class XSDJDaoImp implements XSDJDao {

	@Override
	public PageBean<XSDJBean> findAllByUid(Long uId, int pc) throws SQLException {
		return null;
	}

	@Override
	public PageBean<XSDJBean> findByCombination(String storeName, String receiptType, String beginTime, String endTime,
			String seriNum, Long uId, int pc) throws SQLException, ParseException {
		return null;
	}

}
