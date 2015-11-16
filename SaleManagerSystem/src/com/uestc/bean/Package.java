package com.uestc.bean;

/**
 * Package entity. @author MyEclipse Persistence Tools
 */

public class Package implements java.io.Serializable {

	// Fields

	private Integer PId;
	private Coupons coupons;
	private Integer PParentId;
	private Integer EPackType;
	private String PName;
	private String PStartDate;
	private String PEndDate;
	private Integer PType;
	private Integer PGoods;
	private Integer PGoodsNum;
	private String PPrice;
	private Short PIsdisplay;
	private Integer SDel;

	// Constructors

	/** default constructor */
	public Package() {
	}

	/** full constructor */
	public Package(Coupons coupons, Integer PParentId, Integer EPackType,
			String PName, String PStartDate, String PEndDate, Integer PType,
			Integer PGoods, Integer PGoodsNum, String PPrice, Short PIsdisplay,
			Integer SDel) {
		this.coupons = coupons;
		this.PParentId = PParentId;
		this.EPackType = EPackType;
		this.PName = PName;
		this.PStartDate = PStartDate;
		this.PEndDate = PEndDate;
		this.PType = PType;
		this.PGoods = PGoods;
		this.PGoodsNum = PGoodsNum;
		this.PPrice = PPrice;
		this.PIsdisplay = PIsdisplay;
		this.SDel = SDel;
	}

	// Property accessors

	public Integer getPId() {
		return this.PId;
	}

	public void setPId(Integer PId) {
		this.PId = PId;
	}

	public Coupons getCoupons() {
		return this.coupons;
	}

	public void setCoupons(Coupons coupons) {
		this.coupons = coupons;
	}

	public Integer getPParentId() {
		return this.PParentId;
	}

	public void setPParentId(Integer PParentId) {
		this.PParentId = PParentId;
	}

	public Integer getEPackType() {
		return this.EPackType;
	}

	public void setEPackType(Integer EPackType) {
		this.EPackType = EPackType;
	}

	public String getPName() {
		return this.PName;
	}

	public void setPName(String PName) {
		this.PName = PName;
	}

	public String getPStartDate() {
		return this.PStartDate;
	}

	public void setPStartDate(String PStartDate) {
		this.PStartDate = PStartDate;
	}

	public String getPEndDate() {
		return this.PEndDate;
	}

	public void setPEndDate(String PEndDate) {
		this.PEndDate = PEndDate;
	}

	public Integer getPType() {
		return this.PType;
	}

	public void setPType(Integer PType) {
		this.PType = PType;
	}

	public Integer getPGoods() {
		return this.PGoods;
	}

	public void setPGoods(Integer PGoods) {
		this.PGoods = PGoods;
	}

	public Integer getPGoodsNum() {
		return this.PGoodsNum;
	}

	public void setPGoodsNum(Integer PGoodsNum) {
		this.PGoodsNum = PGoodsNum;
	}

	public String getPPrice() {
		return this.PPrice;
	}

	public void setPPrice(String PPrice) {
		this.PPrice = PPrice;
	}

	public Short getPIsdisplay() {
		return this.PIsdisplay;
	}

	public void setPIsdisplay(Short PIsdisplay) {
		this.PIsdisplay = PIsdisplay;
	}

	public Integer getSDel() {
		return this.SDel;
	}

	public void setSDel(Integer SDel) {
		this.SDel = SDel;
	}

}