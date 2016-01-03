package org.uestc.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.MemInformServiceImp;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WriteException;

@WebServlet(urlPatterns="/shwriteintoexcel",name="shwriteintoexcelServlet")
public class shwriteintoexcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//查询需要导出的数据
		List<Object[]> nlistsp=null;
		nlistsp=new MemInformServiceImp().meminfoinit();
		//String ssl="select a.v_id,a.v_card_no,a.vip_name,a.vip_tel,a.v_level,a.v_balance,a.v_commodityintegral,b.s_name,a.vip_startdate, a.v_status FROM vip a LEFT JOIN store b ON a.s_id=b.s_id ";
		//nlistsp=new MemInformServiceImp().normalfinad(ssl);
		request.setAttribute("nlistsp", nlistsp);
		String path="会员信息.xls";
		
		//***
		String[] title={"会员序号","会员号","姓名","电话","会员等级","余额","积分","开卡门店","开卡时间"};
		//创建工作簿随�?
		jxl.write.WritableWorkbook wb=null;
		String filename=path;
		
		String dictionary="f://";
		File file=new File(dictionary,filename);
		try {
				
		       //设置弹出对话框   
            response.setContentType("application/DOWLOAD");   
               
            //设置工作表的标题   
            response.setHeader("Content-Disposition", "attachment; filename=会员信息.xls");   
            //os = response.getOutputStream();
			//****
			wb=jxl.Workbook.createWorkbook(file);
			//创建工作表
			WritableSheet sheet=wb.createSheet("会员信息统计表", 0);
			//3.添加表头
			Label label=null;
			for(int i=0;i<title.length;i++)
			{
				label=new Label(i,0,title[i]);
				sheet.addCell(label);
			}
			
			// os = response.getOutputStream();  
			//4.追加数据
			Label label2=null;
			Object[] map=null;
 			for(int j=0;j<nlistsp.size();j++)
			{
 				//这里可以取到相应的对�?
 				map=nlistsp.get(j);
 				//转化字符串来构�?�标�?
 				label2=new Label(0, j+1,String.valueOf(map[0].toString()) );
				sheet.addCell(label2);
				
				label2=new Label(1, j+1,String.valueOf(map[1].toString()) );
				sheet.addCell(label2);
				
				label2=new Label(2, j+1,String.valueOf(map[2].toString()) );
				sheet.addCell(label2);
				
				label2=new Label(3, j+1,String.valueOf(map[3].toString()) );
				sheet.addCell(label2);
				
				label2=new Label(4, j+1,String.valueOf(map[4].toString()) );
				sheet.addCell(label2);
				
				label2=new Label(5, j+1,String.valueOf(map[5].toString()) );
				sheet.addCell(label2);
				
				label2=new Label(6, j+1,String.valueOf(map[6].toString()) );
				sheet.addCell(label2);
				
				label2=new Label(7, j+1,String.valueOf(map[7].toString()) );
				sheet.addCell(label2);
				
				label2=new Label(8, j+1,String.valueOf(map[8].toString()) );
				sheet.addCell(label2);
				
				//写入数据
			}
 			wb.write();
			wb.close();
 			//wb.close();
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (WriteException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//***
		//shexcelutil.exportdata(nlistsp, path);
		//response.sendRedirect("f://会员信息.xls");
		
		
	}

}
