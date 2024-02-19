package com.innovv8.booking.objectify.dao.impl;

import com.innovv8.booking.objectify.dao.ReservationDao;
import com.innovv8.booking.objectify.model.Reservation;
import com.philoshopic.objectify.v6.impl.ObjectifyDaoImpl;

public class ReservationDaoImpl extends ObjectifyDaoImpl<Reservation> implements ReservationDao {

	public ReservationDaoImpl() {
		super(Reservation.class);

	}
}
