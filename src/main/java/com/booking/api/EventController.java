package com.booking.api;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.constraints.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.booking.json.model.GenericResponse;
import com.booking.objectify.model.Event;
import com.booking.service.ServiceFactory;
import com.booking.utilities.Constants;

@Validated
@RestController
public class EventController {
	private static final Logger log = Logger.getLogger(EventController.class.getName());
	private static final String BASE_URI = Constants.EVENT_BASE_URI;
	public static final String LANG_REGEX = Constants.LANG_REGEX;

	@GetMapping(value = { BASE_URI })
	public @ResponseBody ResponseEntity<GenericResponse> getEvents(@RequestHeader(required = false) String orgId,
			@RequestHeader(required = false, value = "Lang") @Pattern(regexp = LANG_REGEX) String language) {

		List<Event> events = ServiceFactory.getEventService().get(orgId);

		GenericResponse response = new GenericResponse(HttpStatus.OK.value(), BASE_URI, true, "2000",
				HttpStatus.OK.getReasonPhrase(), null, Constants.TIMEZONE_ID);
		com.booking.json.model.ResponseBody responseBody = new com.booking.json.model.ResponseBody();
		responseBody.setEvents(events);
		response.setResponseBody(responseBody);

		log.info("Response body : " + response.toJsonString());
		return ResponseEntity.status(HttpStatus.OK.value()).body(response);

	}

}
