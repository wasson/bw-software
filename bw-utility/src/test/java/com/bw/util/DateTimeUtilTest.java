package com.bw.util;

import static org.junit.Assert.*;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;

import com.bw.util.DateTimeFormatUtil;
import com.bw.util.DateTimeUtil;

public class DateTimeUtilTest {

	@Test
	public void isMinDate_test() {
		final Date minDate = DateTimeUtil.MIN_DATE;		
		assertTrue(DateTimeUtil.isMinDate(minDate));
		assertFalse(DateTimeUtil.isMinDate(new Date(Long.MAX_VALUE)));
		assertFalse(DateTimeUtil.isMinDate(new Date(Long.MIN_VALUE + 1)));
		// new Date(Long.MIN_VALUE)  is our def of empty date.
		assertTrue(DateTimeUtil.isMinDate(new Date(Long.MIN_VALUE)));
		assertFalse(DateTimeUtil.isMinDate(null));
	}
	
	@Test
	public void stripTime_min_date_test() {
		final Date minDate = DateTimeUtil.MIN_DATE;
		//min_date never strips
		assertEquals(DateTimeUtil.MIN_DATE, DateTimeUtil.stripTime(minDate));
	}
	
	@Test
	public void stripTime_test() {
		final Date dateWithTime = DateTime.parse("2014-01-21 10.10.10.111", DateTimeFormatUtil.DFT_JODA_TS_FMT).toDate();
		final Date dateNoTime = DateTime.parse("20140121", DateTimeFormatUtil.BASIC_DATE_JODA_FMT).toDate();
		assertEquals(DateTimeUtil.stripTime(dateWithTime), dateNoTime);
	}

}
