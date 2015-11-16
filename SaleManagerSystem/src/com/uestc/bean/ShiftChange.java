package com.uestc.bean;

/**
 * ShiftChange entity. @author MyEclipse Persistence Tools
 */

public class ShiftChange implements java.io.Serializable {

	// Fields

	private Integer shiftId;
	private Employee employee;
	private String shiftStartDate;
	private String shiftEndDate;
	private String shiftInfo;
	private Integer SDel;

	// Constructors

	/** default constructor */
	public ShiftChange() {
	}

	/** minimal constructor */
	public ShiftChange(Integer shiftId) {
		this.shiftId = shiftId;
	}

	/** full constructor */
	public ShiftChange(Integer shiftId, Employee employee,
			String shiftStartDate, String shiftEndDate, String shiftInfo,
			Integer SDel) {
		this.shiftId = shiftId;
		this.employee = employee;
		this.shiftStartDate = shiftStartDate;
		this.shiftEndDate = shiftEndDate;
		this.shiftInfo = shiftInfo;
		this.SDel = SDel;
	}

	// Property accessors

	public Integer getShiftId() {
		return this.shiftId;
	}

	public void setShiftId(Integer shiftId) {
		this.shiftId = shiftId;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getShiftStartDate() {
		return this.shiftStartDate;
	}

	public void setShiftStartDate(String shiftStartDate) {
		this.shiftStartDate = shiftStartDate;
	}

	public String getShiftEndDate() {
		return this.shiftEndDate;
	}

	public void setShiftEndDate(String shiftEndDate) {
		this.shiftEndDate = shiftEndDate;
	}

	public String getShiftInfo() {
		return this.shiftInfo;
	}

	public void setShiftInfo(String shiftInfo) {
		this.shiftInfo = shiftInfo;
	}

	public Integer getSDel() {
		return this.SDel;
	}

	public void setSDel(Integer SDel) {
		this.SDel = SDel;
	}

}