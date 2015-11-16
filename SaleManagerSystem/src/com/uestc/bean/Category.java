package com.uestc.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Category entity. @author MyEclipse Persistence Tools
 */

public class Category implements java.io.Serializable {

	// Fields

	private Integer CId;
	private String CName;
	private Integer CParentId;
	private Integer SDel;
	private Set goodses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Category() {
	}

	/** full constructor */
	public Category(String CName, Integer CParentId, Integer SDel, Set goodses) {
		this.CName = CName;
		this.CParentId = CParentId;
		this.SDel = SDel;
		this.goodses = goodses;
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

	public Integer getCParentId() {
		return this.CParentId;
	}

	public void setCParentId(Integer CParentId) {
		this.CParentId = CParentId;
	}

	public Integer getSDel() {
		return this.SDel;
	}

	public void setSDel(Integer SDel) {
		this.SDel = SDel;
	}

	public Set getGoodses() {
		return this.goodses;
	}

	public void setGoodses(Set goodses) {
		this.goodses = goodses;
	}

}