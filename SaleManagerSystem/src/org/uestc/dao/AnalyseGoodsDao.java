package org.uestc.dao;

import java.util.List;

public interface AnalyseGoodsDao {
	
	//分页查询，获取所有要查询语句的信息
	public List<Object[]> find(String sql,Object ...params);
	
	
}
