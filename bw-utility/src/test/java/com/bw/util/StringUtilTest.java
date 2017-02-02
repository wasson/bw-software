package com.bw.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bw.util.StringUtil;

public class StringUtilTest {


	@Test
	public void guavaIsNullOrEmpty_test() {

		assertTrue(com.google.common.base.Strings.isNullOrEmpty(null));
		assertTrue(com.google.common.base.Strings.isNullOrEmpty(""));
		assertFalse(com.google.common.base.Strings.isNullOrEmpty(" "));
		assertFalse(com.google.common.base.Strings.isNullOrEmpty(" test "));
	
	}

	@Test
	public void isBlank_test() {
		
		assertTrue(StringUtil.isBlank(null));
		assertTrue(StringUtil.isBlank(""));
		assertTrue(StringUtil.isBlank(" "));
		assertTrue(StringUtil.isBlank("      "));
		assertFalse(StringUtil.isBlank(" test "));
		
	}
	
	@Test
	public void isNotBlank_test() {
		
		assertFalse(StringUtil.isNotBlank(null));
		assertFalse(StringUtil.isNotBlank(""));
		assertFalse(StringUtil.isNotBlank(" "));
		assertFalse(StringUtil.isNotBlank("      "));
		assertTrue(StringUtil.isNotBlank(" test "));
		
	}
	
	@Test
	public void getNonBlankStringOrNull_test() {
		
		assertNull(StringUtil.getNonBlankStringOrNull(""));
		assertNull(StringUtil.getNonBlankStringOrNull("  "));
		assertNull(StringUtil.getNonBlankStringOrNull(null));
		assertNotNull(StringUtil.getNonBlankStringOrNull("null"));
		assertEquals("null", StringUtil.getNonBlankStringOrNull("null"));
		
	}
	
	@Test
	public void getNonBlankString_test() {
		
		assertFalse(StringUtil.getNonBlankString("").isPresent());
		assertFalse(StringUtil.getNonBlankString("  ").isPresent());
		assertFalse(StringUtil.getNonBlankString(null).isPresent());
		assertTrue(StringUtil.getNonBlankString("null").isPresent());
		assertEquals("null", StringUtil.getNonBlankString("null").get());
		
	}
	
	@Test
	public void getInteger_test() {
		
		assertFalse(StringUtil.getInteger("").isPresent());
		
		assertFalse(StringUtil.getInteger("  ").isPresent());
		
		assertFalse(StringUtil.getInteger(null).isPresent());
		
		assertFalse(StringUtil.getInteger("abc").isPresent());
		
		assertFalse(StringUtil.getInteger("abc123").isPresent());
		
		assertFalse(StringUtil.getInteger("123abc").isPresent());
		
		assertTrue(StringUtil.getInteger("123").isPresent());
		assertEquals(Integer.valueOf(123), StringUtil.getInteger("123").get());
		
		assertTrue(StringUtil.getInteger(" 123 ").isPresent());
		assertEquals(Integer.valueOf(123), StringUtil.getInteger(" 123 ").get());
		
		assertFalse(StringUtil.getInteger("1,230").isPresent());
		assertTrue(StringUtil.getInteger("1,230".replace(",", "")).isPresent());
		assertEquals(Integer.valueOf(1230), StringUtil.getInteger("1,230".replace(",", "")).get());
		
		assertFalse(StringUtil.getInteger("123.0").isPresent());
		assertFalse(StringUtil.getInteger("123.1").isPresent());
		
	}
	
	@Test
	public void getIntegerOrNull_test() {
		
		assertNull(StringUtil.getIntegerOrNull(""));
		
		assertNull(StringUtil.getIntegerOrNull("  "));
		
		assertNull(StringUtil.getIntegerOrNull(null));
		
		assertNull(StringUtil.getIntegerOrNull("abc"));
		
		assertNull(StringUtil.getIntegerOrNull("abc123"));
		
		assertNull(StringUtil.getIntegerOrNull("123abc"));
		
		assertNotNull(StringUtil.getIntegerOrNull("123"));
		assertEquals(Integer.valueOf(123), StringUtil.getIntegerOrNull("123"));
		
		assertNotNull(StringUtil.getIntegerOrNull(" 123 "));
		assertEquals(Integer.valueOf(123), StringUtil.getIntegerOrNull(" 123 "));
		
		assertNull(StringUtil.getIntegerOrNull("1,230"));
		assertNotNull(StringUtil.getIntegerOrNull("1,230".replace(",", "")));
		assertEquals(Integer.valueOf(1230), StringUtil.getIntegerOrNull("1,230".replace(",", "")));
		
		assertNull(StringUtil.getIntegerOrNull("123.0"));
		assertNull(StringUtil.getIntegerOrNull("123.1"));
		
	}
	
