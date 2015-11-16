package com.uestc.bean;

/**
 * Returns entity. @author MyEclipse Persistence Tools
 */

public class Returns implements java.io.Serializable {

	// Fields

	private Integer retId;
	private Goods goods;
	private Booking booking;
	private String retGNum;
	private String retSerialNum;
	private Short retFlag;
	private String retDate;
	private Short SDel;
	private String SDescription;

	// Constructors

	/** default constructor */
	public Returns() {
	}

	/** minimal constructor */
	public Returns(Integer retId) {
		this.retId = retId;
	}

	/** full constructor */
	public Returns(Integer retId, Goods goods, Booking booking, String retGNum,
			String retSerialNum, Short retFlag, String retDate, Short SDel,
			String SDescription) {
		this.retId = retId;
		this.goods = goods;
		this.booking = booking;
		this.retGNum = retGNum;
		this.retSerialNum = retSerialNum;
		this.retFlag = retFlag;
		this.retDate = retDate;
		this.SDel = SDel;
		this.SDescription = SDescription;
	}

	// Property accessors

	public Integer getRetId() {
		return this.retId;
	}

	public void setRetId(Integer retId) {
		this.retId = retId;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Booking getBooking() {
		return this.booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public String getRetGNum() {
		return this.retGNum;
	}

	public void setRetGNum(String retGNum) {
		this.retGNum = retGNum;
	}

	public String getRetSerialNum() {
		return this.retSerialNum;
	}

	public void setRetSerialNum(String retSerialNum) {
		this.retSerialNum = retSerialNum;
	}

	public Short getRetFlag() {
		return this.retFlag;
	}

	public void setRetFlag(Short retFlag) {
		this.retFlag = retFlag;
	}

	public String getRetDate() {
		return this.retDate;
	}

	public void setRetDate(String retDate) {
		this.retDate = retDate;
	}

	public Short getSDel() {
		return this.SDel;
	}

	public void setSDel(Short SDel) {
		this.SDel = SDel;
	}

	public String getSDescription() {
		return this.SDescription;
	}

	public void setSDescription(String SDescription) {
		this.SDescription = SDescription;
	}

}