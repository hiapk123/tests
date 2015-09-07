package org.uestc.serviceImp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.uestc.service.HuoliuService;
import org.uestc.util.JdbcUtils;
import org.uestc.util.SqlHelper;


import com.uestc.bean.Suppliers;

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
		/*String sql="insert into supplier(su_number,su_name,su_pm,su_contacter,su_phone,su_email,su_empower,su_ps_return,su_gd_return,su_address,su_bz,su_sid)values("+
			"'"+supplier.getSu_number()+"','"+
				supplier.getSu_name()+"','"+
				supplier.getSu_pm()+"','"+
				supplier.getSu_contacter()+"','"+
				supplier.getSu_phone()+"','"+
				supplier.getSu_email()+"',"+
				supplier.getSu_empower()+"',"+
				supplier.getSu_ps_return()+","+
				supplier.getSu_gd_return()+",'"+
				supplier.getSu_address()+"','"+
				supplier.getSu_bz()+"',"
				+"?)";
		SqlHelper.executeUpdate(sql, new String[]{ 4+"" });*/
		String sql="insert into supplier(su_number,su_name,su_pm,su_contacter,su_phone,su_email,su_empower,su_ps_return,su_gd_return,su_address,su_bz,su_sid) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		SqlHelper.executeUpdate(sql, new String[]{ supplier.getSu_number(),supplier.getSu_name(),
				supplier.getSu_pm(),supplier.getSu_contacter(),supplier.getSu_phone(),supplier.getSu_email(),
				supplier.getSu_empower(),String.valueOf(supplier.getSu_ps_return()),
				String.valueOf(supplier.getSu_gd_return()),
				supplier.getSu_address(),supplier.getSu_bz(),4+""
		});
	}
	

	

	
	
	
	
	
}