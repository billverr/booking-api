package com.innovv8.booking.utilities;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Constants {
	public static enum ErrorResponse {
		AUTHENTICATION_HEADERS_MISSING("Authentication headers are missing.", "801", 400),
		UNAUTHORISED_REQUEST("Unauthorised request.", "803", 401),
		INVALID_SIGNATURE("Invalid signature.", "804", 401),
		INVALID_JSON_FORMAT("Invalid JSON format.", "805", 400),
		INVALID_JSON_CONTENT("Invalid JSON content.", "806", 400),
		MISSING_PARAMETER("Missing JSON parameter ", "807", 400),
		COULD_NOT_PARSE_BODY("Parsing request's body failed or was interrupted.", "808", 500),

		// for errors and exceptions that are handled by spring-boot. The message value
		// is not needed as we use the exception's message. See
		// ControllerExceptionHandler.
		CONSTRAINT_VIOLATION("", "900", 400),
		MISSING_REQUEST_HEADER("", "901", 400),
		MISSING_SERVLET_REQUEST_PARAMETER("Missing Request Parameter(s)", "902", 400),
		VALIDATION_EXCEPTION("", "903", 400),
		MISSING_REQUIRED_DATE_PARAMETER("Missing Required Date Parameter", "904", 400),
		INVALID_DATE_FORMAT_PARAMETER("Invalid Date Format Parameters", "905", 400),

		NOT_FOUND("Not Found", "1000", 404),

		INTERNAL_SERVER_ERROR("Internal API error occured.", "1002", 500);

		private final String message;
		private final String appCode;
		private final Integer httpCode;

		private ErrorResponse(String message, String appCode, Integer httpCpde) {
			this.message = message;
			this.appCode = appCode;
			this.httpCode = httpCpde;
		}

		public String getAppCode() {
			return appCode;
		}

		public Integer getHttpCode() {
			return httpCode;
		}

		public String getMessage() {
			return message;
		}
	}

	public enum PhiloHttpHeaders {

		PHILO_DATE("PHILO-Date"),
		PHILO_SIGNATURE("PHILO-Signature"),
		PHILO_ORG_ID("PHILO-OrgId"),
		PHILO_SERVICE("PHILO-Service"),
		CONTENT_TYPE("Content-Type"),
		LANG("Lang");

		private final String name;

		private PhiloHttpHeaders(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}

	}

	public static final int AVAILABLE_SEATS_PER_SLOT = 2;

	public static final Set<String> TIME_SLOTS = Stream
			.of("08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00",
					"13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30",
					"19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30")
			.collect(Collectors.toCollection(HashSet::new));

	public static final String VERSION = "v1";
	public static final String TIMEZONE_ID = "Europe/Athens";

	public static final String EXTERNAL_API_BASE_URI = "/external/api";
	public static final String RESERVATION_BASE_URI = EXTERNAL_API_BASE_URI + "/reservation";
	public static final String EVENT_BASE_URI = EXTERNAL_API_BASE_URI + "/event";

	public static final String LANG_REGEX = "^[a-z]{2}(-[A-Z]{2})?$";

	private Constants() {
		throw new IllegalStateException("Constants class");
	}
}
