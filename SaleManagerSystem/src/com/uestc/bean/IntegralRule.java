package com.uestc.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * IntegralRule entity. @author MyEclipse Persistence Tools
 */

public class IntegralRule implements java.io.Serializable {

	// Fields

	private Integer inId;
	private String inName;
	private Integer inType;
	private Short SDel;
	private String inHowmuch;
	private Set goodses = new HashSet(0);

	// Constructors

	/** default constructor */
	public IntegralRule() {
	}

	/** full constructor */
	public IntegralRule(String inName, Integer inType, Short SDel,
			String inHowmuch, Set goodses) {
		this.inName = inName;
		this.inType = inType;
		this.SDel = SDel;
		this.inHowmuch = inHowmuch;
		this.goodses = goodses;
	}

	// Property accessors

	public Integer getInId() {
		return this.inId;
	}

	public void setInId(Integer inId) {
		this.inId = inId;
	}

	public String getInName() {
		return this.inName;
	}

	public void setInName(String inName) {
		this.inName = inName;
	}

	public Integer getInType() {
		return this.inType;
	}

	public void setInType(Integer inType) {
		this.inType = inType;
	}

	public Short getSDel() {
		return this.SDel;
	}

	public void setSDel(Short SDel) {
		this.SDel = SDel;
	}

	public String getInHowmuch() {
		return this.inHowmuch;
	}

	public void setInHowmuch(String inHowmuch) {
		this.inHowmuch = inHowmuch;
	}

	public Set getGoodses() {
		return this.goodses;
	}

	public void setGoodses(Set goodses) {
		this.goodses = goodses;
	}

}