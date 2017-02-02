package com.bw.util;

import static org.junit.Assert.*;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;

import com.bw.util.DateTimeFormatUtil;

public class DateTimeFormatUtilTest {

	@Test(expected=IllegalArgumentException.class)
	public void parse_BASIC_DATE_JODA_FMT_invaliddatestring_test() {
		
		final String testBadDateString = "199908";
		DateTime.parse(testBadDateString, DateTimeFormatUtil.BASIC_DATE_JODA_FMT);

	}
	
	@Test
	public void parse_BASIC_DATE_JODA_FMT_test() {
		final String testBadDateString = "19990828";
		final DateTime parsedDateTime = DateTime.parse(testBadDateString, DateTimeFormatUtil.BASIC_DATE_JODA_FMT);
		assertEquals(1999, parsedDateTime.getYear());
		assertEquals(8, parsedDateTime.getMonthOfYear());
		assertEquals(28, parsedDateTime.getDayOfMonth());;
	}
	
	@Test
	public void date_format_verify_test() {
		
		
		final Date dateToFormat = new DateTime(2016, 6, 30, 0, 0).toDate();
		
		final String dateFormatted = DateTimeFormatUtil.DFT_TS_SIMPLEDATEFORMAT.format(dateToFormat);
		
		System.out.println(dateFormatted);
		
		
	}
	
	

}
