package com.booking.objectify.dao.impl;

import com.booking.objectify.dao.EventDao;
import com.booking.objectify.model.Event;
import com.philoshopic.objectify.v6.impl.ObjectifyDaoImpl;

public class EventDaoImpl extends ObjectifyDaoImpl<Event> implements EventDao {

	public EventDaoImpl() {
		super(Event.class);

	}
}
