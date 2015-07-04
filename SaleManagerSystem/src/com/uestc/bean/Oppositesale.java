package com.uestc.bean;

/**
 * Oppositesale entity. @author MyEclipse Persistence Tools
 */

public class Oppositesale implements java.io.Serializable {

	// Fields

	private Integer OId;
	private Employee employee;
	private Vip vip;
	private String saSerialNum;
	private String saDate;
	private String saGoodsNum;
	private String saGoodsPrice;
	private String saRealPrice;
	private String saType;
	private String saProfit;
	private Short SDel;

	// Constructors

	/** default constructor */
	public Oppositesale() {
	}

	/** minimal constructor */
	public Oppositesale(Integer OId) {
		this.OId = OId;
	}

	/** full constructor */
	public Oppositesale(Integer OId, Employee employee, Vip vip,
			String saSerialNum, String saDate, String saGoodsNum,
			String saGoodsPrice, String saRealPrice, String saType,
			String saProfit, Short SDel) {
		this.OId = OId;
		this.employee = employee;
		this.vip = vip;
		this.saSerialNum = saSerialNum;
		this.saDate = saDate;
		this.saGoodsNum = saGoodsNum;
		this.saGoodsPrice = saGoodsPrice;
		this.saRealPrice = saRealPrice;
		this.saType = saType;
		this.saProfit = saProfit;
		this.SDel = SDel;
	}

	// Property accessors

	public Integer getOId() {
		return this.OId;
	}

	public void setOId(Integer OId) {
		this.OId = OId;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Vip getVip() {
		return this.vip;
	}

	public void setVip(Vip vip) {
		this.vip = vip;
	}

	public String getSaSerialNum() {
		return this.saSerialNum;
	}

	public void setSaSerialNum(String saSerialNum) {
		this.saSerialNum = saSerialNum;
	}

	public String getSaDate() {
		return this.saDate;
	}

	public void setSaDate(String saDate) {
		this.saDate = saDate;
	}

	public String getSaGoodsNum() {
		return this.saGoodsNum;
	}

	public void setSaGoodsNum(String saGoodsNum) {
		this.saGoodsNum = saGoodsNum;
	}

	public String getSaGoodsPrice() {
		return this.saGoodsPrice;
	}

	public void setSaGoodsPrice(String saGoodsPrice) {
		this.saGoodsPrice = saGoodsPrice;
	}

	public String getSaRealPrice() {
		return this.saRealPrice;
	}

	public void setSaRealPrice(String saRealPrice) {
		this.saRealPrice = saRealPrice;
	}

	public String getSaType() {
		return this.saType;
	}

	public void setSaType(String saType) {
		this.saType = saType;
	}

	public String getSaProfit() {
		return this.saProfit;
	}

	public void setSaProfit(String saProfit) {
		this.saProfit = saProfit;
	}

	public Short getSDel() {
		return this.SDel;
	}

	public void setSDel(Short SDel) {
		this.SDel = SDel;
	}

}