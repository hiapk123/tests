package org.uestc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(
	urlPatterns = { "/login" }, 
	name = "loginServlet" 
)
public class LoginServlet extends HttpServlet {
	 
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			HttpSession session=request.getSession();
			
			if(null!=session.getAttribute("sessionUser")){
				session.invalidate();
			}
			
			request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*String uname = request.getParameter("uname");//获取用户名
		String passwd = request.getParameter("passwd");//获取用户密码
*/		
		String path = null;

		try{
			request.getSession().setAttribute("uid", 1);
			path="index.jsp";
		}catch(Exception e){
			e.printStackTrace();
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

}
