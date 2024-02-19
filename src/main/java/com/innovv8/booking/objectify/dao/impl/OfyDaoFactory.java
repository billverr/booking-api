package com.innovv8.booking.objectify.dao.impl;

import com.innovv8.booking.objectify.dao.EventDao;
import com.innovv8.booking.objectify.dao.ReservationDao;
import com.innovv8.booking.objectify.dao.UserDao;

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

	private static class UserDAOHolder {
		public static UserDao instance;

		private static UserDao getUserDAO() {
			if (null == instance) {
				instance = new UserDaoImpl();
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

	public static UserDao getUserDAO() {
		return UserDAOHolder.getUserDAO();
	}

}
