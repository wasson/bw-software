package com.bw.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeFormatUtil {

	public final static DateTimeFormatter BASIC_DATE_JODA_FMT = org.joda.time.format.DateTimeFormat
			.forPattern("yyyyMMdd");

	public final static DateTimeFormatter DFT_JODA_TS_FMT = org.joda.time.format.DateTimeFormat
			.forPattern("yyyy-MM-dd HH.mm.ss.SSS");

	public final static DateTimeFormatter DFT_JODA_DATE_FMT = org.joda.time.format.DateTimeFormat
			.forPattern("yyyy-MM-dd");

	public final static String DFT_ORACLE_TS_FMT = "yyyy-mm-dd HH24.MI.SS.FF3";

	public final static String DFT_POSTGESQL_TS_FMT = "yyyy-mm-dd HH24.MI.SS.MS";

	public final static String DFT_POSTGESQL_DATE_FMT = "yyyy-mm-dd";
	
	public final static DateTimeFormatter REPORTING_DFT_JODA_DATE_FMT = org.joda.time.format.DateTimeFormat
			.forPattern("MM/dd/yy");
	
	public final static DateTimeFormatter REPORTING_DFT_JODA_TS_FMT = org.joda.time.format.DateTimeFormat
			.forPattern("MM/dd/yy h.mm.ss a");

	public final static SimpleDateFormat BASIC_DATE_SIMPLEDATEFORMAT = new SimpleDateFormat("yyyyMMdd");
	
	public final static SimpleDateFormat DFT_DATE_SIMPLEDATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public final static SimpleDateFormat DFT_TS_SIMPLEDATEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss.SS");
	
	

	/**
	 * Converts the passed date to the UTC equivalent in milliseconds.
	 *
	 * Commonly used to convert a default system date to its UTC equivalent in
	 * order to persist the UTC value.
	 *
	 * @param dftDateTime
	 * @return long : The UTC millisecond equivalent for the passed date.
	 */
	public static long toUTCMillis(Date dftDateTime) {
		if (dftDateTime == null) {
			throw new IllegalArgumentException(
					"Passed dftDateTime can not be null.");
		}
		DateTime wrkDateTime = new DateTime(dftDateTime);
		return wrkDateTime.minusMillis(
				DateTimeZone.getDefault().getOffset(null)).getMillis();
	}

	/**
	 * Converts the passed date to the UTC time zone and then performs a to
	 * string using the DFT_JODA_TS_FMT DateTimeFormatter.
	 *
	 * Commonly used to convert a default time zone date to UTC time zone as a
	 * string that can be used to persist the value.
	 *
	 * @param dateTime
	 * @return String : The passed date converted to UTC time zone then
	 *         converted to a String using the DFT_JODA_TS_FMT
	 *         DateTimeFormatter.
	 */
	public static String toUTCDateString(Date dftDateTime) {
		if (dftDateTime == null) {
			throw new IllegalArgumentException(
					"Passed dftDateTime can not be null.");
		}
		return new DateTime(dftDateTime).withZone(DateTimeZone.UTC).toString(
				DateTimeFormatUtil.DFT_JODA_TS_FMT);
	}

	/**
	 * Converts the passed UTC date time string (using DFT_JODA_TS_FMT format)
	 * to a date using the default time zone offset.
	 *
	 * Commonly used to create default system Date instances from db persisted
	 * UTC date strings.
	 *
	 * @param utcDateTime
	 *            : A date String in UTC to be converted to the default time
	 *            zone.
	 * @return Date : A Date converted to the default time zone from UTC.
	 */
	public static Date fromUTCDateString(String utcDateTime) {
		if (utcDateTime == null) {
			throw new IllegalArgumentException(
					"Passed utcDateTime can not be null.");
		}
		return DateTimeFormatUtil.DFT_JODA_TS_FMT
				.parseDateTime(String.valueOf(utcDateTime))
				.plusMillis(DateTimeZone.getDefault().getOffset(null)).toDate();
	}

}
