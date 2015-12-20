package com.uestc.filter;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uestc.bean.Users;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(urlPatterns = { "/*" })
public class LoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		Users user = (Users) req.getSession().getAttribute("sessionUser");
		// System.out.println(req.getContextPath());
		// String
		// redirect=req.getRemoteHost()+":"+req.getRemotePort()+""+req.getContextPath()+"/login.jsp";

		String path = req.getRequestURI();
		// System.out.println(path);

		if (path.contains(".js") || path.contains(".css") || path.contains(".jpg") || path.contains(".png")
				|| path.contains(".ico")||path.contains(".gif")) {
			chain.doFilter(request, response);
			return;
		}

		// 登陆页面无需过滤
		if (path.contains("UserServlet") || path.contains("login.jsp")) {
			chain.doFilter(request, response);
			return;
		}

		// System.out.println(path);
		// System.out.println(req.getRequestURL().toString());
		if (user == null) {
			req.getRequestDispatcher("/error.jsp").forward(req, response);
			/*System.out.println(req.get);
			HttpServletResponse resp = (HttpServletResponse) response;
			//String ip = InetAddress.getLocalHost().getHostAddress();
			String redirect = "/login.html";
			resp.sendRedirect(redirect);*/

		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
