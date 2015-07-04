package com.uestc.bean;

/**
 * Privilege entity. @author MyEclipse Persistence Tools
 */

public class Privilege implements java.io.Serializable {

	// Fields

	private Integer PId;
	private Integer PNo;
	private String PName;
	private Integer SDel;

	// Constructors

	/** default constructor */
	public Privilege() {
	}

	/** minimal constructor */
	public Privilege(Integer PId) {
		this.PId = PId;
	}

	/** full constructor */
	public Privilege(Integer PId, Integer PNo, String PName, Integer SDel) {
		this.PId = PId;
		this.PNo = PNo;
		this.PName = PName;
		this.SDel = SDel;
	}

	// Property accessors

	public Integer getPId() {
		return this.PId;
	}

	public void setPId(Integer PId) {
		this.PId = PId;
	}

	public Integer getPNo() {
		return this.PNo;
	}

	public void setPNo(Integer PNo) {
		this.PNo = PNo;
	}

	public String getPName() {
		return this.PName;
	}

	public void setPName(String PName) {
		this.PName = PName;
	}

	public Integer getSDel() {
		return this.SDel;
	}

	public void setSDel(Integer SDel) {
		this.SDel = SDel;
	}

}