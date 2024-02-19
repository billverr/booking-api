package com.innovv8.booking.objectify.dao.impl;

import com.innovv8.booking.objectify.dao.UserDao;
import com.innovv8.booking.objectify.model.User;
import com.philoshopic.objectify.v6.impl.ObjectifyDaoImpl;

public class UserDaoImpl extends ObjectifyDaoImpl<User> implements UserDao {

	public UserDaoImpl() {
		super(User.class);

	}
}
