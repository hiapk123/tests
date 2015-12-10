package org.uestc.serviceImp;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Statement;
import org.uestc.util.SqlHelper;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.json.JSONObject;
import org.uestc.service.GoodsService;
import org.uestc.util.JdbcUtils;
import org.uestc.util.SqlHelper;
import com.uestc.bean.Good;
import com.uestc.bean.Category;
import com.uestc.bean.CommissionRule;
import com.uestc.bean.IntegralRule;
import com.uestc.bean.ShiftChange;
import com.uestc.bean.Store;
import com.uestc.bean.Supplier;
import com.uestc.bean.Suppliers;
import com.uestc.bean.Vip;

public class GoodsServiceImp implements GoodsService {

	private QueryRunner queryRunner;


	public GoodsServiceImp() {
		queryRunner = new QueryRunner(JdbcUtils.getInstance().getDataSource());
	}


	@Override
	public List<Object[]> findStoreByUserID(int uid) {
		String sql = "select s_id,s_name from store where u_id=?";
		List<Object[]> list = SqlHelper.find(sql, new Object[] { uid });
		return list;
	}

	@Override
	public List<Object[]> goodssearch(int sid, int currentPage, String key, String c_name,String g_del) {
		//String sql = "SELECT g_name,s_name,g_barcode,s_id,g_stock_num,g_pur_price,g_id from goods where s_id=?  limit ?,10";
		String sql = "SELECT g_id, g_name,s_name,g_barcode,g_stock_num,"  //5
				+ "g_pur_price,g_sale_price,g_trade_price,c_name,"      //4
				+ "g_stock_min,g_stock_max,g_prod_date,g_giq,g_pm,"     //5
				+ "su_name,g_flag,vip_id,g_vip_price,zdy1,zdy2,"      //6
				+ "zdy3,zdy4,g_qd_min,g_cl_min,g_stock_nor,g_best,g_sale_nor,g_del,g_info,g_img_path,g_integral,c_id,g_unit,g_howmuch,unit_id"
				+ " from goods where s_id=? and c_name=? and g_del=? and (g_name like '%"+key+"%' or g_barcode  like '%"+key+"%' or g_pm  like '%"+key+"%')  limit ?,10";
		List<Object[]> list = SqlHelper.find(sql, sid,c_name,g_del, currentPage);
		return list;
	}

	@Override
	public void kuaisuluru(int s_id,String g_barcode,String g_name,String c_name,String g_pur_price,String g_sale_price,String g_stock_num, String c_id, String s_name) {
		// TODO Auto-generated method stub
		String sql = "insert into goods(s_id,s_name,g_name,g_del,g_stock_num,g_sale_price,g_pur_price, c_name,g_barcode,g_pm,"
				+ "g_stock_max,g_trade_price,g_prod_date,zdy1,zdy3,su_name,g_stock_min,"
				+ "vip_id,g_vip_price,g_giq,zdy2,zdy4,g_qd_min,g_cl_min,g_stock_nor,"
				+ "g_flag,g_best,g_sale_nor,g_info,g_img_path,g_integral,c_id) value(?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?)";

		SqlHelper.executeUpdate(sql, new String[] { s_id+"", s_name, g_name, 1+"", g_stock_num, g_sale_price,
				g_pur_price, c_name, g_barcode,"","100","",
				"","","","","","1","100",
				"","","","","","","0","",
				"","","","1",c_id});


	}

	@Override
	public void addgood(String s_id, String s_name, String g_name, String g_del, String g_stock_num, String g_sale_price,
			String g_pur_price, String c_name, String g_barcode, String g_pm, String g_stock_max, String g_trade_price,
			String g_prod_date, String zdy1, String zdy3, String su_name, String g_stock_min, String vip_id,
			String g_vip_price, String g_giq, String zdy2, String zdy4, String g_qd_min, String g_cl_min,
			String g_stock_nor, String g_flag, String g_best, String g_sale_nor, String g_info, String g_img_path, String g_integral, String c_id, String unit_id, String g_unit, String g_howmuch) {
		// TODO Auto-generated method stub
		String sql = "insert into goods(s_id,s_name,g_name,g_del,g_stock_num,g_sale_price,g_pur_price, c_name,g_barcode,g_pm,"
				+ "g_stock_max,g_trade_price,g_prod_date,zdy1,zdy3,su_name,g_stock_min,"
				+ "vip_id,g_vip_price,g_giq,zdy2,zdy4,g_qd_min,g_cl_min,g_stock_nor,"
				+ "g_flag,g_best,g_sale_nor,g_info,g_img_path,g_integral,c_id, unit_id, g_unit, g_howmuch) value(?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?)";

		SqlHelper.executeUpdate(sql, new String[] { s_id, s_name, g_name, g_del, g_stock_num, g_sale_price,
				g_pur_price, c_name, g_barcode,g_pm,g_stock_max,g_trade_price,
				g_prod_date,zdy1,zdy3,su_name,g_stock_min,vip_id,g_vip_price,
				g_giq,zdy2,zdy4,g_qd_min,g_cl_min,g_stock_nor,g_flag,g_best,
				g_sale_nor,g_info,g_img_path,g_integral,c_id, unit_id, g_unit, g_howmuch});
	}





