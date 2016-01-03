package org.uestc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.service.TableBatchService;
import org.uestc.serviceImp.TableBatchServiceImp;

/**
 * Servlet implementation class shupdate
 */
@WebServlet(urlPatterns="/shupdate",name="shupdateservlet")
public class shupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doPost(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//接受前台传�?�参�?
		String ids=request.getParameter("ids");
		int tcgz=Integer.parseInt(request.getParameter("tcgz"));
		String  ydfl=request.getParameter("ydfl");
		String ydghs=request.getParameter("ydghs");
		String shjf=request.getParameter("shjf");
		String hyyh=request.getParameter("hyyh");
		int sshjfen=Integer.parseInt(request.getParameter("sshjfen"));
		int  shqyjy=Integer.parseInt(request.getParameter("shqyjy")) ;
		//获取商品提成的编号�??
		//int shbianhao=Integer.parseInt(request.getParameter("shbianhao"));

		//进行分批的修改操�?
		
		TableBatchService sss=new TableBatchServiceImp();
		int shtc=0;;//定义�?个操作类�?
		//商品提成，直接进行操�?
	
	//根据提成进行修改
	//sss.shupdatetcgz(shtc,ids);
	//******************直接修改商品提成******************************
		if(tcgz==-1)
		{
			//不进行操�?
		}else {
			//以�?�择项进行更�?
			sss.shupdatetcgz(tcgz, ids);
		}
	//************************************************************
		//商品分类
		if("-1".equals(ydfl))
		{
			//不进行更新操�?
		}
		else
		{
			//z执行更新的操�?
			sss.shupdateydfl(ydfl, ids);
		}	
		//供货商更�?
		if("-1".equals(ydghs))
		{}
		else {
			sss.shupdateydghs(ydghs, ids);
		}
		
		//商品积分更新
		if(sshjfen==0)
	{
		if("无积�?".equals(shjf))
		{
			sss.shupdateshjf("无积�?", ids);
		}
		else
		{
			sss.shupdateshjf(shjf, ids);
		}
	}
		else {
			
			//先根据shjf(文本输入�?)计算出�?�以后才能计�?
			//1.先取出销售价的�??
			String shsaleprice=new TableBatchServiceImp().shsaleprice(ids);
			float par1=Float.parseFloat(shsaleprice);
			float par2=Float.parseFloat(shjf);
			double par3=par1*par2*0.01;
			String par4=String.valueOf(par3);
			sss.shupdateshjf(par4, ids);
			
		}
		
	    //会员优惠更新**********************************
		if("会员优惠".equals(hyyh))
		{
			sss.shupdatehyyh("1", ids);
			
		}
		else {
			sss.shupdatehyyh("1", ids);
		}
			
		//会员状�?�更�?
		sss.shupdateshqyjy(shqyjy, ids);
		
	}

}
