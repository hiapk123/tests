package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.MemInformServiceImp;

@WebServlet(urlPatterns="/shhuiyuanbianjichuli",name="shhuiyuanbianjichuliServlet")
public class shhuiyuanbianjichuli extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("会员编辑更新");
		int sh_zhuangtai=Integer.parseInt(request.getParameter("sh_zhuangtai"));
		String sh_bianhao=request.getParameter("sh_bianhao");
		String sh_xingming=request.getParameter("sh_xingming");
		String sh_dengji=request.getParameter("sh_dengji");
		String sh_zhekou=request.getParameter("sh_zhekou");
		String sh_yue=request.getParameter("sh_yue");
		String sh_jifen=request.getParameter("sh_jifen");
		String sh_tel=request.getParameter("sh_tel");
		String sh_mima=request.getParameter("sh_mima");
		String sh_shengri=request.getParameter("sh_shengri");
		String sh_kaikariqi=request.getParameter("sh_kaikariqi");
		String sh_daoqishijian=request.getParameter("sh_daoqishijian");
		String sh_shezhang=request.getParameter("sh_shezhang");
		
		String sh_qq=request.getParameter("sh_qq");
		String sh_youxiang=request.getParameter("sh_youxiang");
		String sh_dizhi=request.getParameter("sh_dizhi");
		String sh_beizhu=request.getParameter("sh_beizhu");
		
		int sskkid=Integer.parseInt(request.getParameter("sskkid"));
				
		//开始进行页面的查询
		String klox="UPDATE vip SET v_status="+sh_zhuangtai+",v_card_no="+"'"+sh_bianhao+"'"+",vip_name="+"'"+sh_xingming+"'"+",v_level="+"'"+sh_dengji+"'"+",vip_dsicount="+"'"+sh_zhekou+"'"+",v_balance="+"'"+sh_yue+"'"+",v_commodity_integral="+"'"+sh_jifen+"'"+",vip_tel="+"'"+sh_tel+"'"+",vip_pwd="+"'"+sh_mima+"'"+",v_birthday="+"'"+sh_shengri+"'"+",vip_startdate="+"'"+sh_kaikariqi+"'"+",vip_enddate="+"'"+sh_daoqishijian+"'"+",v_memqq="+"'"+sh_qq+"'"+",v_youxiang="+"'"+sh_youxiang+"'"+",vip_addr="+"'"+sh_dizhi+"'"+",v_beizhu="+"'"+sh_beizhu+"'"+" WHERE v_id="+sskkid;
		
		new MemInformServiceImp().normalupdate(klox);
		
		
	}

}
