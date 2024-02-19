package com.innovv8.booking.objectify.dao.impl;

import com.innovv8.booking.objectify.dao.EventDao;
import com.innovv8.booking.objectify.model.Event;
import com.philoshopic.objectify.v6.impl.ObjectifyDaoImpl;

public class EventDaoImpl extends ObjectifyDaoImpl<Event> implements EventDao {

	public EventDaoImpl() {
		super(Event.class);

	}
}
