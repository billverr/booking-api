package com.booking.objectify.model;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.philoshopic.objectify.v6.ObjectifyEntity;

@Entity
public class Event implements ObjectifyEntity {

	public enum Property {
		id,
		name,
		isoDate,
		createdAt,
		orgId
	}

	private static final long serialVersionUID = -2438824227837579978L;

	@Id
	private String id;
	private String name;
	@Index
	private String isoDate;
	private Date createdAt;
	@Index
	private String orgId;

	public Event() {
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public String getId() {
		return id;
	}

	public String getIsoDate() {
		return isoDate;
	}

	public String getName() {
		return name;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIsoDate(String isoDate) {
		this.isoDate = isoDate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}
