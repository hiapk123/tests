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
	private Set orders = new HashSet(0);
	private Set logisticsesForSIdIn = new HashSet(0);
	private Set logisticsesForSIdOut = new HashSet(0);
	private Set employees = new HashSet(0);
	private Set goodses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Store() {
	}

	/** full constructor */
	public Store(Users users, String SName, Integer SParentId, Integer SFlag,
			Integer SDel, Set orders, Set logisticsesForSIdIn,
			Set logisticsesForSIdOut, Set employees, Set goodses) {
		this.users = users;
		this.SName = SName;
		this.SParentId = SParentId;
		this.SFlag = SFlag;
		this.SDel = SDel;
		this.orders = orders;
		this.logisticsesForSIdIn = logisticsesForSIdIn;
		this.logisticsesForSIdOut = logisticsesForSIdOut;
		this.employees = employees;
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

	public Set getOrders() {
		return this.orders;
	}

	public void setOrders(Set orders) {
		this.orders = orders;
	}

	public Set getLogisticsesForSIdIn() {
		return this.logisticsesForSIdIn;
	}

	public void setLogisticsesForSIdIn(Set logisticsesForSIdIn) {
		this.logisticsesForSIdIn = logisticsesForSIdIn;
	}

	public Set getLogisticsesForSIdOut() {
		return this.logisticsesForSIdOut;
	}

	public void setLogisticsesForSIdOut(Set logisticsesForSIdOut) {
		this.logisticsesForSIdOut = logisticsesForSIdOut;
	}

	public Set getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set employees) {
		this.employees = employees;
	}

	public Set getGoodses() {
		return this.goodses;
	}

	public void setGoodses(Set goodses) {
		this.goodses = goodses;
	}

}