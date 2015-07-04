package com.uestc.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Order entity. @author MyEclipse Persistence Tools
 */

public class Order implements java.io.Serializable {

	// Fields

	private Integer OId;
	private Store store;
	private Long GId;
	private String GNum;
	private String OStatus;
	private String ODate;
	private String OInfo;
	private Integer SDel;
	private Set bookings = new HashSet(0);

	// Constructors

	/** default constructor */
	public Order() {
	}

	/** minimal constructor */
	public Order(Integer OId) {
		this.OId = OId;
	}

	/** full constructor */
	public Order(Integer OId, Store store, Long GId, String GNum,
			String OStatus, String ODate, String OInfo, Integer SDel,
			Set bookings) {
		this.OId = OId;
		this.store = store;
		this.GId = GId;
		this.GNum = GNum;
		this.OStatus = OStatus;
		this.ODate = ODate;
		this.OInfo = OInfo;
		this.SDel = SDel;
		this.bookings = bookings;
	}

	// Property accessors

	public Integer getOId() {
		return this.OId;
	}

	public void setOId(Integer OId) {
		this.OId = OId;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Long getGId() {
		return this.GId;
	}

	public void setGId(Long GId) {
		this.GId = GId;
	}

	public String getGNum() {
		return this.GNum;
	}

	public void setGNum(String GNum) {
		this.GNum = GNum;
	}

	public String getOStatus() {
		return this.OStatus;
	}

	public void setOStatus(String OStatus) {
		this.OStatus = OStatus;
	}

	public String getODate() {
		return this.ODate;
	}

	public void setODate(String ODate) {
		this.ODate = ODate;
	}

	public String getOInfo() {
		return this.OInfo;
	}

	public void setOInfo(String OInfo) {
		this.OInfo = OInfo;
	}

	public Integer getSDel() {
		return this.SDel;
	}

	public void setSDel(Integer SDel) {
		this.SDel = SDel;
	}

	public Set getBookings() {
		return this.bookings;
	}

	public void setBookings(Set bookings) {
		this.bookings = bookings;
	}

}