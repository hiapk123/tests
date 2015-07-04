package com.uestc.bean;

/**
 * Swap entity. @author MyEclipse Persistence Tools
 */

public class Swap implements java.io.Serializable {

	// Fields

	private Integer SId;
	private Coupons coupons;
	private Integer SParentId;
	private Integer SSwapType;
	private String SName;
	private String SStartDate;
	private String SEndDate;
	private Integer SType1;
	private Integer SSwapGoods;
	private Integer SGoodsNum;
	private String STotalPrice;
	private String SDifPrice;
	private Integer SDel;

	// Constructors

	/** default constructor */
	public Swap() {
	}

	/** full constructor */
	public Swap(Coupons coupons, Integer SParentId, Integer SSwapType,
			String SName, String SStartDate, String SEndDate, Integer SType1,
			Integer SSwapGoods, Integer SGoodsNum, String STotalPrice,
			String SDifPrice, Integer SDel) {
		this.coupons = coupons;
		this.SParentId = SParentId;
		this.SSwapType = SSwapType;
		this.SName = SName;
		this.SStartDate = SStartDate;
		this.SEndDate = SEndDate;
		this.SType1 = SType1;
		this.SSwapGoods = SSwapGoods;
		this.SGoodsNum = SGoodsNum;
		this.STotalPrice = STotalPrice;
		this.SDifPrice = SDifPrice;
		this.SDel = SDel;
	}

	// Property accessors

	public Integer getSId() {
		return this.SId;
	}

	public void setSId(Integer SId) {
		this.SId = SId;
	}

	public Coupons getCoupons() {
		return this.coupons;
	}

	public void setCoupons(Coupons coupons) {
		this.coupons = coupons;
	}

	public Integer getSParentId() {
		return this.SParentId;
	}

	public void setSParentId(Integer SParentId) {
		this.SParentId = SParentId;
	}

	public Integer getSSwapType() {
		return this.SSwapType;
	}

	public void setSSwapType(Integer SSwapType) {
		this.SSwapType = SSwapType;
	}

	public String getSName() {
		return this.SName;
	}

	public void setSName(String SName) {
		this.SName = SName;
	}

	public String getSStartDate() {
		return this.SStartDate;
	}

	public void setSStartDate(String SStartDate) {
		this.SStartDate = SStartDate;
	}

	public String getSEndDate() {
		return this.SEndDate;
	}

	public void setSEndDate(String SEndDate) {
		this.SEndDate = SEndDate;
	}

	public Integer getSType1() {
		return this.SType1;
	}

	public void setSType1(Integer SType1) {
		this.SType1 = SType1;
	}

	public Integer getSSwapGoods() {
		return this.SSwapGoods;
	}

	public void setSSwapGoods(Integer SSwapGoods) {
		this.SSwapGoods = SSwapGoods;
	}

	public Integer getSGoodsNum() {
		return this.SGoodsNum;
	}

	public void setSGoodsNum(Integer SGoodsNum) {
		this.SGoodsNum = SGoodsNum;
	}

	public String getSTotalPrice() {
		return this.STotalPrice;
	}

	public void setSTotalPrice(String STotalPrice) {
		this.STotalPrice = STotalPrice;
	}

	public String getSDifPrice() {
		return this.SDifPrice;
	}

	public void setSDifPrice(String SDifPrice) {
		this.SDifPrice = SDifPrice;
	}

	public Integer getSDel() {
		return this.SDel;
	}

	public void setSDel(Integer SDel) {
		this.SDel = SDel;
	}

}