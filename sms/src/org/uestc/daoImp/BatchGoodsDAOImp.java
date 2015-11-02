package org.uestc.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.uestc.dao.BatchGoodsDAO;
import org.uestc.util.JdbcUtils;
import org.uestc.util.SqlHelper;

import com.uestc.bean.batchgoods;
import com.uestc.bean.meminform;

public class BatchGoodsDAOImp  implements BatchGoodsDAO{

	//查找�?售价的函�?(返回�?售价的�??)
	@Override
	public String shsalefind(String sql, Object... params) {
		// TODO Auto-generated method stub
		List<Object[]> list=null;
		String sh="";
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getInstance().getDataSource());
		    list = runner.query(sql, new ArrayListHandler(),params);
		    for(Object[] obj:list)
		    {
		    	sh=obj[0].toString();
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sh;
		
	}
	//进行修改的更新操�?
		public void shupdate(String sql,Object ... param)
		{
			//首先得是建立数据库的连接
			QueryRunner runner=new QueryRunner(JdbcUtils.getInstance().getDataSource());
			SqlHelper s = new SqlHelper(); 
			//函数都将异常全部捕获�?
			s.executeUpdate(sql,null);	
		}
		
	//进行修改的操�?
	public void shinsert(String sql,Object ... param)
	{
		QueryRunner runner=new QueryRunner(JdbcUtils.getInstance().getDataSource());
		SqlHelper s = new SqlHelper(); 
		//函数都将异常全部捕获�?.
		s.executeUpdate(sql,null);
	}	
		
	//删除操作
	public void shdelete(String sql,Object ... param)
	{
		
			QueryRunner runner=new QueryRunner(JdbcUtils.getInstance().getDataSource());
			SqlHelper s = new SqlHelper(); 
			//函数都将异常全部捕获�?
			s.executeUpdate(sql,null);	
	}
	
	
	
	
	//实现基本的查询结果数的使�?
	@Override
	public int countfind(String sql,Object ... param)
	{
		List<Object[]> list=null;
		//连接数据库的操作
		int count=0;
		try
		{
			//数据库连接池
			QueryRunner runner=new QueryRunner(JdbcUtils.getInstance().getDataSource());
			list = runner.query(sql, new ArrayListHandler(),param);
			//从列表中取�??
			for(Object[] obj:list)
			{
				count=Integer.parseInt(obj[0].toString());
				//count++;
			}
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		return count;
	}
	//实现层最终是转化为实体类�?
	//新建转化类将代码加入进去
	@Override
	public List<batchgoods> find(String sql,Object ... param)
	{
		List<Object[]> list=null;
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getInstance().getDataSource());
		    list = runner.query(sql, new ArrayListHandler(),param);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		//新建�?个转换类
		List<batchgoods> newlist=new ArrayList<batchgoods>();
		for(Object[] obj:list){
			//这里取出的是sql的查询的顺序。类型转�?/�?定是按照查询顺序
			batchgoods shbath=new batchgoods();
			
			shbath.setG_id(Integer.parseInt(obj[0].toString()));
			//直接对其列表赋�??
			shbath.setG_name(obj[1].toString());//商品名称
			shbath.setG_barcode(obj[2].toString());//条码
			shbath.setC_name(obj[3].toString());//分类的名�?
			try{
			shbath.setG_vip_price(obj[4].toString());//会员优惠
			}catch(NullPointerException e){
				shbath.setG_vip_price("�?");
			}
			try{
			shbath.setSu_name(obj[5].toString());//供货�?
			}catch(NullPointerException e){
				//e.printStackTrace();
			}
			shbath.setG_flag(Integer.parseInt(obj[6].toString()));//状�??
			shbath.setG_integral(obj[7].toString());//积分
			
			try{
				shbath.setCome_name(obj[9].toString());//提成规则
			}catch(NullPointerException e){
				//e.printStackTrace();
			}
			shbath.setS_name(obj[8].toString());//�?属的门店名字
			newlist.add(shbath);						
		}
		return newlist;
		
	}
	
	//会员资料页面的方法�??
	@Override
	public List<meminform> memfind(String sql,Object ...param)
	{
		List<Object[]> list=null;
		//建立数据库数据池的连接�?�将执行结果放入list
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getInstance().getDataSource());
		    list = runner.query(sql, new ArrayListHandler(),param);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		//新建�?个转化类
		List<meminform> thenewlist=new ArrayList<meminform>();
		for(Object[] obj:list)
		{
			meminform shmeminfo=new meminform();
			try{
			shmeminfo.setV_id(Integer.parseInt(obj[0].toString()));
			shmeminfo.setV_card_no(obj[1].toString());
			shmeminfo.setVip_name(obj[2].toString());
			shmeminfo.setVip_tel(obj[3].toString());
			shmeminfo.setV_level(obj[4].toString());
			shmeminfo.setV_balance(obj[5].toString());
			shmeminfo.setV_commodityintegral(obj[6].toString());
			shmeminfo.setS_name(obj[7].toString());
			shmeminfo.setVip_startdate(obj[8].toString());
			}catch(NullPointerException e)
			{
				e.printStackTrace();
			}
			
			thenewlist.add(shmeminfo);
		}	
		return thenewlist;		
	}

	
}
