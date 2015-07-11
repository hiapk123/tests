package com.uestc.bean;

/**
 * PriceGoods entity. @author MyEclipse Persistence Tools
 */

public class PriceGoods implements java.io.Serializable {

	// Fields

	private PriceGoodsId id;
	private Goods goods;
	private PriceManagement priceManagement;

	// Constructors

	/** default constructor */
	public PriceGoods() {
	}

	/** minimal constructor */
	public PriceGoods(PriceGoodsId id) {
		this.id = id;
	}

	/** full constructor */
	public PriceGoods(PriceGoodsId id, Goods goods,
			PriceManagement priceManagement) {
		this.id = id;
		this.goods = goods;
		this.priceManagement = priceManagement;
	}

	// Property accessors

	public PriceGoodsId getId() {
		return this.id;
	}

	public void setId(PriceGoodsId id) {
		this.id = id;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public PriceManagement getPriceManagement() {
		return this.priceManagement;
	}

	public void setPriceManagement(PriceManagement priceManagement) {
		this.priceManagement = priceManagement;
	}

}