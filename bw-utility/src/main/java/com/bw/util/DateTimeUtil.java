package com.bw.util;

import java.util.Date;
import org.joda.time.DateTime;

public class DateTimeUtil {

	public static Date stripTime(Date date) {
		if (date == null) {
			return null;
		}
		// special case, avoid any java.lang.ArithmeticException: Adding time zone offset caused overflow
		// when min_date is passed in. external comparison logic should be fine with consistent use of MIN_DATE
		if (DateTimeUtil.isMinDate(date)) {
			return date;
		}
		String dateString = new DateTime(date).toString(DateTimeFormatUtil.BASIC_DATE_JODA_FMT);
		DateTime wrkDate = DateTimeFormatUtil.BASIC_DATE_JODA_FMT.parseDateTime(dateString);
		return wrkDate.toDate();
	}
	
	public final static Date MIN_DATE = new Date(Long.MIN_VALUE);
	
	public static boolean isMinDate(Date date) {
		return MIN_DATE.equals(date);
	}

}
