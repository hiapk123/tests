package org.uestc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONArray;
import org.uestc.util.SqlHelper;

import net.sf.json.JSONObject;

@WebServlet(urlPatterns = { "/login" }, name = "loginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (null != session.getAttribute("sessionUser")) {
			session.removeAttribute("sessionUser");
		}

		request.getRequestDispatcher("login.html").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String m = request.getParameter("m");
		if ("findUserByPage".equals(m)) {
			this.findUserByPage(request, response);
		} else if ("addUser".equals(m)) {
			this.addUser(request, response);
		} else if ("del".equals(m)) {
			this.doDelete(request, response);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JSONObject result = new JSONObject();
		try {
			String name = req.getParameter("name");
			String type = req.getParameter("type");
			if (!"管理员".equals(type)) {
				SqlHelper.executeUpdate("update users set s_del=0 where u_name=?", new String[] { name });

				result.accumulate("success", "ok");
				resp.getWriter().write(result.toString());
			}else{
				result.accumulate("success", "ok2");
				resp.getWriter().write(result.toString());
			}
		} catch (Exception e) {

			result.accumulate("success", "error");
			resp.getWriter().write(result.toString());
		}
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");

		if (null != password && null != password2 && password.trim().equals(password.trim()) && null != name
				&& null != sex && null != tel && null != age && null != email) {
			String sql = "INSERT INTO users(u_name,u_type,u_email,u_sex,u_phone,u_password,u_age) VALUE(?,?,?,?,?,?,?)";
			SqlHelper.executeUpdate(sql,
					new String[] { name, "2", email, sex, tel, DigestUtils.md5Hex(password), age });
			try {
				JSONObject object = new JSONObject();
				object.accumulate("status", "ok");
				response.getWriter().write(object.toString());
				response.flushBuffer();
			} catch (IOException e) {

			}
		} else {
			try {
				JSONObject object = new JSONObject();
				object.accumulate("status", "error");
				response.getWriter().write(object.toString());
				response.flushBuffer();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public void findUserByPage(HttpServletRequest request, HttpServletResponse response) {
		String pageNumber = request.getParameter("number");
		if (null == pageNumber) {
			pageNumber = "1";
		}
		String sql = "SELECT u_name,u_phone,u_sex,u_email,u_type FROM users WHERE s_del=1 limit ?,?";
		ArrayList<Map> list = SqlHelper.findAll(sql, 10 * (Integer.valueOf(pageNumber) - 1), 10);

		JSONArray root = new JSONArray();
		JSONObject data = new JSONObject();
		data.accumulate("total", this.getCount());

		for (int i = 0; i < list.size(); i++) {
			JSONObject child = new JSONObject();
			child.accumulate("username", list.get(i).get("u_name"));
			child.accumulate("phone", list.get(i).get("u_phone"));
			child.accumulate("email", list.get(i).get("u_email"));
			String type = list.get(i).get("u_type").toString();
			if ("1".equals(type)) {
				child.accumulate("type", "管理员");
			} else {
				child.accumulate("type", "普通店主");
			}

			child.accumulate("status", "P");

			root.put(child);
		}

		data.accumulate("rows", root.toString());

		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().write(data.toString());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public int getCount() {
		String sql = "SELECT COUNT(u_id) FROM users WHERE s_del=1";
		ArrayList<Object[]> list = SqlHelper.executeQuery(sql, new String[] {});

		if (null == list || list.size() == 1) {
			return Integer.valueOf(list.get(0)[0].toString());
		}

		return 0;
	}
}
