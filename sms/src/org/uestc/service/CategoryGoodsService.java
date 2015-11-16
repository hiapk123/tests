package org.uestc.service;

import java.util.List;

public interface CategoryGoodsService {
	public List<Object[]> findStoreByUserId(int uid);
	/**
	 * 获取某商店ID下的树形菜单
	 * @param storeID
	 * @return
	 */
	public String getStoreNameById(int storeID);
	public String getCategoryTree(int storeID);
	public void addCate(int pid,String name,int storeID);
	
	/**
	 * 删除某一门店下某一分类
	 * @param id
	 * @param storeID
	 * @return 1,success 2,error
	 */
	public boolean deleteCate(int id,int storeID);
	public void updateCate(int id, String name);
}