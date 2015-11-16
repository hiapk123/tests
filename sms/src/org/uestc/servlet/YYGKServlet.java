package org.uestc.servlet;

import cn.itcast.servlet.BaseServlet;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.service.InventoryWarningService;
import org.uestc.service.YYGKService;
import org.uestc.serviceImp.InventoryWarningServiceImp;
import org.uestc.serviceImp.YYGKServiceImp;
import org.uestc.util.PageBean;

import com.uestc.bean.JiaoJieBan_HP;
import com.uestc.bean.SalesSummaryBean;
import com.uestc.bean.Store;
import com.uestc.bean.Users;
import com.uestc.bean.XJSZBean;

@WebServlet("/YYGKServlet")
public class YYGKServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private InventoryWarningService inventoryWarningService = new InventoryWarningServiceImp();
	private YYGKService yygkService = new YYGKServiceImp();
	
	public String initLoad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Users user = (Users) request.getSession().getAttribute("sessionUser");
		List<Store> storeList = inventoryWarningService.findAllStoresByUid(user.getUId());
		request.setAttribute("storeList", storeList);
		
		SalesSummaryBean ssb = yygkService.getSalesSummary(user.getUId());
		request.setAttribute("ssb", ssb);
		
		return "f:/pages/sales/yygk.jsp";
	}
	public String findByCombination(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Users user = (Users) request.getSession().getAttribute("sessionUser");
		List<Store> storeList = inventoryWarningService.findAllStoresByUid(user.getUId());
		request.setAttribute("storeList", storeList);
		
		String storeName = request.getParameter("hp_store");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String condition = request.getParameter("hp_condition");
		
		request.setAttribute("storeName", storeName);
		request.setAttribute("beginTime", beginTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("condition", condition);
		
		if (condition.equals("销售汇总分析")) {
			SalesSummaryBean ssb = yygkService.getSalesSummaryByCombination(storeName, beginTime, endTime, user.getUId());
			request.setAttribute("ssb", ssb);
		}
		
		if (condition.equals("交接班记录")) {
			int pc = getPc(request);
			String url = getUrl(request);
			
			PageBean<JiaoJieBan_HP> pb = null;
			pb = yygkService.findShiftingDutyRecordByCombination(storeName, beginTime, endTime, user.getUId() ,pc);
			
			pb.setUrl(url);
			request.setAttribute("pb", pb);
		}
		
		if (condition.equals("现金收支明细")) {
			int pc = getPc(request);
			String url = getUrl(request);
			
			PageBean<XJSZBean> pb = null;
			
			pb = yygkService.findCashDetailsByCombination(storeName, beginTime, endTime, user.getUId(), pc);
			pb.setUrl(url);
			request.setAttribute("pb", pb);
		}
		
		return "f:/pages/sales/yygk.jsp";
	}

	private int getPc(HttpServletRequest request) {
		int pc = 1;
		String pcParam = request.getParameter("pc");
		if (pcParam != null) {
			if (!pcParam.trim().isEmpty()) {
				try {
					pc = Integer.parseInt(pcParam);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		return pc;
	}

	private String getUrl(HttpServletRequest request) {
		String url = request.getRequestURI() + "?" + request.getQueryString();
		// 如果url中存在pc参数，截取掉，如果不存在就不用截取
		int index = url.lastIndexOf("&pc");
		if (index != -1) {
			url = url.substring(0, index);
		}
		return url;
	}
	
}
