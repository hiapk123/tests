package com.uestc.bean;

/**
 * GoodPersonId entity. @author MyEclipse Persistence Tools
 */

public class GoodPersonId implements java.io.Serializable {

	// Fields

	private Integer gpId;
	private Integer GId;
	private String GNum;
	private String gpName;
	private Integer gpType;
	private String gpTel;
	private String gpGetmoney;
	private Integer SDel;

	// Constructors

	/** default constructor */
	public GoodPersonId() {
	}

	/** full constructor */
	public GoodPersonId(Integer gpId, Integer GId, String GNum, String gpName,
			Integer gpType, String gpTel, String gpGetmoney, Integer SDel) {
		this.gpId = gpId;
		this.GId = GId;
		this.GNum = GNum;
		this.gpName = gpName;
		this.gpType = gpType;
		this.gpTel = gpTel;
		this.gpGetmoney = gpGetmoney;
		this.SDel = SDel;
	}

	// Property accessors

	public Integer getGpId() {
		return this.gpId;
	}

	public void setGpId(Integer gpId) {
		this.gpId = gpId;
	}

	public Integer getGId() {
		return this.GId;
	}

	public void setGId(Integer GId) {
		this.GId = GId;
	}

	public String getGNum() {
		return this.GNum;
	}

	public void setGNum(String GNum) {
		this.GNum = GNum;
	}

	public String getGpName() {
		return this.gpName;
	}

	public void setGpName(String gpName) {
		this.gpName = gpName;
	}

	public Integer getGpType() {
		return this.gpType;
	}

	public void setGpType(Integer gpType) {
		this.gpType = gpType;
	}

	public String getGpTel() {
		return this.gpTel;
	}

	public void setGpTel(String gpTel) {
		this.gpTel = gpTel;
	}

	public String getGpGetmoney() {
		return this.gpGetmoney;
	}

	public void setGpGetmoney(String gpGetmoney) {
		this.gpGetmoney = gpGetmoney;
	}

	public Integer getSDel() {
		return this.SDel;
	}

	public void setSDel(Integer SDel) {
		this.SDel = SDel;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GoodPersonId))
			return false;
		GoodPersonId castOther = (GoodPersonId) other;

		return ((this.getGpId() == castOther.getGpId()) || (this.getGpId() != null
				&& castOther.getGpId() != null && this.getGpId().equals(
				castOther.getGpId())))
				&& ((this.getGId() == castOther.getGId()) || (this.getGId() != null
						&& castOther.getGId() != null && this.getGId().equals(
						castOther.getGId())))
				&& ((this.getGNum() == castOther.getGNum()) || (this.getGNum() != null
						&& castOther.getGNum() != null && this.getGNum()
						.equals(castOther.getGNum())))
				&& ((this.getGpName() == castOther.getGpName()) || (this
						.getGpName() != null && castOther.getGpName() != null && this
						.getGpName().equals(castOther.getGpName())))
				&& ((this.getGpType() == castOther.getGpType()) || (this
						.getGpType() != null && castOther.getGpType() != null && this
						.getGpType().equals(castOther.getGpType())))
				&& ((this.getGpTel() == castOther.getGpTel()) || (this
						.getGpTel() != null && castOther.getGpTel() != null && this
						.getGpTel().equals(castOther.getGpTel())))
				&& ((this.getGpGetmoney() == castOther.getGpGetmoney()) || (this
						.getGpGetmoney() != null
						&& castOther.getGpGetmoney() != null && this
						.getGpGetmoney().equals(castOther.getGpGetmoney())))
				&& ((this.getSDel() == castOther.getSDel()) || (this.getSDel() != null
						&& castOther.getSDel() != null && this.getSDel()
						.equals(castOther.getSDel())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getGpId() == null ? 0 : this.getGpId().hashCode());
		result = 37 * result
				+ (getGId() == null ? 0 : this.getGId().hashCode());
		result = 37 * result
				+ (getGNum() == null ? 0 : this.getGNum().hashCode());
		result = 37 * result
				+ (getGpName() == null ? 0 : this.getGpName().hashCode());
		result = 37 * result
				+ (getGpType() == null ? 0 : this.getGpType().hashCode());
		result = 37 * result
				+ (getGpTel() == null ? 0 : this.getGpTel().hashCode());
		result = 37
				* result
				+ (getGpGetmoney() == null ? 0 : this.getGpGetmoney()
						.hashCode());
		result = 37 * result
				+ (getSDel() == null ? 0 : this.getSDel().hashCode());
		return result;
	}

}