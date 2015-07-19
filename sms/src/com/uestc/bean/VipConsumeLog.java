package com.uestc.bean;

/**
 * VipConsumeLog entity. @author MyEclipse Persistence Tools
 */

public class VipConsumeLog implements java.io.Serializable {

	// Fields

	private Long VCId;
	private Store store;
	private Vip vip;
	private String VDate;
	private String VPayinOff;
	private Short SDel;

	// Constructors

	/** default constructor */
	public VipConsumeLog() {
	}

	/** minimal constructor */
	public VipConsumeLog(Long VCId) {
		this.VCId = VCId;
	}

	/** full constructor */
	public VipConsumeLog(Long VCId, Store store, Vip vip, String VDate,
			String VPayinOff, Short SDel) {
		this.VCId = VCId;
		this.store = store;
		this.vip = vip;
		this.VDate = VDate;
		this.VPayinOff = VPayinOff;
		this.SDel = SDel;
	}

	// Property accessors

	public Long getVCId() {
		return this.VCId;
	}

	public void setVCId(Long VCId) {
		this.VCId = VCId;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Vip getVip() {
		return this.vip;
	}

	public void setVip(Vip vip) {
		this.vip = vip;
	}

	public String getVDate() {
		return this.VDate;
	}

	public void setVDate(String VDate) {
		this.VDate = VDate;
	}

	public String getVPayinOff() {
		return this.VPayinOff;
	}

	public void setVPayinOff(String VPayinOff) {
		this.VPayinOff = VPayinOff;
	}

	public Short getSDel() {
		return this.SDel;
	}

	public void setSDel(Short SDel) {
		this.SDel = SDel;
	}

}