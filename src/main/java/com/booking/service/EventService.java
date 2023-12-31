package com.booking.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import com.booking.objectify.dao.impl.OfyDaoFactory;
import com.booking.objectify.model.Event;
import com.booking.objectify.model.Event.Property;
import com.philoshopic.objectify.v6.impl.ObjectifyServiceImpl;
import com.philoshopic.query.helpers.FilterOperator;
import com.philoshopic.query.helpers.ObjectWrapper;
import com.philoshopic.query.helpers.ObjectWrapper.Type;
import com.philoshopic.query.helpers.QueryFilter;
import com.philoshopic.query.helpers.QueryParameters;

public class EventService extends ObjectifyServiceImpl<Event> {

	private static final Logger log = Logger.getLogger(EventService.class.getName());

	public EventService() {
		super(OfyDaoFactory.getEventDAO());

	}

	public List<Event> get(String orgId) {
		Set<QueryFilter> queryFilterSet = new HashSet<>();
		queryFilterSet.add(
				new QueryFilter(Property.orgId.name(), FilterOperator.EQUAL, new ObjectWrapper(orgId, Type.STRING)));

		return getList(new QueryParameters(queryFilterSet));
	}

}
