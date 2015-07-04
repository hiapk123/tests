package com.uestc.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Vip entity. @author MyEclipse Persistence Tools
 */

public class Vip implements java.io.Serializable {

	// Fields

	private Integer VId;
	private Users users;
	private String VLevel;
	private String VCommodityIntegral;
	private Short VStatus;
	private String VCardNo;
	private String VBalance;
	private Short SDel;
	private Set oppositesales = new HashSet(0);
	private Set sales = new HashSet(0);
	private Set vipConsumeLogs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Vip() {
	}

	/** minimal constructor */
	public Vip(Integer VId) {
		this.VId = VId;
	}

	/** full constructor */
	public Vip(Integer VId, Users users, String VLevel,
			String VCommodityIntegral, Short VStatus, String VCardNo,
			String VBalance, Short SDel, Set oppositesales, Set sales,
			Set vipConsumeLogs) {
		this.VId = VId;
		this.users = users;
		this.VLevel = VLevel;
		this.VCommodityIntegral = VCommodityIntegral;
		this.VStatus = VStatus;
		this.VCardNo = VCardNo;
		this.VBalance = VBalance;
		this.SDel = SDel;
		this.oppositesales = oppositesales;
		this.sales = sales;
		this.vipConsumeLogs = vipConsumeLogs;
	}

	// Property accessors

	public Integer getVId() {
		return this.VId;
	}

	public void setVId(Integer VId) {
		this.VId = VId;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getVLevel() {
		return this.VLevel;
	}

	public void setVLevel(String VLevel) {
		this.VLevel = VLevel;
	}

	public String getVCommodityIntegral() {
		return this.VCommodityIntegral;
	}

	public void setVCommodityIntegral(String VCommodityIntegral) {
		this.VCommodityIntegral = VCommodityIntegral;
	}

	public Short getVStatus() {
		return this.VStatus;
	}

	public void setVStatus(Short VStatus) {
		this.VStatus = VStatus;
	}

	public String getVCardNo() {
		return this.VCardNo;
	}

	public void setVCardNo(String VCardNo) {
		this.VCardNo = VCardNo;
	}

	public String getVBalance() {
		return this.VBalance;
	}

	public void setVBalance(String VBalance) {
		this.VBalance = VBalance;
	}

	public Short getSDel() {
		return this.SDel;
	}

	public void setSDel(Short SDel) {
		this.SDel = SDel;
	}

	public Set getOppositesales() {
		return this.oppositesales;
	}

	public void setOppositesales(Set oppositesales) {
		this.oppositesales = oppositesales;
	}

	public Set getSales() {
		return this.sales;
	}

	public void setSales(Set sales) {
		this.sales = sales;
	}

	public Set getVipConsumeLogs() {
		return this.vipConsumeLogs;
	}

	public void setVipConsumeLogs(Set vipConsumeLogs) {
		this.vipConsumeLogs = vipConsumeLogs;
	}

}