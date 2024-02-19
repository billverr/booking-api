package com.innovv8.booking.json.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.innovv8.booking.utilities.Constants;
import com.innovv8.booking.utilities.DateUtilities;
import com.innovv8.booking.utilities.DateUtilities.DateFormat;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(GenericResponse.class.getName());

	private Integer httpCode;

	private String version = Constants.VERSION;
	private String datetime;
	private String path;
	private Boolean success;
	private String appCode;
	private String message;
	private ResponseBody responseBody;
	private Map<String, String> fieldErrors;

	public GenericResponse() {

	}

	public GenericResponse(Integer httpCode, boolean success, String message, String appCode,
			Map<String, String> fieldErrors, String timezoneId) {
		this.httpCode = httpCode;
		this.datetime = DateUtilities.formatDate(new Date(),
				DateFormat.YEAR_MONTH_DAY_HOURS_MINUTES_SECONDS_TIMEZONE.toString(), "");
		this.success = success;
		this.message = message;
		this.appCode = appCode;
		this.fieldErrors = fieldErrors;
	}

	public GenericResponse(Integer httpCode, boolean success, String message, String appCode, String timezoneId) {
		this.httpCode = httpCode;
		this.datetime = DateUtilities.formatDate(new Date(),
				DateFormat.YEAR_MONTH_DAY_HOURS_MINUTES_SECONDS_TIMEZONE.toString(), timezoneId);
		this.success = success;
		this.message = message;
		this.appCode = appCode;
	}

	public GenericResponse(Integer httpCode, String path, Boolean success, String appCode, String message,
			ResponseBody responseBody, String timezoneId) {
		this.httpCode = httpCode;
		this.datetime = DateUtilities.formatDate(new Date(),
				DateFormat.YEAR_MONTH_DAY_HOURS_MINUTES_SECONDS_TIMEZONE.toString(), timezoneId);
		this.path = path;
		this.success = success;
		this.appCode = appCode;
		this.message = message;
		this.responseBody = responseBody;
	}

	public GenericResponse(Integer httpCode, String version, String datetime, String path, Boolean success,
			String appCode, String message, ResponseBody responseBody, Map<String, String> fieldErrors) {
		super();
		this.httpCode = httpCode;
		this.version = version;
		this.datetime = datetime;
		this.path = path;
		this.success = success;
		this.appCode = appCode;
		this.message = message;
		this.responseBody = responseBody;
		this.fieldErrors = fieldErrors;
	}

	public void addFieldError(String field, String message) {
		this.fieldErrors.put(field, message);
	}

	public String getAppCode() {
		return appCode;
	}

	public String getDatetime() {
		return datetime;
	}

	public Map<String, String> getFieldErrors() {
		return fieldErrors;
	}

	public Integer getHttpCode() {
		return httpCode;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}

	public ResponseBody getResponseBody() {
		return responseBody;
	}

	public String getVersion() {
		return version;
	}

	public Boolean isSuccess() {
		return success;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public void setFieldErrors(Map<String, String> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public void setHttpCode(Integer httpCode) {
		this.httpCode = httpCode;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setResponseBody(ResponseBody responseBody) {
		this.responseBody = responseBody;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String toJsonString() {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		mapper.setSerializationInclusion(Include.NON_NULL);
		try {
			jsonString = mapper.writeValueAsString(this);
		} catch (IOException | OutOfMemoryError e) {
			LOG.severe(e.getMessage() + "::" + e);
		}
		return jsonString;
	}

}
