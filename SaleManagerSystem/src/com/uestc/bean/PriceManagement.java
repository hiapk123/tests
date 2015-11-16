package com.uestc.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * PriceManagement entity. @author MyEclipse Persistence Tools
 */

public class PriceManagement implements java.io.Serializable {

	// Fields

	private Integer ppId;
	private String ppName;
	private Integer ppType;
	private Short SDel;
	private Set priceGoodses = new HashSet(0);

	// Constructors

	/** default constructor */
	public PriceManagement() {
	}

	/** full constructor */
	public PriceManagement(String ppName, Integer ppType, Short SDel,
			Set priceGoodses) {
		this.ppName = ppName;
		this.ppType = ppType;
		this.SDel = SDel;
		this.priceGoodses = priceGoodses;
	}

	// Property accessors

	public Integer getPpId() {
		return this.ppId;
	}

	public void setPpId(Integer ppId) {
		this.ppId = ppId;
	}

	public String getPpName() {
		return this.ppName;
	}

	public void setPpName(String ppName) {
		this.ppName = ppName;
	}

	public Integer getPpType() {
		return this.ppType;
	}

	public void setPpType(Integer ppType) {
		this.ppType = ppType;
	}

	public Short getSDel() {
		return this.SDel;
	}

	public void setSDel(Short SDel) {
		this.SDel = SDel;
	}

	public Set getPriceGoodses() {
		return this.priceGoodses;
	}

	public void setPriceGoodses(Set priceGoodses) {
		this.priceGoodses = priceGoodses;
	}

}