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
		
		//("MarketingServlet-------doGet()");
		
	//	response.setCharacterEncoding("GBK");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		//response.getWriter();
		//response.setHeader("Content-Type", "text/html;charset=UTF-8");
		//
		String encodeType = response.getCharacterEncoding();
		//(encodeType);
		RequestDispatcher rd ;
		String type  = request.getParameter("type");
		//("MarketingServlet---doGet()---type:"+type);
		switch (type) {
		case "dzytj":
			dzytj(request,response);
			
			break;
		case "dejdz":
			dejdz(request, response);
			break;
		case "dzcx":
			dzcx(request, response);
			break;
		case "hgcx":
			hgcx(request,response);
			break;
		case "tccx":
			tccx(request,response);
			break;
		case "mefx":
			mefx(request,response);
			break;
		case "cxspb":
			cxspb(request,response);
			break;
		case "lscxb":
			lscxb(request,response);
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
		//("MarketingServlet-------doPost()");
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
	protected void dejdz(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/DejdzServlet").forward(req, res);
	}
	protected void dzcx(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/DzcxServlet").forward(req, res);
	}
	protected void hgcx(HttpServletRequest req , HttpServletResponse resp) throws ServletException,IOException{
		req.getRequestDispatcher("/HgcxServlet").forward(req, resp);
	}
	protected void tccx(HttpServletRequest req , HttpServletResponse resp) throws ServletException,IOException{
		req.getRequestDispatcher("/TccxServlet").forward(req, resp);
	}protected void mefx(HttpServletRequest req , HttpServletResponse resp) throws ServletException,IOException{
		req.getRequestDispatcher("/MefxServlet").forward(req, resp);
	}
	protected void cxspb(HttpServletRequest req , HttpServletResponse resp) throws ServletException,IOException{
		req.getRequestDispatcher("/CxspbServlet").forward(req, resp);
	}protected void lscxb(HttpServletRequest req , HttpServletResponse resp) throws ServletException,IOException{
		req.getRequestDispatcher("/LscxbServlet").forward(req, resp);
	}
}
