package com.uestc.bean;

/**
 * GoodPerson entity. @author MyEclipse Persistence Tools
 */

public class GoodPerson implements java.io.Serializable {

	// Fields

	private GoodPersonId id;

	// Constructors

	/** default constructor */
	public GoodPerson() {
	}

	/** full constructor */
	public GoodPerson(GoodPersonId id) {
		this.id = id;
	}

	// Property accessors

	public GoodPersonId getId() {
		return this.id;
	}

	public void setId(GoodPersonId id) {
		this.id = id;
	}

}