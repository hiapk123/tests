package com.uestc.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Store entity. @author MyEclipse Persistence Tools
 */

public class Store implements java.io.Serializable {

	// Fields

	private Integer SId;
	private Users users;
	private String SName;
	private Integer SParentId;
	private Integer SFlag;
	private Integer SDel;
	private String SAddr;
	private String SUname;
	private String STel;
	private Set sales = new HashSet(0);
	private Set employees = new HashSet(0);
	private Set vipConsumeLogs = new HashSet(0);
	private Set goodses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Store() {
	}

	/** full constructor */
	public Store(Users users, String SName, Integer SParentId, Integer SFlag,
			Integer SDel, String SAddr, String SUname, String STel, Set sales,
			Set employees, Set vipConsumeLogs, Set goodses) {
		this.users = users;
		this.SName = SName;
		this.SParentId = SParentId;
		this.SFlag = SFlag;
		this.SDel = SDel;
		this.SAddr = SAddr;
		this.SUname = SUname;
		this.STel = STel;
		this.sales = sales;
		this.employees = employees;
		this.vipConsumeLogs = vipConsumeLogs;
		this.goodses = goodses;
	}

	// Property accessors

	public Integer getSId() {
		return this.SId;
	}

	public void setSId(Integer SId) {
		this.SId = SId;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getSName() {
		return this.SName;
	}

	public void setSName(String SName) {
		this.SName = SName;
	}

	public Integer getSParentId() {
		return this.SParentId;
	}

	public void setSParentId(Integer SParentId) {
		this.SParentId = SParentId;
	}

	public Integer getSFlag() {
		return this.SFlag;
	}

	public void setSFlag(Integer SFlag) {
		this.SFlag = SFlag;
	}

	public Integer getSDel() {
		return this.SDel;
	}

	public void setSDel(Integer SDel) {
		this.SDel = SDel;
	}

	public String getSAddr() {
		return this.SAddr;
	}

	public void setSAddr(String SAddr) {
		this.SAddr = SAddr;
	}

	public String getSUname() {
		return this.SUname;
	}

	public void setSUname(String SUname) {
		this.SUname = SUname;
	}

	public String getSTel() {
		return this.STel;
	}

	public void setSTel(String STel) {
		this.STel = STel;
	}

	public Set getSales() {
		return this.sales;
	}

	public void setSales(Set sales) {
		this.sales = sales;
	}

	public Set getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set employees) {
		this.employees = employees;
	}

	public Set getVipConsumeLogs() {
		return this.vipConsumeLogs;
	}

	public void setVipConsumeLogs(Set vipConsumeLogs) {
		this.vipConsumeLogs = vipConsumeLogs;
	}

	public Set getGoodses() {
		return this.goodses;
	}

	public void setGoodses(Set goodses) {
		this.goodses = goodses;
	}

}