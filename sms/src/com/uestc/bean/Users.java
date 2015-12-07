package com.uestc.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */

public class Users implements java.io.Serializable {

	// Fields --------对应数据库表字段

	private Long UId;
	private Integer UType;
	private String UName;
	private String UPhone;
	private String USex;
	private String UAge;
	private String UEmail;
	private String UInfo;
	private Short SDel;
	private Set vips = new HashSet(0);
	private Set stores = new HashSet(0);

	// ------------新加字段UPassword------------
	private String UPassword;
	private String Reloginpass;
	private String VerifyCode;
	
	public String getVerifyCode() {
		return VerifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		VerifyCode = verifyCode;
	}

	public String getReloginpass() {
		return Reloginpass;
	}

	public void setReloginpass(String reloginpass) {
		Reloginpass = reloginpass;
	}

	public String getUPassword() {
		return UPassword;
	}

	public void setUPassword(String uPassword) {
		UPassword = uPassword;
	}

	
	// Constructors
	
	/** default constructor */
	public Users() {
	}

	/** minimal constructor */
	public Users(Long UId) {
		this.UId = UId;
	}

	/** full constructor */
	public Users(Long UId, Integer UType, String UName, String UPhone,
			String USex, String UAge, String UEmail, String UInfo, Short SDel,
			Set vips, Set stores) {
		this.UId = UId;
		this.UType = UType;
		this.UName = UName;
		this.UPhone = UPhone;
		this.USex = USex;
		this.UAge = UAge;
		this.UEmail = UEmail;
		this.UInfo = UInfo;
		this.SDel = SDel;
		this.vips = vips;
		this.stores = stores;
	}

	// Property accessors

	public Long getUId() {
		return this.UId;
	}

	public void setUId(Long UId) {
		this.UId = UId;
	}

	public Integer getUType() {
		return this.UType;
	}

	public void setUType(Integer UType) {
		this.UType = UType;
	}

	public String getUName() {
		return this.UName;
	}

	public void setUName(String UName) {
		this.UName = UName;
	}

	public String getUPhone() {
		return this.UPhone;
	}

	public void setUPhone(String UPhone) {
		this.UPhone = UPhone;
	}

	public String getUSex() {
		return this.USex;
	}

	public void setUSex(String USex) {
		this.USex = USex;
	}

	public String getUAge() {
		return this.UAge;
	}

	public void setUAge(String UAge) {
		this.UAge = UAge;
	}

	public String getUEmail() {
		return this.UEmail;
	}

	public void setUEmail(String UEmail) {
		this.UEmail = UEmail;
	}

	public String getUInfo() {
		return this.UInfo;
	}

	public void setUInfo(String UInfo) {
		this.UInfo = UInfo;
	}

	public Short getSDel() {
		return this.SDel;
	}

	public void setSDel(Short SDel) {
		this.SDel = SDel;
	}

	public Set getVips() {
		return this.vips;
	}

	public void setVips(Set vips) {
		this.vips = vips;
	}

	public Set getStores() {
		return this.stores;
	}

	public void setStores(Set stores) {
		this.stores = stores;
	}

}