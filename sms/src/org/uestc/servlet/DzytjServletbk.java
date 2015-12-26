package org.uestc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.service.MarketingDzytjServicebk;
import org.uestc.serviceImp.MarketingDzytjServiceImpbk;

/**
 * Servlet implementation class DzytjServlet
 */
@WebServlet("/DzytjServletbk")
public class DzytjServletbk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DzytjServletbk() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		String subType =  request.getParameter("subType");
		//("DzytjServlet : doget()");
		//("subType = "+subType);
		 MarketingDzytjServicebk mDzytjService = new MarketingDzytjServiceImpbk();
		if(subType==null||subType.equals("page_init")){
			request.setAttribute("pageType", "添加");
			
			request.setAttribute("init_html",mDzytjService.getPageInit() );
			if(subType==null)
			request.getRequestDispatcher("/pages/marketing/dzytj.jsp").forward(request, response);
			else if (subType.equals("page_init")){
				response.sendRedirect("marketing?type=dzytj");
			}
			
		}else if(subType.equals("init_add_active")){
			
			request.setAttribute("pageType", "返回列表");
			String subpage =request.getParameter("add_update_search");
			//(subpage);
			String d_name = request.getParameter("d_name");
			String d_start_date = request.getParameter("d_start_date");
			String str = mDzytjService.getAddPage(subpage,d_name,d_start_date);
			out.write(str);
			out.flush();
			out.close();
		}else if(subType.equals("getHead")){
			
			request.setAttribute("pageType", "返回列表");
			String active = request.getParameter("active");
			String id  = request.getParameter("id");
			String str = "";
			switch (active) {
			case "xiangxi":
				 str = mDzytjService.getXiangxiHead(id);
				break;
			case "gengxin":
				 str = mDzytjService.getGengxinHead(id);
				break;
			case "add":
				 str = mDzytjService.getAddHead();
				break;
			default:
				break;
			}
			out.write(str);
			out.flush();
			out.close();
		}else if(subType.equals("tanchuceng")){
			String tc_type = request.getParameter("tc_type");
			String str = mDzytjService.progressTCType(tc_type,request);
			out.write(str);
			out.flush();
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	/**
	 * 初始化添加活动界面
	 * @throws IOException 
	 * @throws ServletException 
	 */
/*	protected void init_add_active(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		//("void  init_add_active()");
		//req.getRequestDispatcher("/pages/marketing/dzyt-add-active.jsp").forward(req, res);
		res.sendRedirect("pages/marketing/dzyt-add-active.jsp");
	}
*/
}
