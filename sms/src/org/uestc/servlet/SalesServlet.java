package org.uestc.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.uestc.service.SalesService;
import org.uestc.serviceImp.SalesServiceImp;
import org.uestc.util.DateFormatUtils;

import com.uestc.bean.ShiftChange;

@WebServlet(urlPatterns = { "/sales" })
public class SalesServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6005017459312388968L;

	private SalesService sale = new SalesServiceImp();// ������

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String m = req.getParameter("m");
		if (m.equals("xiaoshou")) {
			this.xiaoShou(req, resp);
			req.getRequestDispatcher("/pages/sales/saleinfo/xiaoshou.jsp").forward(req, resp);
		} else if (m.equals("salesinfo")) {
			this.salesInfo(req, resp);
			req.getRequestDispatcher("/pages/sales/sales-info.jsp").forward(req, resp);
		} else if (m.equals("jiaoban")) {
			this.jiaoBan(req, resp);
			req.getRequestDispatcher("/pages/sales/saleinfo/jiaoban.jsp").forward(req, resp);
		} else if (m.equals("cash")) {
			this.cash(req, resp);
			req.getRequestDispatcher("/pages/sales/saleinfo/cash.jsp").forward(req, resp);
		}

	}
	/**
	 * 现金收支明细
	 * @param req
	 * @param resp
	 */
	private void cash(HttpServletRequest req, HttpServletResponse resp) {
		RequestHelper reqHelper=new RequestHelper(req);
		List<Object[]> list=sale.findCash(reqHelper.sid, reqHelper.start, reqHelper.end);
		req.setAttribute("cashList", list);
	}
	/***
	 * 交接班记录
	 * @param req
	 * @param resp
	 */
	private void jiaoBan(HttpServletRequest req, HttpServletResponse resp) {
		RequestHelper struct=new RequestHelper(req);
		List<ShiftChange> shList=sale.findShiftChange(struct.sid, struct.start, struct.end);
		req.setAttribute("shList", shList);
	}

	private void salesInfo(HttpServletRequest req, HttpServletResponse resp) {
		List<Object[]> storeList = null;
		HttpSession session = req.getSession();
		try {
			storeList = sale.findStoreByUserID(Integer.valueOf(session.getAttribute("uid").toString()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} finally {
			if (storeList == null)
				return;
			req.setAttribute("storeList", storeList);
		}

	}
	/***
	 * 销售汇总
	 * @param req
	 * @param resp
	 */
	private void xiaoShou(HttpServletRequest req, HttpServletResponse resp) {
		RequestHelper struct = new RequestHelper(req);
		List<Object[]> list = sale.getXiaoshouAndLiRun(struct.sid, struct.start, struct.end);
		double xiaoshou = 0.0;//
		double lirun = 0.0;//
		double shouru = 0.0;//
		double zhichu = 0.0;
		if (list != null && list.size() == 1) {
			Object[] obj = list.get(0);

			try {
				xiaoshou = (double) obj[0];
				lirun = (double) obj[1];
				shouru = (double) obj[2];
				zhichu = (double) obj[3];
			} catch (Exception e) {
				// pass
			}

		}
		list.clear();
		double cash = 0.0;//
		double bank = 0.0;//
		double online = 0.0;//
		list = sale.get(struct.sid, struct.start, struct.end, 1);
		if (list != null && list.size() == 1) {
			Object[] obj = list.get(0);
			if (obj[0] != null)
				cash = (double) obj[0];
		}
		list.clear();
		list = sale.get(struct.sid, struct.start, struct.end, 2);
		if (list != null && list.size() == 1) {
			Object[] obj = list.get(0);
			if (obj[0] != null)
				bank = (double) obj[0];
		}
		list.clear();
		list = sale.get(struct.sid, struct.start, struct.end, 3);
		if (list != null && list.size() == 1) {
			Object[] obj = list.get(0);
			if (obj[0] == null)
				return;
			online = (double) obj[0];
		}
		list.clear();
		double vipCash = 0.0;//
		double vipBank = 0.0;
		double vipOnline = 0.0;//
		list = sale.getVip(struct.sid, struct.start, struct.end, 1);
		if (list != null && list.size() == 1) {
			Object[] obj = list.get(0);
			if (obj[0] == null) {
				vipCash = 0.0;
			} else
				vipCash = (double) obj[0];
		}
		list.clear();
		list = sale.getVip(struct.sid, struct.start, struct.end, 2);
		if (list != null && list.size() == 1) {
			Object[] obj = list.get(0);
			if (obj[0] == null) {
				vipBank = 0.0;
			} else
				vipBank = (double) obj[0];
		}
		list.clear();
		list = sale.getVip(struct.sid, struct.start, struct.end, 3);
		if (list != null && list.size() == 1) {
			Object[] obj = list.get(0);
			if (obj[0] == null) {
				vipOnline = 0.0;
			} else
				vipOnline = (double) obj[0];
		}
		list.clear();

		int xiaoshoucount = 0;
		int chongzhicount = 0;
		try {
			xiaoshoucount = sale.getXiaoShouCount(struct.sid, struct.start, struct.end);

			chongzhicount = sale.getChongZhiCount(struct.sid, struct.start, struct.end);
		} catch (Exception e) {

		}

		list = sale.getChongzhiAndGiv(struct.sid, struct.start, struct.end);
		double vipin = 0.0;// 会员支出ֵ
		double vipout = 0.0;
		if (list != null && list.size() == 1) {
			Object[] obj = list.get(0);
			if (obj[0] != null && obj[1] != null) {
				vipin = (double) obj[0];
				vipout = (double) obj[1];
			}

		}
		java.text.DecimalFormat df = new java.text.DecimalFormat("#0.##");
		String[] args = { "xiaoshou", "lirun", "shouru", "zhichu", "cash", "bank", "online", "vipCash", "vipBank",
				"vipOnline", "xiaoshoucount", "chongzhicount", "vipin", "vipout" };
		setAttribute(req, args, df.format(xiaoshou), df.format(lirun), df.format(shouru), df.format(zhichu),
				df.format(cash), df.format(bank), df.format(online), df.format(vipCash), df.format(vipBank), df.format(vipOnline), xiaoshoucount, chongzhicount,
				df.format(vipin), df.format(vipout));

	}

	private void setAttribute(HttpServletRequest req, String[] args, Object... params) {
		if (args.length == params.length) {
			for (int i = 0; i < params.length; i++) {
				req.setAttribute(args[i], params[i]);
			}
		}

	}

	private class RequestHelper {
		public int sid = -1;//
		public String start = "";//
		public String end = "";//

		public RequestHelper(HttpServletRequest req) {
			String tempId = req.getParameter("store");
			String tempStart = req.getParameter("start");// ֤
			String tempEnd = req.getParameter("end");
			/*
			 * Pattern pattern=Pattern.compile("[0-9]+"); Matcher
			 * matcher=pattern.matcher(tempId);
			 */
			try {
				sid = Integer.valueOf(tempId);
				start = DateFormatUtils.StrToDate(tempStart);
				end = DateFormatUtils.StrToDate(tempEnd);
			} catch (NumberFormatException e) {
				sid = -1;
				e.printStackTrace();
			} catch (ParseException e) {
				start = end = "";
				e.printStackTrace();
			}
		}
	}
	
	
}
