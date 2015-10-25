package org.uestc.serviceImp;

import java.util.ArrayList;
import java.util.List;

import org.uestc.dao.CategoryGoodsDao;
import org.uestc.daoImp.CategoryGoodsDaoImp;
import org.uestc.service.CategoryGoodsService;
import org.uestc.util.SqlHelper;
@SuppressWarnings("unchecked")
public class CategoryGoodsServiceImp implements CategoryGoodsService {

	CategoryGoodsDao dao = new CategoryGoodsDaoImp(); 
	SqlHelper sqlhelp = new SqlHelper();
	@Override
	public List<Object[]> findStoreByUserId(int uid) {
		
		String sql="SELECT t1.s_name,t1.s_id FROM store t1 WHERE t1.s_del=1 and t1.s_flag=1 and t1.u_id=?";
		return dao.find(sql, uid);
	}
	@Override
	
/*	<li class="accordion">
	<a href="#" link-rel="myModal" ><i	class="glyphicon glyphicon-plus"></i><span> 父类</span></a>*/
	
	public String getCategoryTree(int storeID) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("<li class=\"nav-header 	\">");
		sb.append("<span id=\"mbt_caname\"data-value="+storeID+" calss=\"input-group-addon\"><i  class=\"glyphicon red\">门店一</i></span></li>");
		
		String sql = "select t1.c_id,t1.c_name,t1.is_final from category t1 where t1.c_parent_id= -1 and t1.s_del = 1 and t1.s_id = "+storeID;
		String[] parameters={};
		List<Object[]> list = SqlHelper.executeQuery(sql, parameters);
		
		for (int i = 0; i < list.size(); i++) {
		
			 String cname= (String)list.get(i)[1];
			 int cid = 	(int)list.get(i)[0];
			 int isFinal = (int)list.get(i)[2];
			 
			 //有子节点
			 if(isFinal !=1)
			 {
				  sb = getSuper(cid,storeID,sb,cname);
				  
			 }else{  //无子节点
				 sb.append("<li><a isfinal=\"1\" link-rel=\"myModal\" name="+cname+" class=\"ajax-link\"  data-value="+cid+"  href=\"javascript:void(0);\"><span> "+cname+"</span></a></li>");
			 }
			}
		return sb.toString();
	}
	/**
	 * 第归获取树形分类
	 * @return
	 */
	public StringBuffer getSuper(int parentID,int storeID,StringBuffer sb,String cname){
		sb.append("<li class=\"accordion\">");
		sb.append("<a isfinal=\"0\" href=\"javascript:void(0);\" name="+cname+" data-value="+parentID+" link-rel=\"myModal\" ><i	class=\"glyphicon glyphicon-plus\"></i><span>"+cname+"</span></a>");			 
		sb.append("<ul class=\"nav nav-pills nav-stacked\">");
		String sql = "select t1.c_id,t1.c_name,t1.is_final from category t1 where t1.c_parent_id= "+parentID+" and t1.s_del = 1 and t1.s_id = " +storeID;
		String parameters[]= {};
		
		List<Object[]> list = SqlHelper.executeQuery(sql, parameters);;
		
		for(int i = 0 ; i<list.size();i++)
		{
				String cname1= (String)list.get(i)[1];
				int cid = 	(int)list.get(i)[0];
				int isFinal = (int)list.get(i)[2];
				if(isFinal!=1){
					sb = getSuper(cid, storeID, sb, cname1);
					
				}else if(isFinal==1){
					
					sb.append("<li><a isfinal=\"1\" link-rel=\"myModal\" name="+cname1+" data-value="+cid+" class=\"ajax-link\" href=\"javascript:void(0);\"><span> "+cname1+"</span></a></li>");
				}
		}
		sb.append("</ul>");
		sb.append("</li>");
		return sb;
	}
	@Override
	public void addCate(int pid, String name, int storeID) {
		// TODO Auto-generated method stub
		String sql = "insert into category (c_name,s_id,c_parent_id,s_del,is_final) values(?,"+storeID+","+pid+",1,1) ";
		System.out.println(sql);
		
		String parameters[] ={name};
		SqlHelper.executeUpdate(sql, parameters);
		
		
		String sql1 = "update category set is_final = 0 where c_id = "+pid;
		String parameters1[] ={};
		SqlHelper.executeUpdate(sql1, parameters1);
		
		
		
	}
	@Override
	public boolean deleteCate(int id,int storeID) {
		// TODO Auto-generated method stub
		
		boolean flag = true;
		//获得该分类
		String getCate = "select c_parent_id,is_final from category where c_id =  "+id;
		String parameters[] ={}; 
		List<Object[]> ca = SqlHelper.executeQuery(getCate, parameters);
		if(id!=-1&&ca.size()!=0&& (int)ca.get(0)[1]==1)
		{
			int parentID = (int) ca.get(0) [0];
			String sql = "delete from category where c_id = "+ id;
			SqlHelper.executeUpdate(sql, parameters);
			
			//子分类是否为0
			String sqlIsFianl = "select * from category where s_id = "+storeID+" and c_parent_id = "+parentID +" and s_del = 1 ";
			List<Object[]> fianlList= SqlHelper.executeQuery(sqlIsFianl, parameters);
			if(fianlList.size()==0){
				String updaSql = "update category set is_final = 1 where c_id = "+parentID;
				SqlHelper.executeUpdate(updaSql, parameters);
			}
		}else if (id==-1){			
			String sql = "delete from category where c_id = "+ id;
			SqlHelper.executeUpdate(sql, parameters);
		}
		else{
			flag = false;
		}
		
		return  flag;
		
		
	}
	@Override
	public void updateCate(int id, String name) {
		// TODO Auto-generated method stub
		String sql = "update category set c_name = ? where c_id = "+id;
		String [] para = {name};
		SqlHelper.executeUpdate(sql, para);
		
	}
	@Override
	@SuppressWarnings("unchecked")
	public String getStoreNameById(int storeID) {
		// TODO Auto-generated method stub
		String sql1 = "select s_name from store  where s_id = "+storeID;
		String parameters1[] ={};
		String sname = "未取到门店名称";
		ArrayList<Object[]> al= SqlHelper.executeQuery(sql1, parameters1);
		if(al.size()!=0)
		sname = (String) al.get(0)[0];
		return sname;
	}

	

}
