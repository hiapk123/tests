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

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String sql = "SELECT g_name,s_name,g_barcode,s_id,g_stock_num,g_pur_price,g_id from goods where s_id=?  limit ?,10";
		List<Object[]> list = SqlHelper.find(sql, sid, currentPage);
		return list;
	}

	@Override
	public void kuaisuluru(int s_id,String g_barcode,String g_name,String c_name,String g_pur_price,String g_sale_price,String g_stock_num) {
		// TODO Auto-generated method stub
		String sql = "insert into goods(s_id,g_name,g_stock_num,g_sale_price,g_pur_price, c_name,g_barcode,g_del) value(?,?,?,?,?,?,?,1)";

	
		SqlHelper.executeUpdate(sql, new String[] { s_id + "",  g_name,  g_stock_num, g_sale_price,
				g_pur_price, c_name, g_barcode });

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
		String sql = "select count(g_id) from goods where  s_id=?";
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
		String sql="select * from goods where s_id=?   order by cast("+ a+" as signed) asc limit ?,10";
		List<Object[]> list = SqlHelper.find(sql,sid,currentPage);
		return list;
	}

	@Override
	public List<Object[]> downsort(int sid, int currentPage,String sorted) {
		// TODO Auto-generated method stub
		String a=sorted;
		//String sql="select * from goods where s_id=? and g_del=1  order by g_pur_price desc limit ?,10";
		String sql="select * from goods where s_id=?   order by cast(" + a+" as signed) desc limit ?,10";
		List<Object[]> list = SqlHelper.find(sql,sid,currentPage);
		return list;
	}
	@Override
	public void importExcel(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String []goodArrary={"门店id","商品名称","门店名称","商品条码"};
		int gid = 0;
		List liststu=new ArrayList();
		// 找到导入的文件
		//InputStream is= Date.class.getClassLoader().getResourceAsStream("/1.xls");
		String sFilePath = "F:/liuyan0/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/sms/WEB-INF/upload/商品导入模板.xls";  
		String message=null;
		InputStream is = new FileInputStream(sFilePath);  
		try {
			//创建工作簿
			Workbook wb=Workbook.getWorkbook(is);
			//创建工作表
			jxl.Sheet sheet=wb.getSheet(0);
			String content=null; 
			String flag="add";
			for (int m = 0; m < sheet.getColumns(); m++) {
				content=sheet.getCell(m, 0).getContents();
				if(!content.equals(goodArrary[m])){
					message="列表的第"+(m+1)+"个字段名错误，正确字段为:"+goodArrary[m];

					throw new Exception();
				}
			}
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
				message= "成功！";
			}

		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		req.setAttribute("message",message);
		req.getRequestDispatcher("/pages/goods/goodsinfo/success.jsp").forward(req, resp);
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

	@Override
	public List<Object[]> toExcel(int s_id) {
		// TODO Auto-generated method stub
		String sql = "SELECT g_name,s_name,g_barcode from goods where s_id=? and g_del=1 ";
		List<Object[]> list = SqlHelper.find(sql, s_id);
		return list;
	}

	@Override
	public void fuzhi(int s_id1,int s_id2) {
		// TODO Auto-generated method stub
		String sql1="select * from goods where s_id=? ";
		List<Object[]> list1 = SqlHelper.find(sql1, s_id1);
		String sql2="select * from goods where s_id=? ";
		List<Object[]> list2 = SqlHelper.find(sql2, s_id2);
		String []sqls=new String[list1.size()];
		String sql="insert into goods(s_id,s_name,g_name,g_barcode)values(?,?,?,?)";
		for (int i = 0; i < sqls.length; i++) {
			sqls[i]="insert into goods(s_id,s_name,g_name,g_barcode)values(?,?,?,?)";
		}
				
		String [][]sb= new String [list1.size()][4];
		//第12位为条码字段
		for(int i=0;i<list1.size();i++){
			int flag=0;
			for (int j = 0; j < list2.size(); j++) {
				//扫描另一个门店，观察有没有条码一样的商品
				if (list1.get(i)[11].equals(list2.get(j)[11])) {
					flag=1;
					
				}
				//  String [][]a={{"0","1","2"},{"a","b","c"}};
			//	sqls[i]="insert into goods(s_id,s_name,g_name,g_barcode)values(?,?,?,?)";
				
			}
   if(flag==0){
			sb[i][0]=s_id2+"";
			sb[i][1]=(String) list1.get(i)[3];
			sb[i][2]=(String) list1.get(i)[1];
			sb[i][3]=(String) list1.get(i)[11];
		
		//String sql="insert into goods(s_id,s_name,g_name,g_barcode)values(?,?,?,?)";
		
   }
		}
		SqlHelper.executeUpdate(sqls, sb);
	}









}
