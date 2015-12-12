package org.uestc.service;

import java.util.List;

public interface HuoliuService {

	public List<Object[]> findStoreByUserID(int uid);

	public List<Object[]> supplierInfo(int s_id, String s_del, String key);

	public List<Object[]> toExcel(int s_id);

	public int getTotalSize(int store);

	List<Object[]> findSuppliers();

	void editSupplier(String su_id, String su_name, String su_phone, String su_email, String su_contacter, String s_del,
			String su_ps_return, String su_gd_return, String su_empower, String su_address, String su_info);

}