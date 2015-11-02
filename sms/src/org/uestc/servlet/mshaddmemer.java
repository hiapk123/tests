package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.MemInformServiceImp;
import org.uestc.serviceImp.TableBatchServiceImp;

@WebServlet(urlPatterns= "/mshaddmemer",
name="mshaddmemerservlet")
public class mshaddmemer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("新增会员的servlet");
		//接受前台传�?�的参数�?	
		String v_level=request.getParameter("sh_v_level").toString();
		String v_card_no=request.getParameter("v_card_no").toString();
		String vip_name=request.getParameter("vip_name").toString();
		String sshhydj=request.getParameter("sshhydj").toString();
		String shhyzk=request.getParameter("shhyzk").toString();
		String shhyye=request.getParameter("shhyye").toString();
		String shhyjf=request.getParameter("shhyjf").toString();
		String shlxdh=request.getParameter("shlxdh").toString();
		String shhymm=request.getParameter("shhymm").toString();
		String shhysr=request.getParameter("shhysr").toString();
		String shkaika=request.getParameter("shkaika").toString();
		String shdaoqi=request.getParameter("shdaoqi").toString();
		int shyxsz=Integer.parseInt(request.getParameter("shyxsz").toString());
		String shqqhao=request.getParameter("shqqhao").toString();
		String shyouxiang=request.getParameter("shyouxiang").toString();
		String shdizhi=request.getParameter("shdizhi").toString();
		String shbeizhu=request.getParameter("shbeizhu").toString();
		//拼接sql语句
		String newqsql="insert into vip(v_status,v_card_no,vip_name,v_level,vip_dsicount,v_balance,v_commodity_integral,vip_tel,vip_pwd,v_birthday,vip_startdate,vip_enddate,vip_allow,v_memqq,v_youxiang,vip_addr,v_beizhu) VALUES ("+
				"'"+v_level+"'"+","+"'"+v_card_no+"'"+","+"'"+vip_name+"'"+","+"'"+sshhydj+"'"+","+"'"+shhyzk+"'"+","+"'"+shhyye+"'"+","+"'"+shhyjf+"'"+","+
				"'"+shlxdh+"'"+","+"'"+shhymm+"'"+","+"'"+shhysr+"'"+","+"'"+shkaika+"'"+","+"'"+shdaoqi+"'"+","+shyxsz+","+"'"+shqqhao+"'"+","+
				"'"+shyouxiang+"'"+","+"'"+shdizhi+"'"+","+"'"+shbeizhu+"'"+")";
		System.out.println(newqsql);
		//进行sql的执行操作�??
		new TableBatchServiceImp().shaddmeminsert(newqsql);
		//有返回�?�得话就说明添加成功�?
		
		
		//在这里进行初始化的刷新操作
		List<Object[]> nlist=null;
		nlist=new MemInformServiceImp().meminfoinit();
		//********
		List<Object[]> shshowinform=null;
		String pldsh="select a.v_id,a.v_card_no,a.vip_name,a.vip_tel,a.v_level,a.v_balance,a.v_commodity_integral,b.s_name,a.vip_startdate, a.v_status FROM vip a LEFT JOIN store b ON a.s_id=b.s_id";
		shshowinform=new MemInformServiceImp().normalfinad(pldsh);
		//*****
		//1.会员数量
		int shshoenum=0;
		//2,总的余额（5）
		int shshoeyue=0;
		//3.总的积分
		int shshoejifen=0;
		if(shshowinform!=null&&shshowinform.size()!=0)
		{
		shshoenum=shshowinform.size();
			
		for(Object[] obj:shshowinform)
		{
			shshoeyue=shshoeyue+Integer.parseInt(obj[5].toString());
			shshoejifen=shshoejifen+Integer.parseInt(obj[6].toString());
		}
		}
		System.out.println(shshoenum);
		System.out.println(shshoeyue);
		System.out.println(shshoejifen);
		int totalPage=0;
		int currentpage=1;
		if(shshoenum%10==0)
		{
			totalPage=shshoenum/10;
			
			
		}
		else {
			
			totalPage=shshoenum/10+1;
		}
		
		request.setAttribute("totalPage", shshoenum);
		request.setAttribute("currentPage", currentpage);
		request.setAttribute("nlist", nlist);
		request.setAttribute("shshoenum", shshoenum);
		request.setAttribute("shshoeyue", shshoeyue);
		request.setAttribute("shshoejifen", shshoejifen);
		request.getRequestDispatcher("/pages/member/shmtable.jsp").forward(request, response);
		
		//初始化的刷新结束
	}

}
