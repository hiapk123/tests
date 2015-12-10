package org.uestc.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
import org.json.JSONException;
import org.json.JSONObject;
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
	private String realSavePath;
	private GoodsServiceImp good = new GoodsServiceImp();

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
			req.getRequestDispatcher("/pages/goods/findgoodspage.jsp").forward(req, resp);
		} else if (m.equals("addGood")) {
			this.addGood(req, resp);
			req.getRequestDispatcher("/pages/goods/goodsinfo/addgood.jsp").forward(req, resp);
		} else if (m.equals("addGood1")) {
			this.addGood0(req, resp);
			//req.getRequestDispatcher("/pages/goods/goodsinfo/addproduct-info.jsp").forward(req, resp);
		}else if (m.equals("addGoods")) {
			this.addGoods(req, resp);
			req.getRequestDispatcher("/pages/goods/goodsinfo/addproduct.jsp").forward(req, resp);
		} else if (m.equals("editGood")) {
			this.editGood(req, resp);
			req.getRequestDispatcher("/pages/goods/goodsinfo/editgood.jsp").forward(req, resp);
		} else if (m.equals("detail")) {
			this.editGood(req, resp);
			req.getRequestDispatcher("/pages/goods/goodsinfo/detail.jsp").forward(req, resp);
		}else if (m.equals("editGood2")) {
			this.editGood2(req, resp);
			//req.getRequestDispatcher("/pages/goods/goodsinfo/tiaozhuan.jsp").forward(req, resp);
			req.getRequestDispatcher("/pages/goods/findgoodspage.jsp").forward(req, resp);
			//req.getRequestDispatcher("/pages/goods/goodsinfo/findgood.jsp").forward(req, resp);
		} else if (m.equals("deleteGood")) {
			this.deleteGood(req, resp);
			req.getRequestDispatcher("/pages/goods/findgoodspage.jsp").forward(req, resp);
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
		}else if (m.equals("kuaisu")) {
			this.kuaisu(req,resp);
			req.getRequestDispatcher("/pages/goods/goodsinfo/kuaisuluru.jsp").forward(req, resp);
		}else if (m.equals("kuaisu1")) {
			this.kuaisu1(req,resp);
			//
		}else if (m.equals("picture")) {
			this.picture(req,resp);
			
		}else if (m.equals("newdw")) {
			this.newdw(req,resp);
			
		}

	}
	

	private void newdw(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String g_unit=req.getParameter("g_unit");
		String g_howmuch=req.getParameter("g_howmuch");
		String s_id=req.getParameter("s_id");
		String sql0="select g_unit from unit where s_id=?";
		List<Object[]>list0=SqlHelper.find(sql0,s_id);
		String message="hege";
		for (int i = 0; i < list0.size(); i++) {
			if (String.valueOf(list0.get(i)[0]).equals(g_unit)) {
				resp.setCharacterEncoding("UTF-8");
				message="buhege";
				req.setAttribute("message", message);
				PrintWriter out=resp.getWriter();
				out.print(message);
				return;
			}
		}
	//	g_unit= String.valueOf(list0.get(0)[0]);
		//Number num = (Number) list0.get(0)[0];  
	//	g_unit= String.valueOf(num.Value());
	
			String sql="insert into unit (g_unit,g_howmuch,s_id) value(?,?,?)";
			SqlHelper.executeUpdate(sql, new String[]{
					g_unit,g_howmuch,s_id
			});
			
			String sql3="select unit_id, g_unit from unit where s_id=? ";
			List<Object[]> danwei = SqlHelper.find(sql3, s_id);
			req.setAttribute("danwei", danwei);
			req.getRequestDispatcher("/pages/goods/goodsinfo/newdw.jsp").forward(req, resp);
	
		
		
	}

	private void picture(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		 String savePath = this.getServletContext().getRealPath("/img");
        //上传时生成的临时文件保存目录
        String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
        File tmpFile = new File(tempPath);
        if (!tmpFile.exists()) {
            //创建临时目录
            tmpFile.mkdir();
        }
        //
        String wenjianming = "";
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
            upload.setFileSizeMax(1024*1024*10);
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
                    wenjianming=filename;
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
                    
                   // message = "文件上传成功！";
                    
                }
            }
        }catch (FileUploadBase.FileSizeLimitExceededException e) {
            e.printStackTrace();
          
            return;
        }catch (FileUploadBase.SizeLimitExceededException e) {
            e.printStackTrace();
          
            return;
        }catch (Exception e) {
       	
            
            e.printStackTrace();
        }
        
        PrintWriter out=resp.getWriter();
	
		JSONObject json=new JSONObject();
		try {
			json.put("name",  wenjianming);
			out.print(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	/***
	 * 快速录制商品功能
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void kuaisu1(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		 String s_name = req.getParameter("s_name");
		 String s_id = req.getParameter("s_id");
	     String g_barcode=req.getParameter("g_barcode");
	     String g_name = req.getParameter("g_name");
	     String c_id = req.getParameter("c_id");
	     String c_name=req.getParameter("c_name");
	     String g_pur_price = req.getParameter("g_pur_price");
	     String g_sale_price=req.getParameter("g_sale_price");
	     String g_stock_num=req.getParameter("g_stock_num");
	     String message = req.getParameter("message");
			message="hege";
			
			String sql="select g_barcode from goods where s_id=? ";
			List<Object[]> list = SqlHelper.find(sql, s_id);
		
			for (int k = 0; k < list.size(); k++) {
				String hehe=(String) list.get(k)[0];
				if ((hehe).equals(g_barcode)){
					resp.setCharacterEncoding("UTF-8");
					message="buhege";
					req.setAttribute("message", message);
					PrintWriter out=resp.getWriter();
					out.print(message);
					return;
				}
			}	
	     good.kuaisuluru(Integer.valueOf(s_id), g_barcode, g_name, c_name, g_pur_price, g_sale_price, g_stock_num,c_id,s_name);
	 	HttpSession session = req.getSession();
	     List<Object[]> storeList = good.findStoreByUserID(Integer.valueOf(session.getAttribute("uid").toString()));
		req.setAttribute("storeList", storeList);
		 String sql2="select c_id,c_name from category where s_id=? ";
			List<Object[]> fenlei = SqlHelper.find(sql2, s_id);
			req.setAttribute("fenlei", fenlei);
			
			req.getRequestDispatcher("/pages/goods/goods-info.jsp").forward(req, resp);	
			
	} 
	/***
	 * 进入快速录制商品页面
	 * @param req
	 * @param resp
	 */
	private void kuaisu(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		 String s_id = req.getParameter("s_id");
	     String s_name=req.getParameter("s_name");
		 req.setAttribute("s_id", s_id);
		 req.setAttribute("s_name", s_name);
		 String sql2="select c_id,c_name from category where s_id=? ";
			List<Object[]> fenlei = SqlHelper.find(sql2, s_id);
			req.setAttribute("fenlei", fenlei);
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
		HttpSession session=req.getSession();
		List <Object[]> storeList=good.findStoreByUserID(Integer.valueOf(session.getAttribute("uid").toString()));
	    req.setAttribute("storeList", storeList);
	}

	/***
	 * 点击导出商品按钮进入导出商品页面
	 * @param req
	 * @param resp
	 */
	private void daochu(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
        String s_id = req.getParameter("s_id");
        String s_name = req.getParameter("s_name");
		
        req.setAttribute("s_name", s_name);
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
		String s_id=req.getParameter("s_id");
		String s_name=req.getParameter("s_name");
     String TruePath="";
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
			upload.setFileSizeMax(1024*1024*10);
			//设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
			upload.setSizeMax(1024*1024*50);
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
					realSavePath = makePath(filename, savePath);
					TruePath=realSavePath + "\\" + filename;
System.out.println("真是路径"+TruePath);
					//创建一个文件输出流
					FileOutputStream out = new FileOutputStream(realSavePath + "\\" + filename);
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
			PrintWriter out=resp.getWriter();
			out.print(message);
			
			return;
		}catch (FileUploadBase.SizeLimitExceededException e) {
			e.printStackTrace();
			req.setAttribute("message", "上传文件的总的大小超出限制的最大值！！！");
			PrintWriter out=resp.getWriter();
			out.print(message);
			return;
		}catch (Exception e) {
			message= "文件上传失败！";
			PrintWriter out=resp.getWriter();
			out.print(message);
			
			e.printStackTrace();
		}
		
		String m=req.getParameter("m");
		if (m.equals("Shangchuanwenjian")) {
			try {
		           List<String> list=good.isRegular(TruePath,s_id);
				    if (list.size()==0) {
					   good.importExcel(req,resp,TruePath,s_id,s_name);
				    }else {
				    	resp.setCharacterEncoding("UTF-8"); 
				    	PrintWriter out=resp.getWriter();
				    	JSONObject json=new JSONObject();
				    	String result="";
				    	for (int i = 0; i < list.size(); i++) {
				    		result+=String.valueOf(list.get(i))+";";
						}
				    	json.put("message", result);
						out.print(json);
					}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(m.equals("shangchuan")){
			req.setAttribute("message","上传成功");
		}


	
	}
	
	/**
	 * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
	 * @Method: makePath
	 * @Description: 
	 * @Anthor:孤傲苍狼
	 *
	 * @param filename 文件名，要根据文件名生成存储目录
	 * @param savePath 文件存储路径
	 * @return 新的存储目录
	 */ 
	private String makePath(String filename,String savePath){
		//得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
		int hashcode = filename.hashCode();
		int dir1 = hashcode&0xf;  //0--15
		int dir2 = (hashcode&0xf0)>>4;  //0-15
		//构造新的保存目录
		String dir = savePath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
		//File既可以代表文件也可以代表目录
		File file = new File(dir);
		//如果目录不存在
		if(!file.exists()){
			//创建目录
			file.mkdirs();
		}
		return dir;
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
		String s_id = req.getParameter("s_id");
		String key = req.getParameter("key");
		String c_name = req.getParameter("c_name");
		String g_del = req.getParameter("g_del");
		int totalSize = getTotalSize(s_id, key,c_name,g_del);
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
				if(pageNo==(totalSize % 10 == 0 ? totalSize / 10 : (totalSize / 10 + 1)))
				{pageNo=pageNo;}else{
				 pageNo++;
				}
			} else if ("prev".equals(which)) {
				if (pageNo==1) {
					pageNo=pageNo;
				}else {
					pageNo--;
				}
			} else if ("last".equals(which)) {
				totalPage = (totalSize % 10 == 0 ? totalSize / 10 : (totalSize / 10 + 1));
				pageNo = totalPage;
			}else {
				pageNo = Integer.valueOf(which.trim());
			}
			List<Object[]> list = good.downsort(Integer.valueOf(s_id), (pageNo-1) * 10,sorted,key,c_name,g_del);
			
			req.setAttribute("c_name", c_name);
			req.setAttribute("key", key);
			req.setAttribute("g_del", g_del);
			req.setAttribute("goodsList", list);
			req.setAttribute("s_id", s_id);
			req.setAttribute("currentPage", pageNo);
			req.setAttribute("totalSize", totalSize);
			req.setAttribute("sorted", sorted);
			HttpSession session=(HttpSession)req.getSession();
			ServletContext application=(ServletContext)session.getServletContext();

			application.setAttribute("method",method);
			//application.setAttribute("sorted",sorted);
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
		String key=req.getParameter("key");
		
		String currentPage = req.getParameter("currentPage");
		String which = req.getParameter("which");
		String s_id = req.getParameter("s_id");
		String c_name = req.getParameter("c_name");
		String g_del = req.getParameter("g_del");
		int totalSize = getTotalSize(s_id, key,c_name,g_del);
		int totalPage = 0;
		if(""==currentPage||null==currentPage){
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
				if(pageNo==(totalSize % 10 == 0 ? totalSize / 10 : (totalSize / 10 + 1)))
				{pageNo=pageNo;}else{
				 pageNo++;
				}
			} else if ("prev".equals(which)) {
				if (pageNo==1) {
					pageNo=pageNo;
				}else {
					pageNo--;
				}
			} else if ("last".equals(which)) {
				totalPage = (totalSize % 10 == 0 ? totalSize / 10 : (totalSize / 10 + 1));
				pageNo = totalPage;
			}else {
				pageNo = Integer.valueOf(which.trim());
			}
			List<Object[]> list = good.upsort(Integer.valueOf(s_id), (pageNo-1) * 10,sorted,key,c_name,g_del);
		
            
			
			req.setAttribute("goodsList", list);
			req.setAttribute("s_id", s_id);
			req.setAttribute("c_name", c_name);
			req.setAttribute("currentPage", pageNo);
			req.setAttribute("totalSize", totalSize);
			req.setAttribute("sorted", sorted);
			req.setAttribute("key", key);
			req.setAttribute("g_del", g_del);
			HttpSession session=(HttpSession)req.getSession();
			ServletContext application=(ServletContext)session.getServletContext();

			application.setAttribute("method",method);
			//application.setAttribute("sorted",sorted);
             
	}

	/***
	 * 
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */

	private void addGood0(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String message = req.getParameter("message");
		message="hege";
		String s_id = req.getParameter("s_id");
		String s_name = req.getParameter("s_name");
		String g_barcode = req.getParameter("g_barcode");
		String sql="select g_barcode from goods where s_id=? ";
		List<Object[]> list = SqlHelper.find(sql, s_id);
	
		for (int k = 0; k < list.size(); k++) {
			String hehe=(String) list.get(k)[0];
			if ((hehe).equals(g_barcode)){
				message="输入的条码与库内商品条码冲突，请重新输入";
				req.setAttribute("message", message);
				break;
			}
		}
		String sql1="select su_id,su_name from supplier where s_id=? ";
		List<Object[]> suppliers = SqlHelper.find(sql1, s_id);
		req.setAttribute("suppliers", suppliers);
		String sql2="select c_id,c_name from category where s_id=? ";
		List<Object[]> fenlei = SqlHelper.find(sql2, s_id);
		req.setAttribute("fenlei", fenlei);
		
		String sql3="select unit_id, g_unit from unit where s_id=? ";
		List<Object[]> danwei = SqlHelper.find(sql3, s_id);
		req.setAttribute("danwei", danwei);
		
		
		req.setAttribute("s_id", s_id);
		req.setAttribute("s_name", s_name);
		req.setAttribute("message", message);
		req.setAttribute("g_barcode", g_barcode);
		if("hege".equals(message)){
			req.getRequestDispatcher("/pages/goods/goodsinfo/addproduct-info.jsp").forward(req, resp);
		}else {
			req.getRequestDispatcher("/pages/goods/goodsinfo/addproduct.jsp").forward(req, resp);
		}
		
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
			findGoodByPage(req,resp);
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
		String s_id = req.getParameter("s_id");
		//必填资料
				
				String s_name = req.getParameter("s_name");
				String g_name = req.getParameter("g_name");
				String g_del = req.getParameter("g_del");
				String g_stock_num = req.getParameter("g_stock_num");
				String g_sale_price = req.getParameter("g_sale_price");
				String g_pur_price = req.getParameter("g_pur_price");
				String c_name = req.getParameter("c_name");
				String g_barcode = req.getParameter("g_barcode");
				//扩展资料
				String g_pm = req.getParameter("g_pm");
				String su_name = req.getParameter("su_name");
				String g_stock_max = req.getParameter("g_stock_max");
				String g_stock_min = req.getParameter("g_stock_min");
				String g_trade_price = req.getParameter("g_trade_price");
				String vip_id = req.getParameter("vip_id");
				String g_vip_price = req.getParameter("g_vip_price");
				String g_prod_date = req.getParameter("g_prod_date");
				String g_giq = req.getParameter("g_giq");
				String zdy1 = req.getParameter("zdy1");
				String zdy2 = req.getParameter("zdy2");
				String zdy3 = req.getParameter("zdy3");
				String zdy4 = req.getParameter("zdy4");
				//报表参数
				String g_qd_min = req.getParameter("g_qd_min");
				String g_cl_min = req.getParameter("g_cl_min");
				String g_stock_nor = req.getParameter("g_stock_nor");
				String g_flag = req.getParameter("g_flag");
				String g_best = req.getParameter("g_best");
				String g_sale_nor = req.getParameter("g_sale_nor");
				//商品描述
				String g_info=req.getParameter("g_info");
				//图片路径
				String g_img_path=req.getParameter("g_img_path");
				//
				String g_integral=req.getParameter("g_integral");
				String c_id=req.getParameter("c_id");
				
				String unit_id=req.getParameter("unit_id");
				String g_unit=req.getParameter("g_unit");
				String g_howmuch="";
				if (g_unit.equals("无")) {
					unit_id="0";
				 g_howmuch="1";
				}else{
					String sql="select g_howmuch from unit where g_unit=? and s_id=?";
					List<Object[]>list=SqlHelper.find(sql, g_unit,s_id);
				 g_howmuch=(String) list.get(0)[0];
				}
				
				
				
		try {

			good.editgood( s_name, g_name, g_del,
					g_stock_num, g_sale_price, g_pur_price, c_name,g_barcode,
					g_pm,g_stock_max,g_trade_price,g_prod_date,zdy1,zdy3,
					s_name,g_stock_min,vip_id,g_vip_price,g_giq,zdy2,zdy4,
					g_qd_min,g_cl_min,g_stock_nor,g_flag,g_best,g_sale_nor,
					g_info,g_img_path, Integer.valueOf(g_id),g_integral,c_id,g_unit,g_howmuch,unit_id);
			
			findGoodByPage(req,resp);
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
		String s_id =  req.getParameter("s_id");
		String list =  req.getParameter("list");
		
		req.setAttribute("list", list);
		String sql="select c_id,c_name from category where s_id=? ";
		List<Object[]> fenlei = SqlHelper.find(sql, s_id);
		req.setAttribute("fenlei", fenlei);
		String sql1="select su_id,su_name from supplier where s_id=? ";
		List<Object[]> suppliers = SqlHelper.find(sql1, s_id);
		req.setAttribute("suppliers", suppliers);
		
		String sql3="select unit_id, g_unit from unit where s_id=? ";
		List<Object[]> danwei = SqlHelper.find(sql3, s_id);
		req.setAttribute("danwei", danwei);
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
		s_name=s_name.trim();
		req.setAttribute("s_id", s_id);
		req.setAttribute("s_name", s_name);
		req.setAttribute("message", "");
		
		
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
	 * 
	 * 
	 * @param req
	 * @param resp
	 */
	private void addGood(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		//必填资料
		String s_id = req.getParameter("s_id");
		String s_name = req.getParameter("s_name");
		String g_name = req.getParameter("g_name");
		String g_del = req.getParameter("g_del");
		String g_stock_num = req.getParameter("g_stock_num");
		String g_sale_price = req.getParameter("g_sale_price");
		String g_pur_price = req.getParameter("g_pur_price");
		String c_name = req.getParameter("c_name");
		String g_barcode = req.getParameter("g_barcode");
		//扩展资料
		String g_pm = req.getParameter("g_pm");
		String su_name = req.getParameter("su_name");
		String g_stock_max = req.getParameter("g_stock_max");
		String g_stock_min = req.getParameter("g_stock_min");
		String g_trade_price = req.getParameter("g_trade_price");
		String vip_id = req.getParameter("vip_id");
		String g_vip_price = req.getParameter("g_vip_price");
		String g_prod_date = req.getParameter("g_prod_date");
		String g_giq = req.getParameter("g_giq");
		String zdy1 = req.getParameter("zdy1");
		String zdy2 = req.getParameter("zdy2");
		String zdy3 = req.getParameter("zdy3");
		String zdy4 = req.getParameter("zdy4");
		//报表参数
		String g_qd_min = req.getParameter("g_qd_min");
		String g_cl_min = req.getParameter("g_cl_min");
		String g_stock_nor = req.getParameter("g_stock_nor");
		String g_flag = req.getParameter("g_flag");
		String g_best = req.getParameter("g_best");
		String g_sale_nor = req.getParameter("g_sale_nor");
		//商品描述
		String g_info=req.getParameter("g_info");
		//图片路径
		String g_img_path=req.getParameter("g_img_path");
		String g_integral=req.getParameter("g_integral");
		String c_id=req.getParameter("c_id");
		String unit_id=req.getParameter("unit_id");
		String g_unit=req.getParameter("g_unit");
		String g_howmuch="";
		
			String sql="select g_howmuch from unit where g_unit=? and s_id=?";
			List<Object[]>list=SqlHelper.find(sql, g_unit,s_id);
		 g_howmuch=(String) list.get(0)[0];
		
		
		try {
			good.addgood(s_id, s_name, g_name, g_del,
					g_stock_num, g_sale_price, g_pur_price, c_name,g_barcode,
					g_pm,g_stock_max,g_trade_price,g_prod_date,zdy1,zdy3,
					su_name,g_stock_min,vip_id,g_vip_price,g_giq,zdy2,zdy4,
					g_qd_min,g_cl_min,g_stock_nor,g_flag,g_best,g_sale_nor,
					g_info,g_img_path,g_integral,c_id,unit_id,g_unit,g_howmuch);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//9
		req.setAttribute("s_id", s_id);
		req.setAttribute("s_name", s_name);
		req.setAttribute("g_name", g_name);
		req.setAttribute("g_del", g_del);
		req.setAttribute("g_stock_num", g_stock_num);
		req.setAttribute("g_sale_price", g_sale_price);
		req.setAttribute("g_pur_price", g_pur_price);
		req.setAttribute("c_name", c_name);
		req.setAttribute("g_barcode", g_barcode);
		//13
		req.setAttribute("g_pm", g_pm);
		req.setAttribute("su_name", su_name);
		req.setAttribute("g_stock_max", g_stock_max);
		req.setAttribute("g_stock_min", g_stock_min);
		req.setAttribute("g_trade_price", g_trade_price);
		req.setAttribute("vip_id", vip_id);
		req.setAttribute("g_vip_price", g_vip_price);
		req.setAttribute("g_prod_date", g_prod_date);
		req.setAttribute("g_giq", g_giq);
		req.setAttribute("zdy1", zdy1);
		req.setAttribute("zdy2", zdy2);
		req.setAttribute("zdy3", zdy3);
		req.setAttribute("zdy4", zdy4);
		//6
		req.setAttribute("g_qd_min", g_qd_min);
		req.setAttribute("g_cl_min", g_cl_min);
		req.setAttribute("g_stock_nor", g_stock_nor);
		req.setAttribute("g_flag", g_flag);
		req.setAttribute("g_best", g_best);
		req.setAttribute("g_sale_nor", g_sale_nor);
		//2
		req.setAttribute("g_info", g_info);
		req.setAttribute("g_img_path", g_img_path);
		//
		req.setAttribute("g_integral", g_integral);
		req.setAttribute("c_id", c_id);
		
		req.setAttribute("g_unit", g_unit);
		req.setAttribute("g_howmuch", g_howmuch);
		req.setAttribute("unit_id", unit_id);
	}

	/***
	 * 
	 * 
	 * @param req
	 * @param resp
	 */
	private void findGoodByPage(HttpServletRequest req, HttpServletResponse resp) {
		
	    String method="findByPage";
		String currentPage=req.getParameter("currentPage");
		String which = req.getParameter("which");
		String s_id = req.getParameter("s_id");
		String key = req.getParameter("key");
		String g_del = req.getParameter("g_del");
		String c_name = req.getParameter("c_name");
		if(key==null){
			key="";
		}
		int totalSize = getTotalSize(s_id,key,c_name,g_del);
		int totalPage = 0;
		if(""==currentPage||currentPage==null){
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
				if(pageNo==(totalSize % 10 == 0 ? totalSize / 10 : (totalSize / 10 + 1)))
				{pageNo=pageNo;}else{
				 pageNo++;
				}
				
			} else if ("prev".equals(which)) {
				if (pageNo==1) {
					pageNo=pageNo;
				}else {
					pageNo--;
				}
				
			} else if ("last".equals(which)) {
				totalPage = (totalSize % 10 == 0 ? totalSize / 10 : (totalSize / 10 + 1));
				pageNo = totalPage;
			}else {
				pageNo = Integer.valueOf(which.trim());
			}
			List<Object[]> list = good.goodssearch(Integer.valueOf(s_id), (pageNo-1) * 10,key,c_name,g_del);
			req.setAttribute("goodsList", list);
			req.setAttribute("s_id", s_id);
			req.setAttribute("key", key);
			req.setAttribute("c_name", c_name);
			req.setAttribute("g_del", g_del);
			req.setAttribute("currentPage", pageNo);
			req.setAttribute("totalSize", totalSize);
			HttpSession session=(HttpSession)req.getSession();
			ServletContext application=(ServletContext)session.getServletContext();
			application.setAttribute("method",method);
			//application.setAttribute("currentPage",pageNo);
	}

	private void goodsInfo(HttpServletRequest req, HttpServletResponse resp) {
		List<Object[]> storeList = null;
		HttpSession session = req.getSession();

		try {
			String sql2="select c_id,c_name from category ";
			List<Object[]> fenlei = SqlHelper.find(sql2);
			req.setAttribute("fenlei", fenlei);
			storeList = good.findStoreByUserID(Integer.valueOf(session.getAttribute("uid").toString()));

			req.setAttribute("storeList", storeList);

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		
	    String method="findByPage";
		String currentPage=req.getParameter("currentPage");
		String which = req.getParameter("which");
		String s_id = req.getParameter("s_id");
		String key = req.getParameter("key");
		String g_del = req.getParameter("g_del");
		String c_name = req.getParameter("c_name");
		if(c_name==null){
			c_name="未分类";
		}
		if(key==null){
			key="";
		}
		int totalSize = getTotalSize(s_id,key,c_name,g_del);
		int totalPage = 0;
		if(""==currentPage||currentPage==null){
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
				if(pageNo==(totalSize % 10 == 0 ? totalSize / 10 : (totalSize / 10 + 1)))
				{pageNo=pageNo;}else{
				 pageNo++;
				}
				
			} else if ("prev".equals(which)) {
				if (pageNo==1) {
					pageNo=pageNo;
				}else {
					pageNo--;
				}
				
			} else if ("last".equals(which)) {
				totalPage = (totalSize % 10 == 0 ? totalSize / 10 : (totalSize / 10 + 1));
				pageNo = totalPage;
			}else {
				pageNo = Integer.valueOf(which.trim());
			}
			List<Object[]> list = good.goodssearch(Integer.valueOf(s_id), (pageNo-1) * 10,key,c_name,g_del);
			req.setAttribute("goodsList", list);
			req.setAttribute("s_id", s_id);
			req.setAttribute("key", key);
			req.setAttribute("c_name", c_name);
			req.setAttribute("g_del", g_del);
			req.setAttribute("currentPage", pageNo);
			req.setAttribute("totalSize", totalSize);
			//HttpSession session=(HttpSession)req.getSession();
			ServletContext application=(ServletContext)session.getServletContext();
			application.setAttribute("method",method);
			//application.setAttribute("currentPage",pageNo);
	

	}

	// 
	private int getTotalSize(String s_id, String key, String c_name, String g_del) {
		int totalsize = good.getTotalSize(Integer.valueOf(s_id),key,c_name,g_del);
		return totalsize;
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