	@Override
	public void editgood(String s_name, String g_name, String g_del, String g_stock_num, String g_sale_price,
			String g_pur_price, String c_name, String g_barcode, String g_pm, String g_stock_max, String g_trade_price,
			String g_prod_date, String zdy1, String zdy3, String su_name, String g_stock_min, String vip_id,
			String g_vip_price, String g_giq, String zdy2, String zdy4, String g_qd_min, String g_cl_min,
			String g_stock_nor, String g_flag, String g_best, String g_sale_nor, String g_info, String g_img_path,
			int g_id, String g_integral, String c_id,String g_unit,String g_howmuch,String unit_id) {
		// TODO Auto-generated method stub
		String sql="update goods set s_name=?,g_name=?,g_del=?,g_stock_num=?,g_sale_price=?,"
				+ "g_pur_price=?,c_name=?,g_barcode=?,g_pm=?,g_stock_max=?,g_trade_price=?,"
				+ "g_prod_date=?, zdy1=?,zdy3=?,su_name=?,g_stock_min=?,vip_id=?,"
				+ "g_vip_price=?,g_giq=?,zdy2=?,zdy4=?,g_qd_min=?,g_cl_min=?,"
				+ "  g_stock_nor=?,g_flag=?,g_best=?,g_sale_nor=?,g_info=?, g_img_path=?,g_integral=?,c_id=?,g_unit=?,"
				+ "g_howmuch=?,unit_id=? where g_id=? ";

		SqlHelper.executeUpdate(sql, new String[] {s_name, g_name, g_del,
				g_stock_num, g_sale_price, g_pur_price, c_name,g_barcode,
				g_pm,g_stock_max,g_trade_price,g_prod_date,zdy1,zdy3,
				su_name,g_stock_min,vip_id,g_vip_price,g_giq,zdy2,zdy4,
				g_qd_min,g_cl_min,g_stock_nor,g_flag,g_best,g_sale_nor,
				g_info,g_img_path,g_integral,c_id,g_unit,g_howmuch, unit_id,String.valueOf(g_id)});

	}

	@Override
	public void deletegood(int g_id) {
		// TODO Auto-generated method stub
		String sql = "delete from goods where g_id=?";
		SqlHelper.executeUpdate(sql, new String[] { g_id + "" });
	}

	@Override
	public int getTotalSize(int store, String key, String c_name, String g_del) {
		String sql = "select count(g_id) from goods where  s_id=? and c_name=? and g_del=? and (g_name like '%"+key+"%' or g_barcode  like '%"+key+"%' or g_pm  like '%"+key+"%')";
		List<Object[]> list = SqlHelper.find(sql, store,c_name,g_del);
		if (null != list && list.size() == 1) {
			return Integer.valueOf(list.get(0)[0]+"");
		}
		return 0;
	}


