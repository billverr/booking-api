package com.innovv8.booking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.innovv8.booking.api.test.helpers.ObjectifySetup;
import com.innovv8.booking.objectify.model.Reservation;
import com.innovv8.booking.utilities.Constants;

public class ReservationServiceTest extends ObjectifySetup {

	private ReservationService service = new ReservationService();

	@Test
	public void testGetAvailableTimeSlots_WhenNoReservations_ThenReturnTIME_SLOTS() {
		List<String> result = service.getAvailableTimeSlots("2024-02-08");

		assertEquals(Constants.TIME_SLOTS.size(), result.size());
		for (String timeSlot : Constants.TIME_SLOTS) {
			assertTrue(result.contains(timeSlot));
		}
	}

	@Test
	public void testGetAvailableTimeSlots_WhenSeatsForTimeslotAreCovered_ThenDoNotReturnThisSlot() {
		String isoDate = "2024-02-08";
		String timeSlot = "08:00";
		for (int i = 0; i < Constants.AVAILABLE_SEATS_PER_SLOT; i++) {
			ServiceFactory.getReservationService()
					.create(new Reservation(isoDate, timeSlot, "name" + i, "mobileNumber" + i));
		}

		List<String> result = service.getAvailableTimeSlots(isoDate);

		assertEquals(Constants.TIME_SLOTS.size() - 1, result.size());
		assertFalse(result.contains(timeSlot));
	}

	@Test
	public void testGetAvailableTimeSlots_WhenSeatsForTimeslotAreNotCovered_ThenReturnThisSlot() {
		String isoDate = "2024-02-08";
		String timeSlot = "08:00";
		for (int i = 0; i < Constants.AVAILABLE_SEATS_PER_SLOT - 1; i++) {
			ServiceFactory.getReservationService()
					.create(new Reservation(isoDate, timeSlot, "name" + i, "mobileNumber" + i));
		}

		List<String> result = service.getAvailableTimeSlots(isoDate);

		assertEquals(Constants.TIME_SLOTS.size(), result.size());
		assertTrue(result.contains(timeSlot));
	}

	@Test
	public void testGetAvailableTimeSlots_WhenSeatsForTimeslotAreOverCovered_ThenDoNotReturnThisSlot() {
		String isoDate = "2024-02-08";
		String timeSlot = "08:00";
		for (int i = 0; i < Constants.AVAILABLE_SEATS_PER_SLOT + 1; i++) {
			ServiceFactory.getReservationService()
					.create(new Reservation(isoDate, timeSlot, "name" + i, "mobileNumber" + i));
		}

		List<String> result = service.getAvailableTimeSlots(isoDate);

		assertEquals(Constants.TIME_SLOTS.size() - 1, result.size());
		assertFalse(result.contains(timeSlot));
	}
}
