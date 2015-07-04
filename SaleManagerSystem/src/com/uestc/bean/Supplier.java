package com.uestc.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Supplier entity. @author MyEclipse Persistence Tools
 */

public class Supplier implements java.io.Serializable {

	// Fields

	private Integer suId;
	private String suName;
	private String suSex;
	private String suPhone;
	private String suGoods;
	private String suAddress;
	private Integer SDel;
	private Set goodses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Supplier() {
	}

	/** full constructor */
	public Supplier(String suName, String suSex, String suPhone,
			String suGoods, String suAddress, Integer SDel, Set goodses) {
		this.suName = suName;
		this.suSex = suSex;
		this.suPhone = suPhone;
		this.suGoods = suGoods;
		this.suAddress = suAddress;
		this.SDel = SDel;
		this.goodses = goodses;
	}

	// Property accessors

	public Integer getSuId() {
		return this.suId;
	}

	public void setSuId(Integer suId) {
		this.suId = suId;
	}

	public String getSuName() {
		return this.suName;
	}

	public void setSuName(String suName) {
		this.suName = suName;
	}

	public String getSuSex() {
		return this.suSex;
	}

	public void setSuSex(String suSex) {
		this.suSex = suSex;
	}

	public String getSuPhone() {
		return this.suPhone;
	}

	public void setSuPhone(String suPhone) {
		this.suPhone = suPhone;
	}

	public String getSuGoods() {
		return this.suGoods;
	}

	public void setSuGoods(String suGoods) {
		this.suGoods = suGoods;
	}

	public String getSuAddress() {
		return this.suAddress;
	}

	public void setSuAddress(String suAddress) {
		this.suAddress = suAddress;
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