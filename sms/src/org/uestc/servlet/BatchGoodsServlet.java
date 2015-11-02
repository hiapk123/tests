package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.TableBatchServiceImp;

import com.uestc.bean.batchgoods;

/**
 * Servlet implementation class BatchGoodsServlet
 */
@WebServlet(
  urlPatterns="/BatchGoods",
  name="BatchGoodsServlet"
		)
public class BatchGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//加上以下这句话会导致页面的额乱码~
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("批量处理的servlet");
		//直接加入连接跳转
		String leixing=request.getParameter("type");
		List<batchgoods> list=new TableBatchServiceImp().showtablebatchbyinit();
		List<batchgoods> list1=new TableBatchServiceImp().showtablebatchbyinit1();
		List<batchgoods> list2=new TableBatchServiceImp().showtablebatchbyinit2();
		
	//绑定后台文本的下拉框
		List<Object[]> list3=new TableBatchServiceImp().shticheng();
		
		request.setAttribute("list", list);
		request.setAttribute("list1", list1);
		request.setAttribute("list2", list2);
		//获取提成
		request.setAttribute("list3", list3);
		
		request.getRequestDispatcher("/pages/goods/batchsetting-goods.jsp").forward(request, response);		
		//结束的末�?		
	}
	
	//�?些需要处理的方法~

}
