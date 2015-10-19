package com.uestc.bean;

public class JiaoJieBan_HP {

	private Long id;
	private Employee employee; // 收银员
	private String total;
	private String totalMoney;
	private String totalAll; // 收银总额
	private String cashPay; // 现金
	private String bankPay; // 银联卡
	private String onlinePay; // 在线
	private String vipIn;
	private String vipOut;
	private String startTime; // 开始时间
	private String endTime; // 结束时间
	private Short sDel;
	private Long machineId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getTotalAll() {
		return totalAll;
	}
	public void setTotalAll(String totalAll) {
		this.totalAll = totalAll;
	}
	public String getCashPay() {
		return cashPay;
	}
	public void setCashPay(String cashPay) {
		this.cashPay = cashPay;
	}
	public String getBankPay() {
		return bankPay;
	}
	public void setBankPay(String bankPay) {
		this.bankPay = bankPay;
	}
	public String getOnlinePay() {
		return onlinePay;
	}
	public void setOnlinePay(String onlinePay) {
		this.onlinePay = onlinePay;
	}
	public String getVipIn() {
		return vipIn;
	}
	public void setVipIn(String vipIn) {
		this.vipIn = vipIn;
	}
	public String getVipOut() {
		return vipOut;
	}
	public void setVipOut(String vipOut) {
		this.vipOut = vipOut;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Short getsDel() {
		return sDel;
	}
	public void setsDel(Short sDel) {
		this.sDel = sDel;
	}
	public Long getMachineId() {
		return machineId;
	}
	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}
	
}
