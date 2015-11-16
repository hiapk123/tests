package com.uestc.bean;

import java.util.List;

public class MarketingTccxBean {
	private String name;
	private String start_time;
	private String end_time;
	private String type ;
	private List<String> name_list;
	private List<String> count_list;
	private String p_price;
	private String package_name;
	private String barcodes;
	private String counts;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public List<String> getCount_list() {
		return count_list;
	}
	public void setCount_list(List<String> count_list) {
		this.count_list = count_list;
	}
	public String getP_price() {
		return p_price;
	}
	public void setP_price(String p_price) {
		this.p_price = p_price;
	}
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public String getBarcodes() {
		return barcodes;
	}
	public void setBarcodes(String barcodes) {
		this.barcodes = barcodes;
	}
	public String getCounts() {
		return counts;
	}
	public void setCounts(String counts) {
		this.counts = counts;
	}
	
}
