package org.uestc.servlet;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.MemInformServiceImp;

@WebServlet(urlPatterns="/shmtijiao",
name="shmtijiaoservlet"
		)
public class shmtijiao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	
		//System.out.println("进入查询按钮的servlet");
		//接受前台传�?�的参数
		String mshopname=request.getParameter("mshopname");
		String mshopdj=request.getParameter("mshopdj");
		int mshopqy=Integer.parseInt(request.getParameter("mshopqy").toString());
		//这里先以姓名作为查询的特征�??
		String mshoptext=request.getParameter("mshoptext");
		//正则表达式（判断是否为汉字）
		Pattern p= Pattern.compile("[\\u4e00-\\u9fa5]+");
		Matcher m=p.matcher(mshoptext); 
		//判断是否为数�?
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher n=pattern.matcher(mshoptext); 
		//拼接sql的字符串
		String haosql="";
		String newsql="select a.v_id,a.v_card_no,a.vip_name,a.vip_tel,a.v_level,a.v_balance,a.v_commodity_integral,b.s_name,a.vip_startdate,a.v_status FROM vip a LEFT JOIN store b ON a.s_id=b.s_id WHERE 1=1 ";
		//定义的显示的�?
		List<Object[]> shsun=null;
		//汉字的情况进行查询
		if(m.matches()==true)
		{
			//按照姓名进行查询
			//System.out.println("汉字匹配成功");
			//使用模糊查询进行匹配
			if("-1".equals(mshopname))
			{
				//商品名称不进行查�?
				//newsql +="";
							
			}
			else {
				newsql +=" and b.s_name="+"'"+mshopname+"'";	
			}
			
			if("-1".equals(mshopdj))
			{
				//不进行规则的匹配
				//newsql+=" and v_level like '%%'";
			}
			else
			{
				//进行相应的查�?
				newsql+=" and v_level ="+"'"+mshopdj+"'";
				
			}
			
			if(mshopqy==0)
			{
				newsql+=" and v_status=0";
				
			}
			else {
				
				newsql+=" and v_status=1";
				
			}
			
			//有输入汉字的情况下进行匹�?(匹配姓名)
			newsql +=" and  vip_name="+"'"+mshoptext+"'";
			
			haosql=newsql;
			//汉字的字符串拼接成功�?
			newsql +=" limit 0,10";
		shsun=new MemInformServiceImp().msubfind(newsql); 
			
		}//汉字匹配结束
		//这里空字符串默认是数字
		else if (n.matches()==true) {
			//如果输入框为空的话是按照数字进行匹配�?
			//System.out.println("数字匹配成功");
			//区分是不是标准的电话格式（电话号码属于是11位的格式�?
			if(mshoptext.length()==11)
			{
				//说明是电话号码进行查�?
				if("-1".equals(mshopname))
				{
					//商品名称不进行查�?
					//newsql +=" b.s_name like '%%'";
								
				}
				else {
					newsql +=" and b.s_name="+"'"+mshopname+"'";	
				}
				
				if("-1".equals(mshopdj))
				{
					//不进行规则的匹配
					//newsql+=" and v_level like '%%'";
				}
				else
				{
					//进行相应的查�?
					newsql+=" and v_level ="+"'"+mshopdj+"'";
					
				}
				
				if(mshopqy==0)
				{
					newsql+=" and v_status=0";
					
				}
				else {
					
					newsql+=" and v_status=1";
					
				}
				//************查询电话号码
				if(mshoptext=="")
				{
				//newsql +=" and  vip_tel like '%%'";
				}
				else {
					newsql +=" and  vip_tel="+"'"+mshoptext+"'";
				}
				haosql=newsql;
				newsql +=" limit 0,10";
				shsun=new MemInformServiceImp().msubfind(newsql); 
				
			}
			else {
				//说明是会员号码进行查�?
				if("-1".equals(mshopname))
				{
					//商品名称不进行查�?
					//newsql +=" b.s_name like '%%'";
								
				}
				else {
					newsql +="and b.s_name="+"'"+mshopname+"'";	
				}
				
				if("-1".equals(mshopdj))
				{
					//不进行规则的匹配
					//newsql+=" and v_level like '%%'";
				}
				else
				{
					//进行相应的查�?
					newsql+=" and v_level ="+"'"+mshopdj+"'";
					
				}
				
				if(mshopqy==0)
				{
					newsql+=" and v_status=0";
					
				}
				else {
					
					newsql+=" and v_status=1";
					
				}
				//***会员号码进行查询
				if(mshoptext=="")
				{
				//newsql +=" and  v_card_no like '%%'";
				}
				else {
					newsql +=" and  v_card_no="+"'"+mshoptext+"'";
				}
				haosql=newsql;
				newsql +=" limit 0,10";
				shsun=new MemInformServiceImp().msubfind(newsql); 
			}
			
		}//数字匹配结束
		else {
			//不是数字也不是汉字的匹配情况�?
		}
	
		int currentpage=1;
		int shshoenum=0;
		//2,总的余额（5）
		int shshoeyue=0;
		//3.总的积分
		int shshoejifen=0;
		
		List<Object[]> sklls=new MemInformServiceImp().normalfinad(haosql);
		if(sklls!=null&&sklls.size()!=0)
		{
		shshoenum=sklls.size();
			
		for(Object[] obj:sklls)
		{
			shshoeyue=shshoeyue+Integer.parseInt(obj[5].toString());
			shshoejifen=shshoejifen+Integer.parseInt(obj[6].toString());
		}
		}
		
		int totalPage=0;
		if(shshoenum%10==0)
		{
			totalPage=shshoenum/10;
			
			
		}
		else {
			
			totalPage=shshoenum/10+1;
		}
	//配置一些分页标签的基本参数
		request.setAttribute("totalPage", shshoenum);
		request.setAttribute("currentPage", currentpage);
		request.setAttribute("shshoenum", shshoenum);
		request.setAttribute("shshoeyue", shshoeyue);
		request.setAttribute("shshoejifen", shshoejifen);
	//***
	request.setAttribute("nlist", shsun);
	request.getRequestDispatcher("/pages/member/shmtable.jsp").forward(request, response);
			
	}

}
