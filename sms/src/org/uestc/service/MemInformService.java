package org.uestc.service;

import java.util.List;

public interface MemInformService 
{
	//查询绑定�?卡的店名�?
	public List<Object[]> shvtid();
	//绑定会员等级
	public List<Object[]> shvlevel();
	//会员卡状态的函数
	public List<Object[]> shvstatue();
	//初始化页面加载函�?
	public List<Object[]> meminfoinit();
	//状�?�提交函�?
	public List<Object[]> msubfind(String ssql);
	
	public List<Object[]> normalfinad(String sql);
	public void normalupdate(String sql);
	public List<Object[]> export();

}
