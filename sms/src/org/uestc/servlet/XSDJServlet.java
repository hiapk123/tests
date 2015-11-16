package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.service.InventoryWarningService;
import org.uestc.service.XSDJService;
import org.uestc.serviceImp.InventoryWarningServiceImp;
import org.uestc.serviceImp.XSDJServiceImp;
import org.uestc.util.PageBean;

import com.uestc.bean.Store;
import com.uestc.bean.Users;
import com.uestc.bean.XSDJBean;

import cn.itcast.servlet.BaseServlet;

/**
 * Servlet implementation class XSDJServlet
 */
@WebServlet("/XSDJServlet")
public class XSDJServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private InventoryWarningService inventoryWarningService = new InventoryWarningServiceImp();
	private XSDJService xsdjService = new XSDJServiceImp();

	public String findByCombination(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Users user = (Users) request.getSession().getAttribute("sessionUser");
		List<Store> storeList = inventoryWarningService.findAllStoresByUid(user.getUId());
		request.setAttribute("storeList", storeList);
		
		String storeName = request.getParameter("hp_store");
		String receiptType = request.getParameter("receiptType");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String seriNum = request.getParameter("seriNum").trim();
		
		request.setAttribute("storeName", storeName);
		request.setAttribute("receiptType", receiptType);
		request.setAttribute("beginTime", beginTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("seriNum", seriNum);
		
		int pc = getPc(request);
		String url = getUrl(request);
		
		PageBean<XSDJBean> pb = null;
		pb = xsdjService.findByCombination(storeName, receiptType, beginTime, endTime, seriNum, user.getUId() ,pc);
		
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		
		return "f:/pages/sales/xsdj.jsp";
	}
	
	
	public String initLoad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Users user = (Users) request.getSession().getAttribute("sessionUser");
		List<Store> storeList = inventoryWarningService.findAllStoresByUid(user.getUId());
		request.setAttribute("storeList", storeList);
		
		int pc = getPc(request);
		String url = getUrl(request);
		
		PageBean<XSDJBean> pb = xsdjService.findAllByUid(user.getUId(), pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		
		return "f:/pages/sales/xsdj.jsp";
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
		int index = url.lastIndexOf("&pc");
		if (index != -1) {
			url = url.substring(0, index);
		}
		return url;
	}

}
