package org.uestc.serviceImp;

import java.sql.SQLException;
import java.text.ParseException;

import org.uestc.service.RJJLService;
import org.uestc.util.PageBean;

import com.uestc.bean.Sale;

public class RJJLServiceImp implements RJJLService {

	@Override
	public PageBean<Sale> findAllSalesByUid(Long uId, int pc) {
		try {
			return rjjlDao.findAllSalesByUid(uId, pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageBean<Sale> findByCombination(String storeName, String beginTime, String endTime, Long uId, int pc) {
		try {
			return rjjlDao.findByCombination(storeName, beginTime, endTime, uId, pc);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageBean<Sale> findAll(int pc) {
		try {
			return rjjlDao.findAll(pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageBean<Sale> findAllByCombination(String storeName, String beginTime, String endTime, int pc) {
		try {
			return rjjlDao.findAllByCombination(storeName, beginTime, endTime, pc);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
