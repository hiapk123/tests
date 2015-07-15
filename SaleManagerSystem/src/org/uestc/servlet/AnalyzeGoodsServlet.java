package org.uestc.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.uestc.service.AnalyseGoodsService;
import org.uestc.serviceImp.AnalyseGoodsServiceImp;
@WebServlet(
		urlPatterns = { "/AnalyzeGoods" }, 
		name = "analyzeGoodsServlet"
)
public class AnalyzeGoodsServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8327745872922672243L;
	private AnalyseGoodsService goodService=null;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("商品分析Servlet!");
		String m=req.getParameter("m");//获取类型
		
		goodService=new AnalyseGoodsServiceImp();
		//为空返回
		if(null==m){
			//失败状态返回为0
			PrintWriter pw=resp.getWriter();
			StringBuffer sb=new StringBuffer();
			sb.append("{\"status\":\"0\"}");
			pw.write(sb.toString());
		}else{
			//初始化店铺
			if("initStore".equals(m)){
				initStore(req,resp);
			    
			}
		}
	}
	
	
	
	
	
	//初始化商品分类
	private void initStore(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		
		JSONArray array=new JSONArray();
		
		String type=req.getParameter("type");//获取类型
		if("All".equals(type)){
			List<Object[]> list= goodService.findStoreByUserId(1);//查询所有分类，父ID为-1
			for(int i=0;i<list.size();i++){
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("sid", list.get(i)[0]);
				jsonObject.put("sname", list.get(i)[1]);
				array.add(jsonObject);
			}
			resp.setContentType("text/json; charset=utf-8");
			resp.setCharacterEncoding("UTF-8");
			PrintWriter out = resp.getWriter();
			
			out.write(array.toString());
			out.flush();
			out.close();
		}
		
	}
}
