package com.bw.util;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;

import com.google.common.base.Optional;

public final class StringUtil {
	
	private StringUtil() {
	}
	
	public static boolean isBlank(String value) {
		return com.google.common.base.Strings.isNullOrEmpty(value) || value.trim().isEmpty();
	}
	
	public static boolean isNotBlank(String value) {
		return !StringUtil.isBlank(value);
	}
	
	public static String getNonBlankStringOrDefault(String value, String dft) {
		return (StringUtil.getNonBlankString(value).isPresent() ? StringUtil.getNonBlankString(value).get() : dft);
	}
	
	public static String getNonBlankStringOrNull(String value) {
		return (StringUtil.getNonBlankString(value).isPresent() ? StringUtil.getNonBlankString(value).get() : null);
	}
	
	// Returns the passed string value as an Optional, populating with the passed String value
	// only if it is non-null and contains a non-blank value and contains a non-empty value.
	public static Optional<String> getNonBlankString(String value) {
		if (StringUtil.isNotBlank(value)) {
			return Optional.of(value);
		}
		return Optional.absent();
	}
	
	public static Integer getIntegerOrNull(String value) {
		return (StringUtil.getInteger(value).isPresent() ? StringUtil.getInteger(value).get() : null);
	}
	
	public static Optional<Integer> getInteger(String value) {
		if (StringUtil.isBlank(value)) {
			return Optional.absent();
		}
		Integer parsed = null;
		final String evaluate = value.trim();
		try {
			parsed = Integer.parseInt(evaluate);
		} catch (NumberFormatException e) {
			// not an int
		}
		return Optional.fromNullable(parsed);
	}
	
	public static Long getLongOrNull(String value) {
		return (StringUtil.getLong(value).isPresent() ? StringUtil.getLong(value).get() : null);
	}
	
	public static Optional<Long> getLong(String value) {
		if (StringUtil.isBlank(value)) {
			return Optional.absent();
		}
		Long parsed = null;
		final String evaluate = value.trim();
		try {
			parsed = Long.parseLong(evaluate);
		} catch (NumberFormatException e) {
			// not a long	
		}
		return Optional.fromNullable(parsed);
	}
	
	public static Double getDoubleOrNull(String value) {
		return (StringUtil.getDouble(value).isPresent() ? StringUtil.getDouble(value).get() : null);
	}
	
	public static Optional<Double> getDouble(String value) {
		if (StringUtil.isBlank(value)) {
			return Optional.absent();
		}
		Double parsed = null;
		final String evaluate = value.trim();
		try {
			parsed = Double.parseDouble(evaluate);
		} catch (NumberFormatException e) {
			// not a long	
		}
		return Optional.fromNullable(parsed);
	}
	
	public static Boolean getBooleanOrNull(String value) {
		return (StringUtil.getBooleanEX(value).isPresent() ? StringUtil.getBooleanEX(value).get() : null);
	}
	
	// Returns an Optional<Boolean> with a populated value only if a case-insensitive "true" / "false" literal value
	// can be evaluated from the passed String.  Any other value will return Optional.absent().
	public static Optional<Boolean> getBoolean(String value) {
		if (StringUtil.isBlank(value)) {
			return Optional.absent();
		}
		final String evaluate = value.trim().toLowerCase();
		if (evaluate.equals("true")) {
			return Optional.of(true);
		}
		if (evaluate.equals("false")) {
			return Optional.of(false);
		}
		return Optional.absent();
	}
	
	public static Boolean getBooleanEXOrNull(String value) {
		return (StringUtil.getBooleanEX(value).isPresent() ? StringUtil.getBooleanEX(value).get() : null);
	}
	
	// Extends the getBoolean evaluation logic to additionally look for:
	// - "yes" / "no" (true / false)
	// - "0" / non-"0" (false / true)
	// - otherwise Optional.absent() will be returned.
	public static Optional<Boolean> getBooleanEX(String value) {
		if (StringUtil.isBlank(value)) {
			return Optional.absent();
		}
		final String evaluate = value.trim().toLowerCase();
		if (StringUtil.getBoolean(evaluate).isPresent()) {
			return StringUtil.getBoolean(evaluate);
		}
		if (evaluate.equals("yes")) {
			return Optional.of(true);
		}
		if (evaluate.equals("no")) {
			return Optional.of(false);
		}
		if (StringUtil.getInteger(value).isPresent()) {
			return StringUtil.getInteger(value).get() == 0 ? Optional.of(false) : Optional.of(true);
		}
		return Optional.absent();
	}
	
	// uses our defined basic date formatter (DateTimeFormatUtil.BASIC_DATE_JODA_FMT).
	public static Optional<Date> getDate(String value) {
		return getDate(value, DateTimeFormatUtil.BASIC_DATE_JODA_FMT);
	}
	
	public static Optional<Date> getDate(String value, DateTimeFormatter formatter) {
		if (StringUtil.isBlank(value)) {
			return Optional.absent();
		}
		Optional<Date> parsedDate = Optional.absent();
		final String evaluate = value.trim();
		try {
			parsedDate = Optional.of(DateTime.parse(evaluate, formatter).toDate());
		} catch (IllegalArgumentException e) {
			// not a string that fits the formatter.
		}
		return parsedDate;
	}
	
}
