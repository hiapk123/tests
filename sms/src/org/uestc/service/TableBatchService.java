package org.uestc.service;

import java.util.List;

import com.uestc.bean.batchgoods;

public interface TableBatchService {
	
	public void shaddmeminsert(String newsql);
	
	public String shsaleprice(String ids);
	//绑定提成下拉�?
	public List<Object[]> shticheng();
	
    //这里是定义更新的操作
	//更新商品提成
	   public void shupdatetcgz(int shtc,String ids);
	//更新分类(int)
	   public void shupdateydfl(String ydfl,String ids);
	//更新供货�?
	   public void shupdateydghs(String ydghs,String ids);
	//更新商品积分
	   public void shupdateshjf(String shjf,String ids);
	//更新会员优惠
	   public void shupdatehyyh(String yhhy,String ids);
	//更新启用或�?�禁用的状�??
	   public void shupdateshqyjy(int shqyjy,String ids);
	
		
	   //这里是初始化查询，显示初始化显示的页面，这里还包括给文本框进行绑�?
		public List<batchgoods> showtablebatchbyinit();
		public List<batchgoods> showtablebatchbyinit1();
		public List<batchgoods> showtablebatchbyinit2();
		
		//这里根据门店名字进行查询显示列表
		public List<batchgoods> showcatedate(String storename);
			
		//根据输入文本的表单进行查询进行查�?
		public List<batchgoods> showgoodsdate(String mendianming,int qiyongzhuangtai,String fenleiming,String gonghuoshang,String tiaoma);

		//用来查询语句的数�?
		public int numofdate();
		public int numofdate1(String storename);
		public int numofdate2(String mendianming,int qiyongzhuangtai,String fenleiming,String gonghuoshang,String tiaoma);
		
		//返回分页的数�?
		public List<batchgoods> mshowtablebatchbyinit(int currentpage);
		public List<batchgoods> mshowcatedate(String storename,int currentpage);
		public List<batchgoods> mshowgoodsdate(String mendianming,int qiyongzhuangtai,String fenleiming,String gonghuoshang,String tiaoma,int currentpage );

		
		//分页函数重写
		//没有条码的情�?
		public int mshcount(String mendianming, int qiyongzhuangtai, String fenleiming,String gonghuoshang);
		//有条码的情况
		public int yshcount(String mendianming, int qiyongzhuangtai, String fenleiming,String gonghuoshang, String tiaoma);
		
		//分页查询函数实现
		//有条码的情况
		public List<batchgoods> mshbathdate(String mendianming, int qiyongzhuangtai, String fenleiming,String gonghuoshang, String tiaoma,int currentpage);
		//没有条码的情�?
		public List<batchgoods> yshbathdate(String mendianming, int qiyongzhuangtai, String fenleiming,String gonghuoshang,int currentpage);
		
		//编写数据的删除的函数
		public void datedelete(String str);
		
		
		
}
