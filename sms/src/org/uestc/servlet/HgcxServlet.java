package org.uestc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.service.MarketingHgcxService;
import org.uestc.serviceImp.MarketingHgcxServiceImp;

/**
 * Servlet implementation class HgcxServlet
 */
@WebServlet("/HgcxServlet")
public class HgcxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HgcxServlet() {
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
		MarketingHgcxService mHgcxService = new MarketingHgcxServiceImp();
		if(subType==null||subType.equals("page_init")){
			mHgcxService.getPageInit(request, response);
			//response.sendRedirect("pages/marketing/hgcx/hgcx.jsp");
			if(request.getParameter("copy")!=null&&request.getParameter("copy").equals("yes")){
				request.setAttribute("copy", "yes");
			}
			request.getRequestDispatcher("/pages/marketing/hgcx/hgcx.jsp").forward(request, response);
		}else if(subType.equals("XGactive")){
			String type =mHgcxService.operateProgress(request, response);
			if(!type.equals("shanchu"))
			request.getRequestDispatcher("/pages/marketing/hgcx/hgcx-xiangxi.jsp").forward(request, response);
			else {PrintWriter out = response.getWriter();
				   out.write("shanchu");
					out.flush();
					out.close();
			}
		}else if(subType.equals("addactive")){
			String ifsave = request.getParameter("ifsave");
			if(ifsave.equals("no")){
				request.getRequestDispatcher("/pages/marketing/hgcx/hgcx-tianjia.jsp").forward(request, response);
			}	
		}else if(subType.equals("tanchuceng")){
			String tc_type = request.getParameter("tc_type");	
			String str = mHgcxService.progressTCType(tc_type,request);
			PrintWriter out = response.getWriter();
			out.write(str);
			out.flush();
			out.close();
			}else if("table".equals(subType)){
				String str = mHgcxService.fysearch(request);
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
