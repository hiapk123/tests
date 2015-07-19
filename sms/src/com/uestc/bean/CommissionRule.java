package com.uestc.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * CommissionRule entity. @author MyEclipse Persistence Tools
 */

public class CommissionRule implements java.io.Serializable {

	// Fields

	private Integer comId;
	private String comName;
	private String comHowmuch;
	private Integer comType;
	private Short SDel;
	private Set goodses = new HashSet(0);

	// Constructors

	/** default constructor */
	public CommissionRule() {
	}

	/** full constructor */
	public CommissionRule(String comName, String comHowmuch, Integer comType,
			Short SDel, Set goodses) {
		this.comName = comName;
		this.comHowmuch = comHowmuch;
		this.comType = comType;
		this.SDel = SDel;
		this.goodses = goodses;
	}

	// Property accessors

	public Integer getComId() {
		return this.comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public String getComName() {
		return this.comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getComHowmuch() {
		return this.comHowmuch;
	}

	public void setComHowmuch(String comHowmuch) {
		this.comHowmuch = comHowmuch;
	}

	public Integer getComType() {
		return this.comType;
	}

	public void setComType(Integer comType) {
		this.comType = comType;
	}

	public Short getSDel() {
		return this.SDel;
	}

	public void setSDel(Short SDel) {
		this.SDel = SDel;
	}

	public Set getGoodses() {
		return this.goodses;
	}

	public void setGoodses(Set goodses) {
		this.goodses = goodses;
	}

}