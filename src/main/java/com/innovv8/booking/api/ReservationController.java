package com.innovv8.booking.api;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.innovv8.booking.json.model.GenericResponse;
import com.innovv8.booking.objectify.model.Reservation;
import com.innovv8.booking.service.ServiceFactory;
import com.innovv8.booking.utilities.Constants;

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
		com.innovv8.booking.json.model.ResponseBody responseBody = new com.innovv8.booking.json.model.ResponseBody();
		responseBody.setReservation(reservation);
		response.setResponseBody(responseBody);

		log.info("Response : " + response.toJsonString());
		return ResponseEntity.status(HttpStatus.OK.value()).body(response);

	}

	@DeleteMapping(value = { BASE_URI + "/{id}" })
	public @ResponseBody ResponseEntity<GenericResponse> deleteReservation(
			@RequestHeader(required = false) String orgId,
			@RequestHeader(required = false, value = "Lang") @Pattern(regexp = LANG_REGEX) String language,
			@NotBlank @PathVariable String id) {

		ServiceFactory.getReservationService().deleteById(id);

		GenericResponse response = new GenericResponse(HttpStatus.OK.value(), BASE_URI, true, "2000",
				HttpStatus.OK.getReasonPhrase(), null, Constants.TIMEZONE_ID);

		log.info("Response : " + response.toJsonString());
		return ResponseEntity.status(HttpStatus.OK.value()).body(response);

	}

	@GetMapping(value = { BASE_URI })
	public @ResponseBody ResponseEntity<GenericResponse> getReservations(@RequestHeader(required = false) String orgId,
			@RequestHeader(required = false, value = "Lang") @Pattern(regexp = LANG_REGEX) String language,
			@RequestParam(required = false) String eventId) {

		List<Reservation> reservations = ServiceFactory.getReservationService().getByEventId(orgId, eventId);

		GenericResponse response = new GenericResponse(HttpStatus.OK.value(), BASE_URI, true, "2000",
				HttpStatus.OK.getReasonPhrase(), null, Constants.TIMEZONE_ID);
		com.innovv8.booking.json.model.ResponseBody responseBody = new com.innovv8.booking.json.model.ResponseBody();
		responseBody.setReservations(reservations);
		response.setResponseBody(responseBody);

		log.info("Response : " + response.toJsonString());
		return ResponseEntity.status(HttpStatus.OK.value()).body(response);

	}

	@GetMapping("/availableTimeSlots")
	public List<String> showReservationForm(@RequestParam(required = true) String isoDate) {
		return ServiceFactory.getReservationService().getAvailableTimeSlots(isoDate);
	}

	// @GetMapping("/reservations")
	// public ModelAndView getReservationsPage() {
	// ModelAndView modelAndView = new ModelAndView();
	// modelAndView.setViewName("reservations.html");
	// List<Reservation> reservations =
	// ServiceFactory.getReservationService().getAll();
	// modelAndView.addObject("reservations", reservations);
	// return modelAndView;
	// }

}
