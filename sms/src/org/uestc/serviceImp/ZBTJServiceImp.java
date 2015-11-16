package org.uestc.serviceImp;

import java.sql.SQLException;

import org.uestc.service.ZBTJService;
import org.uestc.util.PageBean;

import com.uestc.bean.Sale;

public class ZBTJServiceImp implements ZBTJService {

	@Override
	public PageBean<Sale> findAllSalesByUid(Long uId, int pc) {
		try {
			return zbtjDao.findAllSalesByUid(uId, pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageBean<Sale> findByCombination(String storeName, String beginTime, String endTime, String condition,
			Long uId, int pc) {
		try {
			return zbtjDao.findByCombination(storeName, beginTime, endTime, condition, uId, pc);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
