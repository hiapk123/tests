package org.uestc.servlet;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.uestc.serviceImp.HuoliuServiceImp;

/**
 * Servlet implementation class HuoLiuServlet
 */
@WebServlet(urlPatterns = { "/huoliu" })
public class HuoLiuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HuoliuServiceImp huoliu = new HuoliuServiceImp();
	private String realSavePath;
	 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String m = req.getParameter("m");
		if (m.equals("supplierInfo")) {
			this.supplierInfo(req, resp);
			req.getRequestDispatcher("/pages/huoliu/supplierinfo.jsp").forward(req, resp);
		}else if (m.equals("editSupplier")) {
			this.editSupplier(req,resp);
			req.getRequestDispatcher("/pages/huoliu/editSupplier.jsp").forward(req,resp);
		}else if (m.equals("editSupplier2")) {
			this.editSupplier2(req,resp);
		    req.getRequestDispatcher("/pages/huoliu/supplierinfo.jsp").forward(req,resp);
		}else if (m.equals("daoru")) {
			this.daoru(req,resp);
		    req.getRequestDispatcher("/pages/huoliu/daoru.jsp").forward(req,resp);
		}else if (m.equals("Shangchuanwenjian")) {
				this.shangchuan(req,resp);
				req.getRequestDispatcher("/pages/huoliu/success.jsp").forward(req,resp);
	    }else if (m.equals("daochu")) {
			this.daochu(req,resp);
			req.getRequestDispatcher("/pages/huoliu/daochu.jsp").forward(req, resp);
		}else if (m.equals("toExcel")) {
				this.toExcel(req,resp);
				req.getRequestDispatcher("/pages/huoliu/toExcel.jsp").forward(req, resp);
	    }else if (m.equals("hlgl")) {
			this.hlgl(req,resp);
			req.getRequestDispatcher("/pages/huoliu/huoliuguanli.jsp").forward(req, resp);
        }else if (m.equals("jinhuo")) {
			this.jinhuo(req,resp);
			req.getRequestDispatcher("/pages/huoliu/jinhuo.jsp").forward(req, resp);
        }else if (m.equals("jinhuo1")) {
			this.jinhuo1(req,resp);
			req.getRequestDispatcher("/pages/huoliu/goodtable.jsp").forward(req, resp);
        }else if (m.equals("diaohuo1")) {
			this.diaohuo1(req,resp);
			req.getRequestDispatcher("/pages/huoliu/goodtable.jsp").forward(req, resp);
        }else if (m.equals("add")) {
			this.add(req,resp);
			req.getRequestDispatcher("/pages/huoliu/add.jsp").forward(req, resp);
        }else if (m.equals("xzmd")) {
			this.xzmd(req,resp);
			req.getRequestDispatcher("/pages/huoliu/xzmd.jsp").forward(req, resp);
        }else if (m.equals("xzmd1")) {
			this.xzmd1(req,resp);
			req.getRequestDispatcher("/pages/huoliu/xzmd1.jsp").forward(req, resp);
        }else if (m.equals("dr")) {
			this.dr(req,resp);
			req.getRequestDispatcher("/pages/huoliu/dr.jsp").forward(req, resp);
        }else if (m.equals("shangchuan")) {
			this.shangchuan(req,resp);
			req.getRequestDispatcher("/pages/huoliu/success.jsp").forward(req, resp);
        }else if (m.equals("DR")) {
			this.DR(req,resp);
			req.getRequestDispatcher("/pages/huoliu/add.jsp").forward(req, resp);
        }else if (m.equals("Inforuku")) {
			this.Inforuku(req,resp);
			req.getRequestDispatcher("/pages/huoliu/huoliuguanli.jsp").forward(req, resp);
        }else if (m.equals("ruku")) {
			this.ruku(req,resp);
			req.getRequestDispatcher("/pages/huoliu/huoliuguanli.jsp").forward(req, resp);
        }else if (m.equals("zhijieruku1")) {
			this.zhijieruku1(req,resp);
			req.getRequestDispatcher("/pages/huoliu/huoliuguanli.jsp").forward(req, resp);
        }else if (m.equals("findhl")) {
			this.findhl(req,resp);
			req.getRequestDispatcher("/pages/huoliu/findhl.jsp").forward(req, resp);
        }else if (m.equals("detail")) {
			this.detail(req,resp);
			req.getRequestDispatcher("/pages/huoliu/orderdetail.jsp").forward(req, resp);
        }else if (m.equals("diaohuo")) {
			this.diaohuo(req,resp);
			req.getRequestDispatcher("/pages/huoliu/diaohuo.jsp").forward(req, resp);
        }
		
		
		
	}
	private void ruku(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String list=req.getParameter("list");
		String s_id_in=req.getParameter("s_id");
	
		String l_info=req.getParameter("l_info");
	
		
	     list=list.trim();
		huoliu.ruku(list,s_id_in,l_info);
		hlgl(req, resp);
	}

	/***
	 * 调货完成入库
	 * @param req
	 * @param resp
	 */
	private void zhijieruku1(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String s_id_out=req.getParameter("s_id_out");
		String s_id_in=req.getParameter("s_id_in");
		String list=req.getParameter("list");
		String l_info=req.getParameter("l_info");
	
	     list=list.trim();
		huoliu.zhijieruku1(list,s_id_out,s_id_in,l_info);
		hlgl(req, resp);
	}

	/***
	 * 调货
	 * @param req
	 * @param resp
	 */
	private void diaohuo1(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String s_id_out=req.getParameter("s_id_out");
		String s_id_in=req.getParameter("s_id_in");
		String s_name=req.getParameter("s_name");
		String shuru=req.getParameter("shuru");
	    List<Object[]> list = huoliu.search(s_id_out,shuru);
	    if(list.size()==0){
	    	req.setAttribute("status", "找不到相关商品,请关闭窗口重新输入");
	    }
	    req.setAttribute("s_id_out", s_id_out);
		req.setAttribute("s_id_in", s_id_in);
	    req.setAttribute("s_name", s_name);
	   
	    req.setAttribute("goodsList", list);
	}

	/***
	 * 选择调货收货门店
	 * @param req
	 * @param resp
	 */
	private void xzmd1(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String s_id_out=req.getParameter("s_id_out");
		String s_id_in=req.getParameter("s_id_in");
		req.setAttribute("s_id_out", s_id_out);
		req.setAttribute("s_id_in", s_id_in);
	}

	/***
	 * 调货
	 * @param req
	 * @param resp
	 */
	private void diaohuo(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		HttpSession session=req.getSession();
		List <Object[]> storeList=huoliu.findStoreByUserID(Integer.valueOf(session.getAttribute("uid").toString()));
	    req.setAttribute("storeList", storeList);
	}

	/**
	 * 订单页面
	 * @param req
	 * @param resp
	 */
