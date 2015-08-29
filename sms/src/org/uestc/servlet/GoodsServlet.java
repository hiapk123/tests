package org.uestc.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.ProgressListener;
import org.uestc.service.GoodsService;
import org.uestc.serviceImp.GoodsServiceImp;

import org.uestc.util.DateFormatUtils;
import org.uestc.util.HtmlPage;
import org.uestc.util.Page;
import org.uestc.util.SqlHelper;
import java.util.UUID;
import com.uestc.bean.Category;
import com.uestc.bean.CommissionRule;
import com.uestc.bean.Goods;
import com.uestc.bean.IntegralRule;
import com.uestc.bean.Store;
import com.uestc.bean.Supplier;
import com.uestc.bean.Vip;

@WebServlet(urlPatterns = { "/goods" })
public class GoodsServlet extends HttpServlet {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 6005017459312388968L;
	private int s_id = 0;
	private GoodsService good = new GoodsServiceImp();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String m = req.getParameter("m");
		if (m.equals("goodsInfo")) {
			this.goodsInfo(req, resp);
			req.getRequestDispatcher("/pages/goods/goods-info.jsp").forward(req, resp);
		} else if ("findGoodByPage".equals(m)) {
			this.findGoodByPage(req, resp);
			req.getRequestDispatcher("/pages/goods/goodsinfo/findgood.jsp").forward(req, resp);
		} else if (m.equals("addGood")) {
			this.addGood(req, resp);
			req.getRequestDispatcher("/pages/goods/goodsinfo/addgood.jsp").forward(req, resp);
		} else if (m.equals("addGood1")) {
			this.addGood0(req, resp);
			req.getRequestDispatcher("/pages/goods/goodsinfo/addproduct-info.jsp").forward(req, resp);
		}else if (m.equals("addGoods")) {
			this.addGoods(req, resp);
			req.getRequestDispatcher("/pages/goods/goodsinfo/addproduct.jsp").forward(req, resp);
		} else if (m.equals("editGood")) {
			this.editGood(req, resp);
			req.getRequestDispatcher("/pages/goods/goodsinfo/editgood.jsp").forward(req, resp);
		} else if (m.equals("editGood2")) {
			this.editGood2(req, resp);
			req.getRequestDispatcher("/pages/goods/goodsinfo/tiaozhuan.jsp").forward(req, resp);
		//	resp.sendRedirect("<%=basePath %>goods?m=goodsInfo");
		} else if (m.equals("deleteGood")) {
			this.deleteGood(req, resp);
			String url = req.getHeader("Referer");
			resp.sendRedirect(url);
		} else if (m.equals("findByPage")) {
			this.findGoodByPage(req,resp);
			req.getRequestDispatcher("/pages/goods/findgoodspage.jsp").forward(req, resp);
		}else if (m.equals("upsort")) {
			this.upsort(req,resp);
			req.getRequestDispatcher("/pages/goods/findgoodspage.jsp").forward(req, resp);
		}else if (m.equals("downsort")) {
			this.downsort(req,resp);
			req.getRequestDispatcher("/pages/goods/findgoodspage.jsp").forward(req, resp);
		}else if (m.equals("daoru")) {
			this.daoru(req,resp);
			req.getRequestDispatcher("/pages/goods/goodsinfo/daoru.jsp").forward(req, resp);
		}else if (m.equals("Shangchuanwenjian")) {
			this.shangchuan(req,resp);
		}else if (m.equals("daochu")) {
			this.daochu(req,resp);
			req.getRequestDispatcher("/pages/goods/goodsinfo/daochu.jsp").forward(req, resp);
		}else if (m.equals("toExcel")) {
			this.toExcel(req,resp);
			req.getRequestDispatcher("/pages/goods/goodsinfo/toExcel.jsp").forward(req, resp);
		}else if (m.equals("fuzhishangpin")) {
			this.fuzhishangpin(req,resp);
			req.getRequestDispatcher("/pages/goods/goodsinfo/fuzhishangpin.jsp").forward(req, resp);
		}else if (m.equals("fuzhi")) {
			this.fuzhishangpin1(req,resp);
			req.getRequestDispatcher("/pages/goods/goodsinfo/tiaozhuan.jsp").forward(req, resp);
		}

	}
	/***
	 * 复制商品
	 * @param req
	 * @param resp
	 */
	private void fuzhishangpin1(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		 String s_id1 = req.getParameter("s_id1");
		 String s_id2 = req.getParameter("s_id2");
		good.fuzhi(Integer.valueOf(s_id1), Integer.valueOf(s_id2));
	}

	/***
	 * 进入复制商品页面
	 * @param req
	 * @param resp
	 */
	private void fuzhishangpin(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		 String s_id = req.getParameter("s_id");
		 String s_name = req.getParameter("s_name");
		 req.setAttribute("s_id", s_id);
		 req.setAttribute("s_name", s_name);
			List<Object[]> storeList = null;
			HttpSession session = req.getSession();

			try {
				storeList = good.findStoreByUserID(Integer.valueOf(session.getAttribute("uid").toString()));

				req.setAttribute("storeList", storeList);

			} catch (NumberFormatException e) {
				e.printStackTrace();
			}

	}

	/***
	 * 点击导入商品按钮进入导入商品页面
	 * @param req
	 * @param resp
	 */
	private void daoru(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		 String s_id = req.getParameter("s_id");
		 String s_name = req.getParameter("s_name");
			
			
			req.setAttribute("s_id", s_id);
			req.setAttribute("s_name", s_name);
	}

	/***
	 * 点击导出商品按钮进入导出商品页面
	 * @param req
	 * @param resp
	 */
	private void daochu(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
        String s_id = req.getParameter("s_id");
		
		
		
		req.setAttribute("s_id", s_id);
		
	}

	/***
	 * 将数据从数据库导入表格并下载
	 * @param req
	 * @param resp
	 */
	private void toExcel(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String s_id = req.getParameter("s_id");
		
		List<Object[]> list = good.toExcel(Integer.valueOf(s_id));
		req.setAttribute("goodsList", list);
		req.setAttribute("s_id", s_id);
	}

	/***
	 * 上传文件并导入数据库
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void shangchuan(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
         //上传时生成的临时文件保存目录
         String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
         File tmpFile = new File(tempPath);
         if (!tmpFile.exists()) {
             //创建临时目录
             tmpFile.mkdir();
         }
         //消息提示
         String message = "";
         try{
             //使用Apache文件上传组件处理文件上传步骤：
             //1、创建一个DiskFileItemFactory工厂
             DiskFileItemFactory factory = new DiskFileItemFactory();
             //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
             factory.setSizeThreshold(1024*100);//设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
             //设置上传时生成的临时文件的保存目录
             factory.setRepository(tmpFile);
             //2、创建一个文件上传解析器
             ServletFileUpload upload = new ServletFileUpload(factory);
             //监听文件上传进度
             upload.setProgressListener(new ProgressListener(){
                 public void update(long pBytesRead, long pContentLength, int arg2) {
                     System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
                     /**
                      * 文件大小为：14608,当前已处理：4096
                         文件大小为：14608,当前已处理：7367
                         文件大小为：14608,当前已处理：11419
                         文件大小为：14608,当前已处理：14608
                      */
                 }
             });
              //解决上传文件名的中文乱码
             upload.setHeaderEncoding("UTF-8"); 
             //3、判断提交上来的数据是否是上传表单的数据
             if(!ServletFileUpload.isMultipartContent(req)){
                 //按照传统方式获取数据
                 return;
             }
             
             //设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
             upload.setFileSizeMax(1024*1024);
             //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
             upload.setSizeMax(1024*1024*10);
             //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
             List<FileItem> list = upload.parseRequest(req);
             for(FileItem item : list){
                 //如果fileitem中封装的是普通输入项的数据
                 if(item.isFormField()){
                     String name = item.getFieldName();
                     //解决普通输入项的数据的中文乱码问题
                     String value = item.getString("UTF-8");
                     //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                     System.out.println(name + "=" + value);
                 }else{//如果fileitem中封装的是上传文件
                     //得到上传的文件名称，
                     String filename = item.getName();
                     System.out.println(filename);
                     if(filename==null || filename.trim().equals("")){
                         continue;
                     }
                     //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                     //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                     filename = filename.substring(filename.lastIndexOf("\\")+1);
                     //得到上传文件的扩展名
                     String fileExtName = filename.substring(filename.lastIndexOf(".")+1);
                     //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
                     System.out.println("上传的文件的扩展名是："+fileExtName);
                     //获取item中的上传文件的输入流
                     InputStream in = item.getInputStream();
                   
                     //创建一个文件输出流
                     FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
                     //创建一个缓冲区
                     byte buffer[] = new byte[1024];
                     //判断输入流中的数据是否已经读完的标识
                     int len = 0;
                     //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                     while((len=in.read(buffer))>0){
                         //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                         out.write(buffer, 0, len);
                     }
                     //关闭输入流
                     in.close();
                     //关闭输出流
                     out.close();
                     //删除处理文件上传时生成的临时文件
                     message = "文件上传成功！";
                     
                 }
             }
         }catch (FileUploadBase.FileSizeLimitExceededException e) {
             e.printStackTrace();
             req.setAttribute("message", "单个文件超出最大值！！！");
             req.getRequestDispatcher("/pages/goods/goodsinfo/success.jsp").forward(req, resp);
             return;
         }catch (FileUploadBase.SizeLimitExceededException e) {
             e.printStackTrace();
             req.setAttribute("message", "上传文件的总的大小超出限制的最大值！！！");
             req.getRequestDispatcher("/pages/goods/goodsinfo/success.jsp").forward(req, resp);
             return;
         }catch (Exception e) {
        	 message= "文件上传失败！";
             
             e.printStackTrace();
         }
        // req.setAttribute("message",message);
         //req.getRequestDispatcher("/pages/goods/goodsinfo/success.jsp").forward(req, resp);
         good.importExcel(req,resp);
}
	

	/***
	 * 将商品模板导入数据库
	 * @param req
	 * @param resp
	 * @throws FileNotFoundException 
	 */
	/*private void ExcelToMySql(HttpServletRequest req, HttpServletResponse resp) throws FileNotFoundException {
		// TODO Auto-generated method stub
		good.importExcel();
		
	}*/

	/***
	 * 降序
	 * 
	 * @param req
	 * @param resp
	 */
	private void downsort(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String method=req.getParameter("m");
		String sorted=req.getParameter("sorted");
		String currentPage = req.getParameter("currentPage");
		String which = req.getParameter("which");
		String store = req.getParameter("store");
		int totalSize = getTotalSize(store);
		int totalPage = 0;
		if(""==currentPage){
			currentPage="1";
		}
		
			int pageNo = Integer.valueOf(currentPage.trim());
			if ("" == which) {
				which = "first";
				pageNo = 1;
			} else if ("first".equals(which)) {
				which = "first";
				pageNo = 1;
			}else if ("↑".equals(which)) {
				which = "first";
				pageNo = 1;
			}  else if ("next".equals(which)) {
				pageNo++;
			} else if ("prev".equals(which)) {
				pageNo--;
			} else if ("last".equals(which)) {
				totalPage = (totalSize % 10 == 0 ? totalSize / 10 : (totalSize / 10 + 1));
				pageNo = totalPage;
			}else {
				pageNo = Integer.valueOf(which.trim());
			}
			List<Object[]> list = good.downsort(Integer.valueOf(store), (pageNo-1) * 10,sorted);
			
            
			
			req.setAttribute("goodsList", list);
			req.setAttribute("store", store);
			req.setAttribute("currentPage", pageNo);
			req.setAttribute("totalPage", totalSize);
			req.setAttribute("sorted", sorted);
			HttpSession session=(HttpSession)req.getSession();
			ServletContext application=(ServletContext)session.getServletContext();

			application.setAttribute("method",method);
			application.setAttribute("sorted",sorted);
	}

	/***
	 * 升序
	 * 
	 * @param req
	 * @param resp
	 */
	private void upsort(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String method=req.getParameter("m");
		String sorted=req.getParameter("sorted");
		
		String currentPage = req.getParameter("currentPage");
		String which = req.getParameter("which");
		String store = req.getParameter("store");
		int totalSize = getTotalSize(store);
		int totalPage = 0;
		if(""==currentPage){
			currentPage="1";
		}
		
		
			int pageNo = Integer.valueOf(currentPage.trim());
			if ("" == which) {
				which = "first";
				pageNo = 1;
			} else if ("first".equals(which)) {
				which = "first";
				pageNo = 1;
			}else if ("↑".equals(which)) {
				which = "first";
				pageNo = 1;
			}  else if ("next".equals(which)) {
				pageNo++;
			} else if ("prev".equals(which)) {
				pageNo--;
			} else if ("last".equals(which)) {
				totalPage = (totalSize % 10 == 0 ? totalSize / 10 : (totalSize / 10 + 1));
				pageNo = totalPage;
			}else {
				pageNo = Integer.valueOf(which.trim());
			}
			List<Object[]> list = good.upsort(Integer.valueOf(store), (pageNo-1) * 10,sorted);
		
            
			
			req.setAttribute("goodsList", list);
			req.setAttribute("store", store);
			req.setAttribute("currentPage", pageNo);
			req.setAttribute("totalPage", totalSize);
			req.setAttribute("sort", sorted);
			HttpSession session=(HttpSession)req.getSession();
			ServletContext application=(ServletContext)session.getServletContext();

			application.setAttribute("method",method);
			application.setAttribute("sorted",sorted);
             
	}

	/***
	 * 
	 * 
	 * @param req
	 * @param resp
	 */

	private void addGood0(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String s_id = req.getParameter("s_id");
		String s_name = req.getParameter("s_name");
		String g_barcode = req.getParameter("g_barcode");
		req.setAttribute("s_id", s_id);
		req.setAttribute("s_name", s_name);
		req.setAttribute("g_barcode", g_barcode);
	}

	/***
	 * 
	 * 
	 * @param req
	 * @param resp
	 */
	private void deleteGood(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String g_id = req.getParameter("g_id");
		try {

			good.deletegood(Integer.valueOf(g_id));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/***
	 * 
	 * 
	 * @param req
	 * @param resp
	 */
	private void editGood2(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

		String g_id = req.getParameter("g_id");
		String s_id = (String) req.getParameter("s_id");
		String s_name = req.getParameter("s_name");
		String g_name = req.getParameter("g_name");
		// String g_flag = req.getParameter("state");
		String g_stock_num = req.getParameter("kucun");
		String g_sale_price = req.getParameter("xiaoshoujia");
		String g_pur_price = req.getParameter("jinhuojia");
		String c_name = req.getParameter("c_name");
		String g_barcode = req.getParameter("g_barcode");
		// int g_flag0 = Integer.parseInt(g_flag);

		
		

		/*
		 * req.setAttribute(s_name, s_name); req.setAttribute(g_name, g_name);
		 * // req.setAttribute(g_flag, g_flag); req.setAttribute(g_stock_num,
		 * g_stock_num); req.setAttribute(g_sale_price, g_sale_price);
		 * req.setAttribute(g_pur_price, g_pur_price); req.setAttribute(c_name,
		 * c_name); req.setAttribute(g_barcode, g_barcode);
		 */
		try {

			good.editgood(Integer.valueOf(s_id), s_name, g_name, g_stock_num, g_sale_price, g_pur_price, c_name,
					g_barcode, Integer.valueOf(g_id));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/***
	 * 
	 * 
	 * @param req
	 * @param resp
	 */

	private void editGood(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String g_id = req.getParameter("g_id");
		String g_barcode = req.getParameter("g_barcode");
		String s_id = req.getParameter("s_id");
		String s_name = req.getParameter("s_name");
		String g_name = req.getParameter("g_name");
		// String g_flag = req.getParameter("state");
		// String g_stock_num = req.getParameter("kucun");
		// String g_sale_price = req.getParameter("xiaoshoujia");
		// String g_pur_price = req.getParameter("jinhuojia");
		String c_name = req.getParameter("c_name");
		// String g_barcode = req.getParameter("tiaoma");
		req.setAttribute("s_name", s_name);
		req.setAttribute("c_name", c_name);
		req.setAttribute("g_barcode", g_barcode);
		req.setAttribute("g_name", g_name);
		req.setAttribute("s_id", s_id);
		req.setAttribute("g_id", g_id);

	}

	/***
	 * 
	 * 
	 * @param req
	 * @param resp
	 */

	private void addGoods(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

		String s_id = req.getParameter("s_id");
		String s_name = req.getParameter("s_name");
		req.setAttribute("s_id", s_id);
		req.setAttribute("s_name", s_name);
	}

	/***
	 * 
	 * 
	 * @param req
	 * @param resp
	 */
	private void addGood(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String s_id = req.getParameter("s_id");
		String s_name = req.getParameter("s_name");
		String g_name = req.getParameter("g_name");
		String g_flag = req.getParameter("g_flag");
		String g_stock_num = req.getParameter("kucun");
		String g_sale_price = req.getParameter("xiaoshoujia");
		String g_pur_price = req.getParameter("jinhuojia");
		String c_name = req.getParameter("fenlei");
		String g_barcode = req.getParameter("g_barcode");
		System.out.println("gg"+s_id);
		System.out.println("gg"+s_name);
		System.out.println("gg"+g_barcode);
		System.out.println("gg"+g_flag);


		try {
			good.addgood(Integer.valueOf(s_id), s_name, g_name, Integer.valueOf(g_flag), g_stock_num, g_sale_price, g_pur_price, c_name,
					g_barcode);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/***
	 * 
	 * 
	 * @param req
	 * @param resp
	 */
	private void findGoodByPage(HttpServletRequest req, HttpServletResponse resp) {
	   
		
	    String method="findByPage";
		String currentPage = req.getParameter("currentPage");
		String which = req.getParameter("which");
		String store = req.getParameter("store");
		int totalSize = getTotalSize(store);
		int totalPage = 0;
		if(""==currentPage){
			currentPage="1";
		}
		
			int pageNo = Integer.valueOf(currentPage.trim());
			if (null == which) {
				which = "first";
				pageNo = 1;
			} else if ("first".equals(which)) {
				which = "first";
				pageNo = 1;
			} else if ("next".equals(which)) {
				pageNo++;
			} else if ("prev".equals(which)) {
				pageNo--;
			} else if ("last".equals(which)) {
				totalPage = (totalSize % 10 == 0 ? totalSize / 10 : (totalSize / 10 + 1));
				pageNo = totalPage;
			}else {
				pageNo = Integer.valueOf(which.trim());
			}

			List<Object[]> list = good.goodssearch(Integer.valueOf(store), (pageNo-1) * 10);
			req.setAttribute("goodsList", list);
			req.setAttribute("store", store);
			req.setAttribute("currentPage", pageNo);
			req.setAttribute("totalPage", totalSize);
			HttpSession session=(HttpSession)req.getSession();
			ServletContext application=(ServletContext)session.getServletContext();

			application.setAttribute("method",method);
			

	}

	/***
	 * 
	 * 
	 * @param req
	 * @param resp
	 */
	/*private void findGoods(HttpServletRequest req, HttpServletResponse resp) {
		RequestHelper reqHelper = new RequestHelper(req);
		List<Object[]> list = good.goodssearch(reqHelper.sid, 0);
		int totalPage = 0;
		totalPage = 20;
		s_id = reqHelper.sid;
		req.setAttribute("currentPage", 1);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("goodsList", list);
		req.setAttribute("s_id", s_id);

	}
*/
	private void goodsInfo(HttpServletRequest req, HttpServletResponse resp) {
		List<Object[]> storeList = null;
		HttpSession session = req.getSession();

		try {
			storeList = good.findStoreByUserID(Integer.valueOf(session.getAttribute("uid").toString()));

			req.setAttribute("storeList", storeList);

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}

	// 
	private int getTotalSize(String id) {
		try {
			return good.getTotalSize(Integer.valueOf(id));
		} catch (NumberFormatException e) {

			e.printStackTrace();
		}
		return 0;
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

		public RequestHelper(HttpServletRequest req) {
			String tempId = req.getParameter("store");
			try {
				sid = Integer.valueOf(tempId);

			} catch (NumberFormatException e) {
				sid = -1;
				e.printStackTrace();
			}
		}
	}

}
