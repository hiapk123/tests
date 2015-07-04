package com.uestc.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Employee entity. @author MyEclipse Persistence Tools
 */

public class Employee implements java.io.Serializable {

	// Fields

	private Integer empId;
	private Store store;
	private String empName;
	private String empNo;
	private Integer empType;
	private String empPwd;
	private String empTel;
	private String empPrivilege;
	private Short empStatus;
	private Integer SDel;
	private Set shiftChanges = new HashSet(0);
	private Set oppositesales = new HashSet(0);
	private Set sales = new HashSet(0);

	// Constructors

	/** default constructor */
	public Employee() {
	}

	/** minimal constructor */
	public Employee(Integer empId) {
		this.empId = empId;
	}

	/** full constructor */
	public Employee(Integer empId, Store store, String empName, String empNo,
			Integer empType, String empPwd, String empTel, String empPrivilege,
			Short empStatus, Integer SDel, Set shiftChanges, Set oppositesales,
			Set sales) {
		this.empId = empId;
		this.store = store;
		this.empName = empName;
		this.empNo = empNo;
		this.empType = empType;
		this.empPwd = empPwd;
		this.empTel = empTel;
		this.empPrivilege = empPrivilege;
		this.empStatus = empStatus;
		this.SDel = SDel;
		this.shiftChanges = shiftChanges;
		this.oppositesales = oppositesales;
		this.sales = sales;
	}

	// Property accessors

	public Integer getEmpId() {
		return this.empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getEmpName() {
		return this.empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpNo() {
		return this.empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public Integer getEmpType() {
		return this.empType;
	}

	public void setEmpType(Integer empType) {
		this.empType = empType;
	}

	public String getEmpPwd() {
		return this.empPwd;
	}

	public void setEmpPwd(String empPwd) {
		this.empPwd = empPwd;
	}

	public String getEmpTel() {
		return this.empTel;
	}

	public void setEmpTel(String empTel) {
		this.empTel = empTel;
	}

	public String getEmpPrivilege() {
		return this.empPrivilege;
	}

	public void setEmpPrivilege(String empPrivilege) {
		this.empPrivilege = empPrivilege;
	}

	public Short getEmpStatus() {
		return this.empStatus;
	}

	public void setEmpStatus(Short empStatus) {
		this.empStatus = empStatus;
	}

	public Integer getSDel() {
		return this.SDel;
	}

	public void setSDel(Integer SDel) {
		this.SDel = SDel;
	}

	public Set getShiftChanges() {
		return this.shiftChanges;
	}

	public void setShiftChanges(Set shiftChanges) {
		this.shiftChanges = shiftChanges;
	}

	public Set getOppositesales() {
		return this.oppositesales;
	}

	public void setOppositesales(Set oppositesales) {
		this.oppositesales = oppositesales;
	}

	public Set getSales() {
		return this.sales;
	}

	public void setSales(Set sales) {
		this.sales = sales;
	}

}