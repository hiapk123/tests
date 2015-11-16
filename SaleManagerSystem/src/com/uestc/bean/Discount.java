package com.uestc.bean;

/**
 * Discount entity. @author MyEclipse Persistence Tools
 */

public class Discount implements java.io.Serializable {

	// Fields

	private Integer DId;
	private Goods goods;
	private Coupons coupons;
	private Integer DParentId;
	private String DName;
	private String DStartDate;
	private String DEndDate;
	private Integer DType;
	private String DDiscount;
	private Integer SDel;
	private Short activeType;

	// Constructors

	/** default constructor */
	public Discount() {
	}

	/** full constructor */
	public Discount(Goods goods, Coupons coupons, Integer DParentId,
			String DName, String DStartDate, String DEndDate, Integer DType,
			String DDiscount, Integer SDel, Short activeType) {
		this.goods = goods;
		this.coupons = coupons;
		this.DParentId = DParentId;
		this.DName = DName;
		this.DStartDate = DStartDate;
		this.DEndDate = DEndDate;
		this.DType = DType;
		this.DDiscount = DDiscount;
		this.SDel = SDel;
		this.activeType = activeType;
	}

	// Property accessors

	public Integer getDId() {
		return this.DId;
	}

	public void setDId(Integer DId) {
		this.DId = DId;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Coupons getCoupons() {
		return this.coupons;
	}

	public void setCoupons(Coupons coupons) {
		this.coupons = coupons;
	}

	public Integer getDParentId() {
		return this.DParentId;
	}

	public void setDParentId(Integer DParentId) {
		this.DParentId = DParentId;
	}

	public String getDName() {
		return this.DName;
	}

	public void setDName(String DName) {
		this.DName = DName;
	}

	public String getDStartDate() {
		return this.DStartDate;
	}

	public void setDStartDate(String DStartDate) {
		this.DStartDate = DStartDate;
	}

	public String getDEndDate() {
		return this.DEndDate;
	}

	public void setDEndDate(String DEndDate) {
		this.DEndDate = DEndDate;
	}

	public Integer getDType() {
		return this.DType;
	}

	public void setDType(Integer DType) {
		this.DType = DType;
	}

	public String getDDiscount() {
		return this.DDiscount;
	}

	public void setDDiscount(String DDiscount) {
		this.DDiscount = DDiscount;
	}

	public Integer getSDel() {
		return this.SDel;
	}

	public void setSDel(Integer SDel) {
		this.SDel = SDel;
	}

	public Short getActiveType() {
		return this.activeType;
	}

	public void setActiveType(Short activeType) {
		this.activeType = activeType;
	}

}