package org.uestc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.uestc.util.SqlHelper;

import com.uestc.bean.Users;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet(urlPatterns = "/initTree")
public class InitTree extends HttpServlet {
	private static int curPage = 1;// 当前页；
	private int pageSize = 5;// 每页显示5条

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String m = req.getParameter("m");
		String result = "";
		if ("initS".equals(m)) {
			result = getStore(req.getSession());
		} else if ("initT".equals(m)) {
			result = getTree(req.getParameter("id"));
		} else if ("initG".equals(m)) {
			result = getGoods(req, req.getParameter("id"), req.getParameter("sid"));
		} else if ("findByPage".equals(m)) {
			result = findByPage(req);
		}else if("getTreeData".equals(m)){
			this.getTreeData(req,resp);
			return;
		}
		resp.setCharacterEncoding("utf-8");
		if (null != result && !"".equals(result))
			resp.getWriter().write(result);
		else
			resp.getWriter().write("{\"flag\":\"0\"}");

	}

	@SuppressWarnings("unchecked")
	private void getTreeData(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String sql="SELECT DISTINCT(gcategory) FROM good_base";
			
			List<Object[]> list=SqlHelper.executeQuery(sql, null);
			org.json.JSONArray root =new org.json.JSONArray();
			
			if(list!=null){
				for (int i = 0; i < list.size(); i++) {
					org.json.JSONObject node=new org.json.JSONObject();
					node.accumulate("id", i+1);
					node.accumulate("text", list.get(i)[0]);
					node.accumulate("state", "open");
					root.put(node);
				}
			}else{
				org.json.JSONObject node=new org.json.JSONObject();
				node.accumulate("id",1);
				node.accumulate("text", "无数据");
				node.accumulate("state", "open");
				root.put(node);
			}
			//System.out.println(root.toString());
			resp.setCharacterEncoding("utf-8");
			resp.getWriter().write(root.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private String findByPage(HttpServletRequest req) {
		String sid = req.getParameter("sid");
		String which = req.getParameter("which");
		String result = "";
		if (null == sid || "".equals(sid)) {
			return null;
		}

		switch (which) {
		case "first":
			curPage = 1;
			result = this.getGoods(req, req.getParameter("id"), req.getParameter("sid"));
			break;
		case "next":
			curPage++;
			result = this.getGoods(req, req.getParameter("id"), req.getParameter("sid"));
			break;
		case "prev":
			curPage--;
			result = this.getGoods(req, req.getParameter("id"), req.getParameter("sid"));
			break;
		case "last":
			curPage = -1;
			result = this.getGoods(req, req.getParameter("id"), req.getParameter("sid"));
			break;
		default:
			break;
		}

		return result;
	}

	private String getGoods(HttpServletRequest req, String id, String sid) {
		if ("0".equals(sid) || null == sid) {
			return "";
		}
		// 获取总记录个数
		String countSQL = "SELECT count(g_barcode) FROM goods WHERE c_id=? AND g_del=1 AND s_id=?";
		@SuppressWarnings("unchecked")
		List<Object[]> sizeList = SqlHelper.executeQuery(countSQL, new String[] { id, sid });
		int size = 0;
		int pageNo = 0;
		if (sizeList != null && sizeList.size() == 1) {
			if (sizeList.get(0)[0] != null) {
				size = Integer.valueOf(sizeList.get(0)[0].toString());
			} else {
				return null;
			}
		}

		if (curPage < 1) {
			if (curPage == -1) {
				curPage = size / pageSize + 1;
			} else
				curPage = 1;
		}

		if (curPage > size / pageSize + 1) {
			curPage = size / pageSize + 1;
		}
		pageNo = curPage;

		JSONArray page = new JSONArray();
		JSONObject p = new JSONObject();
		p.accumulate("totalSum", size / pageSize + 1);
		p.accumulate("pageNo", pageNo);

		page.add(p);

		String sql = "SELECT g_name,g_barcode,g_sale_price,g_prod_date FROM goods WHERE c_id=? AND g_del=1 AND s_id=? limit ?,?";
		ArrayList<Map> list = SqlHelper.findAll(sql, id, sid, (pageNo - 1) * pageSize, pageSize);
		JSONArray root = new JSONArray();
		if (list.isEmpty()) {
			return null;
		}
		for (int i = 0; i < list.size(); i++) {
			JSONObject child = new JSONObject();
			child.accumulate("name", list.get(i).get("g_name"));
			child.accumulate("barcode", list.get(i).get("g_barcode"));
			child.accumulate("price", list.get(i).get("g_sale_price"));
			child.accumulate("date", list.get(i).get("g_prod_date"));

			root.add(child);
		}

		root.add(p);
		return root.toString();
	}

	private String getStore(HttpSession session) {
		// 测试
		Users user = (Users) session.getAttribute("user");

		String sql = "SELECT s_id,s_name FROM store WHERE s_del=1 AND s_flag=1 AND u_id=?";
		/*
		if (null!=user.getUType()&&user.getUType() == 1) {
			sql = "SELECT s_id,s_name FROM store WHERE s_del=1 AND s_flag=1 and 1!=?";
		}*/

		@SuppressWarnings("rawtypes")
		ArrayList<Map> list = SqlHelper.findAll(sql, session.getAttribute("uid"));// 测试，设定u_id为1
		JSONArray array = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			JSONObject object = new JSONObject();
			object.accumulate("id", list.get(i).get("s_id"));
			object.accumulate("name", list.get(i).get("s_name"));
			array.add(object);
		}

		return array.toString();
	}

	private String getTree(String sid) {
		String sql = "SELECT c_id,c_name,c_parent_id FROM category where s_del=1 and s_id=? order by c_id asc";
		// System.out.println(sql);
		@SuppressWarnings({ "rawtypes" })
		ArrayList<Map> c = SqlHelper.findAll(sql, sid);

		JSONArray root = new JSONArray();

		for (int i = 0; i < c.size(); i++) {
			JSONObject node = new JSONObject();
			node.accumulate("id", c.get(i).get("c_id"));
			node.accumulate("pId", c.get(i).get("c_parent_id"));
			node.accumulate("name", c.get(i).get("c_name"));
			node.accumulate("t", c.get(i).get("c_name"));
			node.accumulate("open", true);
			root.add(node);
		}

		return root.toString();
	}

	@SuppressWarnings("unused")
	private int getCurrentPage(int currentPage, String name, int totalPage) {
		int cur = currentPage;
		if (cur <= 1) {
			return 1;
		}

		switch (name) {
		case "first":
			cur = 1;
			break;
		case "prev":
			cur = currentPage - 1;
			break;
		case "next":
			cur = currentPage + 1;
		case "last":
			cur = totalPage;
			break;
		default:
			break;
		}

		return cur;
	}
}
