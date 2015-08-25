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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
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
	public List<Object[]> goodssearch(int sid, int currentPage) {
		String sql = "SELECT g_name,s_name,g_barcode,s_id,g_stock_num,g_pur_price,g_id from goods where s_id=? and g_del=1 limit ?,10";
		List<Object[]> list = SqlHelper.find(sql, sid, currentPage);
		return list;
	}

	@Override
	public void addgood(int s_id, String s_name, String g_name, int g_flag, String g_stock_num, String g_sale_price,
			String g_pur_price, String c_name, String g_barcode) {
		// TODO Auto-generated method stub
		String sql = "insert into goods(s_id,s_name,g_name,g_flag,g_stock_num,g_sale_price,g_pur_price, c_name,g_barcode,g_del) value(?,?,?,?,?,?,?,?,?,1)";
	
		SqlHelper.executeUpdate(sql, new String[] { s_id + "", s_name, g_name, String.valueOf(g_flag), g_stock_num, g_sale_price,
				g_pur_price, c_name, g_barcode });

	}

	@Override
	public void editgood(int s_id, String s_name, String g_name, String g_stock_num, String g_sale_price,
			String g_pur_price, String c_name, String g_barcode, int g_id) {
		// TODO Auto-generated method stub
		String sql = "update goods set s_id=?,s_name=?, g_name=?,g_stock_num=?,g_sale_price=?, g_pur_price=?,c_name=? ,g_barcode=? where g_id=?";

		SqlHelper.executeUpdate(sql, new String[] { s_id + "", s_name, g_name, g_stock_num, g_sale_price, g_pur_price,
				c_name, g_barcode, g_id + "" });
	}

	@Override
	public void deletegood(int g_id) {
		// TODO Auto-generated method stub
		String sql = "delete from goods where g_id=?";
		SqlHelper.executeUpdate(sql, new String[] { g_id + "" });
	}

	@Override
	public int getTotalSize(int store) {
		String sql = "select count(g_id) from goods where g_del=1 and g_flag=1 and s_id=?";
		List<Object[]> list = SqlHelper.find(sql, store);
		if (null != list && list.size() == 1) {
			return Integer.valueOf(list.get(0)[0]+"");
		}
		return 0;
	}


	@Override
	public List<Object[]> upsort(  int sid,int currentPage,String sorted) {
		// TODO Auto-generated method stub
		String a=sorted;
		String sql="select * from goods where s_id=? and g_del=1  order by cast("+ a+" as signed) asc limit ?,10";
		List<Object[]> list = SqlHelper.find(sql,sid,currentPage);
		return list;
	}

	@Override
	public List<Object[]> downsort(int sid, int currentPage,String sorted) {
		// TODO Auto-generated method stub
		String a=sorted;
		//String sql="select * from goods where s_id=? and g_del=1  order by g_pur_price desc limit ?,10";
		String sql="select * from goods where s_id=? and g_del=1  order by cast(" + a+" as signed) desc limit ?,10";
		List<Object[]> list = SqlHelper.find(sql,sid,currentPage);
		return list;
	}
	@Override
	public void importExcel() throws FileNotFoundException {
		// TODO Auto-generated method stub
        int gid = 0;
		List liststu=new ArrayList();
		// 找到导入的文件
		//InputStream is= Date.class.getClassLoader().getResourceAsStream("/1.xls");
		String sFilePath = "F:/商品导入模板.xls";  
		InputStream is = new FileInputStream(sFilePath);  
		try {
			//创建工作簿
			Workbook wb=Workbook.getWorkbook(is);
			//创建工作表
			jxl.Sheet sheet=wb.getSheet(0);
			String content=null; 
			String flag="add";

			for(int i=1;i<sheet.getRows();i++)
			{   
				Good good=new Good();
				for(int j=0;j<sheet.getColumns();j++)
				{
					content=sheet.getCell(j, i).getContents();
					if(good.getS_id()==0)
					{
						good.setS_id(Integer.parseInt(sheet.getCell(j, i).getContents()));
						continue;
					}
					if(good.getG_name()==null)
					{
						good.setG_name(sheet.getCell(j, i).getContents());
						continue;
					}
					if(good.getS_name()==null)
					{
						good.setS_name(sheet.getCell(j, i).getContents());
						continue;
					}
					String sql="select g_barcode,g_id from goods where s_id=? ";
					List<Object[]> list = SqlHelper.find(sql, good.getS_id());
					for (int k = 0; k < list.size(); k++) {
						String sb=(String) list.get(k)[0];

						if(content.equals(sb))
						{
							flag="update";
							Number num = (Number) list.get(k)[1];  
							gid= num.intValue();

							String mm=null;
							break;
						}
					}
					if(good.getG_barcode()==null)
					{
						good.setG_bar_code(sheet.getCell(j, i).getContents());
						continue;
					}
				}
				if(flag=="add")
				{
					daoruexcel(good);
				}else if(flag=="update"){
					daoruexcel1(good,gid);
				}
			}
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void daoruexcel1(Good good,int g_id) {
		// TODO Auto-generated method stub
		String sql="update goods set s_id="+good.getS_id()+",g_name="+"'"
				+good.getG_name()+"',"+"s_name="+"'"+good.getS_name()+"',"
				+ ""+"g_barcode='"+good.getG_barcode()+"'"+"where g_id=?";

		SqlHelper.executeUpdate(sql, new String[] { g_id+"" });
		String m=null;
	}
	public void daoruexcel(Good good)
	{   
		String sql="insert into goods(s_id,g_name,s_name,g_barcode) values("+"?,"+
				"'"+good.getG_name()+"','"+good.getS_name()+"','"+
				good.getG_barcode()+"'"+")";
		SqlHelper.executeUpdate(sql, new String[] { good.getS_id() + "" });
	}

	
	
	



	

}
