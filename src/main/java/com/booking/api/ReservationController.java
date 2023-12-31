package com.booking.api;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.constraints.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.booking.json.model.GenericResponse;
import com.booking.objectify.model.Reservation;
import com.booking.service.ServiceFactory;
import com.booking.utilities.Constants;

@Validated
@RestController
public class ReservationController {
	private static final Logger log = Logger.getLogger(ReservationController.class.getName());
	private static final String BASE_URI = Constants.RESERVATION_BASE_URI;
	public static final String LANG_REGEX = Constants.LANG_REGEX;

	@PostMapping(value = { BASE_URI })
	public @ResponseBody ResponseEntity<GenericResponse> createReservation(
			@RequestHeader(required = false, value = "Lang") @Pattern(regexp = LANG_REGEX) String language,
			@RequestBody Reservation reservation) {

		reservation = ServiceFactory.getReservationService().create(reservation);

		GenericResponse response = new GenericResponse(HttpStatus.OK.value(), BASE_URI, true, "2000",
				HttpStatus.OK.getReasonPhrase(), null, Constants.TIMEZONE_ID);
		com.booking.json.model.ResponseBody responseBody = new com.booking.json.model.ResponseBody();
		responseBody.setReservation(reservation);
		response.setResponseBody(responseBody);

		log.info("Response body : " + response.toJsonString());
		return ResponseEntity.status(HttpStatus.OK.value()).body(response);

	}

	@GetMapping(value = { BASE_URI })
	public @ResponseBody ResponseEntity<GenericResponse> getReservations(@RequestHeader(required = false) String orgId,
			@RequestHeader(required = false, value = "Lang") @Pattern(regexp = LANG_REGEX) String language,
			@RequestParam(required = false) String eventId) {

		List<Reservation> reservations = ServiceFactory.getReservationService().getByEventId(orgId, eventId);

		GenericResponse response = new GenericResponse(HttpStatus.OK.value(), BASE_URI, true, "2000",
				HttpStatus.OK.getReasonPhrase(), null, Constants.TIMEZONE_ID);
		com.booking.json.model.ResponseBody responseBody = new com.booking.json.model.ResponseBody();
		responseBody.setReservations(reservations);
		response.setResponseBody(responseBody);

		log.info("Response body : " + response.toJsonString());
		return ResponseEntity.status(HttpStatus.OK.value()).body(response);

	}

}
