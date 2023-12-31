package com.booking.utilities;

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
