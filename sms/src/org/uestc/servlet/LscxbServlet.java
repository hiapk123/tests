package org.uestc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.RepaintManager;

import org.uestc.service.MarketingLscxbService;
import org.uestc.serviceImp.MarketingLscxbServiceImp;

/**
 * Servlet implementation class Lscxb
 */
@WebServlet("/LscxbServlet")
public class LscxbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LscxbServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
	
		String subType =  request.getParameter("subType");
		
		MarketingLscxbService mHgcxService = new MarketingLscxbServiceImp();
		if(subType==null||subType.equals("page_init")){
			String strjson = mHgcxService.getPageInit(request, response);
			request.getRequestDispatcher("/pages/marketing/lscxb/lscxb.jsp").forward(request, response);
		}
		if("shanchu".equals(subType)){
			mHgcxService.operateProgress(request, response);
			PrintWriter out = response.getWriter();
			out.write("shanchu");
			out.flush();
			out.close();
		}
		if("table".equals(subType)){
			String strjson = mHgcxService.profenye(request, response);
			System.out.println("AJAX Page"+strjson);
			PrintWriter out = response.getWriter();
			out.write(strjson);
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

}