	@Override
	public List<Object[]> upsort(  int sid,int currentPage,String sorted, String key,String c_name,String g_del) {
		// TODO Auto-generated method stub
		String a=sorted;
		//String sql="select * from goods where s_id=? and c_name=? and g_del=? and (g_name like '%"+key+"%' or g_barcode  like '%"+key+"%' or g_pm  like '%"+key+"%')  order by cast("+ a+" as signed) asc limit ?,10";
		String sql = "SELECT g_id, g_name,s_name,g_barcode,g_stock_num,"  //5
				+ "g_pur_price,g_sale_price,g_trade_price,c_name,"      //4
				+ "g_stock_min,g_stock_max,g_prod_date,g_giq,g_pm,"     //5
				+ "su_name,g_flag,vip_id,g_vip_price,zdy1,zdy2,"      //6
				+ "zdy3,zdy4,g_qd_min,g_cl_min,g_stock_nor,g_best,g_sale_nor,g_del,g_info,g_img_path,g_integral,c_id,g_unit,g_howmuch,unit_id"
				+ " from goods where s_id=? and c_name=? and g_del=? and (g_name like '%"+key+"%' or g_barcode  like '%"+key+"%' or g_pm  like '%"+key+"%')  order by cast("+ a+" as signed) asc   limit ?,10";
		List<Object[]> list = SqlHelper.find(sql,sid,c_name,g_del,currentPage);
		return list;
	}

	@Override
	public List<Object[]> downsort(int sid, int currentPage,String sorted, String key,String c_name,String g_del) {
		// TODO Auto-generated method stub
		String a=sorted;
		//String sql="select * from goods where s_id=? and g_del=1  order by g_pur_price desc limit ?,10";
		//String sql="select * from goods where s_id=? and c_name=? and g_del=? and (g_name like '%"+key+"%' or g_barcode  like '%"+key+"%' or g_pm  like '%"+key+"%')  order by cast(" + a+" as signed) desc limit ?,10";
		String sql = "SELECT g_id, g_name,s_name,g_barcode,g_stock_num,"  //5
				+ "g_pur_price,g_sale_price,g_trade_price,c_name,"      //4
				+ "g_stock_min,g_stock_max,g_prod_date,g_giq,g_pm,"     //5
				+ "su_name,g_flag,vip_id,g_vip_price,zdy1,zdy2,"      //6
				+ "zdy3,zdy4,g_qd_min,g_cl_min,g_stock_nor,g_best,g_sale_nor,g_del,g_info,g_img_path,g_integral,c_id,g_unit,g_howmuch,unit_id"
				+ " from goods where s_id=? and c_name=? and g_del=? and (g_name like '%"+key+"%' or g_barcode  like '%"+key+"%' or g_pm  like '%"+key+"%')  order by cast(" + a+" as signed) desc   limit ?,10";
		List<Object[]> list = SqlHelper.find(sql,sid,c_name,g_del,currentPage);
		return list;
	}



	@Override
	public List<Object[]> toExcel(int s_id, int c_id) {
		// TODO Auto-generated method stub
		String sql = "SELECT g_name,c_name,g_barcode,g_stock_num,g_pur_price,g_sale_price,"
				+ "g_trade_price,g_vip_price,g_integral,vip_id,"
				+ "g_stock_max,g_stock_min,su_name,g_prod_date,g_giq,g_pm,zdy1,zdy2,zdy3,zdy4,"
				+ "g_del,g_info from goods where s_id=? and c_id=?";
		List<Object[]> list = SqlHelper.find(sql, s_id,c_id);
		return list;
	}

