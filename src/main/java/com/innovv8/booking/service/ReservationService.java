package com.innovv8.booking.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.innovv8.booking.objectify.dao.impl.OfyDaoFactory;
import com.innovv8.booking.objectify.model.Reservation;
import com.innovv8.booking.objectify.model.Reservation.Property;
import com.innovv8.booking.utilities.Constants;
import com.innovv8.booking.utilities.PasswordUtilities;
import com.philoshopic.objectify.v6.impl.ObjectifyServiceImpl;
import com.philoshopic.query.helpers.FilterOperator;
import com.philoshopic.query.helpers.ObjectWrapper;
import com.philoshopic.query.helpers.ObjectWrapper.Type;
import com.philoshopic.query.helpers.OrderOperator;
import com.philoshopic.query.helpers.QueryFilter;
import com.philoshopic.query.helpers.QueryOrder;
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

	public List<String> getAvailableTimeSlots(String isoDate) {
		List<String> availableTimeSlots = new ArrayList();
		Set<String> availableTimeSlotsSet = new HashSet<>();
		List<Reservation> reservations = getByIsoDateOrderByTimeSlot(isoDate);
		if (reservations.isEmpty()) {
			availableTimeSlotsSet = Constants.TIME_SLOTS;
		}

		Map<String, Integer> availableSeats = initializeAvailableSeats();
		for (Reservation reservation : reservations) {
			String timeSlot = reservation.getTimeSlot();
			availableSeats.merge(timeSlot, 0, (currentSeats, reservedSeatsCount) -> currentSeats - 1);
		}

		availableTimeSlotsSet = availableSeats.entrySet().stream().filter(entry -> entry.getValue() > 0)
				.map(Map.Entry::getKey).collect(Collectors.toSet());

		availableTimeSlots.addAll(availableTimeSlotsSet);
		Collections.sort(availableTimeSlots);
		return availableTimeSlots;
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

	public List<Reservation> getByIsoDateOrderByTimeSlot(String isoDate) {
		Set<QueryFilter> queryFilterSet = new HashSet<>();
		queryFilterSet.add(new QueryFilter(Property.isoDate.name(), FilterOperator.EQUAL,
				new ObjectWrapper(isoDate, Type.STRING)));
		LinkedHashSet<QueryOrder> queryOrderLinkedHashSet = new LinkedHashSet<>();
		queryOrderLinkedHashSet.add(new QueryOrder(OrderOperator.ASC, Property.timeSlot.name()));
		return getList(new QueryParameters(queryFilterSet, queryOrderLinkedHashSet));
	}

	private String generateId(Reservation resevation) {
		return PasswordUtilities.getMD5Utility().digest(resevation.getEventId() + resevation.getIsoDate()
				+ resevation.getMobileNumber() + resevation.getName() + resevation.getOrgId());
	}

	private Map<String, Integer> initializeAvailableSeats() {
		Map<String, Integer> availableSeats = new HashMap<>();
		Constants.TIME_SLOTS.forEach(timeSlot -> availableSeats.put(timeSlot, Constants.AVAILABLE_SEATS_PER_SLOT));
		return availableSeats;
	}

}
