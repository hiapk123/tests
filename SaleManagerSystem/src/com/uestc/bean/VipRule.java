package com.uestc.bean;

/**
 * VipRule entity. @author MyEclipse Persistence Tools
 */

public class VipRule implements java.io.Serializable {

	// Fields

	private Integer vipId;
	private String vipName;
	private Integer vipType;
	private String vipHowmuch;
	private Short SDel;

	// Constructors

	/** default constructor */
	public VipRule() {
	}

	/** full constructor */
	public VipRule(String vipName, Integer vipType, String vipHowmuch,
			Short SDel) {
		this.vipName = vipName;
		this.vipType = vipType;
		this.vipHowmuch = vipHowmuch;
		this.SDel = SDel;
	}

	// Property accessors

	public Integer getVipId() {
		return this.vipId;
	}

	public void setVipId(Integer vipId) {
		this.vipId = vipId;
	}

	public String getVipName() {
		return this.vipName;
	}

	public void setVipName(String vipName) {
		this.vipName = vipName;
	}

	public Integer getVipType() {
		return this.vipType;
	}

	public void setVipType(Integer vipType) {
		this.vipType = vipType;
	}

	public String getVipHowmuch() {
		return this.vipHowmuch;
	}

	public void setVipHowmuch(String vipHowmuch) {
		this.vipHowmuch = vipHowmuch;
	}

	public Short getSDel() {
		return this.SDel;
	}

	public void setSDel(Short SDel) {
		this.SDel = SDel;
	}

}