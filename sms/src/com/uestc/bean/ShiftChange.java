package com.uestc.bean;

/**
 * ShiftChange entity. @author MyEclipse Persistence Tools
 */

public class ShiftChange implements java.io.Serializable {

	//多个表综合查询的结果封装成一个自定义的类，交接班记录

	public Integer shiftId;
	public String empName;
	public String shiftStartDate;
	public String shiftEndDate;
	public String shiftTotalMoney;//收银总额
	public String cash;//现金
	public String bank;//银联卡
	public String online;//网银
	public String pettyCash;//备用金
}