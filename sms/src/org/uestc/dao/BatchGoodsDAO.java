package org.uestc.dao;

import java.util.List;

import com.uestc.bean.batchgoods;
import com.uestc.bean.meminform;

public interface BatchGoodsDAO {
	
	
	public void shinsert(String sql,Object ... param);
	//
	public String shsalefind(String sql, Object... params);
	//设置的是�?个返回一个实体类的方法�??
	public List<batchgoods> find(String sql,Object ... param);
	
	//设置的是�?个查询查询结果个数的方法，用于判断分页�?�共有多少页
	public int countfind(String sql,Object ... param);
	
	//删除操作
	public void shdelete(String sql,Object ... param);
	
	//进行修改的更新操�?
	public void shupdate(String sql,Object ... param);
	
	
	//会员资料页面方法
	public List<meminform> memfind(String sql,Object ...param);

}
