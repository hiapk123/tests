package org.uestc.serviceImp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLPermission;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.json.JSONException;
import org.json.JSONObject;
import org.uestc.service.HuoliuService;
import org.uestc.util.JdbcUtils;
import org.uestc.util.SqlHelper;

import com.sun.javafx.image.impl.IntArgb;
import com.sun.rowset.internal.Row;
import com.uestc.bean.Suppliers;
import com.uestc.bean.logistics;

import jxl.Workbook;
import jxl.read.biff.BiffException;

public class HuoliuServiceImp implements HuoliuService{
	private QueryRunner queryRunner;


	/*	public HuoliuServiceImp() {
		queryRunner = new QueryRunner(JdbcUtils.getInstance().getDataSource());
	}*/
	@Override
	public List<Object[]> findStoreByUserID(int uid) {
		// TODO Auto-generated method stub
		String sql = "select s_id,s_name from store where u_id=?";
		List<Object[]> list = SqlHelper.find(sql, new Object[] { uid });
		return list;
	}
	@Override
	public List<Object[]> findSuppliers() {
		// TODO Auto-generated method stub
		String sql = "select su_id, su_name from supplier ";
		List<Object[]> list = SqlHelper.find(sql);
		return list;
	}
	@Override
	public List<Object[]> supplierInfo(int s_id,String s_del,String key) {
		String sql = "select su_number,su_name,su_contacter,su_phone,su_email,su_empower,su_id,s_id"
				+ " from supplier where s_id=? and s_del=? and "
				+ "(su_name like '%"+key+"%' or su_number  like '%"+key+"%' or su_phone  like '%"+key+"%')";
		List<Object[]> list = SqlHelper.find(sql, s_id,s_del);
		return list;
	}

	@Override
	public void editSupplier(String su_id,String su_name,String su_phone,String su_email,
			String su_contacter,String s_del,String su_ps_return, String su_gd_return,
			String su_empower,  String su_address, String su_info) {
		// TODO Auto-generated method stub

		String sql="update supplier set su_name=?,su_phone=?,su_email=?,su_contacter=?,"
				+ "s_del=?,su_ps_return=?,su_gd_return=?,su_empower=?,su_address=?,su_info=?"
				+ " where su_id=? ";

		SqlHelper.executeUpdate(sql, new String[] {su_name,su_phone,su_email,su_contacter,
				s_del,su_ps_return,su_gd_return,su_empower,su_address,su_info,su_id});
	}

	public void importExcel(HttpServletRequest req, HttpServletResponse resp, String truePath, String s_id,String s_name) throws Exception {
		// TODO Auto-generated method stub
		String []supplierArrary={"供货商编号(必填)","供货商名称(必填)","联系人","联系电话","邮箱","状态",
				"配送费返点","固定返利点","地址","备注"};
		int su_id = 0;
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
		for (int m = 0; m < sheet.getColumns(); m++) {
			content=sheet.getCell(m, 0).getContents();
			
		}
		for(int i=1;i<sheet.getRows();i++)
		{   
			Suppliers supplier=new Suppliers();
			for(int j=0;j<sheet.getColumns();j++)
			{
				content=sheet.getCell(0, i).getContents();
				if(supplier.getSu_number()==null)
				{
					supplier.setSu_number(sheet.getCell(j, i).getContents());
					continue;
				}
				if(supplier.getSu_name()==null)
				{
					supplier.setSu_name(sheet.getCell(j, i).getContents());
					continue;
				}
				

				if(supplier.getSu_contacter()==null)
				{
					supplier.setSu_contacter(sheet.getCell(j, i).getContents());
					continue;
				}
				
				if(supplier.getSu_phone()==null)
				{
						supplier.setSu_phone(sheet.getCell(j, i).getContents());
						continue;
				}
				if(supplier.getSu_email()==null)
				{
					supplier.setSu_email(sheet.getCell(j, i).getContents());
					continue;
				}
				if(supplier.getS_del()==null)
				{
					if (sheet.getCell(j, i).getContents().trim().equals("禁用")) {
						supplier.setS_del("0");
						continue;
						}else if(sheet.getCell(j, i).getContents().equals("")||
								sheet.getCell(j, i).getContents().trim().equals("启用")){
							supplier.setS_del("1");
							continue;
						}
				}
				if(supplier.getSu_ps_return()==null)
				{
					if (sheet.getCell(j, i).getContents().equals("")) {
						supplier.setSu_ps_return("0");
						continue;
					}else{
						supplier.setSu_ps_return(sheet.getCell(j, i).getContents());
						continue;
					}
					
				}
				if(supplier.getSu_gd_return()==null)
				{
					if (sheet.getCell(j, i).getContents().equals("")) {
						supplier.setSu_gd_return("0");
						continue;
					}else{
					supplier.setSu_gd_return(sheet.getCell(j, i).getContents());
					continue;
					}
				}

				if(supplier.getSu_address()==null)
				{
					supplier.setSu_address(sheet.getCell(j, i).getContents());
					continue;
				}


				String sql="select su_number,su_id from supplier where s_id=?";
				List<Object[]> list = SqlHelper.find(sql, s_id);
				for (int k = 0; k < list.size(); k++) {
					String sb=(String) list.get(k)[0];

					if(content.equals(sb))
					{
						flag="update";
						Number num = (Number) list.get(k)[1];  
						su_id= num.intValue();

						String mm=null;
						break;
					}
				}
				if(supplier.getSu_info()==null)
				{
					supplier.setSu_info(sheet.getCell(j, i).getContents());
					continue;
				}
			}
			if(flag=="add")
			{
				daoruexcel(supplier,s_id,s_name);
			}else if(flag=="update"){
				daoruexcel1(supplier,su_id);
			}
		}

		String message="导入成功";
    	resp.setCharacterEncoding("UTF-8"); 
    	PrintWriter out=resp.getWriter();
    	JSONObject json=new JSONObject();
    	json.put("message", message);
		out.print(json);
	
		
	}
	//修改
	private void daoruexcel1(Suppliers supplier,int su_id) {
		// TODO Auto-generated method stub
		String sql="update supplier set su_name=?,su_contacter=?,"
				+ "su_phone=?,su_email=?,su_empower=?,su_ps_return=?,"
				+ "su_gd_return=?,su_address=?,su_info=? where su_id=?";

		SqlHelper.executeUpdate(sql, new String[] { supplier.getSu_name(),
				supplier.getSu_contacter(),supplier.getSu_phone(),supplier.getSu_email(),
				supplier.getS_del(),String.valueOf(supplier.getSu_ps_return()),String.valueOf(supplier.getSu_gd_return()),
				supplier.getSu_address(),supplier.getSu_info(),
				su_id+""});
		String m=null;
	}
	
