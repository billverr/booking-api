package com.innovv8.booking.objectify.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.philoshopic.objectify.v6.ObjectifyEntity;

@Entity
public class User implements ObjectifyEntity {

	public enum Property {
		id,
		password
	}

	public enum Role {
		ADMIN
	}

	private static final long serialVersionUID = 6575449454081169268L;

	@Id
	private String id;
	private String password;
	private String role;
	private String orgId;

	public User() {
		super();
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
