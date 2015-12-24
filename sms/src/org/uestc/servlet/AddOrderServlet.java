package org.uestc.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.uestc.util.SqlHelper;
import org.uestc.util.Utils;

import jdk.nashorn.internal.parser.JSONParser;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

@WebServlet(urlPatterns = { "/addOrder" }, description = "新增订单")
public class AddOrderServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4057535354424994136L;

	// 查看订单
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String m = req.getParameter("m");
		// 查看所有订单，分页查询
		if ("all".equals(m)) {
			// this.getAll(req,resp);
			req.getRequestDispatcher("/www/add_order.jsp").forward(req, resp);
		} else if ("initGood".equals(m)) {
			this.initGood(resp, 1);
		} else if ("initTable".equals(m)) {
			this.initTable(req, resp);
			req.getRequestDispatcher("/www/content.jsp").forward(req, resp);
		} else if ("addOrderRemote".equals(m)) {
			req.getRequestDispatcher("/www/add_order_from_remote.jsp").forward(req, resp);
		}
	}

	private void getGoodsByCategory(HttpServletRequest req, HttpServletResponse resp) {
		String c = req.getParameter("c");

		String pageNum = req.getParameter("number");

		if (null == pageNum || null == c) {
			return;
		}

		try {
			this.initGoodByWhere(resp, c, Integer.parseInt(pageNum));
		} catch (NumberFormatException e) {

			e.printStackTrace();
		}
	}

	public void initGoodByWhere(HttpServletResponse resp, String category, int pageNum) {
		boolean flag = false;
		if (null != category)
			flag = true;
		// System.out.println(pageNum);
		List<Map> list = null;
		if (null == category)
			list = SqlHelper.findAll("SELECT * FROM good_base WHERE s_del=1 LIMIT ?,?", (pageNum - 1) * 10, 10);
		else
			list = SqlHelper.findAll("SELECT * FROM good_base WHERE s_del=1 AND gcategory=? LIMIT ?,?", category,
					(pageNum - 1) * 10, 10);

		// JSONArray root=new JSONArray();

		JSONObject root = new JSONObject();
		root.accumulate("total", this.getCount(category));

		JSONArray rows = new JSONArray();
		if (flag) {
			for (int i = 0; i < list.size(); i++) {
				JSONObject item = new JSONObject();
				// System.out.println(list.get(i));
				item.accumulate("itemid", list.get(i).get("gno"));
				item.accumulate("itemname", list.get(i).get("gname"));
				item.accumulate("itemprice1", list.get(i).get("gprice1"));
				item.accumulate("itemprice2", list.get(i).get("gprice2"));
				item.accumulate("itemcategory", list.get(i).get("gcategory"));
				item.accumulate("itemnum", 1);
				// System.out.println(item.toString());
				rows.put(item);
			}
		} else {
			for (int i = 0; i < list.size(); i++) {
				JSONObject item = new JSONObject();
				// System.out.println(list.get(i));
				item.accumulate("itemid", list.get(i).get("gno"));
				item.accumulate("itemname", list.get(i).get("gname"));
				item.accumulate("itemprice1", list.get(i).get("gprice1"));
				item.accumulate("itemprice2", list.get(i).get("gprice2"));
				item.accumulate("itemcategory", list.get(i).get("gcategory"));
				// item.accumulate("itemnum", 1);
				// System.out.println(item.toString());
				rows.put(item);
			}
		}
		// System.out.println(rows.toString());
		root.accumulate("rows", rows.toString());

		// System.out.println(root.toString());

		try {
			resp.setCharacterEncoding("utf-8");
			resp.getWriter().write(root.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initGood(HttpServletResponse resp, int pageNum) {
		// String sql = "SELECT * FROM good_base WHERE s_del=1 LIMIT ?,?";
		initGoodByWhere(resp, null, pageNum);
	}

	@SuppressWarnings("unchecked")
	public int getCount(String c) {
		// String sql = "SELECT COUNT(id) FROM good_base WHERE s_del=1";
		List<Object[]> list = null;
		if (null == c)
			list = SqlHelper.executeQuery("SELECT COUNT(id) FROM good_base WHERE s_del=1", null);
		else
			list = SqlHelper.executeQuery("SELECT COUNT(id) FROM good_base WHERE s_del=1 AND gcategory=?",
					new String[] { c });

		if (null != list && 1 == list.size()) {
			return Integer.valueOf(list.get(0)[0].toString());
		}

		return 0;
	}

	private void initTable(HttpServletRequest req, HttpServletResponse resp) {

		String pageNo = req.getParameter("pageNo");// 当前页
		String store = req.getParameter("store");
		if (null == pageNo || "".equals(pageNo))
			pageNo = "1";

		if (null == store || "".equals(store))
			return;

		String sql = "SELECT t1.b_no,t2.s_name,t1.b_date,t1.b_status FROM booking t1,store t2 WHERE t1.s_id=t2.s_id AND t1.s_del=1 AND t1.s_id=? limit ?,?";
		@SuppressWarnings("rawtypes")
		ArrayList<Map> list = SqlHelper.findAll(sql, store, (Integer.parseInt(pageNo) - 1) * 10, 10);
		if (list.isEmpty()) {
			list = new ArrayList<>();
			HashMap<Object, Object> map = new HashMap<>();
			map.put("b_no", "无");
			map.put("s_name", "无");
			map.put("b_date", System.currentTimeMillis());
			map.put("b_status", "无");
			list.add(map);
		}

		req.setAttribute("pageNo", 1);
		req.setAttribute("totalSum", getBooKingCount(store));
		req.setAttribute("list", list);
	}

	public int getBooKingCount(String store) {
		String sql = "SELECT count(t1.b_no) FROM booking t1,store t2 WHERE t1.s_id=t2.s_id AND t1.s_del=1 AND t1.s_id=?";
		List<Object[]> list = SqlHelper.executeQuery(sql, new String[] { store });
		if (!list.isEmpty()) {
			return Integer.parseInt(list.get(0)[0].toString());
		}
		return 0;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if ("add".equals(req.getParameter("m"))) {
			this.add(req, resp);
		} else if ("initGood".equals(req.getParameter("m"))) {
			this.initGood(resp, 1);
		} else if ("findByPage".equals(req.getParameter("m"))) {
			this.findByPage(req, resp);
		} else if ("PUT".equals(req.getParameter("m"))) {
			this.put(req, resp);
		} else if ("del".equals(req.getParameter("m"))) {
			this.del(req, resp);
		} else if ("addGoods".equals(req.getParameter("m"))) {
			this.addGoods(req, resp);
		} else if ("getGoodsByCategory".equals(req.getParameter("m"))) {
			this.getGoodsByCategory(req, resp);
		}
	}



	private void addGoods(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		try {
			String gno = req.getParameter("gno");
			String gname = req.getParameter("gname");
			String gprice1 = req.getParameter("gprice1");
			String gprice2 = req.getParameter("gprice2");
			String gcategory = req.getParameter("gcategory");
			String sql = "insert into good_base(gno,gname,gprice1,gprice2,gcategory,gscale) value(?,?,?,?,?,?)";

			SqlHelper.executeUpdate(sql, new String[] { gno, gname, gprice1, gprice2, gcategory, "无" });
			org.json.JSONObject ret = new org.json.JSONObject();
			ret.accumulate("msg", "ok");
			resp.getWriter().write(ret.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void del(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String ids = req.getParameter("itemids").substring(0, req.getParameter("itemids").length() - 1);
			String sql = "delete from good_base where gno in (?)";
			SqlHelper.executeUpdate(sql, new String[] { ids });

			org.json.JSONObject ret = new org.json.JSONObject();
			ret.accumulate("msg", "ok");
			resp.getWriter().write(ret.toString());
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void put(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String gno = req.getParameter("gno");
			String gname = req.getParameter("gname");
			String gprice1 = req.getParameter("gprice1");
			String gprice2 = req.getParameter("gprice2");
			String gcategory = req.getParameter("gcategory");
			String sql = "update good_base set gname=?,gprice1=?,gprice2=?,gcategory=? where gno=?";

			SqlHelper.executeUpdate(sql, new String[] { gname, gprice1, gprice2, gcategory, gno });
			org.json.JSONObject ret = new org.json.JSONObject();
			ret.accumulate("msg", "ok");
			resp.getWriter().write(ret.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void findByPage(HttpServletRequest req, HttpServletResponse resp) {

		String pageNum = req.getParameter("number");

		if (null == pageNum) {
			return;
		}

		try {
			this.initGood(resp, Integer.parseInt(pageNum));
		} catch (NumberFormatException e) {

			e.printStackTrace();
		}
	}

	// 更新或者创建订单
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	// 删除订单
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@SuppressWarnings("unused")
	private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String ids = req.getParameter("ids");
		String nums = req.getParameter("nums");
		String store = req.getParameter("store");

		if (ids.length() == 0) {
			JSONObject object = new JSONObject();
			object.accumulate("flag", "error");
			resp.getWriter().write(object.toString());
			return;
		}

		if (null == store)
			return;

		String[] idArray = ids.split(",");
		String[] numArray = nums.split(",");

		Map<String, Object> map = new HashMap<>();

		for (int i = 0; i < idArray.length; i++) {
			if (!map.containsKey(idArray[i])) {
				map.put(idArray[i], numArray[i]);
			} else {
				int oldNum = Integer.valueOf(map.get(idArray[i]).toString());
				int addNum = Integer.valueOf(numArray[i].toString());
				map.remove(idArray[i]);
				map.put(idArray[i], oldNum + addNum);
			}
		}

		Set<Entry<String, Object>> keySet = map.entrySet();

		Iterator<Entry<String, Object>> it = keySet.iterator();
		String ids1 = "";
		String nums1 = "";
		String info = "";
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			ids1 += entry.getKey() + ";";
			nums1 += entry.getValue() + ";";
			info += "无;";
		}

		String sql = "insert into booking(b_no,g_id,b_num,s_id,b_status,b_info,b_date,s_del) VALUE(?,?,?,?,?,?,?,?)";
		SqlHelper.executeUpdate(sql, new String[] { Utils.generateOrderNo(), ids1, nums1, store, "待审核", info,
				System.currentTimeMillis() + "", "1" });
		JSONObject object = new JSONObject();
		object.accumulate("flag", "ok");
		resp.getWriter().write(object.toString());
	}

	public static void main(String[] args) {
		JSONArray array = new JSONArray();
		for (int i = 0; i < 100; i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("numId", i + 1);
			jsonObject.accumulate("numValue", i + 1);
			array.put(jsonObject);
		}
		
		System.out.println(array.toString());
	}
}