	//添加
	public void daoruexcel(Suppliers supplier,String s_id,String s_name)
	{   

		String sql="insert into supplier(su_number,su_name,su_contacter,su_phone,su_email,s_del,su_ps_return,su_gd_return,su_address,su_info,s_id,s_name,su_empower) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		SqlHelper.executeUpdate(sql, new String[]{ supplier.getSu_number(),supplier.getSu_name(),
				supplier.getSu_contacter(),supplier.getSu_phone(),supplier.getSu_email(),
				supplier.getS_del(),String.valueOf(supplier.getSu_ps_return()),
				String.valueOf(supplier.getSu_gd_return()),
				supplier.getSu_address(),supplier.getSu_info(),s_id,s_name,0+""
		});
	}

	@Override
	public List<Object[]> toExcel(int s_id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String sql = "SELECT su_number,su_name,su_contacter,su_phone,su_email,"
				+ "su_empower,su_ps_return,su_gd_return,su_address,"
				+ "su_info from supplier where s_id=? ";
		List<Object[]> list = SqlHelper.find(sql, s_id);
		return list;
	}



	public List<Object[]> search(String s_id, String shuru) {
		// TODO Auto-generated method stub
		String sql="select g_name,g_barcode,g_stock_num,su_name,g_pur_price,g_id from goods where s_id=? and"
				+ " (g_name like '%"+shuru+"%' or g_barcode  like '%"+shuru+"%')";
		List<Object[]> list=SqlHelper.find(sql, s_id);
		return list;
	}

	public void dr(HttpServletRequest req, HttpServletResponse resp,String realSavePath, int s_id) throws Exception {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		List List=new ArrayList();
		String []Arrary={"商品名称","条码(必填)","货流量(必填)","货流价"};

		List liststu=new ArrayList();
		// 找到导入的文件
		//InputStream is= Date.class.getClassLoader().getResourceAsStream("/1.xls");
		//String sFilePath = "F:/liuyan0/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/sms/WEB-INF/upload/货单模板.xls";  
		String sFilePath = realSavePath+"/货单模板.xls";  

		String message=null;
		InputStream is = new FileInputStream(sFilePath);  

		//创建工作簿
		Workbook wb=Workbook.getWorkbook(is);
		//创建工作表
		jxl.Sheet sheet=wb.getSheet(0);
		String content=null; 
		int a=sheet.getColumns();
		for (int m = 0; m < sheet.getColumns(); m++) {
			content=sheet.getCell(m, 0).getContents();
			if(!content.equals(Arrary[m])){
				message="列表的第"+(m+1)+"个字段名错误，正确字段为:"+Arrary[m];

				throw new Exception();
			}
		}
		for(int i=1;i<sheet.getRows();i++)
		{   
			logistics l=new logistics();
			for(int j=0;j<sheet.getColumns();j++)
			{
				content=sheet.getCell(0, i).getContents();
				if(l.getG_name()==null)
				{
					l.setG_name(sheet.getCell(j, i).getContents());
					continue;
				}
				if(l.getG_barcode()==null)
				{
					l.setG_barcode(sheet.getCell(j, i).getContents());
					continue;
				}
				if(l.getL_num()==null)
				{
					l.setL_num(sheet.getCell(j, i).getContents());
					continue;
				}
				if(l.getL_price()==null)
				{
					l.setL_price(sheet.getCell(j, i).getContents());
					continue;
				}

			}
			String arr[]=new String[]{l.getG_name(),l.getG_barcode(),l.getL_num(),l.getL_price()};
			//List.add(arr);
			String sql="select g_name,g_barcode,g_stock_num,su_name,g_id from goods where s_id=? and g_barcode="+"'"+l.getG_barcode()+"'";
			List<Object[]> list=(ArrayList) SqlHelper.find(sql, s_id);
			String brr[]=new String[]{(String) list.get(0)[0],(String) list.get(0)[1],(String) list.get(0)[2],
					(String) list.get(0)[3],l.getL_price(),l.getL_num(),(String) list.get(0)[4]};

			List.add(brr);
		}

		req.setAttribute("List", List);


	}

	public void Inforuku(String order,String s_id_in,String l_pre_price,String l_info) {
		// TODO Auto-generated method stub
		String sql="insert into logistics(s_id_in,l_detail,"
				+ "l_serial_num,l_date,l_type,l_status,l_num,l_price,l_pre_price,l_info) value("
				+ "?,?,?,?,'进货单','待审核',?,?,?,?)";
		
		/*String sql1="insert into s_settlement(ss_sid_in,ss_detail,"
				+ "ss_serial_num,ss_date,ss_type,ss_status,ss_num,ss_price,ss_pre_price,ss_info) value("
				+ "?,?,?,?,'进货单','待确认进货',?,?,?,?)";*/
		SimpleDateFormat simpledateformat;
		simpledateformat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date dt=new Date();
		String strDate=simpledateformat.format(dt);
		System.out.println(strDate);
		String []orderArr=order.split(" ");
		
		
		int l_num=0;
		for (int i = 0; i < orderArr.length/5; i++) {
			l_num=l_num+Integer.valueOf(orderArr[3+5*i]);
		}
		double l_price=0;
		for (int i = 0; i < orderArr.length/5; i++) {
			l_price=l_price+Integer.valueOf(orderArr[3+5*i])*Double.parseDouble(orderArr[4+5*i]);
		}
		
		
		
		String SQL[]=new String[orderArr.length/5];
		String detail[]=new String[orderArr.length/5];
		String liuyan[][]=new String[orderArr.length/5][9];
		String liupeng[]=new String[]{s_id_in,strDate,strDate,String.valueOf(l_num),
				String.valueOf(l_price),l_pre_price,l_info
		
		};
		for (int i = 0; i < orderArr.length/5; i++) {
			detail[i]=orderArr[i*5]+" "+orderArr[5*i+1]+" "+orderArr[5*i+2]+" "+orderArr[5*i+3]+" "+orderArr[5*i+4];
			for (int j = 0; j <1; j++) {
				liuyan[i][j]=liupeng[j];
			}
			for (int j = 1; j <2; j++) {
				liuyan[i][j]=detail[i];
			}
			for (int j = 2; j <8; j++) {
				liuyan[i][j]=liupeng[j-1];
			}
			for (int j = 8; j <9; j++) {
				liuyan[i][j]=orderArr[i*5+2];
			}
			
			SQL[i]="insert into s_settlement(ss_sid_in,ss_detail,"
					+ "ss_serial_num,ss_date,ss_type,ss_status,ss_num,ss_price,ss_pre_price,ss_info,ss_supplier) value("
					+ "?,?,?,?,'进货单','待确认进货',?,?,?,?,?)";
		}
		
		
		
		SqlHelper.executeUpdate(sql, new String[]{
				s_id_in,order,strDate,strDate,String.valueOf(l_num),String.valueOf(l_price),l_pre_price,l_info
		});
		/*SqlHelper.executeUpdate(sql1, new String[]{
				s_id_in,order,strDate,strDate,String.valueOf(l_num),String.valueOf(l_price),l_pre_price,l_info
		});*/
		SqlHelper.executeUpdate(SQL,liuyan);
	}

	public List<Object[]> findhl(String s_id, String type,int currentPage) {
		// TODO Auto-generated method stub
		
		if (!s_id.equals("")) {
		String sql="select l_detail,l_serial_num,l_date,l_type,s_id_out,s_id_in,"
				+ "l_status,l_num,l_price,l_pre_price,l_info,l_id"
				+ " from logistics where s_id_in=? and l_type=? limit ?,10";
		List<Object[]> list=SqlHelper.find(sql, s_id,type, currentPage);
		return list;
		}else {
			String sql="select l_detail,l_serial_num,l_date,l_type,s_id_out,s_id_in,"
					+ "l_status,l_num,l_price,l_pre_price,l_info,l_id"
					+ " from logistics where  l_type=? limit ?,10";
			List<Object[]> list=SqlHelper.find(sql,type, currentPage);
			return list;
		}
	}
	/***
	 * 完成调货功能
	 * 
	 * @param s_id_out
	 * @param s_id_out2
	 */
	public void zhijieruku1(String order,String s_id_out,String s_id_in,String l_info) {
		// TODO Auto-generated method stub
		String sql="insert into logistics(s_id_out,s_id_in,l_detail,"
				+ "l_serial_num,l_type,l_status,l_num,l_price,l_info) value("
				+ "?,?,?,?,'调货单','已完成进货',?,?,?)";
		SimpleDateFormat simpledateformat;
		simpledateformat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date dt=new Date();
		String strDate=simpledateformat.format(dt);
		System.out.println(strDate);
		String []orderArr=order.split(" ");
		int l_num=0;
		for (int i = 0; i < orderArr.length/5; i++) {
			l_num=l_num+Integer.valueOf(orderArr[3+5*i]);
		}
		double l_price=0;
		for (int i = 0; i < orderArr.length/5; i++) {
			l_price=l_price+Integer.valueOf(orderArr[3+5*i])*Double.parseDouble(orderArr[4+5*i]);
		}
		SqlHelper.executeUpdate(sql, new String[]{
				s_id_in,order,strDate,String.valueOf(l_num),String.valueOf(l_price),l_info
		});

	}

	public void ruku( String s_id_out,String s_id_in, String l_id,String l_detail, String l_serial_num, String l_date) {
		// TODO Auto-generated method stub
		String []orderArr=l_detail.split(" ");
		if(s_id_out.equals("null")){ 
		String sql="update  logistics set l_status='已完成进货' where l_id=? and s_id_in=?";
	SqlHelper.executeUpdate(sql, new String[]{l_id,s_id_in});
	String sql1="update  s_settlement set ss_status='待对账' where ss_serial_num=? and ss_sid_in=?";
	SqlHelper.executeUpdate(sql1, new String[]{l_serial_num,s_id_in});
	
	
	
		}else if(s_id_in.equals("null")){       
			String sql="update  logistics set l_status='已完成退货' where l_id=? ";
			SqlHelper.executeUpdate(sql, new String[]{l_id });
		}else{
			String sql="update  logistics set l_status='已完成调货' where l_id=? ";
			SqlHelper.executeUpdate(sql, new String[]{l_id });
			
		}
		
		
		
		
		if(s_id_out.equals("null")){        //进货
			String SQL[]=new String[orderArr.length/5];
			for (int i = 0; i < SQL.length; i++) {
				SQL[i]="";
			}
			String a[][]=new String[orderArr.length/5][2];
			for (int i = 0; i < orderArr.length/5; i++) {
				a[i][0]=orderArr[1+5*i];
				a[i][1]=s_id_in;
			}
			for (int j = 0; j < orderArr.length/5; j++) {
				SQL[j]="update goods set g_stock_num=g_stock_num+"+orderArr[3+5*j]+" where g_barcode=? and s_id=?"; 

			}
			SqlHelper.executeUpdate(SQL,a);
		}else if(s_id_in.equals("null")){   //退货

			String SQL[]=new String[orderArr.length/5];
			
			for (int i = 0; i < SQL.length; i++) {
				SQL[i]="";
			}
			String a[][]=new String[orderArr.length/5][2];
			for (int i = 0; i < orderArr.length/5; i++) {
				a[i][0]=orderArr[1+5*i];
				a[i][1]=s_id_out;
			}
			for (int j = 0; j < orderArr.length/5; j++) {
				SQL[j]="update goods set g_stock_num=g_stock_num-"+orderArr[3+5*j]+" where g_barcode=? and s_id=?"; 

			}
			SqlHelper.executeUpdate(SQL,a);
			String sql5="insert into s_settlement(ss_detail,ss_date,ss_wl_serial,ss_wl_date,ss_type,"
					+ "ss_goods_num,ss_goods_price,ss_remark)values(?,?,?,?,?,?,?,?)";
			SimpleDateFormat simpledateformat;
			simpledateformat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date dt=new Date();
			String strDate=simpledateformat.format(dt);
			
			int l_num=0;
			for (int i = 0; i < orderArr.length/5; i++) {
				l_num=l_num+Integer.valueOf(orderArr[3+5*i]);
			}
			double l_price=0;
			for (int i = 0; i < orderArr.length/5; i++) {
				l_price=l_price+Integer.valueOf(orderArr[3+5*i])*Double.parseDouble(orderArr[4+5*i]);
			}
			SqlHelper.executeUpdate(sql5,new String[]{
				l_detail,strDate,l_serial_num,l_date,"退货单",
				String.valueOf(l_num),String.valueOf(l_price),"备注"
					
			});

		} else{                    //调货

			String SQL[]=new String[orderArr.length*2/5];
			for (int i = 0; i < SQL.length; i++) {
				SQL[i]="";
			}
			String a[][]=new String[orderArr.length*2/5][2];
			for (int i = 0; i < orderArr.length*2/5; i++) {
				a[i][0]=orderArr[1+5*(i/2)];
				if((i%2)==0){
					a[i][1]=s_id_out;
				}else {
					a[i][1]=s_id_in;
				}
			}
			for (int j = 0; j < orderArr.length*2/5; j++) {
				if((j%2)==0){
					SQL[j]="update goods set g_stock_num=g_stock_num-"+orderArr[3+5*(j/2)]+" where g_barcode=? and s_id=?"; 

				}else {
					SQL[j]="update goods set g_stock_num=g_stock_num+"+orderArr[3+5*(j/2)]+" where g_barcode=? and s_id=?"; 

				}

			}
			SqlHelper.executeUpdate(SQL,a);

		}
	}

	@Override
	public int getTotalSize(int store) {
		// TODO Auto-generated method stub
		String sql = "select count(l_id) from logistics where  s_id_in=? and l_type='进货单'";
		List<Object[]> list = SqlHelper.find(sql, store);
		if (null != list && list.size() == 1) {
			return Integer.valueOf(list.get(0)[0]+"");
		}
		return 0;
	}

	public void Inforuku1(String order, String s_id_out, String s_id_in, String l_info) {
		// TODO Auto-generated method stub
		String sql="insert into logistics(s_id_out,s_id_in,l_detail,"
				+ "l_serial_num,l_date,l_type,l_status,l_num,l_price,l_info) value("
				+ "?,?,?,?,?,'调货单','待审核',?,?,?)";
		SimpleDateFormat simpledateformat;
		simpledateformat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date dt=new Date();
		String strDate=simpledateformat.format(dt);
		System.out.println(strDate);
		String []orderArr=order.split(" ");
		int l_num=0;
		for (int i = 0; i < orderArr.length/5; i++) {
			l_num=l_num+Integer.valueOf(orderArr[3+5*i]);
		}
		double l_price=0;
		for (int i = 0; i < orderArr.length/5; i++) {
			l_price=l_price+Integer.valueOf(orderArr[3+5*i])*Double.parseDouble(orderArr[4+5*i]);
		}

		SqlHelper.executeUpdate(sql, new String[]{
				s_id_out,s_id_in,order,strDate,strDate,String.valueOf(l_num),String.valueOf(l_price),l_info
		});
	}

	public List<Object[]> findhl1(String s_id, String type, int currentPage) {
		// TODO Auto-generated method stub
		if (!s_id.equals("")) {
		String sql="select l_detail,l_serial_num,l_date,l_type,s_id_out,s_id_in,"
				+ "l_status,l_num,l_price,l_pre_price,l_info,l_id"
				+ " from logistics where s_id_out=? and l_type=? limit ?,10";
		List<Object[]> list=SqlHelper.find(sql, s_id,"调货单", currentPage);
		return list;
		}else {
			String sql="select l_detail,l_serial_num,l_date,l_type,s_id_out,s_id_in,"
					+ "l_status,l_num,l_price,l_pre_price,l_info,l_id"
					+ " from logistics where  l_type=? limit ?,10";
			List<Object[]> list=SqlHelper.find(sql, "调货单", currentPage);
			return list;
		}
	}

	public int getTotalSize1(int store ) {
		// TODO Auto-generated method stub
		String sql = "select count(l_id) from logistics where  s_id_out or s_id_in=? and l_type='调货单'";
		List<Object[]> list = SqlHelper.find(sql, store);
		if (null != list && list.size() == 1) {
			return Integer.valueOf(list.get(0)[0]+"");
		}
		return 0;
	}

	public List<Object[]> findhl2(String s_id, String type, int currentPage) {
		// TODO Auto-generated method stub
		if (!s_id.equals("")) {
		String sql="select l_detail,l_serial_num,l_date,l_type,s_id_out,s_id_in,"
				+ "l_status,l_num,l_price,l_pre_price,l_info,l_id"
				+ " from logistics where s_id_in=? and l_type=? limit ?,10";
		List<Object[]> list=SqlHelper.find(sql, s_id,"调货单", currentPage);
		return list;
		}else {
			String sql="select l_detail,l_serial_num,l_date,l_type,s_id_out,s_id_in,"
					+ "l_status,l_num,l_price,l_pre_price,l_info,l_id"
					+ " from logistics where  l_type=? limit ?,10";
			List<Object[]> list=SqlHelper.find(sql, "调货单", currentPage);
			return list;
		}
	}

	public void Inforuku2(String order, String s_id, String l_info) {
		// TODO Auto-generated method stub
		String sql="insert into logistics(s_id_out,l_detail,"
				+ "l_serial_num,l_date,l_type,l_status,l_num,l_price,l_info) value("
				+ "?,?,?,?,'退货单','待同意',?,?,?)";
		SimpleDateFormat simpledateformat;
		simpledateformat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date dt=new Date();
		String strDate=simpledateformat.format(dt);
		System.out.println(strDate);
		String []orderArr=order.split(" ");
		int l_num=0;
		for (int i = 0; i < orderArr.length/5; i++) {
			l_num=l_num+Integer.valueOf(orderArr[3+5*i]);
		}
		double l_price=0;
		for (int i = 0; i < orderArr.length/5; i++) {
			l_price=l_price+Integer.valueOf(orderArr[3+5*i])*Double.parseDouble(orderArr[4+5*i]);
		}

		SqlHelper.executeUpdate(sql, new String[]{
				s_id,order,strDate,strDate,String.valueOf(l_num),String.valueOf(l_price),l_info
		});

	}

	public List<Object[]> findhl3(String s_id, String type, int currentPage) {
		// TODO Auto-generated method stub
		if (!s_id.equals("")) {
		String sql="select l_detail,l_serial_num,l_date,l_type,s_id_out,s_id_in,"
				+ "l_status,l_num,l_price,l_pre_price,l_info,l_id"
				+ " from logistics where s_id_out=? and l_type=? limit ?,10";
		List<Object[]> list=SqlHelper.find(sql, s_id,type, currentPage);
		return list;
		}else {
			String sql="select l_detail,l_serial_num,l_date,l_type,s_id_out,s_id_in,"
					+ "l_status,l_num,l_price,l_pre_price,l_info,l_id"
					+ " from logistics where  l_type=? limit ?,10";
			List<Object[]> list=SqlHelper.find(sql,type, currentPage);
			return list;
		}
	}

	public List<Object[]> findhl4(String s_id, String type, int currentPage) {
		// TODO Auto-generated method stub
		List<Object[]> list=null;
		if(!s_id.equals("")){
		String sql="select l_detail,l_serial_num,l_date,l_type,s_id_out,s_id_in,"
				+ "l_status,l_num,l_price,l_pre_price,l_info,l_id"
				+ " from logistics where s_id_out or s_id_in=?  limit ?,10";
		 list=SqlHelper.find(sql, s_id, currentPage);
		}else {
			String sql="select l_detail,l_serial_num,l_date,l_type,s_id_out,s_id_in,"
					+ "l_status,l_num,l_price,l_pre_price,l_info,l_id"
					+ " from logistics   limit ?,10";
			 list=SqlHelper.find(sql, currentPage);
		}
		return list;
	}

	public int getTotalSize2(int store) {
		// TODO Auto-generated method stub
		String sql = "select count(l_id) from logistics where  s_id_out=? and l_type='退货单'";
		List<Object[]> list = SqlHelper.find(sql, store);
		if (null != list && list.size() == 1) {
			return Integer.valueOf(list.get(0)[0]+"");
		}
		return 0;
	}
	public int unsuccess(int store, String supplier) {
		// TODO Auto-generated method stub
		String sql = "select count(ss_id) from s_settlement where   ss_sid_in=? and (ss_status='已拒绝进货' or ss_status='待确认进货') and ss_supplier=? ";
		List<Object[]> list = SqlHelper.find(sql, store,supplier);
		if (null != list && list.size() == 1) {
			return Integer.valueOf(list.get(0)[0]+"");
		}
		return 0;
	}
	public int getTotalSize3(int store) {
		String sql="";
		if(!String.valueOf(store).equals("")){
	     sql = "select count(l_id) from logistics where  s_id_out or s_id_in=? ";
		}else {
		sql = "select count(l_id) from logistics  ";
		}
		List<Object[]> list = SqlHelper.find(sql, store);
		if (null != list && list.size() == 1) {
			return Integer.valueOf(list.get(0)[0]+"");
		}
		return 0;
	}

	public int getTotalSize0(int store) {
		// TODO Auto-generated method stub
		String sql = "select count(l_id) from logistics where  s_id_in=? and l_type='调货单'";
		List<Object[]> list = SqlHelper.find(sql, store);
		if (null != list && list.size() == 1) {
			return Integer.valueOf(list.get(0)[0]+"");
		}
		return 0;
	}
	public int preCheck(int store, String supplier) {
		// TODO Auto-generated method stub
		String sql = "select count(ss_id) from s_settlement where ss_supplier=? and  ss_sid_in=? and ss_status='待对账' ";
		List<Object[]> list = SqlHelper.find(sql, supplier,store);
		if (null != list && list.size() == 1) {
			return Integer.valueOf(list.get(0)[0]+"");
		}
		return 0;
	}
	public int daijs(int store, String supplier) {
		// TODO Auto-generated method stub
		String sql = "select count(ss_id) from s_settlement where ss_supplier=? and   ss_sid_in=? and ss_status='待结算' ";
		List<Object[]> list = SqlHelper.find(sql, supplier,store);
		if (null != list && list.size() == 1) {
			return Integer.valueOf(list.get(0)[0]+"");
		}
		return 0;
	}
	public int yijs(int store) {
		// TODO Auto-generated method stub
		String sql = "select count(l_id) from s_settlement where  (s_id_out or s_id_in=?) and l_status='已结算' ";
		List<Object[]> list = SqlHelper.find(sql, store);
		if (null != list && list.size() == 1) {
			return Integer.valueOf(list.get(0)[0]+"");
		}
		return 0;
	}
	public int allStatus(int store) {
		// TODO Auto-generated method stub
		String sql = "select count(ss_id) from s_settlement where   ss_sid_in=?  ";
		List<Object[]> list = SqlHelper.find(sql, store);
		if (null != list && list.size() == 1) {
			return Integer.valueOf(list.get(0)[0]+"");
		}
		return 0;
	}
	public List<Object[]> findjs0(String s_id, String supplier, int currentPage) {

		// TODO Auto-generated method stub
		if (!s_id.equals("")&&!supplier.equals("")) {
		String sql="select ss_detail,ss_serial_num,ss_date,ss_type,ss_sid_in,"
				+ "ss_status,ss_num,ss_price,ss_pre_price,ss_info,ss_id"
				+ " from s_settlement where ss_sid_in=? and ss_supplier=? and (ss_status='已拒绝进货' or ss_status='待确认进货' ) limit ?,10";
		List<Object[]> list=SqlHelper.find(sql, s_id,supplier, currentPage);
		return list;
		}else if(s_id.equals("")&&!supplier.equals("")){
			String sql="select ss_detail,ss_serial_num,ss_date,ss_type,ss_sid_in,"
					+ "ss_status,ss_num,ss_price,ss_pre_price,ss_info,ss_id"
					+ " from s_settlement where ss_supplier=? and (ss_status='已拒绝进货' or ss_status='待确认进货' ) limit ?,10";
			List<Object[]> list=SqlHelper.find(sql, supplier, currentPage);
			return list;
		}else if(!s_id.equals("")&&supplier.equals("")){
			String sql="select ss_detail,ss_serial_num,ss_date,ss_type,ss_sid_in,"
					+ "ss_status,ss_num,ss_price,ss_pre_price,ss_info,ss_id"
					+ " from s_settlement where ss_sid_in=? and (ss_status='已拒绝进货' or ss_status='待确认进货' ) limit ?,10";
			List<Object[]> list=SqlHelper.find(sql, s_id, currentPage);
			return list;
		}else {
			String sql="select ss_detail,ss_serial_num,ss_date,ss_type,ss_sid_in,"
					+ "ss_status,ss_num,ss_price,ss_pre_price,ss_info,ss_id"
					+ " from s_settlement where  (ss_status='已拒绝进货' or ss_status='待确认进货' ) limit ?,10";
			List<Object[]> list=SqlHelper.find(sql, currentPage);
			return list;
		}
	
	
	}
	public List<Object[]> findjs1(String s_id, String supplier, int currentPage) {
		// TODO Auto-generated method stub
		if (!s_id.equals("")&&!supplier.equals("")) {
			String sql="select ss_detail,ss_serial_num,ss_date,ss_type,ss_sid_in,"
					+ "ss_status,ss_num,ss_price,ss_pre_price,ss_info,ss_id"
					+ " from s_settlement where ss_supplier=? and ss_sid_in=? and ss_status='待对账' limit ?,10";
			List<Object[]> list=SqlHelper.find(sql,supplier, s_id, currentPage);
			return list;
			}else if(s_id.equals("")&&!supplier.equals("")){
				String sql="select ss_detail,ss_serial_num,ss_date,ss_type,ss_sid_in,"
						+ "ss_status,ss_num,ss_price,ss_pre_price,ss_info,ss_id"
						+ " from s_settlement where ss_supplier=? and ss_status='待对账'  limit ?,10";
				List<Object[]> list=SqlHelper.find(sql, supplier, currentPage);
				return list;
			}else if(!s_id.equals("")&&supplier.equals("")){

				String sql="select ss_detail,ss_serial_num,ss_date,ss_type,ss_sid_in,"
						+ "ss_status,ss_num,ss_price,ss_pre_price,ss_info,ss_id"
						+ " from s_settlement where ss_sid_in=? and ss_status='待对账'  limit ?,10";
				List<Object[]> list=SqlHelper.find(sql, s_id, currentPage);
				return list;
			
			}else{


				String sql="select ss_detail,ss_serial_num,ss_date,ss_type,ss_sid_in,"
						+ "ss_status,ss_num,ss_price,ss_pre_price,ss_info,ss_id"
						+ " from s_settlement where  ss_status='待对账'  limit ?,10";
				List<Object[]> list=SqlHelper.find(sql,  currentPage);
				return list;
			
			
			}
	}
	public List<Object[]> findjs2(String s_id, String supplier, int currentPage) {
		// TODO Auto-generated method stub
		if (!s_id.equals("")&&!supplier.equals("")) {
			String sql="select ss_detail,ss_serial_num,ss_date,ss_type,ss_sid_in,"
					+ "ss_status,ss_num,ss_price,ss_pre_price,ss_info,ss_id"
					+ " from s_settlement where ss_supplier=? and ss_sid_in=? and ss_status='待结算' limit ?,10";
			List<Object[]> list=SqlHelper.find(sql, supplier,s_id, currentPage);
			return list;
			}else if(s_id.equals("")&&!supplier.equals("")){
				String sql="select ss_detail,ss_serial_num,ss_date,ss_type,ss_sid_in,"
						+ "ss_status,ss_num,ss_price,ss_pre_price,ss_info,ss_id"
						+ " from s_settlement where ss_supplier=? and ss_status='待结算'  limit ?,10";
				List<Object[]> list=SqlHelper.find(sql, supplier, currentPage);
				return list;
			}else if(!s_id.equals("")&&supplier.equals("")){

				String sql="select ss_detail,ss_serial_num,ss_date,ss_type,ss_sid_in,"
						+ "ss_status,ss_num,ss_price,ss_pre_price,ss_info,ss_id"
						+ " from s_settlement where ss_sid_in=? and ss_status='待结算'  limit ?,10";
				List<Object[]> list=SqlHelper.find(sql, s_id, currentPage);
				return list;
			
			}else{
				String sql="select ss_detail,ss_serial_num,ss_date,ss_type,ss_sid_in,"
						+ "ss_status,ss_num,ss_price,ss_pre_price,ss_info,ss_id"
						+ " from s_settlement where  ss_status='待结算'  limit ?,10";
				List<Object[]> list=SqlHelper.find(sql,  currentPage);
				return list;
			}
		
	}
	public List<Object[]> findjs3(String s_id, String type, int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Object[]> findjs4(String s_id, String type, int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}
	public void qrdz1(String list) {
		// TODO Auto-generated method stub
		String a[]=list.split(" ");
		String SQL[]=new String[a.length];
		String b[][]=new String[a.length][1];
		for (int i = 0; i < a.length; i++) {
			SQL[i]="update s_settlement set ss_status='待结算' where ss_id=?";
			b[i][0]=a[i];
		}
		
		SqlHelper.executeUpdate(SQL,b);
	}
	public List<Object[]> qrjs(String supplier) {
		// TODO Auto-generated method stub
		String sql="select su_gd_return,su_ps_return from supplier where su_name=?";
	List<Object[]> list=	SqlHelper.find(sql, supplier);
		return list;
		
		
	}
	public void addsupplier(String s_id, String s_name, String su_name, String su_phone, String su_email, String su_contacter,
			String s_del, String su_ps_return, String su_gd_return, String su_number, String su_empower,
			String su_address, String su_info) {
		// TODO Auto-generated method stub
		String sql="insert into supplier(s_id,s_name,su_name,su_phone,su_email,su_contacter,s_del,"
			+"su_ps_return,su_gd_return,su_number,su_empower,su_address,su_info) values ("
			+ "?,?,?,?,?,?,?,?,?,?,?,?,?)";
		SqlHelper.executeUpdate(sql, new String[]{s_id,s_name,su_name,su_phone,su_email,su_contacter,s_del,
				su_ps_return,su_gd_return,su_number,su_empower,su_address,su_info});
	}
	public ArrayList isRegular(String truePath) throws BiffException, IOException {
		// TODO Auto-generated method stub
		ArrayList list = new ArrayList();
		boolean kong = true;
		
		JSONObject json=new JSONObject();
		String []supplierArrary=new String[]{"供货商编号(必填)","供货商名称(必填)","联系人","联系电话","邮箱","状态",
				"配送费返点","固定返利点","地址","备注"};
		int su_id = 0;
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
		if (sheet.getColumns()>10) {
			String	message="该表列数超过了模板的列数，请使用模板";
			list.add(message);
			return list;
		}else{
			for (int m = 0; m < sheet.getColumns(); m++) {
				content=sheet.getCell(m, 0).getContents();
				if(!content.equals(supplierArrary[m])){
				String	message="列表的第"+(m+1)+"个字段名错误，正确字段为:"+supplierArrary[m];
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
		{   int a=0,b=0;
			Suppliers supplier=new Suppliers();
			for(int j=0;j<sheet.getColumns();j++)
			{
				content=sheet.getCell(0, i).getContents();
				if(supplier.getSu_number()==null&&a==0)
				{ 
					a=a+1;
					if (sheet.getCell(j, i).getContents().trim().equals("")){
						if (kong) {
							String	message="该表无数据！" ;
							 list.add(message) ;
						}else{
							String	message="第"+(i+1)+"行"+"供货商编号不能为空!" ;
							 list.add(message) ;
						}
						
						 
					}else {
						supplier.setSu_number(sheet.getCell(j, i).getContents());
						continue;
					}
					
				}
				if(supplier.getSu_name()==null&&b==0)
				{
					b=b+1;
					if (sheet.getCell(j, i).getContents().trim().equals("")){
						
						if (kong) {
							
						}else{
						String	message="第"+(i+1)+"行"+"供货商名字不能为空!" ;
						 list.add(message) ;
						}
					}else {
					supplier.setSu_name(sheet.getCell(j, i).getContents());
					continue;
					}
				}
				

				if(supplier.getSu_contacter()==null)
				{
					supplier.setSu_contacter(sheet.getCell(j, i).getContents());
					continue;
				}
				
				if(supplier.getSu_phone()==null)
				{
						supplier.setSu_phone(sheet.getCell(j, i).getContents());
						continue;
				}
				if(supplier.getSu_email()==null)
				{
					supplier.setSu_email(sheet.getCell(j, i).getContents());
					continue;
				}
				if(supplier.getS_del()==null)
				{
					if (sheet.getCell(j, i).getContents().trim().equals("禁用")) {
					supplier.setS_del("0");
					continue;
					}else if(sheet.getCell(j, i).getContents().equals("")||
							sheet.getCell(j, i).getContents().trim().equals("启用")){
						supplier.setS_del("1");
						continue;
					}else {
						 String	message="第"+(i+1)+"行"+"状态数据格式错误，请输入启用或者禁止" ;
						 list.add(message) ;
					}
				}
				if(supplier.getSu_ps_return()==null)
				{
					if (sheet.getCell(j, i).getContents().matches("[0-9]+")) {
						supplier.setSu_ps_return(sheet.getCell(j, i).getContents());
						continue;
					}else if(sheet.getCell(j, i).getContents().equals("")){
						supplier.setSu_ps_return("0");
						continue;
					}else {
						
					   String	message="第"+(i+1)+"行"+"配送返费点数据格式错误！" ;
					   list.add(message) ;
					}
					
				}
				if(supplier.getSu_gd_return()==null)
				{
					if (sheet.getCell(j, i).getContents().matches("[0-9]+")) {
						supplier.setSu_gd_return(sheet.getCell(j, i).getContents());
						continue;
					}else if(sheet.getCell(j, i).getContents().equals("")){
						supplier.setSu_gd_return("0");
						continue;
					}else {
						String	message="第"+(i+1)+"行"+"固定返利点数据格式错误！" ;
						 list.add(message) ;
						
					}
						
						
						
					
					
					
				}

				if(supplier.getSu_address()==null)
				{
					supplier.setSu_address(sheet.getCell(j, i).getContents());
					continue;
				}

				if(supplier.getSu_info()==null)
				{
					supplier.setSu_info(sheet.getCell(j, i).getContents());
					continue;
				}
			}
			
		
		}
		return list;
		
		
	
			
		
		
		
	
	}
	



















}