package org.uestc.serviceImp;

import java.util.List;

import org.uestc.service.HuoliuService;
import org.uestc.util.SqlHelper;

public class HuoliuServiceImp implements HuoliuService{

	@Override
	public List<Object[]> findStoreByUserID(int uid) {
		// TODO Auto-generated method stub
		String sql = "select s_id,s_name from store where u_id=?";
		List<Object[]> list = SqlHelper.find(sql, new Object[] { uid });
		return list;
	}

	@Override
	public List<Object[]> supplierInfo() {
		String sql = "select su_name,s_name from store where s_id=1";
		List<Object[]> list = SqlHelper.find(sql, 1);
		return list;
	}
	
	
	
	
}