	@Override
	public void fuzhi(int s_id1,int s_id2) {
		// TODO Auto-generated method stub
		String sql1="select s_id,s_name,g_name,g_del,g_stock_num,g_sale_price,g_pur_price, c_name,g_barcode,g_pm,"//10
					+ "g_stock_max,g_trade_price,g_prod_date,zdy1,zdy3,su_name,g_stock_min,"//7
					+ "vip_id,g_vip_price,g_giq,zdy2,zdy4,g_qd_min,g_cl_min,g_stock_nor,"//8
					+ "g_flag,g_best,g_sale_nor,g_info,g_img_path,g_integral,c_id from goods where s_id=? ";
		List<Object[]> list1 = SqlHelper.find(sql1, s_id1);
		String sql2="select g_barcode from goods where s_id=? ";
		List<Object[]> list2 = SqlHelper.find(sql2, s_id2);
		String []sqls=new String[list1.size()];
		String sql="insert into goods(s_id,s_name,g_name,g_barcode)values(?,?,?,?)";
		for (int i = 0; i < sqls.length; i++) {
		//	sqls[i]="insert into goods(s_id,s_name,g_name,g_barcode)values(?,?,?,?)";
			sqls[i] = "insert into goods(s_id,s_name,g_name,g_del,g_stock_num,g_sale_price,g_pur_price, c_name,g_barcode,g_pm,"//10
					+ "g_stock_max,g_trade_price,g_prod_date,zdy1,zdy3,su_name,g_stock_min,"//7
					+ "vip_id,g_vip_price,g_giq,zdy2,zdy4,g_qd_min,g_cl_min,g_stock_nor,"//8
					+ "g_flag,g_best,g_sale_nor,g_info,g_img_path,g_integral,c_id)"//7
					+ " value(?,?,?,?,?,?,?,?,?,?,"
					+ "?,?,?,?,?,?,?,"
					+ "?,?,?,?,?,?,?,?,"
					+ "?,?,?,?,?,?,?)";
		}

		String [][]sb= new String [list1.size()][32];
		//第12位为条码字段
		for(int i=0;i<list1.size();i++){
			int flag=0;
			for (int j = 0; j < list2.size(); j++) {
				//扫描另一个门店，观察有没有条码一样的商品
				if (list1.get(i)[8].equals(list2.get(j)[0])) {
					flag=1;

				}
				//  String [][]a={{"0","1","2"},{"a","b","c"}};
				//	sqls[i]="insert into goods(s_id,s_name,g_name,g_barcode)values(?,?,?,?)";

			}
			if(flag==0){
				sb[i][0]=String.valueOf(s_id2) ;
				for (int k = 1; k < 32; k++) {
					sb[i][k]=String.valueOf(list1.get(i)[k]) ;
				}
			

				//String sql="insert into goods(s_id,s_name,g_name,g_barcode)values(?,?,?,?)";

			}
		}
		SqlHelper.executeUpdate(sqls, sb);
	}



