package org.uestc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.service.MarketingMefxService;
import org.uestc.serviceImp.MarketingMefxServiceImp;

/**
 * Servlet implementation class MefxServlet
 */
@WebServlet("/MefxServlet")
public class MefxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MefxServlet() {
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
		String subType =  request.getParameter("subType");
		System.out.println("MefxServlet : doget()");
		System.out.println("subType = "+subType);
		MarketingMefxService mMefxService = new MarketingMefxServiceImp();
		if(subType==null||subType.equals("page_init")){
			mMefxService.getPageInit(request, response);
			request.getRequestDispatcher("/pages/marketing/mefx/mefx.jsp").forward(request, response);
		}else if(subType.equals("XGactive")){
			String type =mMefxService.operateProgress(request, response);
			if(!type.equals("shanchu"))
			request.getRequestDispatcher("/pages/marketing/mefx/mefx-xiangxi.jsp").forward(request, response);
			else {
				   out.write("shanchu");
					out.flush();
					out.close();
			}
		}else if(subType.equals("addactive")){
			String ifsave = request.getParameter("ifsave");
			if(ifsave.equals("no")){
				request.getRequestDispatcher("/pages/marketing/mefx/mefx-tianjia.jsp").forward(request, response);
			}	
		}else if(subType.equals("tanchuceng")){
			String tc_type = request.getParameter("tc_type");	
			String str = mMefxService.progressTCType(tc_type,request,response);
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
