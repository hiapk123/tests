package org.uestc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.service.MarketingDejdzService;
import org.uestc.service.MarketingDzcxService;
import org.uestc.serviceImp.MarketingDejdzServiceImp;
import org.uestc.serviceImp.MarketingDzcxServiceImp;

/**
 * Servlet implementation class DEJTJServlet
 */
@WebServlet("/DzcxServlet")
public class DzcxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DzcxServlet() {
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
		System.out.println("DejdzServlet : doget()");
		System.out.println("subType = "+subType);
		MarketingDzcxService mDzcxService = new MarketingDzcxServiceImp();
		if(subType==null||subType.equals("page_init")){
			mDzcxService.getPageInit(request, response);
			if(request.getParameter("copy")!=null&&request.getParameter("copy").equals("yes")){
				request.setAttribute("copy", "yes");
			}
			request.getRequestDispatcher("/pages/marketing/dzcx/dzcx.jsp").forward(request, response);
		}else if(subType.equals("XGactive")){
			String type =mDzcxService.operateProgress(request, response);
			if(!type.equals("shanchu"))
			request.getRequestDispatcher("/pages/marketing/dzcx/dzcx-xiangxi.jsp").forward(request, response);
			else {PrintWriter out = response.getWriter();
				   out.write("shanchu");
					out.flush();
					out.close();
			}
		}else if(subType.equals("addactive")){
			String ifsave = request.getParameter("ifsave");
			if(ifsave.equals("no")){
				request.getRequestDispatcher("/pages/marketing/dzcx/dzcx-tianjia.jsp").forward(request, response);
			}	
		}else if(subType.equals("tanchuceng")){
			String tc_type = request.getParameter("tc_type");
			PrintWriter out = response.getWriter();
			String str = mDzcxService.progressTCType(tc_type,request);
		   out.write(str);
			out.flush();
			out.close();
		}else if("table".equals(subType)){
			PrintWriter out = response.getWriter();
			String str = mDzcxService.fysearch(request);
		   out.write(str);
			out.flush();
			out.close();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}



}
