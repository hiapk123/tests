package org.uestc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uestc.bean.memedit;

@WebServlet(urlPatterns="/shbangding",
			 name="shbangdingservlet")
public class shbangding extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		//System.out.println("bangding");
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
				
				List<Object[]> sheditlist=null;
				for(Object[] obj:sheditlist)
				{
					obj[0]=v_level;
					obj[1]=v_card_no;
					obj[2]=vip_name;
					obj[3]=sshhydj;
					obj[4]=shhyzk;
					obj[5]=shhyye;
					obj[6]=shhyjf;
					obj[7]=shlxdh;
					obj[8]=shhymm;
					obj[9]=shhysr;
					obj[10]=shkaika;
					obj[11]=shdaoqi;
					obj[12]=Integer.toString(shyxsz);
					obj[13]=shqqhao;
					obj[14]=shyouxiang;
					obj[15]=shdizhi;
					obj[16]=shbeizhu;
				}
				request.setAttribute("sheditlist", sheditlist);
		
	}

}
