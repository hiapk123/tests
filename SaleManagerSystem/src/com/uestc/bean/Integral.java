package com.uestc.bean;

/**
 * Integral entity. @author MyEclipse Persistence Tools
 */

public class Integral implements java.io.Serializable {

	// Fields

	private Integer IId;
	private Integer IType;
	private Integer IIType;
	private Integer ITypeMoney;
	private String II;
	private Integer SDel;

	// Constructors

	/** default constructor */
	public Integral() {
	}

	/** minimal constructor */
	public Integral(Integer IId) {
		this.IId = IId;
	}

	/** full constructor */
	public Integral(Integer IId, Integer IType, Integer IIType,
			Integer ITypeMoney, String II, Integer SDel) {
		this.IId = IId;
		this.IType = IType;
		this.IIType = IIType;
		this.ITypeMoney = ITypeMoney;
		this.II = II;
		this.SDel = SDel;
	}

	// Property accessors

	public Integer getIId() {
		return this.IId;
	}

	public void setIId(Integer IId) {
		this.IId = IId;
	}

	public Integer getIType() {
		return this.IType;
	}

	public void setIType(Integer IType) {
		this.IType = IType;
	}

	public Integer getIIType() {
		return this.IIType;
	}

	public void setIIType(Integer IIType) {
		this.IIType = IIType;
	}

	public Integer getITypeMoney() {
		return this.ITypeMoney;
	}

	public void setITypeMoney(Integer ITypeMoney) {
		this.ITypeMoney = ITypeMoney;
	}

	public String getII() {
		return this.II;
	}

	public void setII(String II) {
		this.II = II;
	}

	public Integer getSDel() {
		return this.SDel;
	}

	public void setSDel(Integer SDel) {
		this.SDel = SDel;
	}

}