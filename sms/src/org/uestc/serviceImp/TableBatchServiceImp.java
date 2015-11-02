package org.uestc.serviceImp;

import java.util.ArrayList;
import java.util.List;

import org.uestc.dao.AnalyseGoodsDao;
import org.uestc.dao.BatchGoodsDAO;
import org.uestc.dao.CategoryGoodsDao;
import org.uestc.daoImp.AnalyseGoodsDaoImp;
import org.uestc.daoImp.BatchGoodsDAOImp;
import org.uestc.daoImp.CategoryGoodsDaoImp;
import org.uestc.service.TableBatchService;

import com.uestc.bean.batchgoods;

public class TableBatchServiceImp implements TableBatchService {

	
	// 编写测试方法看是否能够实现
		public static void main(String[] args) {
			List<batchgoods> sh = new ArrayList<batchgoods>();
			List<batchgoods> sh1 = new ArrayList<batchgoods>();
			List<batchgoods> sh2 = new ArrayList<batchgoods>();
			TableBatchService haha = new TableBatchServiceImp();
			System.out.println("测试函数");
			sh = haha.showtablebatchbyinit();
			System.out.println(sh.size());

		}

		BatchGoodsDAO bathdao = new BatchGoodsDAOImp();

		CategoryGoodsDao category=new CategoryGoodsDaoImp();
		//定义获取�?售价的objective【�??
		public String shsaleprice(String ids)
		{
			String ssql="select g_sale_price from goods where g_id in ("+ids+")";
			return bathdao.shsalefind(ssql);
		}
		
		//绑定提成下拉�?
				public List<Object[]> shticheng()
				{
					String sqlv="select com_id,com_name from commission_rule";
					return category.find(sqlv);
					
				}
				
				public void shaddmeminsert(String newsql)
				{
					bathdao.shinsert(newsql, null);
				}
			
		 //这里是定义更新的操作
	      //这里是定义更新的操作
		//更新商品提成//更新商品提成
		   public void shupdatetcgz(int shtc,String ids)
		   {
			   String newsql6="UPDATE goods SET com_id="+shtc+" WHERE g_id  in ("+ids+")";
			   bathdao.shupdate(newsql6, null);
		   }
		
		   //更新分类(int)
		   public void shupdateydfl(String ydfl,String ids)
		   {
			   String newsql1="UPDATE goods SET c_name="+"'"+ydfl+"'"+" WHERE g_id in ("+ids+")";
			   bathdao.shupdate(newsql1, null);
		   }
		//更新供货�?
		   public void shupdateydghs(String ydghs,String ids)
		   {
			   String newsql2="UPDATE goods SET su_name="+"'"+ydghs+"'"+"WHERE g_id in ("+ids+")";
			   bathdao.shupdate(newsql2, null);
		   }
		//更新商品积分
		   public void shupdateshjf(String shjf,String ids)
		   {
			   String newsql3="UPDATE goods SET g_integral="+"'"+shjf+"'"+"WHERE g_id in ("+ids+")";
			   bathdao.shupdate(newsql3, null);
			   
		   }
		//更新会员优惠
		   public void shupdatehyyh(String yhhy,String ids)
		   {
			   String newsql4="UPDATE goods SET g_vip_price="+"'"+yhhy+"'"+"WHERE g_id in ("+ids+")";
			   bathdao.shupdate(newsql4, null);
			   
		   }
		//更新启用或�?�禁用的状�??
		   public void shupdateshqyjy(int shqyjy,String ids)
		   {
			   String newsql5="UPDATE goods SET g_flag="+shqyjy+" WHERE g_id in ("+ids+")";
			   bathdao.shupdate(newsql5, null);
			   
		   }
		
		
		// 初始化的页面�?
		public int numofdate()
		{
			String sql = "SELECT count(*) from goods a LEFT JOIN commission_rule b  ON a.c_id=b.com_id";
			return bathdao.countfind(sql);

		}

