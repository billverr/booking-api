package com.innovv8.booking.api.test.helpers;

import static com.googlecode.objectify.ObjectifyService.factory;

import org.junit.jupiter.api.BeforeEach;

import com.innovv8.booking.objectify.model.Event;
import com.innovv8.booking.objectify.model.Reservation;
import com.philoshopic.objectify.v6.TestBase;

public class ObjectifySetup extends TestBase {

	@BeforeEach
	public void initTestClass() {
		objectifySetup();
	}

	public void objectifySetup() {
		factory().register(Reservation.class);
		factory().register(Event.class);
	}

}