package com.uestc.bean;

/**
 * ForGift entity. @author MyEclipse Persistence Tools
 */

public class ForGift implements java.io.Serializable {

	// Fields

	private Integer EId;
	private Coupons coupons;
	private Goods goods;
	private Integer EParentId;
	private Integer EGiftType;
	private String EName;
	private String EStartDate;
	private Integer EType;
	private Integer SDel;
	private String EEndDate;
	private Integer EGoodsNum;
	private Short EGoodsType;

	// Constructors

	/** default constructor */
	public ForGift() {
	}

	/** full constructor */
	public ForGift(Coupons coupons, Goods goods, Integer EParentId,
			Integer EGiftType, String EName, String EStartDate, Integer EType,
			Integer SDel, String EEndDate, Integer EGoodsNum, Short EGoodsType) {
		this.coupons = coupons;
		this.goods = goods;
		this.EParentId = EParentId;
		this.EGiftType = EGiftType;
		this.EName = EName;
		this.EStartDate = EStartDate;
		this.EType = EType;
		this.SDel = SDel;
		this.EEndDate = EEndDate;
		this.EGoodsNum = EGoodsNum;
		this.EGoodsType = EGoodsType;
	}

	// Property accessors

	public Integer getEId() {
		return this.EId;
	}

	public void setEId(Integer EId) {
		this.EId = EId;
	}

	public Coupons getCoupons() {
		return this.coupons;
	}

	public void setCoupons(Coupons coupons) {
		this.coupons = coupons;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Integer getEParentId() {
		return this.EParentId;
	}

	public void setEParentId(Integer EParentId) {
		this.EParentId = EParentId;
	}

	public Integer getEGiftType() {
		return this.EGiftType;
	}

	public void setEGiftType(Integer EGiftType) {
		this.EGiftType = EGiftType;
	}

	public String getEName() {
		return this.EName;
	}

	public void setEName(String EName) {
		this.EName = EName;
	}

	public String getEStartDate() {
		return this.EStartDate;
	}

	public void setEStartDate(String EStartDate) {
		this.EStartDate = EStartDate;
	}

	public Integer getEType() {
		return this.EType;
	}

	public void setEType(Integer EType) {
		this.EType = EType;
	}

	public Integer getSDel() {
		return this.SDel;
	}

	public void setSDel(Integer SDel) {
		this.SDel = SDel;
	}

	public String getEEndDate() {
		return this.EEndDate;
	}

	public void setEEndDate(String EEndDate) {
		this.EEndDate = EEndDate;
	}

	public Integer getEGoodsNum() {
		return this.EGoodsNum;
	}

	public void setEGoodsNum(Integer EGoodsNum) {
		this.EGoodsNum = EGoodsNum;
	}

	public Short getEGoodsType() {
		return this.EGoodsType;
	}

	public void setEGoodsType(Short EGoodsType) {
		this.EGoodsType = EGoodsType;
	}

}