	public ArrayList isRegular(String truePath, String s_id) throws BiffException, IOException {
		// TODO Auto-generated method stub
		ArrayList list = new ArrayList();
		boolean kong = true;

		JSONObject json=new JSONObject();
		String []goodArrary=new String[]{"名称(必填)","分类(必填)","条码(必填)","库存量(必填)","进货价(必填)","销售价(必填)",
				"批发价","会员价","积分商品","会员折扣","库存上限","库存下限","供货商","生产日期",
				"保质期","拼音码","自定义1","自定义2","自定义3","自定义4","商品状态","商品描述"};
		int g_id = 0;
		List liststu=new ArrayList();
		// 找到导入的文件
		//InputStream is= Date.class.getClassLoader().getResourceAsStream("/1.xls");
		//String sFilePath = "F:/liuyan00/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/sms/WEB-INF/upload/供货商资料模板.xls";  
		String sFilePath =truePath;

		InputStream is = new FileInputStream(sFilePath);  

		//创建工作簿
		Workbook wb=Workbook.getWorkbook(is);
		//创建工作表
		jxl.Sheet sheet=wb.getSheet(0);
		String content=null; 
		String flag="add";
		String category="";
		String HH="";
		if (sheet.getColumns()>22) {
			String	message="该表列数超过了模板的列数，请使用模板";
			list.add(message);
			return list;
		}else{
			for (int m = 0; m < sheet.getColumns(); m++) {
				content=sheet.getCell(m, 0).getContents();
				if(!content.equals(goodArrary[m])){
					String	message="列表的第"+(m+1)+"个字段名错误，正确字段为:"+goodArrary[m];
					list.add(message);
					return list;
				}
			}
		}

		if (sheet.getRows()==2) {
			for(int i=1;i<2;i++){
				for(int j=0;j<sheet.getColumns();j++){
					if(!sheet.getCell(j, 1).getContents().trim().equals("")){
						kong=false;
						break;
					}
				}
			}
		}

		for(int i=1;i<sheet.getRows();i++)
		{   
			Good g=new Good();
			for(int j=0;j<sheet.getColumns();j++)
			{
				content=sheet.getCell(0, i).getContents();
				if(g.getG_name()==null)
				{ 

					if (sheet.getCell(j, i).getContents().trim().equals("")){
						if (kong) {
							String	message="该表无数据！" ;
							list.add(message) ;
							continue;
						}else{
							String	message="第"+(i+1)+"行"+"名称不能为空!" ;
							list.add(message) ;
							continue;
						}


					}else {
						g.setG_name(sheet.getCell(j, i).getContents());
						continue;
					}

				}
				if(g.getC_name()==null)
				{

					if (sheet.getCell(j, i).getContents().trim().equals("")){

						if (kong) {
							continue;
						}else{
							String	message="第"+(i+1)+"行"+"分类不能为空!" ;
							list.add(message) ;
							continue;
						}
					}else {
						String sql="select c_name from category where s_id=? ";
						List<Object[]> list0=SqlHelper.find(sql, s_id);
						
						for (int k = 0; k < list0.size(); k++) {
							String fenlei=(String) list0.get(k)[0];
							if (sheet.getCell(j, i).getContents().equals(fenlei)) {
								category="hege";
								break;
							}
							
						}
						if(category.equals("hege")){
							g.setC_name(sheet.getCell(j, i).getContents());
							category="";
									
							continue;
							
						}else {
							g.setC_name(sheet.getCell(j, i).getContents());
							String	message="第"+(i+1)+"行"+"分类不存在，请填入正确的分类!" ;
							list.add(message) ;
							continue;
						}
					}
				}

				if(g.getG_barcode()==null)
				{
					if (sheet.getCell(j, i).getContents().trim().equals("")){
						if (kong) {
						}else{
							String	message="第"+(i+1)+"行"+"条码不能为空!" ;
							list.add(message) ;
							continue;
						}
					}else {
						g.setG_barcode(sheet.getCell(j, i).getContents());
						continue;
					}
				}
				if(g.getG_stock_num()==null)
				{
					if (sheet.getCell(j, i).getContents().trim().equals("")){
						if (kong) {
							continue;
						}else{
							String	message="第"+(i+1)+"行"+"库存量不能为空!" ;
							list.add(message) ;
							continue;
						}
					}else {
						g.setG_stock_num(sheet.getCell(j, i).getContents());
						continue;
					}
				}

				if(g.getG_pur_price()==null)
				{
					if (sheet.getCell(j, i).getContents().trim().equals("")){
						if (kong) {
							continue;
						}else{
							String	message="第"+(i+1)+"行"+"进货价不能为空!" ;
							list.add(message) ;
							continue;
						}
					}else {
						g.setG_pur_price(sheet.getCell(j, i).getContents());
						continue;
					}
				}

				if(g.getG_sale_price()==null)
				{
					if (sheet.getCell(j, i).getContents().trim().equals("")){
						if (kong) {
							continue;
						}else{
							String	message="第"+(i+1)+"行"+"销售价不能为空!" ;
							list.add(message) ;
							continue;
						}
					}else {
						g.setG_sale_price(sheet.getCell(j, i).getContents());
						continue;
					}
				}

				if(g.getG_trade_price()==null)
				{
					g.setG_trade_price(sheet.getCell(j, i).getContents());
					continue;
				}
				if(g.getG_vip_price()==null)
				{
					g.setG_vip_price(sheet.getCell(j, i).getContents());
					continue;
				}
				if(g.getG_integral()==null)
				{
					String mm=sheet.getCell(j, i).getContents();
					if (sheet.getCell(j, i).getContents().trim().equals("否")) {
						g.setG_integral("0");
						continue;
					}else if(sheet.getCell(j, i).getContents().trim().equals("是")){
						g.setG_integral("1");
						continue;
					}else {
						String	message="第"+(i+1)+"行"+j+"列状态数据格式错误，请输入是或者否" ;
						list.add(message) ;
						g.setG_integral("");
						continue;
					}
				}
				if(g.getVip_id()==null)
				{
					String mm=sheet.getCell(j, i).getContents();
					if (sheet.getCell(j, i).getContents().trim().equals("否")) {
						g.setVip_id("1");
						continue;
					}else if(sheet.getCell(j, i).getContents().trim().equals("是")){
						g.setVip_id("0");
						continue;
					}else {
						String	message="第"+(i+1)+"行"+j+"列状态数据格式错误，请输入是或者否" ;
						list.add(message) ;
						g.setVip_id("");
						continue;
					}
				}
				if(g.getG_stock_max()==null)
				{
					g.setG_stock_max(sheet.getCell(j, i).getContents());
					continue;
				}
				if(g.getG_stock_min()==null)
				{
					g.setG_stock_min(sheet.getCell(j, i).getContents());
					continue;
				}
				if(g.getSu_name()==null)
				{


					if (sheet.getCell(j, i).getContents().trim().equals("")){

						if (kong) {
							continue;
						}
					}else {
						String sql="select su_name from supplier where s_id=? ";
						List<Object[]> list0=SqlHelper.find(sql, s_id);
						
						for (int k = 0; k < list0.size(); k++) {
							String su_name=(String) list0.get(k)[0];
							if (sheet.getCell(j, i).getContents().equals(su_name)) {
								HH="hege";
								break;
							}
							
						}
						if(HH.equals("hege")){
							g.setSu_name(sheet.getCell(j, i).getContents());
							HH="";
									
							continue;
							
						}else {
							g.setSu_name(sheet.getCell(j, i).getContents());
							String	message="第"+(i+1)+"行"+"供货商不存在，请填入已经存在的供货商!" ;
							list.add(message) ;
							continue;
						}
					}
				
					
					
				}
				if(g.getG_prod_date()==null)
				{
					g.setG_prod_date(sheet.getCell(j, i).getContents());
					continue;
				}
				if(g.getG_giq()==null)
				{
					g.setG_giq(sheet.getCell(j, i).getContents());
					continue;
				}
				if(g.getG_pm()==null)
				{
					g.setG_pm(sheet.getCell(j, i).getContents());
					continue;
				}
				if(g.getZdy1()==null)
				{
					g.setZdy1(sheet.getCell(j, i).getContents());
					continue;
				}
				if(g.getZdy2()==null)
				{
					g.setZdy2(sheet.getCell(j, i).getContents());
					continue;
				}
				if(g.getZdy3()==null)
				{
					g.setZdy3(sheet.getCell(j, i).getContents());
					continue;
				}
				if(g.getZdy4()==null)
				{
					
					g.setZdy4(sheet.getCell(j, i).getContents());
					continue;
				}
				if(g.getG_del()==null)
				{
					String mm=sheet.getCell(j, i).getContents();
					if (sheet.getCell(j, i).getContents().trim().equals("禁用")) {
						g.setG_del("0");
						continue;
					}else if(sheet.getCell(j, i).getContents().trim().equals("")||
							sheet.getCell(j, i).getContents().trim().equals("启用")){
						g.setG_del("1");
						continue;
					}else {
						String	message="第"+(i+1)+"行"+"状态数据格式错误，请输入启用或者禁止" ;
						list.add(message) ;
						continue;
					}
				}
				if(g.getG_info()==null)
				{
					g.setG_info(sheet.getCell(j, i).getContents());
					continue;
				}

			
			
		}

		
		
		
		}
		return list;

	}




