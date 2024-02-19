package com.innovv8.booking.service;

import com.innovv8.booking.objectify.dao.impl.OfyDaoFactory;
import com.innovv8.booking.objectify.model.User;
import com.philoshopic.objectify.v6.impl.ObjectifyServiceImpl;

public class UserService extends ObjectifyServiceImpl<User> {

	public UserService() {
		super(OfyDaoFactory.getUserDAO());
	}

}
