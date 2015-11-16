package org.uestc.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.service.MarketingCxspbService;
import org.uestc.serviceImp.MarketingCxspbServiceImp;

/**
 * Servlet implementation class CxspbServlet
 */
@WebServlet("/CxspbServlet")
public class CxspbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CxspbServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		
		String subType =  request.getParameter("subType");
		System.out.println("HgcxServlet : doget()");
		System.out.println("subType = "+subType);
		MarketingCxspbService mHgcxService = new MarketingCxspbServiceImp();
		if(subType==null||subType.equals("page_init")){
			mHgcxService.getPageInit(request, response);
			request.getRequestDispatcher("/pages/marketing/cxspb/cxspb.jsp").forward(request, response);
		}
		if("table".equals(subType)){
				String str = mHgcxService.profenye(request, response);
				PrintWriter out = response.getWriter();
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

}
