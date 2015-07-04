package com.uestc.bean;

/**
 * CashBack entity. @author MyEclipse Persistence Tools
 */

public class CashBack implements java.io.Serializable {

	// Fields

	private Integer CId;
	private Coupons coupons;
	private String CTotalPricce;
	private String CCashBack;
	private String CName;
	private String CStartDate;
	private String CEndDate;
	private Integer CType;
	private Integer SDel;

	// Constructors

	/** default constructor */
	public CashBack() {
	}

	/** minimal constructor */
	public CashBack(String CTotalPricce) {
		this.CTotalPricce = CTotalPricce;
	}

	/** full constructor */
	public CashBack(Coupons coupons, String CTotalPricce, String CCashBack,
			String CName, String CStartDate, String CEndDate, Integer CType,
			Integer SDel) {
		this.coupons = coupons;
		this.CTotalPricce = CTotalPricce;
		this.CCashBack = CCashBack;
		this.CName = CName;
		this.CStartDate = CStartDate;
		this.CEndDate = CEndDate;
		this.CType = CType;
		this.SDel = SDel;
	}

	// Property accessors

	public Integer getCId() {
		return this.CId;
	}

	public void setCId(Integer CId) {
		this.CId = CId;
	}

	public Coupons getCoupons() {
		return this.coupons;
	}

	public void setCoupons(Coupons coupons) {
		this.coupons = coupons;
	}

	public String getCTotalPricce() {
		return this.CTotalPricce;
	}

	public void setCTotalPricce(String CTotalPricce) {
		this.CTotalPricce = CTotalPricce;
	}

	public String getCCashBack() {
		return this.CCashBack;
	}

	public void setCCashBack(String CCashBack) {
		this.CCashBack = CCashBack;
	}

	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
	}

	public String getCStartDate() {
		return this.CStartDate;
	}

	public void setCStartDate(String CStartDate) {
		this.CStartDate = CStartDate;
	}

	public String getCEndDate() {
		return this.CEndDate;
	}

	public void setCEndDate(String CEndDate) {
		this.CEndDate = CEndDate;
	}

	public Integer getCType() {
		return this.CType;
	}

	public void setCType(Integer CType) {
		this.CType = CType;
	}

	public Integer getSDel() {
		return this.SDel;
	}

	public void setSDel(Integer SDel) {
		this.SDel = SDel;
	}

}