	@Test
	public void getLong_test() {
		
		assertFalse(StringUtil.getLong("").isPresent());
		
		assertFalse(StringUtil.getLong("  ").isPresent());
		
		assertFalse(StringUtil.getLong(null).isPresent());
		
		assertFalse(StringUtil.getLong("abc").isPresent());
		
		assertFalse(StringUtil.getLong("abc123").isPresent());
		
		assertFalse(StringUtil.getLong("123abc").isPresent());
		
		
		assertTrue(StringUtil.getLong("123").isPresent());
		assertEquals(Long.valueOf(123), StringUtil.getLong("123").get());
		
		assertTrue(StringUtil.getLong(" 123 ").isPresent());
		assertEquals(Long.valueOf(123), StringUtil.getLong(" 123 ").get());
		
		assertFalse(StringUtil.getLong("1,230").isPresent());
		assertTrue(StringUtil.getLong("1,230".replace(",", "")).isPresent());
		assertEquals(Long.valueOf(1230), StringUtil.getLong("1,230".replace(",", "")).get());
		
		assertFalse(StringUtil.getLong("123.0").isPresent());
		assertFalse(StringUtil.getLong("123.1").isPresent());
		
	}
	
	@Test
	public void getLongOrNull_test() {
		
		assertNull(StringUtil.getLongOrNull(""));
		
		assertNull(StringUtil.getLongOrNull("  "));
		
		assertNull(StringUtil.getLongOrNull(null));
		
		assertNull(StringUtil.getLongOrNull("abc"));
		
		assertNull(StringUtil.getLongOrNull("abc123"));
		
		assertNull(StringUtil.getLongOrNull("123abc"));
		
		assertNotNull(StringUtil.getLongOrNull("123"));
		assertEquals(Long.valueOf(123), StringUtil.getLongOrNull("123"));
		
		assertNotNull(StringUtil.getLongOrNull(" 123 "));
		assertEquals(Long.valueOf(123), StringUtil.getLongOrNull(" 123 "));
		
		assertNull(StringUtil.getLongOrNull("1,230"));
		assertNotNull(StringUtil.getLongOrNull("1,230".replace(",", "")));
		assertEquals(Long.valueOf(1230), StringUtil.getLongOrNull("1,230".replace(",", "")));
		
		assertNull(StringUtil.getLongOrNull("123.0"));
		assertNull(StringUtil.getLongOrNull("123.1"));
		
	}
	
	@Test
	public void getDouble_test() {
		
		assertFalse(StringUtil.getDouble("").isPresent());
		
		assertFalse(StringUtil.getDouble("  ").isPresent());
		
		assertFalse(StringUtil.getDouble(null).isPresent());
		
		assertFalse(StringUtil.getDouble("abc").isPresent());
		
		assertFalse(StringUtil.getDouble("abc123").isPresent());
		
		assertFalse(StringUtil.getDouble("123abc").isPresent());
		
		
		assertTrue(StringUtil.getDouble("123").isPresent());
		assertEquals(Double.valueOf(123), StringUtil.getDouble("123").get());
		
		assertTrue(StringUtil.getDouble(" 123 ").isPresent());
		assertEquals(Double.valueOf(123), StringUtil.getDouble(" 123 ").get());
		
		assertFalse(StringUtil.getDouble("1,230").isPresent());
		assertTrue(StringUtil.getDouble("1,230".replace(",", "")).isPresent());
		assertEquals(Double.valueOf(1230), StringUtil.getDouble("1,230".replace(",", "")).get());
		
		assertTrue(StringUtil.getDouble("123.0").isPresent());
		assertEquals(Double.valueOf(123d), StringUtil.getDouble("123.0").get());
		assertTrue(StringUtil.getDouble("123.1").isPresent());
		assertEquals(Double.valueOf(123.1d), StringUtil.getDouble("123.1").get());
		
	}
	