	public void importExcel(HttpServletRequest req, HttpServletResponse resp, String truePath, String s_id,String s_name) throws Exception {
		// TODO Auto-generated method stub
		
		String []goodArrary=new String[]{"名称(必填)","分类(必填)","条码(必填)","库存量(必填)","进货价(必填)","销售价(必填)",
				"批发价","会员价","积分商品","会员折扣","库存上限","库存下限","供货商","生产日期",
				"保质期","拼音码","自定义1","自定义2","自定义3","自定义4","商品状态","商品描述"};
		
		List liststu=new ArrayList();
		String sFilePath =truePath;
		InputStream is = new FileInputStream(sFilePath);  
		//创建工作簿
		Workbook wb=Workbook.getWorkbook(is);
		//创建工作表
		jxl.Sheet sheet=wb.getSheet(0);
		String content=null; 
		String flag="add";
		for (int m = 0; m < sheet.getColumns(); m++) {
			content=sheet.getCell(m, 0).getContents();

		}
		String SQL[]=new String[sheet.getRows()-1];
		String ly[][]=new String[sheet.getRows()-1][26];

		for(int i=1;i<sheet.getRows();i++)
		{   int g_id = 0;
			Good g=new Good();
			for(int j=0;j<sheet.getColumns();j++)
			{
				content=sheet.getCell(2, i).getContents();
				if(g.getG_name()==null)
				{ 
						g.setG_name(sheet.getCell(j, i).getContents());
						continue;
				}
				if(g.getC_name()==null)
				{
						g.setC_name(sheet.getCell(j, i).getContents());
						continue;
				}

				if(g.getG_barcode()==null)
				{
						g.setG_barcode(sheet.getCell(j, i).getContents());
						continue;
				
				}
				if(g.getG_stock_num()==null)
				{
						g.setG_stock_num(sheet.getCell(j, i).getContents());
						continue;
				}

				if(g.getG_pur_price()==null)
				{
						g.setG_pur_price(sheet.getCell(j, i).getContents());
						continue;
				}

				if(g.getG_sale_price()==null)
				{
						g.setG_sale_price(sheet.getCell(j, i).getContents());
						continue;
				}

				if(g.getG_trade_price()==null)
				{
					g.setG_trade_price(sheet.getCell(j, i).getContents());
					continue;
				}
				if(g.getG_vip_price()==null)
				{
					g.setG_vip_price(sheet.getCell(j, i).getContents());
					continue;
				}
				if(g.getG_integral()==null)
				{
					if (sheet.getCell(j, i).getContents().trim().equals("是")) {
						g.setG_integral("1");
						continue;
					}else if(sheet.getCell(j, i).getContents().equals("")||
							sheet.getCell(j, i).getContents().trim().equals("否")){
						g.setG_integral("0");
						continue;
					}
					
				}
				if(g.getVip_id()==null)
				{
					if (sheet.getCell(j, i).getContents().trim().equals("是")) {
						g.setVip_id("0");
						continue;
					}else if(sheet.getCell(j, i).getContents().equals("")||
							sheet.getCell(j, i).getContents().trim().equals("否")){
						g.setVip_id("1");
						continue;
					}
				
				}
				if(g.getG_stock_max()==null)
				{
					g.setG_stock_max(sheet.getCell(j, i).getContents());
					continue;
				}
				if(g.getG_stock_min()==null)
				{
					g.setG_stock_min(sheet.getCell(j, i).getContents());
					continue;
				}
				if(g.getSu_name()==null)
				{
					g.setSu_name(sheet.getCell(j, i).getContents());
					continue;
				}
				if(g.getG_prod_date()==null)
				{
					g.setG_prod_date(sheet.getCell(j, i).getContents().trim());
					continue;
				}
				if(g.getG_giq()==null)
				{
					g.setG_giq(sheet.getCell(j, i).getContents());
					continue;
				}
				if(g.getG_pm()==null)
				{
					g.setG_pm(sheet.getCell(j, i).getContents());
					continue;
				}
				if(g.getZdy1()==null)
				{
					g.setZdy1(sheet.getCell(j, i).getContents());
					continue;
				}
				if(g.getZdy2()==null)
				{
					g.setZdy2(sheet.getCell(j, i).getContents());
					continue;
				}
				if(g.getZdy3()==null)
				{
					g.setZdy3(sheet.getCell(j, i).getContents());
					continue;
				}
				if(g.getZdy4()==null)
				{
					g.setZdy4(sheet.getCell(j, i).getContents());
					continue;
				}
				if(g.getG_del()==null)
				{
					if (sheet.getCell(j, i).getContents().trim().equals("禁用")) {
						g.setG_del("0");
						continue;
					}else if(sheet.getCell(j, i).getContents().equals("")||
							sheet.getCell(j, i).getContents().trim().equals("启用")){
						g.setG_del("1");
						continue;
					}
				}
				String sql="select g_barcode,g_id from goods where s_id=?";
				List<Object[]> list = SqlHelper.find(sql, s_id);
				for (int k = 0; k < list.size(); k++) {
					String sb=(String) list.get(k)[0];

					if(content.equals(sb))
					{
						flag="update";
						Number num = (Number) list.get(k)[1];  
						g_id= num.intValue();

						String mm=null;
						break;
					}
				}
				if(g.getG_info()==null)
				{
					g.setG_info(sheet.getCell(j, i).getContents());
					continue;
				}
			}
			String c_id="";
			String sql5="select c_id from category where s_id=? and c_name=?";
			List<Object[]> list5=SqlHelper.find(sql5, s_id,g.getC_name());
			Number num1 = (Number) list5.get(0)[0];  
		    int	C_id= num1.intValue();
			c_id=String.valueOf(C_id) ;
			String su_id="";
			String sql6="select su_id from supplier where s_id=? and su_name=?";
			List<Object[]> list6=SqlHelper.find(sql6, s_id,g.getSu_name());
			Number num2 = (Number) list6.get(0)[0];  
		    int	Su_id= num2.intValue();
			su_id=String.valueOf(Su_id) ;
			String yan[]={s_id,s_name, g.getG_name(),g.getC_name(),g.getG_barcode(), //5
				g.getG_stock_num(),g.getG_pur_price(),g.getG_sale_price(),g.getG_trade_price(),//4
				g.getG_vip_price(),g.getG_integral(),g.getVip_id(),g.getG_stock_max(),g.getG_stock_min(),//5
				g.getSu_name(),g.getG_prod_date(),g.getG_giq(),g.getG_pm(),g.getZdy1(),//5
				g.getZdy2(),g.getZdy3(),g.getZdy4(),g.getG_del(),g.getG_info(),c_id,su_id};//7
			if(flag=="add")
			{
				//daoruexcel(g,s_id,s_name);
			
				String sql="insert into goods(s_id,s_name,g_name,c_name,g_barcode,g_stock_num,g_pur_price,"//7
						+ "g_sale_price,g_trade_price,g_vip_price,g_integral,vip_id,g_stock_max,g_stock_min,"//7
						+ "su_name,g_prod_date,g_giq,g_pm,zdy1,zdy2,zdy3,zdy4,g_del,g_info,c_id,su_id) values"     //10
						+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				SQL[i-1]=sql;
				
				for(int m=0;m<26;m++){
					ly[i-1][m]=yan[m];
				}
				
				
			}else if(flag=="update"){
				//daoruexcel1(g,g_id);
				String sql="update goods set s_id=?,s_name=?, g_name=?,c_name=?,g_barcode=?,g_stock_num=?,g_pur_price=?,"
						+ "g_sale_price=?,g_trade_price=?,g_vip_price=?,g_integral=?,vip_id=?,"
						+ "g_stock_max=?,g_stock_min=?,su_name=?,g_prod_date=?,g_giq=?,g_pm=?,"
						+ "zdy1=?,zdy2=?,zdy3=?,zdy4=?,g_del=?,g_info=?,c_id=?,su_id=? where g_id="+g_id;
				SQL[i-1]=sql;
				
				for(int m=0;m<26;m++){
					ly[i-1][m]=yan[m];
			}
		}

		

		}
		SqlHelper.executeUpdate(SQL, ly);
		String message="导入成功";
		resp.setCharacterEncoding("UTF-8"); 
		PrintWriter out=resp.getWriter();
		JSONObject json=new JSONObject();
		json.put("message", message);
		out.print(json);
	}

