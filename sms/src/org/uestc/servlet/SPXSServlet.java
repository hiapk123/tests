package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.service.InventoryWarningService;
import org.uestc.service.SPXSService;
import org.uestc.serviceImp.InventoryWarningServiceImp;
import org.uestc.serviceImp.SPXSServiceImp;
import org.uestc.util.PageBean;

import com.uestc.bean.SPXSBean;
import com.uestc.bean.Sale;
import com.uestc.bean.Store;
import com.uestc.bean.Users;

import cn.itcast.servlet.BaseServlet;

/**
 * Servlet implementation class SPXSServlet
 */
@WebServlet("/SPXSServlet")
public class SPXSServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private InventoryWarningService inventoryWarningService = new InventoryWarningServiceImp();
	private SPXSService spxsService = new SPXSServiceImp();

	public String findByCombination(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Users user = (Users) request.getSession().getAttribute("sessionUser");
		List<Store> storeList = null;
		if (user.getUType() == 1) { // 管理员
			storeList = inventoryWarningService.findAllStore();
		} else {
			storeList = inventoryWarningService.findAllStoresByUid(user.getUId());
		}
		request.setAttribute("storeList", storeList);
		
		String storeName = request.getParameter("hp_store");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String condition = request.getParameter("gBarcodeOrGName").trim();
		
		request.setAttribute("storeName", storeName);
		request.setAttribute("beginTime", beginTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("condition", condition);
		
		int pc = getPc(request);
		String url = getUrl(request);
		
		PageBean<SPXSBean> pb = null;
		if (user.getUType() == 1) {
			pb = spxsService.findAllByCombination(storeName, beginTime, endTime, condition, pc);
		} else {
			pb = spxsService.findByCombination(storeName, beginTime, endTime, condition, user.getUId() ,pc);
		}
		
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		
		return "f:/pages/sales/spxs.jsp";
	}
	
	public String initLoad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Users user = (Users) request.getSession().getAttribute("sessionUser");
//		System.out.println(user.getUType());
		List<Store> storeList = null;
		if (user.getUType() == 1) { // 该用户为管理员
			storeList = inventoryWarningService.findAllStore();
		} else {
			storeList = inventoryWarningService.findAllStoresByUid(user.getUId());
		}
		request.setAttribute("storeList", storeList);
		
		int pc = getPc(request);
		String url = getUrl(request);
		PageBean<SPXSBean> pb = null;
		if (user.getUType() == 1) { // 该用户为管理员
			pb = spxsService.findAll(pc);
		} else {
			pb = spxsService.findAllByUid(user.getUId(), pc);
		}
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "f:/pages/sales/spxs.jsp";
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

