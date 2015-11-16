package org.uestc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.service.MarketingTccxService;
import org.uestc.serviceImp.MarketingTccxServiceImp;

/**
 * Servlet implementation class HgcxServlet
 */
@WebServlet("/TccxServlet")
public class TccxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TccxServlet() {
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
		System.out.println("TccxServlet : doget()");
		System.out.println("subType = "+subType);
		MarketingTccxService mTccxService = new MarketingTccxServiceImp();
		if(subType==null||subType.equals("page_init")){
			mTccxService.getPageInit(request, response);
			if(request.getParameter("copy")!=null&&request.getParameter("copy").equals("yes")){
				request.setAttribute("copy", "yes");
			}
			request.getRequestDispatcher("/pages/marketing/tccx/tccx.jsp").forward(request, response);
		}else if(subType.equals("XGactive")){
			String type =mTccxService.operateProgress(request, response);
			if(!type.equals("shanchu"))
			request.getRequestDispatcher("/pages/marketing/tccx/tccx-xiangxi.jsp").forward(request, response);
			else {
				   out.write("shanchu");
					out.flush();
					out.close();
			}
		}else if(subType.equals("addactive")){
			String ifsave = request.getParameter("ifsave");
			if(ifsave.equals("no")){
				request.getRequestDispatcher("/pages/marketing/tccx/tccx-tianjia.jsp").forward(request, response);
			}	
		}else if(subType.equals("tanchuceng")){
			String tc_type = request.getParameter("tc_type");	
			String str = mTccxService.progressTCType(tc_type,request);
		   out.write(str);
			out.flush();
			out.close();
			}else if("table".equals(subType)){
				String str = mTccxService.fysearch(request);
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
