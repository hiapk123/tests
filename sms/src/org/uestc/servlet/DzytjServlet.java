package org.uestc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.service.MarketingDzytjService;
import org.uestc.serviceImp.MarketingDzytjServiceImp;

/**
 * Servlet implementation class DzytjServlet
 */
@WebServlet("/DzytjServlet")
public class DzytjServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DzytjServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String subType =  request.getParameter("subType");
		System.out.println("DzytjServlet : doget()");
		System.out.println("subType = "+subType);
		 MarketingDzytjService mDzytjService = new MarketingDzytjServiceImp();
		if(subType==null){
			request.setAttribute("pageType", "添加");
			request.setAttribute("pageFunction", "addactive");
			request.setAttribute("init_html",mDzytjService.getPageInit() );
			request.getRequestDispatcher("/pages/marketing/dzytj.jsp").forward(request, response);
		}else if(subType.equals("init_add_active")){
			response.setContentType("text/html");
			response.setCharacterEncoding("utf8");
			request.setAttribute("pageType", "返回列表");
			request.setAttribute("pageFunction", "backToList");
			PrintWriter out = response.getWriter();
			String str = mDzytjService.getAddPage();
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
	protected void init_add_active(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		System.out.println("void  init_add_active()");
		//req.getRequestDispatcher("/pages/marketing/dzyt-add-active.jsp").forward(req, res);
		res.sendRedirect("pages/marketing/dzyt-add-active.jsp");
	}

}
