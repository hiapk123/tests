package org.uestc.serviceImp;

import java.sql.SQLException;
import java.text.ParseException;

import org.uestc.service.QSFXService;
import org.uestc.util.PageBean;

import com.uestc.bean.Sale;

public class QSFXServiceImp implements QSFXService {

	@Override
	public PageBean<Sale> findAllSalesByUid(Long uId, int pc) {
		try {
			return qsfxDao.findAllSalesByUid(uId, pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageBean<Sale> findByCombination(String storeName, String beginTime, String endTime, String condition,
			Long uId, int pc) {
		try {
			return qsfxDao.findByCombination(storeName, beginTime, endTime, condition, uId, pc);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
