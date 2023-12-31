package com.booking.objectify.model;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.philoshopic.objectify.v6.ObjectifyEntity;

@Entity
public class Reservation implements ObjectifyEntity {

	public enum Property {
		id,
		isoDate,
		name,
		numberOfPeople,
		eventId,
		createdAt,
		orgId
	}

	private static final long serialVersionUID = -2438824227837579978L;

	@Id
	private String id;
	@Index
	private String isoDate;
	private String name;
	private Integer numberOfPeople;
	@Index
	private String eventId;
	private Date createdAt;
	@Index
	private String orgId;
	@Index
	private String mobileNumber;

	public Reservation() {
	}

	public Reservation(String id, String isoDate, String name, Integer numberOfPeople, String eventId, Date createdAt,
			String orgId, String mobileNumber) {
		super();
		this.id = id;
		this.isoDate = isoDate;
		this.name = name;
		this.numberOfPeople = numberOfPeople;
		this.eventId = eventId;
		this.createdAt = createdAt;
		this.orgId = orgId;
		this.mobileNumber = mobileNumber;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public String getEventId() {
		return eventId;
	}

	public String getId() {
		return id;
	}

	public String getIsoDate() {
		return isoDate;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getName() {
		return name;
	}

	public Integer getNumberOfPeople() {
		return numberOfPeople;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIsoDate(String isoDate) {
		this.isoDate = isoDate;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumberOfPeople(Integer numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}
