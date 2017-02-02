package com.bw.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bw.util.NumberFormatUtil;

public class NumberFormatUtilTest {

	@Test
	public void getBasicUSCurrencyFormat_test() {
		
		final String expected = "$23,004";
		final int intToFormat = 23004;
		final String formattedInt = NumberFormatUtil.getBasicUSCurrencyFormat().format(intToFormat);
		assertEquals(expected, formattedInt);
		
		final double dblToFormat = 23004.33;
		final String formattedDbl = NumberFormatUtil.getBasicUSCurrencyFormat().format(dblToFormat);
		assertEquals(expected, formattedDbl);
	
	}
	
	@Test
	public void getBasicUSIntegerFormat_test() {
		
		final String expected = "23,004";
		final int intToFormat = 23004;
		final String formattedInt = NumberFormatUtil.getBasicUSIntegerFormat().format(intToFormat);
		assertEquals(expected, formattedInt);
		
		final double dblToFormat = 23004.33;
		final String formattedDbl = NumberFormatUtil.getBasicUSIntegerFormat().format(dblToFormat);
		assertEquals(expected, formattedDbl);
	
	}
	
	@Test
	public void round_test() {
		final double test1 = 1.002d;
		assertEquals(Double.valueOf(1d), Double.valueOf(NumberFormatUtil.round(test1, 2)));
		System.out.println(String.valueOf(NumberFormatUtil.round(test1, 2)));
		
		
		final double test2 = 1.1002999d;
		assertEquals(Double.valueOf(1.1d), Double.valueOf(NumberFormatUtil.round(test2, 2)));
		System.out.println(String.valueOf(NumberFormatUtil.round(test2, 2)));
		
		final double test3 = 1.1055999d;
		assertEquals(Double.valueOf(1.11d), Double.valueOf(NumberFormatUtil.round(test3, 2)));
		System.out.println(String.valueOf(NumberFormatUtil.round(test3, 2)));
		
	
	}
	

}
