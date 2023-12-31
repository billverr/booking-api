package com.booking.utilities;

import java.util.Arrays;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtilities {

	private static final Logger LOG = Logger.getLogger(JsonUtilities.class.getName());

	public static <T> T jsonToObject(String jsonString, Class<T> type) {

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(jsonString, type);
		} catch (JsonProcessingException e) {
			LOG.severe(e.getMessage() + "\n" + e + "\n" + Arrays.toString(e.getStackTrace()));
			throw new NullPointerException();
		}
	}

	public static String objectToString(Object obj) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			LOG.severe(e.getMessage() + "\n" + e + "\n" + Arrays.toString(e.getStackTrace()));
			throw new NullPointerException(e.getMessage() + "\n" + e + "\n" + Arrays.toString(e.getStackTrace()));
		}

	}
}
