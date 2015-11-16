package org.uestc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.service.MarketingCxspbService;
import org.uestc.service.MarketingSupportService;
import org.uestc.serviceImp.MarketingCxspbServiceImp;
import org.uestc.serviceImp.MarketingSupportImp;

/**
 * Servlet implementation class MarketingSuportServlet
 */
@WebServlet("/MarketingSuportServlet")
public class MarketingSuportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketingSuportServlet() {
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
		PrintWriter out = response.getWriter();
		String subType =  request.getParameter("type");
		
		MarketingSupportService mSupport = new MarketingSupportImp();
		if(subType!=null&&subType.equals("getCoupons")){
			String str = mSupport.getCoupons();
			out.write(str);
			out.flush();
			out.close();
		}
		if(subType!=null&&subType.equals("saveToActive")){
			mSupport.saveCouponsToActive(request, response);
		}
		if(subType!=null&&subType.equals("search_coupons")){
			String keywords  = request.getParameter("keywords");
			String str="";
			if(keywords!=null)
			if(!(keywords.equals("名称/条码")||keywords.equals("")))
			   str = mSupport.searchCoupons(keywords);
			if(str.equals(""))
				str = mSupport.getCoupons();
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
