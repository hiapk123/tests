package com.uestc.bean;

import java.util.List;

public class MarketingMefxBean {
	private String active_id;
	
	private String name;
	private String start_time;
	private String end_time;
	private String type ;
	private List<String> mane_list;
	private List<String> back_list;
	
	private String manes;
	private String backs;
	private String c_num;
	public String getC_num() {
		return c_num;
	}
	public void setC_num(String c_num) {
		this.c_num = c_num;
	}
	public String getActive_id() {
		return active_id;
	}
	public void setActive_id(String active_id) {
		this.active_id = active_id;
	}
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
	public List<String> getMane_list() {
		return mane_list;
	}
	public void setMane_list(List<String> mane_list) {
		this.mane_list = mane_list;
	}
	public List<String> getBack_list() {
		return back_list;
	}
	public void setBack_list(List<String> back_list) {
		this.back_list = back_list;
	}
	public String getManes() {
		return manes;
	}
	public void setManes(String manes) {
		this.manes = manes;
	}
	public String getBacks() {
		return backs;
	}
	public void setBacks(String backs) {
		this.backs = backs;
	}
	
	
}
