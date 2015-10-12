package org.uestc.dao;

import java.util.List;

public interface CategoryGoodsDao {
	public List<Object[]> find(String sql,Object ...params);
}
