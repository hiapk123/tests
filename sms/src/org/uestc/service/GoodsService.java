package org.uestc.service;

import java.util.List;



public interface GoodsService {

	public List<Object[]> findStoreByUserID(int uid);

	
	public List<Object[]> goodssearch(int sid,int cunrentPage);
}
