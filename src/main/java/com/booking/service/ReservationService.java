package com.booking.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import com.booking.objectify.dao.impl.OfyDaoFactory;
import com.booking.objectify.model.Reservation;
import com.booking.objectify.model.Reservation.Property;
import com.booking.utilities.PasswordUtilities;
import com.philoshopic.objectify.v6.impl.ObjectifyServiceImpl;
import com.philoshopic.query.helpers.FilterOperator;
import com.philoshopic.query.helpers.ObjectWrapper;
import com.philoshopic.query.helpers.ObjectWrapper.Type;
import com.philoshopic.query.helpers.QueryFilter;
import com.philoshopic.query.helpers.QueryParameters;

public class ReservationService extends ObjectifyServiceImpl<Reservation> {

	private static final Logger log = Logger.getLogger(ReservationService.class.getName());

	public ReservationService() {
		super(OfyDaoFactory.getReservationDAO());

	}

	@Override
	public Reservation create(Reservation resevation) {
		resevation.setId(generateId(resevation));
		resevation.setCreatedAt(new Date());
		return super.create(resevation);
	}

	public List<Reservation> getByEventId(String orgId, String eventId) {
		Set<QueryFilter> queryFilterSet = new HashSet<>();
		if (null != eventId && !eventId.equals("")) {
			queryFilterSet.add(new QueryFilter(Property.eventId.name(), FilterOperator.EQUAL,
					new ObjectWrapper(eventId, Type.STRING)));
		}
		if (null != orgId && !orgId.equals("")) {
			queryFilterSet.add(new QueryFilter(Property.orgId.name(), FilterOperator.EQUAL,
					new ObjectWrapper(orgId, Type.STRING)));
		}

		return getList(new QueryParameters(queryFilterSet));
	}

	private String generateId(Reservation resevation) {
		return PasswordUtilities.getMD5Utility().digest(resevation.getEventId() + resevation.getIsoDate()
				+ resevation.getMobileNumber() + resevation.getName() + resevation.getOrgId());
	}

}
