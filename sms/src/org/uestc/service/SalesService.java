package org.uestc.service;

import java.util.List;

public interface SalesService {
	public void save();
	
	//查询该用户所有的店铺
	public List<Object[]> findStoreByUserID(int uid);
	//按照店铺和日期查询销售额和利润,现在支出,现金收入
	public List<Object[]> getXiaoshouAndLiRun(int sid, String start, String end);
	//根据店铺和日期查询销售总单数
	public int getXiaoShouCount(int sid, String start, String end);
	//现金单数
	public int getCashCount(int sid, String start, String end);
	//银联单数
	public int getBankCount(int sid, String start, String end);
	//网银单数
	public int getOnlineCount(int sid, String start, String end);
	//会员充值次数
	public int getChongZhiCount(int sid, String start, String end);
	//充值赠送金额
	public List<Object[]> getChongzhiAndGiv(int sid, String start, String end);
	//现金销售单数金额
	//public List<Object[]> getCash(int sid, String start, String end);
	//银行卡销售金额
	//public List<Object[]> getBank(int sid, String start, String end);
	//网银
	//public List<Object[]> getOnline(int sid, String start, String end);
	//上面3个汇总
	public List<Object[]> get(int sid, String start, String end,int type);
	//会员充值方式
	public List<Object[]> getVip(int sid, String start, String end,int type);
	

}
