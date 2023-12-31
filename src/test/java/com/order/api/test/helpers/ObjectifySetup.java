package com.order.api.test.helpers;

import static com.googlecode.objectify.ObjectifyService.factory;

import org.junit.jupiter.api.BeforeEach;

import com.booking.objectify.model.Event;
import com.booking.objectify.model.Reservation;
import com.philoshopic.objectify.v6.TestBase;

public class ObjectifySetup extends TestBase {

	// public ObjectifySetup(GAE_LOCAL_ENV_CONFIG gaeLocalEnvConfig) {
	// super(gaeLocalEnvConfig);
	// }
	//
	// public ObjectifySetup(GAE_LOCAL_ENV_CONFIG gaeLocalEnvConfig, String
	// queueXmlPath) {
	// super(gaeLocalEnvConfig, queueXmlPath);
	// }

	@BeforeEach
	public void initTestClass() {
		objectifySetup();
	}

	public void objectifySetup() {
		factory().register(Reservation.class);
		factory().register(Event.class);

	}

}