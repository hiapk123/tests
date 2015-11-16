package com.uestc.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * CouponsFunction entity. @author MyEclipse Persistence Tools
 */

public class CouponsFunction implements java.io.Serializable {

	// Fields

	private Integer CCid;
	private String CCname;
	private String CCmoney;
	private Set couponses = new HashSet(0);

	// Constructors

	/** default constructor */
	public CouponsFunction() {
	}

	/** full constructor */
	public CouponsFunction(String CCname, String CCmoney, Set couponses) {
		this.CCname = CCname;
		this.CCmoney = CCmoney;
		this.couponses = couponses;
	}

	// Property accessors

	public Integer getCCid() {
		return this.CCid;
	}

	public void setCCid(Integer CCid) {
		this.CCid = CCid;
	}

	public String getCCname() {
		return this.CCname;
	}

	public void setCCname(String CCname) {
		this.CCname = CCname;
	}

	public String getCCmoney() {
		return this.CCmoney;
	}

	public void setCCmoney(String CCmoney) {
		this.CCmoney = CCmoney;
	}

	public Set getCouponses() {
		return this.couponses;
	}

	public void setCouponses(Set couponses) {
		this.couponses = couponses;
	}

}