package com.uestc.bean;

import java.util.List;

public class MarketingHgcxBean {
	private String active_name;
	private String start_time;
	private String end_time;
	private String type;
	private List<String> name_list;
	private List<String> cnt_list;
	private String m_price;
	private String b_price;
	private String str_barcode;
	public String getActive_name() {
		return active_name;
	}
	public void setActive_name(String active_name) {
		this.active_name = active_name;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getName_list() {
		return name_list;
	}
	public void setName_list(List<String> name_list) {
		this.name_list = name_list;
	}
	public List<String> getCnt_list() {
		return cnt_list;
	}
	public void setCnt_list(List<String> cnt_list) {
		this.cnt_list = cnt_list;
	}
	public String getM_price() {
		return m_price;
	}
	public void setM_price(String m_price) {
		this.m_price = m_price;
	}
	public String getB_price() {
		return b_price;
	}
	public void setB_price(String b_price) {
		this.b_price = b_price;
	}
	public String getStr_barcode() {
		return str_barcode;
	}
	public void setStr_barcode(String str_barcode) {
		this.str_barcode = str_barcode;
	}
	public String getStr_count() {
		return str_count;
	}
	public void setStr_count(String str_count) {
		this.str_count = str_count;
	}
	private String str_count;
}
