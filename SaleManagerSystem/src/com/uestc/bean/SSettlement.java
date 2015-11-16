package com.uestc.bean;

/**
 * SSettlement entity. @author MyEclipse Persistence Tools
 */

public class SSettlement implements java.io.Serializable {

	// Fields

	private Integer ssId;
	private String ssDate;
	private String ssWlDate;
	private String ssType;
	private String ssGoodsNum;
	private String ssGoodsPrice;
	private String ssDetails;
	private Integer SDel;

	// Constructors

	/** default constructor */
	public SSettlement() {
	}

	/** minimal constructor */
	public SSettlement(Integer ssId) {
		this.ssId = ssId;
	}

	/** full constructor */
	public SSettlement(Integer ssId, String ssDate, String ssWlDate,
			String ssType, String ssGoodsNum, String ssGoodsPrice,
			String ssDetails, Integer SDel) {
		this.ssId = ssId;
		this.ssDate = ssDate;
		this.ssWlDate = ssWlDate;
		this.ssType = ssType;
		this.ssGoodsNum = ssGoodsNum;
		this.ssGoodsPrice = ssGoodsPrice;
		this.ssDetails = ssDetails;
		this.SDel = SDel;
	}

	// Property accessors

	public Integer getSsId() {
		return this.ssId;
	}

	public void setSsId(Integer ssId) {
		this.ssId = ssId;
	}

	public String getSsDate() {
		return this.ssDate;
	}

	public void setSsDate(String ssDate) {
		this.ssDate = ssDate;
	}

	public String getSsWlDate() {
		return this.ssWlDate;
	}

	public void setSsWlDate(String ssWlDate) {
		this.ssWlDate = ssWlDate;
	}

	public String getSsType() {
		return this.ssType;
	}

	public void setSsType(String ssType) {
		this.ssType = ssType;
	}

	public String getSsGoodsNum() {
		return this.ssGoodsNum;
	}

	public void setSsGoodsNum(String ssGoodsNum) {
		this.ssGoodsNum = ssGoodsNum;
	}

	public String getSsGoodsPrice() {
		return this.ssGoodsPrice;
	}

	public void setSsGoodsPrice(String ssGoodsPrice) {
		this.ssGoodsPrice = ssGoodsPrice;
	}

	public String getSsDetails() {
		return this.ssDetails;
	}

	public void setSsDetails(String ssDetails) {
		this.ssDetails = ssDetails;
	}

	public Integer getSDel() {
		return this.SDel;
	}

	public void setSDel(Integer SDel) {
		this.SDel = SDel;
	}

}