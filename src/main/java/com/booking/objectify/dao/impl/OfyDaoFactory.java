package com.booking.objectify.dao.impl;

import com.booking.objectify.dao.EventDao;
import com.booking.objectify.dao.ReservationDao;

public class OfyDaoFactory {

	private static class EventDAOHolder {
		public static EventDao instance;

		private static EventDao getEventDAO() {
			if (null == instance) {
				instance = new EventDaoImpl();
			}
			return instance;
		}
	}

	private static class ReservationDAOHolder {
		public static ReservationDao instance;

		private static ReservationDao getReservationDAO() {
			if (null == instance) {
				instance = new ReservationDaoImpl();
			}
			return instance;
		}
	}

	public static EventDao getEventDAO() {
		return EventDAOHolder.getEventDAO();
	}

	public static ReservationDao getReservationDAO() {
		return ReservationDAOHolder.getReservationDAO();
	}

}