	//修改
	private void daoruexcel1(Good g,int g_id) {
		// TODO Auto-generated method stub
		String sql="update goods set g_name=?,c_name=?,g_barcode=?,g_stock_num=?,g_pur_price=?,"
				+ "g_sale_price=?,g_trade_price=?,g_vip_price=?,g_integral=?,g_vip=?,"
				+ "g_stock_max=?,g_stock_min=?,su_name=?,g_prod_date=?,g_giq=?,g_pm=?,"
				+ "zdy1=?,zdy2=?,zdy3=?,zdy4=?,g_del=?,g_info=? where g_id=?";

		SqlHelper.executeUpdate(sql, new String[] { g.getG_name(),g.getC_name(),g.getG_barcode(),
				g.getG_stock_num(),g.getG_pur_price(),g.getG_sale_price(),g.getG_trade_price(),
				g.getG_vip_price(),g.getG_integral(),g.getVip_id(),g.getG_stock_max(),g.getG_stock_min(),
				g.getSu_name(),g.getG_prod_date(),g.getG_giq(),g.getG_pm(),g.getZdy1(),
				g.getZdy2(),g.getZdy3(),g.getZdy4(),g.getG_del(),g.getG_info(),g_id+""});
		
	}

	//添加
	public void daoruexcel(Good g,String s_id,String s_name)
	{   

		String sql="insert into goods(s_id,s_name,g_name,c_name,g_barcode,g_stock_num,g_pur_price,"//7
				+ "g_sale_price,g_trade_price,g_vip_price,g_integral,vip_id,g_stock_max,g_stock_min,"//7
				+ "su_name,g_prod_date,g_giq,g_pm,zdy1,zdy2,zdy3,zdy4,g_del,g_info) values"     //10
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		SqlHelper.executeUpdate(sql, new String[]{s_id,s_name, g.getG_name(),g.getC_name(),g.getG_barcode(),
				g.getG_stock_num(),g.getG_pur_price(),g.getG_sale_price(),g.getG_trade_price(),
				g.getG_vip_price(),g.getG_integral(),g.getVip_id(),g.getG_stock_max(),g.getG_stock_min(),
				g.getSu_name(),g.getG_prod_date(),g.getG_giq(),g.getG_pm(),g.getZdy1(),
				g.getZdy2(),g.getZdy3(),g.getZdy4(),g.getG_del(),g.getG_info()});
	}


}