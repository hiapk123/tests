package com.uestc.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */

public class Goods implements java.io.Serializable {

	// Fields

	private Long GId;
	private Supplier supplier;
	private Category category;
	private Store store;
	private CommissionRule commissionRule;
	private Vip vip;
	private IntegralRule integralRule;
	private String GName;
	private String SName;
	private String GStockNum;
	private String GPurPrice;
	private String GSalePrice;
	private String GTradePrice;
	private String CName;
	private String GIntegral;
	private String GBarcode;
	private String GStockMin;
	private String GStockMax;
	private String GProdDate;
	private String GGiq;
	private String GScale;
	private String GPm;
	private String GImgPath;
	private String suName;
	private Integer GFlag;
	private Integer GDel;
	private String GUnit;
	private String GHowmuch;
	private Set restocks = new HashSet(0);
	private Set discounts = new HashSet(0);
	private Set returnses = new HashSet(0);
	private Set forGifts = new HashSet(0);
	private Set sales = new HashSet(0);
	private Set priceGoodses = new HashSet(0);
	private Set bookings = new HashSet(0);

	// Constructors

	/** default constructor */
	public Goods() {
	}

	/** full constructor */
	public Goods(Supplier supplier, Category category, Store store,
			CommissionRule commissionRule, Vip vip, IntegralRule integralRule,
			String GName, String SName, String GStockNum, String GPurPrice,
			String GSalePrice, String GTradePrice, String CName,
			String GIntegral, String GBarcode, String GStockMin,
			String GStockMax, String GProdDate, String GGiq, String GScale,
			String GPm, String GImgPath, String suName, Integer GFlag,
			Integer GDel, String GUnit, String GHowmuch, Set restocks,
			Set discounts, Set returnses, Set forGifts, Set sales,
			Set priceGoodses, Set bookings) {
		this.supplier = supplier;
		this.category = category;
		this.store = store;
		this.commissionRule = commissionRule;
		this.vip = vip;
		this.integralRule = integralRule;
		this.GName = GName;
		this.SName = SName;
		this.GStockNum = GStockNum;
		this.GPurPrice = GPurPrice;
		this.GSalePrice = GSalePrice;
		this.GTradePrice = GTradePrice;
		this.CName = CName;
		this.GIntegral = GIntegral;
		this.GBarcode = GBarcode;
		this.GStockMin = GStockMin;
		this.GStockMax = GStockMax;
		this.GProdDate = GProdDate;
		this.GGiq = GGiq;
		this.GScale = GScale;
		this.GPm = GPm;
		this.GImgPath = GImgPath;
		this.suName = suName;
		this.GFlag = GFlag;
		this.GDel = GDel;
		this.GUnit = GUnit;
		this.GHowmuch = GHowmuch;
		this.restocks = restocks;
		this.discounts = discounts;
		this.returnses = returnses;
		this.forGifts = forGifts;
		this.sales = sales;
		this.priceGoodses = priceGoodses;
		this.bookings = bookings;
	}

	// Property accessors

	public Long getGId() {
		return this.GId;
	}

	public void setGId(Long GId) {
		this.GId = GId;
	}

	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public CommissionRule getCommissionRule() {
		return this.commissionRule;
	}

	public void setCommissionRule(CommissionRule commissionRule) {
		this.commissionRule = commissionRule;
	}

	public Vip getVip() {
		return this.vip;
	}

	public void setVip(Vip vip) {
		this.vip = vip;
	}

	public IntegralRule getIntegralRule() {
		return this.integralRule;
	}

	public void setIntegralRule(IntegralRule integralRule) {
		this.integralRule = integralRule;
	}

	public String getGName() {
		return this.GName;
	}

	public void setGName(String GName) {
		this.GName = GName;
	}

	public String getSName() {
		return this.SName;
	}

	public void setSName(String SName) {
		this.SName = SName;
	}

	public String getGStockNum() {
		return this.GStockNum;
	}

	public void setGStockNum(String GStockNum) {
		this.GStockNum = GStockNum;
	}

	public String getGPurPrice() {
		return this.GPurPrice;
	}

	public void setGPurPrice(String GPurPrice) {
		this.GPurPrice = GPurPrice;
	}

	public String getGSalePrice() {
		return this.GSalePrice;
	}

	public void setGSalePrice(String GSalePrice) {
		this.GSalePrice = GSalePrice;
	}

	public String getGTradePrice() {
		return this.GTradePrice;
	}

	public void setGTradePrice(String GTradePrice) {
		this.GTradePrice = GTradePrice;
	}

	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
	}

	public String getGIntegral() {
		return this.GIntegral;
	}

	public void setGIntegral(String GIntegral) {
		this.GIntegral = GIntegral;
	}

	public String getGBarcode() {
		return this.GBarcode;
	}

	public void setGBarcode(String GBarcode) {
		this.GBarcode = GBarcode;
	}

	public String getGStockMin() {
		return this.GStockMin;
	}

	public void setGStockMin(String GStockMin) {
		this.GStockMin = GStockMin;
	}

	public String getGStockMax() {
		return this.GStockMax;
	}

	public void setGStockMax(String GStockMax) {
		this.GStockMax = GStockMax;
	}

	public String getGProdDate() {
		return this.GProdDate;
	}

	public void setGProdDate(String GProdDate) {
		this.GProdDate = GProdDate;
	}

	public String getGGiq() {
		return this.GGiq;
	}

	public void setGGiq(String GGiq) {
		this.GGiq = GGiq;
	}

	public String getGScale() {
		return this.GScale;
	}

	public void setGScale(String GScale) {
		this.GScale = GScale;
	}

	public String getGPm() {
		return this.GPm;
	}

	public void setGPm(String GPm) {
		this.GPm = GPm;
	}

	public String getGImgPath() {
		return this.GImgPath;
	}

	public void setGImgPath(String GImgPath) {
		this.GImgPath = GImgPath;
	}

	public String getSuName() {
		return this.suName;
	}

	public void setSuName(String suName) {
		this.suName = suName;
	}

	public Integer getGFlag() {
		return this.GFlag;
	}

	public void setGFlag(Integer GFlag) {
		this.GFlag = GFlag;
	}

	public Integer getGDel() {
		return this.GDel;
	}

	public void setGDel(Integer GDel) {
		this.GDel = GDel;
	}

	public String getGUnit() {
		return this.GUnit;
	}

	public void setGUnit(String GUnit) {
		this.GUnit = GUnit;
	}

	public String getGHowmuch() {
		return this.GHowmuch;
	}

	public void setGHowmuch(String GHowmuch) {
		this.GHowmuch = GHowmuch;
	}

	public Set getRestocks() {
		return this.restocks;
	}

	public void setRestocks(Set restocks) {
		this.restocks = restocks;
	}

	public Set getDiscounts() {
		return this.discounts;
	}

	public void setDiscounts(Set discounts) {
		this.discounts = discounts;
	}

	public Set getReturnses() {
		return this.returnses;
	}

	public void setReturnses(Set returnses) {
		this.returnses = returnses;
	}

	public Set getForGifts() {
		return this.forGifts;
	}

	public void setForGifts(Set forGifts) {
		this.forGifts = forGifts;
	}

	public Set getSales() {
		return this.sales;
	}

	public void setSales(Set sales) {
		this.sales = sales;
	}

	public Set getPriceGoodses() {
		return this.priceGoodses;
	}

	public void setPriceGoodses(Set priceGoodses) {
		this.priceGoodses = priceGoodses;
	}

	public Set getBookings() {
		return this.bookings;
	}

	public void setBookings(Set bookings) {
		this.bookings = bookings;
	}

}