		// 这里是显示列表，页面加载显示�?属页�?
		public List<batchgoods> showtablebatchbyinit() {
			
			String sql = "SELECT a.g_id,a.g_name,a.g_barcode,a.c_name,a.g_vip_price,a.su_name,a.g_flag,a.g_integral,a.s_name,b.com_name,a.s_name from goods a LEFT JOIN commission_rule b  ON a.c_id=b.com_id GROUP BY a.s_name";
			return bathdao.find(sql);

		}
		
		public List<batchgoods> showtablebatchbyinit1() {
			
			String sql = "SELECT a.g_id,a.g_name,a.g_barcode,a.c_name,a.g_vip_price,a.su_name,a.g_flag,a.g_integral,a.s_name,b.com_name,a.s_name from goods a LEFT JOIN commission_rule b  ON a.c_id=b.com_id GROUP BY a.c_name";
			return bathdao.find(sql);

		}
		
		public List<batchgoods> showtablebatchbyinit2() {
			
			String sql = "SELECT a.g_id,a.g_name,a.g_barcode,a.c_name,a.g_vip_price,a.su_name,a.g_flag,a.g_integral,a.s_name,b.com_name,a.s_name from goods a LEFT JOIN commission_rule b  ON a.c_id=b.com_id GROUP BY a.su_name";
			return bathdao.find(sql);

		}
		
		
		// 门店查询�?有的页面的数量的�?

		public int numofdate1(String storename) {

			String sql1 = "SELECT count(*) from goods a LEFT JOIN commission_rule b  ON a.c_id=b.com_id where a.s_name="
					+ storename;
			return bathdao.countfind(sql1);
		}

		// 根据门店的名字进行查�?
		public List<batchgoods> showcatedate(String storename) {
			
			
			String sql1 = "SELECT a.g_name,a.g_barcode,a.c_name,a.g_vip_price,a.su_name,a.g_flag,a.g_integral,a.s_name,b.com_name,a.s_name from goods a LEFT JOIN commission_rule b  ON a.c_id=b.com_id where a.s_name="
					+ storename;
			return bathdao.find(sql1);
		}

		// 分页（多条件的联合查询）
		// 实现多个条件的联合查�?
		public List<batchgoods> showgoodsdate(String mendianming, int qiyongzhuangtai, String fenleiming,
				String gonghuoshang, String tiaoma) {
			
			String sql2 = "SELECT count(*) from goods a LEFT JOIN commission_rule b  ON a.c_id=b.com_id a.s_name="
					+ mendianming + " and a.g_flag=" + qiyongzhuangtai + " and a.c_name= " +"'"+ fenleiming +"'"+ " and a.su_name="
					+ gonghuoshang + " and a.g_barcode= " + tiaoma;

			return bathdao.find(sql2);

		}

		// 实现多个条件的联合查�?
		public int numofdate2(String mendianming, int qiyongzhuangtai, String fenleiming, String gonghuoshang,String tiaoma) {
			String sql2 = "SELECT a.g_name,a.g_barcode,a.c_name,a.g_vip_price,a.su_name,a.g_flag,a.g_integral,a.s_name,b.com_name,a.s_name from goods a LEFT JOIN commission_rule b  ON a.c_id=b.com_id a.s_name="
					+ mendianming + " and a.g_flag=" + qiyongzhuangtai + " and a.c_name= " + fenleiming + " and a.su_name="
					+ gonghuoshang + " and a.g_barcode= " + tiaoma;

			return bathdao.countfind(sql2);

		}
		
		//分页函数的实�?
		public List<batchgoods> mshowtablebatchbyinit(int currentpage) 
		{
			int current=10*currentpage;
			String sql = "SELECT a.g_name,a.g_barcode,a.c_name,a.g_vip_price,a.su_name,a.g_flag,a.g_integral,a.s_name,b.com_name,a.s_name from goods a LEFT JOIN commission_rule b  ON a.c_id=b.com_id limit "+current+",10";
			return bathdao.find(sql);

		}
		public List<batchgoods> mshowcatedate(String storename,int currentpage) 
		{
			
			int current=10*currentpage;
			String sql1 = "SELECT a.g_name,a.g_barcode,a.c_name,a.g_vip_price,a.su_name,a.g_flag,a.g_integral,a.s_name,b.com_name,a.s_name from goods a LEFT JOIN commission_rule b  ON a.c_id=b.com_id where a.s_name="
					+ storename+"limit"+current+",10";
			return bathdao.find(sql1);
		}
		
