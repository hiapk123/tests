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
 * Servlet implementation class DEJTJServlet
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
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		
		String subType =  request.getParameter("subType");
		System.out.println("DzytjServlet : doget()");
		System.out.println("subType = "+subType);
		MarketingDzytjService mDzytjService = new MarketingDzytjServiceImp();
		if(subType==null||subType.equals("page_init")){
			mDzytjService.getPageInit(request, response);
			if(request.getParameter("copy")!=null&&request.getParameter("copy").equals("yes")){
				request.setAttribute("copy", "yes");
			}
			request.setAttribute("page", "first");
			request.getRequestDispatcher("/pages/marketing/dzytj/dzytj.jsp").forward(request, response);
		}else if(subType.equals("XGactive")){
			String type =mDzytjService.operateProgress(request, response);
			if(!type.equals("shanchu"))
			request.getRequestDispatcher("/pages/marketing/dzytj/dzytj-xiangxi.jsp").forward(request, response);
			else {PrintWriter out = response.getWriter();
				   out.write("shanchu");
					out.flush();
					out.close();
			}
		}else if(subType.equals("addactive")){
			String ifsave = request.getParameter("ifsave");
			if(ifsave.equals("no")){
				request.getRequestDispatcher("/pages/marketing/dzytj/dzytj-tianjia.jsp").forward(request, response);
			}	
		}else if(subType.equals("tanchuceng")){
			String tc_type = request.getParameter("tc_type");
			PrintWriter out = response.getWriter();
			String str = mDzytjService.progressTCType(tc_type,request);
		   out.write(str);
			out.flush();
			out.close();
		}else if("table".equals(subType)){
			String s = mDzytjService.fysearch(request);
			PrintWriter out = response.getWriter();
			out.write(s);
			out.flush();
			out.close();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}



}
