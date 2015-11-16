package com.uestc.bean;

//显示列表的实体类
public class batchgoods {
	
	 private int g_id;//主键ID，商品序�?
	 private  String g_name;//商品名称	
	 private String g_barcode;//商品条码
	 private String s_name;//�?属门�?
	 private int c_id;//商品分类
	 private String g_stock_num;//库存
	 private double g_pur_price;//进货�?
	 private double g_sale_price;//�?售价
	 private double maoli;//这里定义的是毛利 进价-售价
	 private String g_vip_price;//会员优惠
	 private String su_name;//供货�?
	 private int g_flag;//是否启用的状�?
	 private String come_name;//商品提成规则
	 private String g_integral;
	 private String c_name;//分类名称
	public int getG_id() {
		return g_id;
	}
	public void setG_id(int g_id) {
		this.g_id = g_id;
	}
	public String getG_name() {
		return g_name;
	}
	public void setG_name(String g_name) {
		this.g_name = g_name;
	}
	public String getG_barcode() {
		return g_barcode;
	}
	public void setG_barcode(String g_barcode) {
		this.g_barcode = g_barcode;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public String getG_stock_num() {
		return g_stock_num;
	}
	public void setG_stock_num(String g_stock_num) {
		this.g_stock_num = g_stock_num;
	}
	public double getG_pur_price() {
		return g_pur_price;
	}
	public void setG_pur_price(double g_pur_price) {
		this.g_pur_price = g_pur_price;
	}
	public double getG_sale_price() {
		return g_sale_price;
	}
	public void setG_sale_price(double g_sale_price) {
		this.g_sale_price = g_sale_price;
	}
	public double getMaoli() {
		return maoli;
	}
	public void setMaoli(double maoli) {
		this.maoli = maoli;
	}
	public String getG_vip_price() {
		return g_vip_price;
	}
	public void setG_vip_price(String g_vip_price) {
		this.g_vip_price = g_vip_price;
	}
	public String getSu_name() {
		return su_name;
	}
	public void setSu_name(String su_name) {
		this.su_name = su_name;
	}
	public int getG_flag() {
		return g_flag;
	}
	public void setG_flag(int g_flag) {
		this.g_flag = g_flag;
	}
	public String getCome_name() {
		return come_name;
	}
	public void setCome_name(String come_name) {
		this.come_name = come_name;
	}
	public String getG_integral() {
		return g_integral;
	}
	public void setG_integral(String g_integral) {
		this.g_integral = g_integral;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	 
	
				
}