		public List<batchgoods> mshowgoodsdate(String mendianming, int qiyongzhuangtai, String fenleiming,String gonghuoshang, String tiaoma,int currentpage)
		{
			int current=10*currentpage;	
			String sql2 = "SELECT count(*) from goods a LEFT JOIN commission_rule b  ON a.c_id=b.com_id a.s_name="
					+ mendianming + " and a.g_flag=" + qiyongzhuangtai + " and a.c_name= " +"'"+ fenleiming+"'" + " and a.su_name="
					+ gonghuoshang + " and a.g_barcode= " + tiaoma+"limit"+current+",10";

			return bathdao.find(sql2);

		}
		
		//分页功能的实�?,分为有商品查询名称情况和无商品查询名称情�?
		
		//没有条码的查询情�?
		public int mshcount(String mendianming, int qiyongzhuangtai, String fenleiming,String gonghuoshang)
		{
			
			String sql2 = "SELECT count(*) from goods a LEFT JOIN commission_rule b  ON a.c_id=b.com_id where a.s_name="+"'" +mendianming+"'" + " and a.g_flag=" + qiyongzhuangtai + " and a.c_name= " + "'"+fenleiming +"'"+ " and a.su_name="+"'"+ gonghuoshang+"'";
			//System.out.println("tiaomajinruchengg");
			return bathdao.countfind(sql2);
			
			
		}
		
		//有商品条码的查询情况
		public int yshcount(String mendianming, int qiyongzhuangtai, String fenleiming,String gonghuoshang, String tiaoma)
		{
			
			String sql2 = "SELECT count(*) from goods a LEFT JOIN commission_rule b  ON a.c_id=b.com_id  where a.s_name="+"'"+ mendianming +"'"+ " and a.g_flag=" + qiyongzhuangtai + " and a.c_name= " +"'"+ fenleiming +"'"+ " and a.su_name="+ "'"+gonghuoshang+"'" + " and a.g_barcode= " +"'" +tiaoma+"'";
			return bathdao.countfind(sql2);
				
		}
		
		//没有条码查询情况
		public List<batchgoods> yshbathdate(String mendianming, int qiyongzhuangtai, String fenleiming,String gonghuoshang,int currentpage)
		{
			currentpage=(currentpage-1)*10;
			String sql2 = "SELECT a.g_id,a.g_name,a.g_barcode,a.c_name,a.g_vip_price,a.su_name,a.g_flag,a.g_integral,a.s_name,b.com_name,a.s_name from goods a LEFT JOIN commission_rule b  ON a.com_id=b.com_id  where a.s_name="+"'"+mendianming+"'"+"and a.g_flag="+qiyongzhuangtai+" and a.c_name="+"'"+fenleiming+"'"+" and a.su_name="+"'"+gonghuoshang+"'"+" limit "+currentpage+",10";
			return bathdao.find(sql2);
		}
		
		//有条码的情况
		public List<batchgoods> mshbathdate(String mendianming, int qiyongzhuangtai, String fenleiming,String gonghuoshang, String tiaoma,int currentpage)
		{
			int current=10*(currentpage-1);	
			String sql2 = "SELECT a.g_id,a.g_name,a.g_barcode,a.c_name,a.g_vip_price,a.su_name,a.g_flag,a.g_integral,a.s_name,b.com_name,a.s_name from goods a LEFT JOIN commission_rule b  ON a.c_id=b.com_id  where a.s_name="+"'"+ mendianming +"'"+ " and a.g_flag=" + qiyongzhuangtai + " and a.c_name= " +"'"+ fenleiming+"'" + " and a.su_name="+ "'"+gonghuoshang +"'"+ " and a.g_barcode= " +"'"+ tiaoma+"'"+" limit "+current+",10";
			return bathdao.find(sql2);
			
		}
		
		
		public void datedelete(String str)
		{
			String sql="DELETE FROM goods WHERE g_id in ("+str+")";
			//执行删除的操�?
			bathdao.shdelete(sql, null);
			
		}
		
	

}
