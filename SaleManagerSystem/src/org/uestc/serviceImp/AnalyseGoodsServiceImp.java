package org.uestc.serviceImp;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.uestc.dao.AnalyseGoodsDao;
import org.uestc.daoImp.AnalyseGoodsDaoImp;
import org.uestc.service.AnalyseGoodsService;

public class AnalyseGoodsServiceImp implements AnalyseGoodsService{
	private AnalyseGoodsDao goodDao=new AnalyseGoodsDaoImp();
	@Override
	public List<Object[]> findGoodsByPage(int currentPage, int pageSize,int store) {
		//商品信息分析
		String sql="select t1.*,t2.*,t4.*,t6.* from goods t1 INNER JOIN sale t2 INNER JOIN (select t3.* from restock t3 where t3.r_flag=0)t4 INNER JOIN (SELECT t5.* FROM restock t5 WHERE t5.r_flag=1)t6 WHERE t1.g_id=t2.g_id and t1.g_id=t4.g_id and t1.g_id=t6.g_id where t1.g_del=1 and t1.g_flag=1 and t1.s_id=? limit ?,?";
		Object[] params=new Object[]{store,currentPage,pageSize};
		return goodDao.find(sql, params);//调用dao;
	}
	@Override
	public List<Object[]> findStoreByUserId(int uid) {
		
		String sql="SELECT t1.s_name,t1.s_id FROM store t1 WHERE t1.s_del=1 and t1.s_flag=1 and t1.u_id=?";
		return goodDao.find(sql, uid);
	}
	@Override
	public List<Object[]> findByCategoryId(int pid) {
		String sql="select * from category where c_parent_id=?";
		return goodDao.find(sql, pid);
	}
}
