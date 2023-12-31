package com.booking.service;

public class ServiceFactory {

	private static class EventServiceHolder {
		public static EventService instance;

		private EventServiceHolder() {
		}

		private static EventService getInstance() {
			if (null == instance) {
				instance = new EventService();
			}
			return instance;
		}
	}

	private static class ReservationServiceHolder {
		public static ReservationService instance;

		private ReservationServiceHolder() {
		}

		private static ReservationService getInstance() {
			if (null == instance) {
				instance = new ReservationService();
			}
			return instance;
		}
	}

	public static EventService getEventService() {
		return EventServiceHolder.getInstance();
	}

	public static ReservationService getReservationService() {
		return ReservationServiceHolder.getInstance();
	}
}
