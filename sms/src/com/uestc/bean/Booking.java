package com.uestc.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Booking entity. @author MyEclipse Persistence Tools
 */

public class Booking implements java.io.Serializable {

	// Fields

	private Integer BId;
	private String BNo;
	private Goods goods;
	private String BNum;
	private Integer SId;
	private Store store;
	private String BStatus;
	private String BInfo;
	private String BDate;
	private Short SDel;
	private Set returnses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Booking() {
	}

	/** minimal constructor */
	public Booking(Integer BId) {
		this.BId = BId;
	}

	/** full constructor */
	public Booking(Integer BId, Goods goods, String BNum, Integer SId,
			String BStatus, String BInfo, String BDate, Short SDel,
			Set returnses) {
		this.BId = BId;
		this.goods = goods;
		this.BNum = BNum;
		this.SId = SId;
		this.BStatus = BStatus;
		this.BInfo = BInfo;
		this.BDate = BDate;
		this.SDel = SDel;
		this.returnses = returnses;
	}

	// Property accessors

	public Integer getBId() {
		return this.BId;
	}

	public void setBId(Integer BId) {
		this.BId = BId;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getBNum() {
		return this.BNum;
	}

	public void setBNum(String BNum) {
		this.BNum = BNum;
	}

	public Integer getSId() {
		return this.SId;
	}

	public void setSId(Integer SId) {
		this.SId = SId;
	}

	public String getBStatus() {
		return this.BStatus;
	}

	public void setBStatus(String BStatus) {
		this.BStatus = BStatus;
	}

	public String getBInfo() {
		return this.BInfo;
	}

	public void setBInfo(String BInfo) {
		this.BInfo = BInfo;
	}

	public String getBDate() {
		return this.BDate;
	}

	public void setBDate(String BDate) {
		this.BDate = BDate;
	}

	public Short getSDel() {
		return this.SDel;
	}

	public void setSDel(Short SDel) {
		this.SDel = SDel;
	}

	public Set getReturnses() {
		return this.returnses;
	}

	public void setReturnses(Set returnses) {
		this.returnses = returnses;
	}

	public String getBNo() {
		return BNo;
	}

	public void setBNo(String bNo) {
		BNo = bNo;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

}