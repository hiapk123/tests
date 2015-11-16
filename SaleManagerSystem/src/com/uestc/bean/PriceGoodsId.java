package com.uestc.bean;

/**
 * PriceGoodsId entity. @author MyEclipse Persistence Tools
 */

public class PriceGoodsId implements java.io.Serializable {

	// Fields

	private Goods goods;
	private PriceManagement priceManagement;
	private Short SDel;

	// Constructors

	/** default constructor */
	public PriceGoodsId() {
	}

	/** full constructor */
	public PriceGoodsId(Goods goods, PriceManagement priceManagement, Short SDel) {
		this.goods = goods;
		this.priceManagement = priceManagement;
		this.SDel = SDel;
	}

	// Property accessors

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

	public Short getSDel() {
		return this.SDel;
	}

	public void setSDel(Short SDel) {
		this.SDel = SDel;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PriceGoodsId))
			return false;
		PriceGoodsId castOther = (PriceGoodsId) other;

		return ((this.getGoods() == castOther.getGoods()) || (this.getGoods() != null
				&& castOther.getGoods() != null && this.getGoods().equals(
				castOther.getGoods())))
				&& ((this.getPriceManagement() == castOther
						.getPriceManagement()) || (this.getPriceManagement() != null
						&& castOther.getPriceManagement() != null && this
						.getPriceManagement().equals(
								castOther.getPriceManagement())))
				&& ((this.getSDel() == castOther.getSDel()) || (this.getSDel() != null
						&& castOther.getSDel() != null && this.getSDel()
						.equals(castOther.getSDel())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getGoods() == null ? 0 : this.getGoods().hashCode());
		result = 37
				* result
				+ (getPriceManagement() == null ? 0 : this.getPriceManagement()
						.hashCode());
		result = 37 * result
				+ (getSDel() == null ? 0 : this.getSDel().hashCode());
		return result;
	}

}