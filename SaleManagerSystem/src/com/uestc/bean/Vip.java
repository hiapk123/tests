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
	private String vipStartdate;
	private String vipEnddate;
	private String vipDsicount;
	private String vipTel;
	private Short vipAllow;
	private String vipHowmuch;
	private String vipAddr;
	private String vipPwd;
	private Set sales = new HashSet(0);
	private Set goodses = new HashSet(0);
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
			String VBalance, Short SDel, String vipStartdate,
			String vipEnddate, String vipDsicount, String vipTel,
			Short vipAllow, String vipHowmuch, String vipAddr, String vipPwd,
			Set sales, Set goodses, Set vipConsumeLogs) {
		this.VId = VId;
		this.users = users;
		this.VLevel = VLevel;
		this.VCommodityIntegral = VCommodityIntegral;
		this.VStatus = VStatus;
		this.VCardNo = VCardNo;
		this.VBalance = VBalance;
		this.SDel = SDel;
		this.vipStartdate = vipStartdate;
		this.vipEnddate = vipEnddate;
		this.vipDsicount = vipDsicount;
		this.vipTel = vipTel;
		this.vipAllow = vipAllow;
		this.vipHowmuch = vipHowmuch;
		this.vipAddr = vipAddr;
		this.vipPwd = vipPwd;
		this.sales = sales;
		this.goodses = goodses;
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

	public String getVipStartdate() {
		return this.vipStartdate;
	}

	public void setVipStartdate(String vipStartdate) {
		this.vipStartdate = vipStartdate;
	}

	public String getVipEnddate() {
		return this.vipEnddate;
	}

	public void setVipEnddate(String vipEnddate) {
		this.vipEnddate = vipEnddate;
	}

	public String getVipDsicount() {
		return this.vipDsicount;
	}

	public void setVipDsicount(String vipDsicount) {
		this.vipDsicount = vipDsicount;
	}

	public String getVipTel() {
		return this.vipTel;
	}

	public void setVipTel(String vipTel) {
		this.vipTel = vipTel;
	}

	public Short getVipAllow() {
		return this.vipAllow;
	}

	public void setVipAllow(Short vipAllow) {
		this.vipAllow = vipAllow;
	}

	public String getVipHowmuch() {
		return this.vipHowmuch;
	}

	public void setVipHowmuch(String vipHowmuch) {
		this.vipHowmuch = vipHowmuch;
	}

	public String getVipAddr() {
		return this.vipAddr;
	}

	public void setVipAddr(String vipAddr) {
		this.vipAddr = vipAddr;
	}

	public String getVipPwd() {
		return this.vipPwd;
	}

	public void setVipPwd(String vipPwd) {
		this.vipPwd = vipPwd;
	}

	public Set getSales() {
		return this.sales;
	}

	public void setSales(Set sales) {
		this.sales = sales;
	}

	public Set getGoodses() {
		return this.goodses;
	}

	public void setGoodses(Set goodses) {
		this.goodses = goodses;
	}

	public Set getVipConsumeLogs() {
		return this.vipConsumeLogs;
	}

	public void setVipConsumeLogs(Set vipConsumeLogs) {
		this.vipConsumeLogs = vipConsumeLogs;
	}

}