package com.bw.util;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateDiffTesting {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DateDiffTesting.class);
	
	@Test
	public void datedifftesting_test() {
		
		// y m d 0 0 0
		final DateTime listDate = new DateTime(2014, 6, 5, 0, 0, 0);
		final DateTime saleDate = new DateTime(2015, 2, 18, 0, 0, 0);
		final Days daysBetween = Days.daysBetween(saleDate, listDate);
		LOGGER.debug(String.valueOf(daysBetween.getDays()));

	}

}
