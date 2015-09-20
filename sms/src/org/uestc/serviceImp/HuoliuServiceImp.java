package org.uestc.serviceImp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.uestc.service.HuoliuService;
import org.uestc.util.JdbcUtils;
import org.uestc.util.SqlHelper;


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
	public List<Object[]> supplierInfo(int s_id) {
		String sql = "select su_number,su_name,su_contacter,su_phone,su_email,su_empower,su_id,su_sid"
		+ " from supplier where su_sid=?";
		List<Object[]> list = SqlHelper.find(sql, s_id);
		return list;
	}

	@Override
	public void editSupplier(String su_number,String su_name,String su_contacter,String
			su_phone,String su_email,String su_empower,String su_id) {
		// TODO Auto-generated method stub
		
		String sql="update supplier set su_number=?,su_name=?,su_contacter=?,su_phone=?,su_email=?,su_empower=? where su_id=? ";
		
		SqlHelper.executeUpdate(sql, new String[] {su_number,su_name,su_contacter,su_phone,su_email,su_empower,su_id});
	}

	public void importExcel(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub

		String []supplierArrary={"供货商编号","供货商名称","拼音码","联系人","联系电话","邮箱","状态",
				"配送费返点","固定返利点","地址","备注"};
		int su_id = 0;
		List liststu=new ArrayList();
		// 找到导入的文件
		//InputStream is= Date.class.getClassLoader().getResourceAsStream("/1.xls");
		String sFilePath = "F:/liuyan0/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/sms/WEB-INF/upload/供货商资料模板.xls";  
		String message=null;
		InputStream is = new FileInputStream(sFilePath);  
		
			//创建工作簿
			Workbook wb=Workbook.getWorkbook(is);
			//创建工作表
			jxl.Sheet sheet=wb.getSheet(0);
			String content=null; 
			String flag="add";
			for (int m = 0; m < sheet.getColumns(); m++) {
				content=sheet.getCell(m, 0).getContents();
				if(!content.equals(supplierArrary[m])){
					message="列表的第"+(m+1)+"个字段名错误，正确字段为:"+supplierArrary[m];

					throw new Exception();
				}
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
					if(supplier.getSu_pm()==null)
					{
						supplier.setSu_pm(sheet.getCell(j, i).getContents());
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
					if(supplier.getSu_empower()==null)
					{
						supplier.setSu_empower(sheet.getCell(j, i).getContents());
						continue;
					}
					if(supplier.getSu_ps_return()==0)
					{
						supplier.setSu_ps_return(Integer.valueOf(sheet.getCell(j, i).getContents()));
						continue;
					}
					if(supplier.getSu_gd_return()==0)
					{
						supplier.setSu_gd_return(Integer.valueOf(sheet.getCell(j, i).getContents()));
						continue;
					}
					
					if(supplier.getSu_address()==null)
					{
						supplier.setSu_address(sheet.getCell(j, i).getContents());
						continue;
					}
					
					
					String sql="select su_number,su_id from supplier where su_sid=?";
					List<Object[]> list = SqlHelper.find(sql, 4);
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
					if(supplier.getSu_bz()==null)
					{
						supplier.setSu_bz(sheet.getCell(j, i).getContents());
						continue;
					}
				}
				if(flag=="add")
				{
					daoruexcel(supplier);
				}else if(flag=="update"){
					daoruexcel1(supplier,su_id);
					
				}
				message= "成功！";
			}

		

		req.setAttribute("message",message);
		req.getRequestDispatcher("/pages/huoliu/success.jsp").forward(req, resp);
	}
	private void daoruexcel1(Suppliers supplier,int su_id) {
		// TODO Auto-generated method stub
		String sql="update supplier set su_name=?,su_pm=?,su_contacter=?,"
				+ "su_phone=?,su_email=?,su_empower=?,su_ps_return=?,"
				+ "su_gd_return=?,su_address=?,su_bz=? where su_id=?";

		SqlHelper.executeUpdate(sql, new String[] { supplier.getSu_name(),supplier.getSu_pm(),
				supplier.getSu_contacter(),supplier.getSu_phone(),supplier.getSu_email(),
				supplier.getSu_empower(),String.valueOf(supplier.getSu_ps_return()),String.valueOf(supplier.getSu_gd_return()),
				supplier.getSu_address(),supplier.getSu_bz(),
				su_id+""});
		String m=null;
	}
	public void daoruexcel(Suppliers supplier)
	{   
	
		String sql="insert into supplier(su_number,su_name,su_pm,su_contacter,su_phone,su_email,su_empower,su_ps_return,su_gd_return,su_address,su_bz,su_sid) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		SqlHelper.executeUpdate(sql, new String[]{ supplier.getSu_number(),supplier.getSu_name(),
				supplier.getSu_pm(),supplier.getSu_contacter(),supplier.getSu_phone(),supplier.getSu_email(),
				supplier.getSu_empower(),String.valueOf(supplier.getSu_ps_return()),
				String.valueOf(supplier.getSu_gd_return()),
				supplier.getSu_address(),supplier.getSu_bz(),4+""
		});
	}

	@Override
	public List<Object[]> toExcel(int s_id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String sql = "SELECT su_number,su_name,su_pm,su_contacter,su_phone,su_email,"
				+ "su_empower,su_ps_return,su_gd_return,su_address,"
				+ "su_bz from supplier where su_sid=? ";
		List<Object[]> list = SqlHelper.find(sql, s_id);
		return list;
	}

	

	public List<Object[]> search(String s_id, String shuru) {
		// TODO Auto-generated method stub
		String sql="select g_name,g_barcode,g_stock_num,su_name,g_pur_price,g_id from goods where s_id=? and"
				+ " g_name like '%"+shuru+"%'";
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
				+ "l_serial_num,l_type,l_status,l_num,l_price,l_pre_price,l_info) value("
				+ "?,?,?,'进货单','未审核',?,?,?,?)";
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
	    	s_id_in,order,strDate,String.valueOf(l_num),String.valueOf(l_price),l_pre_price,l_info
	    });
		
	}

	public List<Object[]> findhl(String s_id, String type) {
		// TODO Auto-generated method stub
		String sql="select l_detail,l_serial_num,l_date,l_type,s_id_out,"
				+ "l_status,l_num,l_price,l_pre_price,l_info,l_id"
				+ " from logistics where s_id_in=? and l_type=?";
	        List<Object[]> list=SqlHelper.find(sql, s_id,type);
	        return list;
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

public void ruku(String order, String s_id_in, String l_info) {
	// TODO Auto-generated method stub
	String sql="update  logistics set l_status='已审核' where l_id=?";
	//String sql1 = "update goods set s_id=?,s_name=?, g_name=?,g_stock_num=?,g_sale_price=?, g_pur_price=?,c_name=? ,g_barcode=? where g_id=?"; 
   
	SqlHelper.executeUpdate(sql, new String[]{
    	"l_id"
    });
	
}

	

	

	

	
	

	

	
	
	
	
	
}