package org.uestc.serviceImp;

import java.util.List;

import org.uestc.dao.AnalyseGoodsDao;
import org.uestc.dao.BatchGoodsDAO;
import org.uestc.daoImp.AnalyseGoodsDaoImp;
import org.uestc.daoImp.BatchGoodsDAOImp;
import org.uestc.service.MemInformService;

public class MemInformServiceImp implements MemInformService {
	
	AnalyseGoodsDao amaly=new  AnalyseGoodsDaoImp();
	BatchGoodsDAO bathdao=new BatchGoodsDAOImp();
	
	//编写�?个�?�用的查找函�?
	public List<Object[]> normalfinad(String sql)
	{
		return amaly.find(sql, null);
	}
	
	//编写�?个�?�用的插入和修改的操作�??
	public void normalupdate(String sql)
	{
		bathdao.shinsert(sql);
	}
	
	//状�?�提交函�?
		public List<Object[]> msubfind(String ssql)
		{
			return amaly.find(ssql, null);
		}
		
		public List<Object[]> export()
		{
			String sql4="select a.v_id,a.v_card_no,a.vip_name,a.vip_tel,a.v_level,a.v_balance,a.v_commodityintegral,b.s_name,a.vip_startdate, a.v_status FROM vip a LEFT JOIN store b ON a.s_id=b.s_id";
			return amaly.find(sql4, null);
		}
	
		
		
	//初始化页面加载函�?
		public List<Object[]> meminfoinit()
		{
			String sql4="select a.v_id,a.v_card_no,a.vip_name,a.vip_tel,a.v_level,a.v_balance,a.v_commodity_integral,b.s_name,a.vip_startdate, a.v_status FROM vip a LEFT JOIN store b ON a.s_id=b.s_id limit 0,10";
			return amaly.find(sql4, null);
		}
	
	//查询绑定�?卡的店名�?
		public List<Object[]> shvtid()
		{
			String sql1="select s_name from store group by s_name";
			return amaly.find(sql1, null);
			
		}
		//绑定会员等级和会员卡状�?�的函数
		public List<Object[]> shvlevel()
		{
			
			String sql2="select v_level from vip group by v_level ";	
			return amaly.find(sql2, null);
		}
		//员卡状�?�的函数
		public List<Object[]> shvstatue()
		{
			String sql3="select v_status from vip group by v_status";
			return amaly.find(sql3, null);
			
		}
}
