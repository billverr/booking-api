package com.innovv8.booking.json.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.innovv8.booking.objectify.model.Event;
import com.innovv8.booking.objectify.model.Reservation;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBody implements Serializable {

	private static final long serialVersionUID = -409092510389039008L;

	private List<Reservation> reservations;
	private Reservation reservation;
	private List<Event> events;

	public ResponseBody() {
	}

	public List<Event> getEvents() {
		return events;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

}
