package org.uestc.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.uestc.serviceImp.MemInformServiceImp;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WriteException;




/*
 * 导入导出工具�?
 * */
public class shexcelutil {

	/*数据的导入的输入类
	 * 
	 */
	public static void importdata(String path)
	{
		
		
	}
	
	
	/*
	 * 数据导出
	 * 这里可以生成�?个数据文件�?�写入到excel
	 * 这里可以实现在页面的直接调用
	 * */
	public static void  exportdata(String path)
	{
	//***
		System.out.println("将文件写入excel");
		//查询需要导出的数据
		List<Object[]> nlistsp=null;
		//nlistsp=new MemInformServiceImp().meminfoinit();
		//但是为空数据的时候导出的时候是会报错的
		String ssl="select a.v_id,a.v_card_no,a.vip_name,a.vip_tel,a.v_level,a.v_balance,a.v_commodityintegral,b.s_name,a.vip_startdate, a.v_status FROM vip a LEFT JOIN store b ON a.s_id=b.s_id ";
		nlistsp=new MemInformServiceImp().normalfinad(ssl);
		
		
		//***
		String[] title={"会员序号","会员号","姓名","电话","会员等级","余额","积分","开卡门店","开卡时间"};
		//创建工作簿随�?
		jxl.write.WritableWorkbook wb=null;
		String filename=path;
		File file=new File(filename);
		System.out.println(file.getPath());
		try {
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
	}
}
