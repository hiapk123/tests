package org.uestc.daoImp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.uestc.dao.MarketingDiscDao;
import org.uestc.util.SqlHelper;

import com.uestc.bean.MarketingDiscBean;
@SuppressWarnings("unchecked")
public class MarketingDiscDaoImp implements MarketingDiscDao {

	@Override
	public String insertActive(String type, String name, String start_time) {
		// TODO Auto-generated method stub
		String tabName = getTableName(type);
		switch (tabName) {
		case "discount":
			insertDiscount(type,name,start_time);
			 break;
		case "for_gift":
			insertFor_gift(name, start_time);
			 break;
		case "swap":
			insertSwap(name, start_time);
			 break;
		case "package":
			insertPackage(name, start_time);
			break;

		default:
			break;
		}
		return tabName;
	}

	@Override
	public List<MarketingDiscBean> getMarketingDiscBeanBySQL(String sql) {
		// TODO Auto-generated method stub
		List <MarketingDiscBean>  mb= new ArrayList<MarketingDiscBean>();
		List<Map>  lmap = SqlHelper.findAll(sql);
		
		boolean flag = true;
		for(Iterator<Map> it = lmap.iterator();it.hasNext();){
			Map m = it.next();	
			MarketingDiscBean b = new MarketingDiscBean();
			b.setD_active_name(m.get("d_active_name")+"");
			b.setD_active_type(m.get("d_active_type")+"");
			b.setD_start_date(m.get("d_start_date")+"");
			b.setD_end_date(m.get("d_end_date")+"");
			b.setD_goodbar_list(m.get("d_goodbar_list")+"");
			b.setD_saleprice_list(m.get("d_saleprice_list")+"");
			b.setD_disprice_list(m.get("d_disprice_list")+"");
			b.setD_type(m.get("d_type")+"");
			b.setD_goodname_lsit(getNameByBar(""+m.get("d_goodbar_list")));
			mb.add(b);
		} 
		return mb;
	}
	
	private String getNameByBar(String bar){
		String  name ="";
		String sql = "select g_name from goods where g_barcode = "+bar;
		List<Map> lgood = SqlHelper.findAll(sql);
		if(lgood!=null)
		if(lgood.size()!=0){
			name = ""+lgood.get(0).get("g_name");
		}
		return name ;
	}
	
