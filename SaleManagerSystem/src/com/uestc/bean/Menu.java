package com.uestc.bean;

/**
 * Menu entity. @author MyEclipse Persistence Tools
 */

public class Menu implements java.io.Serializable {

	// Fields

	private Integer MId;
	private String MName;
	private Integer MParentId;
	private String MInfo;
	private Short SDel;

	// Constructors

	/** default constructor */
	public Menu() {
	}

	/** minimal constructor */
	public Menu(Integer MId) {
		this.MId = MId;
	}

	/** full constructor */
	public Menu(Integer MId, String MName, Integer MParentId, String MInfo,
			Short SDel) {
		this.MId = MId;
		this.MName = MName;
		this.MParentId = MParentId;
		this.MInfo = MInfo;
		this.SDel = SDel;
	}

	// Property accessors

	public Integer getMId() {
		return this.MId;
	}

	public void setMId(Integer MId) {
		this.MId = MId;
	}

	public String getMName() {
		return this.MName;
	}

	public void setMName(String MName) {
		this.MName = MName;
	}

	public Integer getMParentId() {
		return this.MParentId;
	}

	public void setMParentId(Integer MParentId) {
		this.MParentId = MParentId;
	}

	public String getMInfo() {
		return this.MInfo;
	}

	public void setMInfo(String MInfo) {
		this.MInfo = MInfo;
	}

	public Short getSDel() {
		return this.SDel;
	}

	public void setSDel(Short SDel) {
		this.SDel = SDel;
	}

}