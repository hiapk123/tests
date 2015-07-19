package com.uestc.bean;

/**
 * Restock entity. @author MyEclipse Persistence Tools
 */

public class Restock implements java.io.Serializable {

	// Fields

	private Long RId;
	private Goods goods;
	private Integer GNum;
	private Short RFlag;
	private String RDate;
	private Short SDel;
	private String GPrice;

	// Constructors

	/** default constructor */
	public Restock() {
	}

	/** full constructor */
	public Restock(Goods goods, Integer GNum, Short RFlag, String RDate,
			Short SDel, String GPrice) {
		this.goods = goods;
		this.GNum = GNum;
		this.RFlag = RFlag;
		this.RDate = RDate;
		this.SDel = SDel;
		this.GPrice = GPrice;
	}

	// Property accessors

	public Long getRId() {
		return this.RId;
	}

	public void setRId(Long RId) {
		this.RId = RId;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Integer getGNum() {
		return this.GNum;
	}

	public void setGNum(Integer GNum) {
		this.GNum = GNum;
	}

	public Short getRFlag() {
		return this.RFlag;
	}

	public void setRFlag(Short RFlag) {
		this.RFlag = RFlag;
	}

	public String getRDate() {
		return this.RDate;
	}

	public void setRDate(String RDate) {
		this.RDate = RDate;
	}

	public Short getSDel() {
		return this.SDel;
	}

	public void setSDel(Short SDel) {
		this.SDel = SDel;
	}

	public String getGPrice() {
		return this.GPrice;
	}

	public void setGPrice(String GPrice) {
		this.GPrice = GPrice;
	}

}