private void detail(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String list=req.getParameter("list");
		/*System.out.println(list);
		String order[]=list.split(" ");*/
		req.setAttribute("order", list);
	}

/***
 * 货单查询
 * @param req
 * @param resp
 */
	
private void findhl(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String s_id=req.getParameter("s_id");
		String type=req.getParameter("type");
		 List<Object[]> list=huoliu.findhl(s_id,type);
		req.setAttribute("list", list);
	}

private void Inforuku(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String list=req.getParameter("list");
		String s_id_in=req.getParameter("s_id");
		String l_pre_price=req.getParameter("l_pre_price");
		String l_info=req.getParameter("l_info");
		
		System.out.println("list:   "+list);
	     list=list.trim();
		huoliu.Inforuku(list,s_id_in,l_pre_price,l_info);
		hlgl(req, resp);
	}


/***
 * 进入add.jsp页面
 * @param req
 * @param resp
 */
private void DR(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
	
		 try{
			 //System.out.println(realSavePath);
			 if(realSavePath==null){
				 req.getRequestDispatcher("/pages/huoliu/tishi.jsp").forward(req, resp);
			 }else if(realSavePath!=""){
				 String s_id=req.getParameter("s_id");
				 huoliu.dr(req,resp,realSavePath,Integer.valueOf(s_id));
				 
			 }
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
	}
	

/***
 * 进入导入货单页面
 * @param req
 * @param resp
 */
	private void dr(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String s_id=req.getParameter("s_id");
	
		req.setAttribute("s_id", s_id);
	}

	private void xzmd(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String s_id=req.getParameter("s_id");
		String mm=req.getParameter("mm");
		req.setAttribute("s_id", s_id);
	}
	 
	private void add(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
		String s_id=req.getParameter("s_id");
		String g_name=req.getParameter("g_name");
		String g_barcode=req.getParameter("g_barcode");
		String g_stock_num=req.getParameter("g_stock_num");
		String su_name=req.getParameter("su_name");
		String g_pur_price=req.getParameter("g_pur_price");
		String g_id=req.getParameter("g_id");
		String arr[]=new String[]{g_name,g_barcode,g_stock_num,su_name,"1",g_pur_price,g_id};
		ArrayList list = new ArrayList();
	 
	    list.add(arr);
	   
	  
	    req.setAttribute("s_id", s_id);
	    req.setAttribute("List", list);
	  
	  
	}

	private void jinhuo1(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

		String s_id=req.getParameter("s_id");
		String s_name=req.getParameter("s_name");
		String shuru=req.getParameter("shuru");
	    List<Object[]> list = huoliu.search(s_id,shuru);
	    if(list.size()==0){
	    	req.setAttribute("status", "找不到相关商品,请关闭窗口重新输入");
	    }
	    req.setAttribute("s_id", s_id);
	    req.setAttribute("s_name", s_name);
	   
	    req.setAttribute("goodsList", list);
	    
	}

	/***
	 * 进入进货页面
	 * @param req
	 * @param resp
	 */
	private void jinhuo(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
		String s_id=req.getParameter("s_id");
		String s_name=req.getParameter("s_name");
		HttpSession session=req.getSession();
		List <Object[]> storeList=huoliu.findStoreByUserID(Integer.valueOf(session.getAttribute("uid").toString()));
	    req.setAttribute("storeList", storeList);
	    req.setAttribute("s_id", s_id);
	    req.setAttribute("s_name", s_name);
	  
	    
	}

	/***
	 * 进入货流管理页面
	 * @param req
	 * @param resp
	 */
	private void hlgl(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		HttpSession session=req.getSession();
		List <Object[]> storeList=huoliu.findStoreByUserID(Integer.valueOf(session.getAttribute("uid").toString()));
	    req.setAttribute("storeList", storeList);
	}
/***
 * 进入导出页面
 * @param req
 * @param resp
 */
	private void daochu(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
        String s_id = req.getParameter("s_id");
		req.setAttribute("s_id", s_id);
	}
/***
 * 将供货商资料导入表格并下载
 * @param req
 * @param resp
 */
	private void toExcel(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
        String s_id = req.getParameter("s_id");
		List<Object[]> list = huoliu.toExcel(Integer.valueOf(s_id));
		req.setAttribute("supplierList", list);
		req.setAttribute("s_id", s_id);
	}

	/***
	 * 上传文件
	 * @param req
	 * @param resp
	 * @throws Exception 
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
                     realSavePath = makePath(filename, savePath);
                     
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
         String m=req.getParameter("m");
         if (m.equals("Shangchuanwenjian")) {
        	 try {
     			huoliu.importExcel(req,resp);
     			
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
	 * 进入导入页面
	 * @param req
	 * @param resp
	 */
	private void daoru(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String s_id=req.getParameter("s_id");
		String s_name=req.getParameter("s_name");
		req.setAttribute("s_id", s_id);
		req.setAttribute("s_name", s_name);
	}

	/***
	 * 修改用户信息页面
	 * @param req
	 * @param resp
	 */
	private void editSupplier2(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		HttpSession session=req.getSession();
		String su_id=req.getParameter("su_id");
		String su_number=req.getParameter("su_number");
		String su_name=req.getParameter("su_name");
		String su_contacter=req.getParameter("su_contacter");
		String su_phone=req.getParameter("su_phone");
		String su_email=req.getParameter("su_email");
		String su_empower=req.getParameter("su_empower");
		huoliu.editSupplier(su_number, su_name, su_contacter, su_phone, su_email, su_empower,su_id);
		supplierInfo(req,resp);
	}

	/***
	 * 进入修改用户信息页面
	 * @param req
	 * @param resp
	 */
private void editSupplier(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String su_id=req.getParameter("su_id");
		String su_number=req.getParameter("su_number");
		String su_name=req.getParameter("su_name");
		String su_contacter=req.getParameter("su_contacter");
		String su_phone=req.getParameter("su_phone");
		String su_email=req.getParameter("su_email");
		String su_empower=req.getParameter("su_empower");
		//huoliu.editSupplier(su_number, su_name, su_contacter, su_phone, su_email, su_empower,su_id);
		req.setAttribute("su_id", su_id);
		req.setAttribute("su_number",su_number );
		req.setAttribute("su_name",su_name );
		req.setAttribute("su_contacter",su_contacter);
		req.setAttribute("su_phone",su_phone );
		req.setAttribute("su_email",su_email );
		req.setAttribute("su_empower", su_empower);
		
		
	}

/***
 * 初始页面
 * @param req
 * @param resp
 */
	private void supplierInfo(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		HttpSession session=req.getSession();
		String s_id=req.getParameter("s_id");
		String s_name=req.getParameter("s_name");
		List <Object[]> list=huoliu.supplierInfo(Integer.valueOf(s_id));
		List <Object[]> storeList=huoliu.findStoreByUserID(Integer.valueOf(session.getAttribute("uid").toString()));
		req.setAttribute("s_id", s_id);
		req.setAttribute("s_name", s_name);
		req.setAttribute("list", list);
		req.setAttribute("storeList", storeList);
	}


}

