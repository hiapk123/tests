package org.uestc.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.uestc.service.UserService;
import org.uestc.serviceImp.UserServiceImp;

import com.uestc.bean.Users;

import cn.itcast.servlet.BaseServlet;

@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImp();

	public String quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		return "f:/login.jsp";
	}

	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.封装参数
		 */
		String loginname = request.getParameter("loginname");
		String loginpass = request.getParameter("loginpass");

		Users formUser = new Users();
		formUser.setUName(loginname);
		formUser.setUPassword(loginpass);

		/*
		 * 2.校验表单数据
		 */
		Map<String, String> errors = validateLogin(formUser);
		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			request.setAttribute("formUser", formUser);
			return "f:/login.jsp";
		}

		/*
		 * 3.调用service层的方法
		 */
		Users user = userService.login(formUser);

		if (user == null) {
			request.setAttribute("msg", "用户名或密码错误");
			request.setAttribute("formUser", formUser);
			return "f:/login.jsp";
		} else {
			request.getSession().setAttribute("sessionUser", user); // 保存用户到session

			// 获取用户名保存到cookie中
			// String loginname = user.getUName();
			loginname = URLEncoder.encode(loginname, "utf-8");
			Cookie cookie = new Cookie("loginname", loginname);
			cookie.setMaxAge(60 * 60 * 24 * 10); // 保存10天
			response.addCookie(cookie);
			return "r:/index.jsp"; // 重定向到主页
		}
	}

	private Map<String, String> validateLogin(Users formUser) {

		Map<String, String> errors = new HashMap<String, String>();

		/*
		 * 用户名校验
		 */
		String loginname = formUser.getUName();
		if (loginname == null || loginname.trim().isEmpty()) {
			errors.put("loginname", "用户名不能为空");
		} else if (loginname.length() < 3 || loginname.length() > 20) {
			errors.put("loginname", "用户名长度必须在3~20之间");
		}
		/*
		 * 密码校验
		 */
		String loginpass = formUser.getUPassword();
		if (loginpass == null || loginpass.trim().isEmpty()) {
			errors.put("loginpass", "密码不能为空");
		} else if (loginpass.length() < 3 || loginpass.length() > 20) {
			errors.put("loginpass", "密码长度必须在3~20之间");
		}
		return errors;
	}

	public String regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserServlet.regist()");
		/*
		 * 1.获取参数，设置给user对象
		 */
		String loginname = request.getParameter("loginname");
		String loginpass = request.getParameter("loginpass");
		String reloginpass = request.getParameter("reloginpass");
		String email = request.getParameter("email");
		Users formUser = new Users();
		formUser.setUName(loginname);
//		String md5Digest = DigestUtils.md5Hex(loginpass);
//		formUser.setUPassword(md5Digest);
		formUser.setUPassword(loginpass);
		formUser.setReloginpass(reloginpass);
		formUser.setUEmail(email);

		/*
		 * 2.校验参数
		 */
		Map<String, String> errors = validateRegist(formUser);
		if (errors.size() > 0) {
			request.setAttribute("errors", errors); // 显示错误信息
			request.setAttribute("formUser", formUser); // 回显表单信息
			return "f:/regist.jsp";
		}

		/*
		 * 3.调用service层方法
		 */
		userService.regist(formUser);

		return "f:/login.jsp"; // 转发到登录页面
	}

	private Map<String, String> validateRegist(Users formUser) {

		Map<String, String> errors = new HashMap<String, String>();

		/*
		 * 用户名校验
		 */
		String loginname = formUser.getUName();
		if (loginname == null || loginname.trim().isEmpty()) {
			errors.put("loginname", "用户名不能为空");
		} else if (loginname.length() < 3 || loginname.length() > 20) {
			errors.put("loginname", "用户名长度必须在3~20之间");
		} else if (!userService.ajaxValidateLoginname(loginname)) {
			errors.put("loginname", "用户名已被注册");
		}
		/*
		 * 密码校验
		 */
		String loginpass = formUser.getUPassword();
		if (loginpass == null || loginpass.trim().isEmpty()) {
			errors.put("loginpass", "密码不能为空");
		} else if (loginpass.length() < 3 || loginpass.length() > 20) {
			errors.put("loginpass", "密码长度必须在3~20之间");
		}
		/*
		 * 确认密码校验
		 */
		String reloginpass = formUser.getReloginpass();
		if (reloginpass == null || reloginpass.trim().isEmpty()) {
			errors.put("reloginpass", "确认密码不能为空");
		} else if (!reloginpass.equals(loginpass)) {
			errors.put("reloginpass", "两次输入不一致");
		}
		/*
		 * Email校验
		 */
		String email = formUser.getUEmail();
		if (email == null || email.trim().isEmpty()) {
			errors.put("email", "Email不能为空");
		} else if (!email.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$")) {
			errors.put("email", "Email格式错误");
		}

		return errors;
	}

	public String ajaxValidateLoginname(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginname = request.getParameter("loginname");
		boolean b = userService.ajaxValidateLoginname(loginname);
		response.getWriter().print(b); // 发送给客户端
		return null;

	}

	public String ajaxValidateEmail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		boolean b = userService.ajaxValidateEmail(email);
		response.getWriter().print(b);
		return null;
	}
}
