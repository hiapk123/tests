package org.uestc.service;

import java.util.List;

public interface HuoliuService{
	
	public List<Object[]> findStoreByUserID(int uid);
	public List<Object[]> supplierInfo(int s_id);
    public void editSupplier(String su_number, String su_name, String su_contacter, String su_phone, String su_email,
			String su_empower,String su_id);
    public List<Object[]> toExcel(int s_id);	
	
    public int getTotalSize(int store);
	List<Object[]> findSuppliers();
	
	
	
	
	
	
	
	
}