	@Test
	public void getDoubleOrNull_test() {
		
		assertNull(StringUtil.getDoubleOrNull(""));
		
		assertNull(StringUtil.getDoubleOrNull("  "));
		
		assertNull(StringUtil.getDoubleOrNull(null));
		
		assertNull(StringUtil.getDoubleOrNull("abc"));
		
		assertNull(StringUtil.getDoubleOrNull("abc123"));
		
		assertNull(StringUtil.getDoubleOrNull("123abc"));
		
		assertNotNull(StringUtil.getDoubleOrNull("123"));
		assertEquals(Double.valueOf(123), StringUtil.getDoubleOrNull("123"));
		
		assertNotNull(StringUtil.getDoubleOrNull(" 123 "));
		assertEquals(Double.valueOf(123), StringUtil.getDoubleOrNull(" 123 "));
		
		assertNull(StringUtil.getDoubleOrNull("1,230"));
		assertNotNull(StringUtil.getDoubleOrNull("1,230".replace(",", "")));
		assertEquals(Double.valueOf(1230), StringUtil.getDoubleOrNull("1,230".replace(",", "")));
		
		assertNotNull(StringUtil.getDoubleOrNull("123.0"));
		assertEquals(Double.valueOf(123d), StringUtil.getDoubleOrNull("123.0"));
		assertNotNull(StringUtil.getDoubleOrNull("123.1"));
		assertEquals(Double.valueOf(123.1d), StringUtil.getDoubleOrNull("123.1"));
		
	}
	
	@Test
	public void getBooleanEX_test() {
		
		assertFalse(StringUtil.getBooleanEX("").isPresent());
		assertFalse(StringUtil.getBooleanEX("null").isPresent());
		assertFalse(StringUtil.getBooleanEX(null).isPresent());
		assertFalse(StringUtil.getBooleanEX("sure").isPresent());
		
		assertTrue(StringUtil.getBooleanEX("0").isPresent());
		assertFalse(StringUtil.getBooleanEX("0").get());
		
		assertTrue(StringUtil.getBooleanEX("1").isPresent());
		assertTrue(StringUtil.getBooleanEX("1").get());
		
		assertTrue(StringUtil.getBooleanEX("-1").isPresent());
		assertTrue(StringUtil.getBooleanEX("-1").get());
		
		assertTrue(StringUtil.getBooleanEX("100").isPresent());
		assertTrue(StringUtil.getBooleanEX("100").get());
		
		assertTrue(StringUtil.getBooleanEX("yes").isPresent());
		assertTrue(StringUtil.getBooleanEX("yes").get());
		
		assertTrue(StringUtil.getBooleanEX("no").isPresent());
		assertFalse(StringUtil.getBooleanEX("no").get());
		

	}
	
	@Test
	public void getBoolean_test() {
		
		assertFalse(StringUtil.getBoolean(null).isPresent());
		assertFalse(StringUtil.getBoolean("").isPresent());
		assertFalse(StringUtil.getBoolean("  ").isPresent());

		assertFalse(StringUtil.getBoolean("yes").isPresent());
		assertFalse(StringUtil.getBoolean("no").isPresent());
		
		assertFalse(StringUtil.getBoolean("0").isPresent());
		assertFalse(StringUtil.getBoolean("1").isPresent());
		
		assertTrue(StringUtil.getBoolean("True").isPresent());
		assertTrue(StringUtil.getBoolean("True").get());
		assertTrue(StringUtil.getBoolean("False").isPresent());
		assertFalse(StringUtil.getBoolean("False").get());
		assertTrue(StringUtil.getBoolean("true").isPresent());
		assertTrue(StringUtil.getBoolean("true").get());
		assertTrue(StringUtil.getBoolean("false").isPresent());
		assertFalse(StringUtil.getBoolean("false").get());
		assertTrue(StringUtil.getBoolean(" true ").isPresent());
		assertTrue(StringUtil.getBoolean(" true ").get());
		assertTrue(StringUtil.getBoolean(" false ").isPresent());
		assertFalse(StringUtil.getBoolean(" false ").get());
		
		

	}
	
}
