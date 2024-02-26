package com.innovv8.booking.utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.logging.Logger;

public class DateUtilities {

	public enum DateFormat {
		DATE("yyyy-MM-dd"),
		YEAR_MONTH_DAY_HOURS_MINUTES_SECONDS("yyyyMMddHHmmss"),
		YEAR_MONTH_DAY_HOURS_MINUTES_SECONDS_TIMEZONE("yyyy-MM-dd'T'HH:mm:ss.SSSZ"),
		ISO_DATE("yyyy-MM-dd'T'HH:mm:ss");

		private final String name;

		DateFormat(String name) {
			this.name = name;
		}

		public static boolean contains(String test) {

			for (DateFormat df : DateFormat.values()) {
				if (df.name().equals(test)) {
					return true;
				}
			}
			return false;
		}

		@Override
		public String toString() {
			return this.name;
		}
	}

	private static final Logger log = Logger.getLogger(DateUtilities.class.getName());

	private DateUtilities() {
		throw new IllegalStateException("Utilities class");
	}

	public static String formatDate(Date date, String pattern, String timezoneId) {
		ZoneId zone = ZoneId.of(timezoneId);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return ZonedDateTime.ofInstant(date.toInstant(), zone).format(formatter);
	}

	// public static boolean isValidDate(String formattedDate, String datePattern,
	// int allowedDiffInMins) {
	// if (!StringValidator.isValid(formattedDate,
	// RegExpPattern.YEAR_MONTH_DAY_TIME_TIMEZONE, 28, 28, true, true,
	// false)) {
	// return false;
	// }
	// DateTimeFormatter dateTimeFormatter =
	// DateTimeFormatter.ofPattern(datePattern);
	// Date date = Date.from(ZonedDateTime.parse(formattedDate,
	// dateTimeFormatter).toInstant());
	//
	// Date dateNow = new Date();
	// Date dateMinsDiff = Date.from(dateNow.toInstant().minus(allowedDiffInMins,
	// ChronoUnit.MINUTES));
	//
	// if (date.before(dateMinsDiff)) {
	// return false;
	// }
	// return true;
	// }

	public static Date minus(Date date, long amountToSubtract, TemporalUnit unit) {
		return Date.from(date.toInstant().minus(amountToSubtract, unit));
	}

	public static Date minusDays(Date date, long amountToSubtract) {
		return minus(date, amountToSubtract, ChronoUnit.DAYS);
	}

	public static Date parseDateAtStartOfDay(String text, DateTimeFormatter formatter, String timezoneId) {
		return Date.from(LocalDate.parse(text, formatter).atStartOfDay(ZoneId.of(timezoneId)).toInstant());
	}
	
	public static Date parseDateAtStartOfDay(String text, String pattern, String timezoneId) {
		return Date.from(LocalDate.parse(text,  DateTimeFormatter.ofPattern(pattern)).atStartOfDay(ZoneId.of(timezoneId)).toInstant());
	}

	public static Date parseDateTime(String text, DateTimeFormatter formatter, String timezoneId) {
		return Date.from(LocalDateTime.parse(text, formatter).atZone(ZoneId.of(timezoneId)).toInstant());
	}

	public static Date parseDateTime(String text, String pattern, String timezoneId) {
		return parseDateTime(text, DateTimeFormatter.ofPattern(pattern), timezoneId);
	}

	public static Date plus(Date date, long amountToAdd, TemporalUnit unit) {
		return Date.from(date.toInstant().plus(amountToAdd, unit));
	}

	public static Date plusDays(Date date, long amountToAdd) {
		return plus(date, amountToAdd, ChronoUnit.DAYS);
	}

	public static Date stringToDate(String formattedDate, String datePattern) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(datePattern);
		return Date.from(ZonedDateTime.parse(formattedDate, dateTimeFormatter).toInstant());

	}

}
