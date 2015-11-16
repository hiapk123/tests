package org.uestc.servlet;
/*
 * 示例
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/SystemServlet")
public class SystemServlet extends HttpServlet {
	private String param = "";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			param=req.getParameter("method");
			if(param==null){
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
			}else if(param.equals("add")){
				this.add(req,resp);
			}else if(param.equals("findAll")){
				this.findAll(req,resp);
			}else{
				
			}
	}
	
	//在下面添加的需要的方法
	/**
	 * 查询所有商品信息的方法
	 * @param req
	 * @param resp
	 */
	private void findAll(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("findAll Servlet被调用！");	
		try {
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 添加商品的方法
	 * @param req
	 * @param resp
	 */
	private void add(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("add Servlet被调用！");
		try {
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
