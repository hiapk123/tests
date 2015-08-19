package org.uestc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Marketing
 */
@WebServlet(urlPatterns = { "/marketing" },
			name ="MarketingServlet")
public class MarketingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("MarketingServlet-------doGet()");
		
	//	response.setCharacterEncoding("GBK");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		//response.getWriter();
		//response.setHeader("Content-Type", "text/html;charset=UTF-8");
		//
		String encodeType = response.getCharacterEncoding();
		System.out.println(encodeType);
		RequestDispatcher rd ;
		String type  = request.getParameter("type");
		System.out.println("MarketingServlet---doGet()---type:"+type);
		switch (type) {
		case "dzytj":
			dzytj(request,response);
			
			break;

		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		System.out.println("MarketingServlet-------doPost()");
	}
	/**
	 * 打折与特价页面功能处理
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void dzytj(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/DzytjServlet").forward(request, response);
	
	}

}
