package com.booking.objectify.dao.impl;

import com.booking.objectify.dao.ReservationDao;
import com.booking.objectify.model.Reservation;
import com.philoshopic.objectify.v6.impl.ObjectifyDaoImpl;

public class ReservationDaoImpl extends ObjectifyDaoImpl<Reservation> implements ReservationDao {

	public ReservationDaoImpl() {
		super(Reservation.class);

	}
}
