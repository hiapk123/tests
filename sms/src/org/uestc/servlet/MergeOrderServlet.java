package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.service.AuditOrderService;
import org.uestc.service.InventoryWarningService;
import org.uestc.service.MergeOrderService;
import org.uestc.serviceImp.AuditOrderServiceImp;
import org.uestc.serviceImp.InventoryWarningServiceImp;
import org.uestc.serviceImp.MergeOrderServiceImp;
import org.uestc.util.PageBean;

import com.uestc.bean.OrderItem;
import com.uestc.bean.Store;
import com.uestc.bean.TbOrder;
import com.uestc.bean.Users;

import cn.itcast.servlet.BaseServlet;

@WebServlet("/MergeOrderServlet")
public class MergeOrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private InventoryWarningService inventoryWarningService = new InventoryWarningServiceImp();
	private MergeOrderService mergeOrderService = new MergeOrderServiceImp();

	public String getBookingDetailByBNos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bnos = request.getParameter("bnos");
//		System.out.println("bnos: " + bnos);
		
		List<OrderItem> orderItemList = mergeOrderService.findByBNos(bnos);
		// 思路：1.构造json数组，页面遍历json数组
		// 构造json数组
		StringBuilder sb = new StringBuilder("[");
		
		int index = 0; // 标记最后一个json对象
		for (OrderItem orderItem : orderItemList) {
			index++;
			sb.append("{");
			
			// 构造json对象
			sb.append("\"barcode\"").append(":").append("\""+orderItem.getBarcode()+"\"");
			sb.append(","); // 数据由逗号分隔
			sb.append("\"gName\"").append(":").append("\""+orderItem.getgName()+"\"");
			sb.append(","); 
			sb.append("\"gNum\"").append(":").append("\""+orderItem.getgNum()+"\"");
			sb.append(","); 
			sb.append("\"price\"").append(":").append("\""+orderItem.getPrice()+"\"");
			sb.append(","); 
			sb.append("\"gInfo\"").append(":").append("\""+orderItem.getgInfo()+"\"");
			
			if (index == orderItemList.size()) {
				sb.append("}");
			} else {
				sb.append("},");
			}
		}
		sb.append("]");
		response.getWriter().print(sb); // 将构造的json数组写回客户端
		
		return null;
	}
	
	public String findByCombination(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Users user = (Users) request.getSession().getAttribute("sessionUser");
		List<Store> storeList = inventoryWarningService.findAllStore();
		request.setAttribute("storeList", storeList);
		List<String> dateList = mergeOrderService.findAllDate();
		request.setAttribute("dateList", dateList);
		
		String bookingNo = request.getParameter("bookingNo");
		String date = request.getParameter("date");
		String storeName = request.getParameter("storeName");
		
		request.setAttribute("bookingNo", bookingNo);
		request.setAttribute("date", date);
		request.setAttribute("storeName", storeName);
		
		int pc = getPc(request);
		String url = getUrl(request);
		
		PageBean<TbOrder> pb = null;
		pb = mergeOrderService.findByCombination(bookingNo, date, storeName, pc);
		
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		
		return "f:/pages/admin/admin-merge-order.jsp";
	}
	
	public String initLoad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Users user = (Users) request.getSession().getAttribute("sessionUser");
		List<Store> storeList = inventoryWarningService.findAllStore();
		request.setAttribute("storeList", storeList);
		
		List<String> dateList = mergeOrderService.findAllDate();
		request.setAttribute("dateList", dateList);
		
		int pc = getPc(request);
		String url = getUrl(request);
		
		PageBean<TbOrder> pb = mergeOrderService.findAllOrder(pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		
		return "f:/pages/admin/admin-merge-order.jsp";
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
