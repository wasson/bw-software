package com.bw.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public final class NumberFormatUtil {

	private static final NumberFormat US_CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(Locale.US);
	private static final NumberFormat US_INTEGER_FORMAT = NumberFormat.getIntegerInstance(Locale.US);
	
	// use for non-fractional US currency formatting ($x,xxx).
	public static NumberFormat getBasicUSCurrencyFormat() {
		final NumberFormat usCurrencyFormat = NumberFormatUtil.US_CURRENCY_FORMAT;
		usCurrencyFormat.setMaximumFractionDigits(0);
		return usCurrencyFormat;
	}
	
	// use for non-fractional US integer formating (x,xxx)
	public static NumberFormat getBasicUSIntegerFormat() {
		final NumberFormat usIntegerFormat = NumberFormatUtil.US_INTEGER_FORMAT;
		usIntegerFormat.setMaximumFractionDigits(0);
		return usIntegerFormat;
	}
	
	/**
	 * Rounds using RoundingMode.HALF_UP for the desired places.
	 * 
	 * @param value
	 * @param places
	 * @return
	 */
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException("Passed places must be a positive value.");
	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	
		
}
