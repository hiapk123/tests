package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uestc.bean.Category;
import com.uestc.bean.Goods;
import com.uestc.bean.Store;
import com.uestc.bean.Supplier;
import com.uestc.bean.Users;

import cn.itcast.servlet.BaseServlet;

/**
 * Servlet implementation class PayPerRideServlet
 */
@WebServlet("/PayPerRideServlet")
public class PayPerRideServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String loadBaseMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		return "f:/pages/goods/payPerRide.jsp";
	}
	
}
