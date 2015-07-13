package org.uestc.dao;

import java.util.List;

public interface AnalyseGoods {
	public List<Object[]> findByPage(int currentPage,int pageSize);
}