	private String getTableName(String type){
		String name = "";
		switch (type) {
		case "打折与特价":
			name = "discount";
			break;
		case "第二件打折":
			name = "discount";
			break;
		case "搭赠促销":
			name = "for_gift";
			break;
		case "换购促销":
			name = "swap";
			break;
		case "套餐促销":
			name = "package";
			break;
		default:
			break;
		}
		return name;
	}
	private String  insertDiscount(String type,String name,String start_time){
		String active_type = "0";
		if(type.equals("第二件打折")){
			active_type = "1";
		}
		MarketingDiscBean mdb = new MarketingDiscBean();
		mdb.setD_active_name(name);
		mdb.setD_start_date(start_time);
		mdb.setD_active_type(type);
		List<String> goodbar_list = new ArrayList<String>();
		List<String> salepirce_list = new ArrayList<String>();
		List<String> disprice_list = new ArrayList<String>();
		
		boolean flag = true;
		String sql = "select * from discount where s_del = '1' and d_name = '"+name+"' and d_start_date = '"+start_time+"' and active_type = '"+active_type+"'";
		List<Map> dlist = SqlHelper.findAll(sql);
		for(Iterator<Map> it = dlist.iterator();it.hasNext();){
			Map d = it.next();
			if(flag){
				mdb.setD_end_date(""+d.get("d_end_date"));
				mdb.setD_type(""+d.get("d_type"));
				flag =false;
			}
			String g_bar = ""+d.get("g_id");
			if(g_bar.equals("null")){
				g_bar = ""+d.get("g_barcode");
			}
			String[] s = {};
			goodbar_list.add(g_bar);
			List<Map> gList = SqlHelper.findAll("select * from goods where g_barcode = "+g_bar);
			if(gList.size()!=0){
				
			 	float saleprice = Float.parseFloat((String)gList.get(0).get("g_sale_price")) ;
			 	float discount  = Float.parseFloat(""+d.get("d_discount"));
			 	float disprice = saleprice * discount / 100 ;
			 	DecimalFormat decimal = new DecimalFormat(".00");
			 	String strDisprice = decimal.format(disprice);
			 	salepirce_list.add(saleprice+"");
			 	disprice_list.add(strDisprice);
			}
		}
		for(int x= 0;x<goodbar_list.size();x++){
			String isql = "insert into disc_goods (d_active_type,d_active_name,d_start_date,d_end_date,d_goodbar_list,d_saleprice_list,d_disprice_list,d_type)";
			isql +="values('"+mdb.getD_active_type()+"','"+mdb.getD_active_name()+"','"+mdb.getD_start_date()+"','"+mdb.getD_end_date()+"','"+(goodbar_list.get(x))+"','"+(salepirce_list.get(x))+"','"+(disprice_list.get(x))+"','"+mdb.getD_type()+"')";
			System.out.println("写如Discount表数据："+isql);
			SqlHelper.executeUpdate(isql, null);
		}
		return null;
	}
	private String insertSwap(String name ,String start_time){
		String sql = "select * from swap where s_del = '1' and s_name = '"+name +"' and s_start_date = '"+start_time +"'";
		List<String> barlist = new ArrayList<String>();
		List<String> salepirce_list = new ArrayList<String>();
		String end_time ="";
		String type="";
		String active_type = "换购促销";
		List<Map> slist = SqlHelper.findAll(sql);
		boolean flag = true;
		for(Iterator<Map> it = slist.iterator();it.hasNext();){
			Map m = it.next();
			if(flag){
				end_time = ""+m.get("s_end_date");
				type = ""+m.get("s_type1");
				flag = false;
			}
			String[] st = (""+m.get("s_swap_goods")).split(",");
			for(int i = 0;i<st.length;i++){
				if(!barlist.contains(st[i])){
					barlist.add(st[i]);
					List<Map> gList = SqlHelper.findAll("select * from goods where g_barcode = "+st[i]);
					if(gList.size()!=0){
					 String saleprice = (String)gList.get(0).get("g_sale_price") ;
					 	salepirce_list.add(saleprice+"");
					}
				}
			}
			//barlist.add(""+m.get("s_swap_goods"));
		}
		for(int x = 0 ; x<barlist.size();x++){
			String sql_disc = "insert into disc_goods (d_active_type,d_active_name,d_start_date,d_end_date,d_goodbar_list,d_type,d_saleprice_list)";
			sql_disc +="values('换购促销','"+name+"','"+start_time+"','"+end_time+"','"+(barlist.get(x))+"',"+type+",'"+(salepirce_list.get(0))+"')";
			SqlHelper.executeUpdate(sql_disc, null);
		}
		return null;
	 }
	private String insertFor_gift(String name ,String start_time){
		String sql = "select * from for_gift where s_del = '1' and e_name = '"+name +"' and e_start_date = '"+start_time +"'";
		List<String> barlist = new ArrayList<String>();
		List<String> salepirce_list = new ArrayList<>();
		String end_time ="";
		String type="";
		String active_type = "搭赠促销";
		List<Map> slist = SqlHelper.findAll(sql);
		boolean flag = true;
		for(Iterator<Map> it = slist.iterator();it.hasNext();){
			Map m = it.next();
			if(flag){
				end_time = ""+m.get("e_end_date");
				type = ""+m.get("e_type");
				flag = false;
			}
			String[] st = (""+m.get("e_giving")).split(",");
			for(int i = 0;i<st.length;i++){
				if(!barlist.contains(st[i])){
					barlist.add(st[i]);
					List<Map> gList = SqlHelper.findAll("select * from goods where g_barcode = "+st[i]);
					if(gList.size()!=0){
					 String saleprice = (String)gList.get(0).get("g_sale_price") ;
					 	salepirce_list.add(saleprice+"");
					}
				}
			}
			//barlist.add(""+m.get("e_giving"));
		
		}
		for(int x = 0 ; x<barlist.size();x++){
			String sql_disc = "insert into disc_goods (d_active_type,d_active_name,d_start_date,d_end_date,d_goodbar_list,d_type,d_saleprice_list)";
			sql_disc +="values('搭赠促销','"+name+"','"+start_time+"','"+end_time+"','"+(barlist.get(x))+"',"+type+",'"+(salepirce_list.get(x))+"')";
			SqlHelper.executeUpdate(sql_disc, null);
		}
		
		return null;
	 }
	private String insertPackage(String name ,String start_time){
		String sql = "select * from package  where s_del = '1' and p_name = '"+name +"' and p_start_date = '"+start_time +"'";
		List<String> barlist = new ArrayList<String>();
		List<String> salepirce_list = new ArrayList<String>();
		String end_time ="";
		String type="";
		String active_type = "套餐促销";
		List<Map> slist = SqlHelper.findAll(sql);
		boolean flag = true;
		for(Iterator<Map> it = slist.iterator();it.hasNext();){
			Map m = it.next();
			if(flag){
				end_time = ""+m.get("p_end_date");
				type = ""+m.get("p_type");
				flag = false;
			}
			String[] st = (""+m.get("p_goods_list")).split(",");
			for(int i = 0;i<st.length;i++){
				if(!barlist.contains(st[i])){
					barlist.add(st[i]);
					List<Map> gList = SqlHelper.findAll("select * from goods where g_barcode = "+st[i]);
					if(gList.size()!=0){
					 String saleprice = (String)gList.get(0).get("g_sale_price") ;
					 	salepirce_list.add(saleprice+"");
					}
				}
			}
		//	barlist.add(""+m.get("p_goods_list"));
		}
		for(int x = 0 ; x<barlist.size();x++){
			String sql_disc = "insert into disc_goods (d_active_type,d_active_name,d_start_date,d_end_date,d_goodbar_list,d_type,d_saleprice_list)";
			sql_disc +="values('套餐促销','"+name+"','"+start_time+"','"+end_time+"','"+(barlist.get(x))+"',"+type+",'"+(salepirce_list.get(x))+"')";
			SqlHelper.executeUpdate(sql_disc, null);
		}
		return null;
	 }
	private String listToString(List<String> stringList){
        if (stringList==null) {
            return null;
        	}
        StringBuilder result=new StringBuilder();
        boolean flag=false;
        for (String string : stringList) {
            if (flag) {
                result.append(",");
            }else {
                flag=true;
            	}
            result.append(string);
        }
        return result.toString();
    }

	@Override
	public String deleteActive(String type, String name, String start_time) {
		// TODO Auto-generated method stub
		String sql_disc = "delete from disc_goods where d_active_type = \'"+type+"\' and d_active_name = \'"+name+"\' and d_start_date =\'"+start_time+"\' ";
		SqlHelper.executeUpdate(sql_disc, null);
		return null;
	}
	//去除数组中重复的记录  
	
}
