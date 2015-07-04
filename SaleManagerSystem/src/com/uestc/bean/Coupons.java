package com.uestc.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Coupons entity. @author MyEclipse Persistence Tools
 */

public class Coupons implements java.io.Serializable {

	// Fields

	private Integer CId;
	private String CName;
	private String CStartDate;
	private String CEndDate;
	private Integer CNum;
	private Short CStatus;
	private String CDescribe;
	private Short COnlyVip;
	private String CMeetPrice;
	private String CModel;
	private Set discounts = new HashSet(0);
	private Set forGifts = new HashSet(0);
	private Set cashBacks = new HashSet(0);
	private Set packages = new HashSet(0);
	private Set swaps = new HashSet(0);

	// Constructors

	/** default constructor */
	public Coupons() {
	}

	/** full constructor */
	public Coupons(String CName, String CStartDate, String CEndDate,
			Integer CNum, Short CStatus, String CDescribe, Short COnlyVip,
			String CMeetPrice, String CModel, Set discounts, Set forGifts,
			Set cashBacks, Set packages, Set swaps) {
		this.CName = CName;
		this.CStartDate = CStartDate;
		this.CEndDate = CEndDate;
		this.CNum = CNum;
		this.CStatus = CStatus;
		this.CDescribe = CDescribe;
		this.COnlyVip = COnlyVip;
		this.CMeetPrice = CMeetPrice;
		this.CModel = CModel;
		this.discounts = discounts;
		this.forGifts = forGifts;
		this.cashBacks = cashBacks;
		this.packages = packages;
		this.swaps = swaps;
	}

	// Property accessors

	public Integer getCId() {
		return this.CId;
	}

	public void setCId(Integer CId) {
		this.CId = CId;
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

	public Integer getCNum() {
		return this.CNum;
	}

	public void setCNum(Integer CNum) {
		this.CNum = CNum;
	}

	public Short getCStatus() {
		return this.CStatus;
	}

	public void setCStatus(Short CStatus) {
		this.CStatus = CStatus;
	}

	public String getCDescribe() {
		return this.CDescribe;
	}

	public void setCDescribe(String CDescribe) {
		this.CDescribe = CDescribe;
	}

	public Short getCOnlyVip() {
		return this.COnlyVip;
	}

	public void setCOnlyVip(Short COnlyVip) {
		this.COnlyVip = COnlyVip;
	}

	public String getCMeetPrice() {
		return this.CMeetPrice;
	}

	public void setCMeetPrice(String CMeetPrice) {
		this.CMeetPrice = CMeetPrice;
	}

	public String getCModel() {
		return this.CModel;
	}

	public void setCModel(String CModel) {
		this.CModel = CModel;
	}

	public Set getDiscounts() {
		return this.discounts;
	}

	public void setDiscounts(Set discounts) {
		this.discounts = discounts;
	}

	public Set getForGifts() {
		return this.forGifts;
	}

	public void setForGifts(Set forGifts) {
		this.forGifts = forGifts;
	}

	public Set getCashBacks() {
		return this.cashBacks;
	}

	public void setCashBacks(Set cashBacks) {
		this.cashBacks = cashBacks;
	}

	public Set getPackages() {
		return this.packages;
	}

	public void setPackages(Set packages) {
		this.packages = packages;
	}

	public Set getSwaps() {
		return this.swaps;
	}

	public void setSwaps(Set swaps) {
		this.swaps = swaps;
	}

}