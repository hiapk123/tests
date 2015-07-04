package com.uestc.bean;

/**
 * Logistics entity. @author MyEclipse Persistence Tools
 */

public class Logistics implements java.io.Serializable {

	// Fields

	private Integer LId;
	private Store storeBySIdOut;
	private Goods goods;
	private Store storeBySIdIn;
	private String LSerialNum;
	private String LNum;
	private String LType;
	private String LDate;
	private String LStatus;
	private String SNum;
	private String LSum;
	private String LInfo;
	private Short SDel;

	// Constructors

	/** default constructor */
	public Logistics() {
	}

	/** minimal constructor */
	public Logistics(Integer LId) {
		this.LId = LId;
	}

	/** full constructor */
	public Logistics(Integer LId, Store storeBySIdOut, Goods goods,
			Store storeBySIdIn, String LSerialNum, String LNum, String LType,
			String LDate, String LStatus, String SNum, String LSum,
			String LInfo, Short SDel) {
		this.LId = LId;
		this.storeBySIdOut = storeBySIdOut;
		this.goods = goods;
		this.storeBySIdIn = storeBySIdIn;
		this.LSerialNum = LSerialNum;
		this.LNum = LNum;
		this.LType = LType;
		this.LDate = LDate;
		this.LStatus = LStatus;
		this.SNum = SNum;
		this.LSum = LSum;
		this.LInfo = LInfo;
		this.SDel = SDel;
	}

	// Property accessors

	public Integer getLId() {
		return this.LId;
	}

	public void setLId(Integer LId) {
		this.LId = LId;
	}

	public Store getStoreBySIdOut() {
		return this.storeBySIdOut;
	}

	public void setStoreBySIdOut(Store storeBySIdOut) {
		this.storeBySIdOut = storeBySIdOut;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Store getStoreBySIdIn() {
		return this.storeBySIdIn;
	}

	public void setStoreBySIdIn(Store storeBySIdIn) {
		this.storeBySIdIn = storeBySIdIn;
	}

	public String getLSerialNum() {
		return this.LSerialNum;
	}

	public void setLSerialNum(String LSerialNum) {
		this.LSerialNum = LSerialNum;
	}

	public String getLNum() {
		return this.LNum;
	}

	public void setLNum(String LNum) {
		this.LNum = LNum;
	}

	public String getLType() {
		return this.LType;
	}

	public void setLType(String LType) {
		this.LType = LType;
	}

	public String getLDate() {
		return this.LDate;
	}

	public void setLDate(String LDate) {
		this.LDate = LDate;
	}

	public String getLStatus() {
		return this.LStatus;
	}

	public void setLStatus(String LStatus) {
		this.LStatus = LStatus;
	}

	public String getSNum() {
		return this.SNum;
	}

	public void setSNum(String SNum) {
		this.SNum = SNum;
	}

	public String getLSum() {
		return this.LSum;
	}

	public void setLSum(String LSum) {
		this.LSum = LSum;
	}

	public String getLInfo() {
		return this.LInfo;
	}

	public void setLInfo(String LInfo) {
		this.LInfo = LInfo;
	}

	public Short getSDel() {
		return this.SDel;
	}

	public void setSDel(Short SDel) {
		this.SDel = SDel;
	}

}