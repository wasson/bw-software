package com.bw.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bw.util.StringFormatUtil;

public class StringFormatUtilTest {

	@Test
	public void underscoreToSpace_test() {
		
		final String test1 = "";
		final String test2 = "_";
		final String test3 = "__";
		final String test4 = "this_that_";
		final String test5 = "_this_that_";
		
		assertEquals("", StringFormatUtil.underscoreToSpace(test1));
		assertEquals(" ", StringFormatUtil.underscoreToSpace(test2));
		assertEquals(" _", StringFormatUtil.underscoreToSpace(test3));
		assertEquals("this that_", StringFormatUtil.underscoreToSpace(test4));
		assertEquals(" this_that_", StringFormatUtil.underscoreToSpace(test5));
	}
	
	
	@Test
	public void underscoresToSpaces_test() {
		final String test1 = "";
		final String test2 = "_";
		final String test3 = "__";
		final String test4 = "this_that_";
		final String test5 = "_this_that_";
		
		assertEquals("", StringFormatUtil.underscoresToSpaces(test1));
		assertEquals(" ", StringFormatUtil.underscoresToSpaces(test2));
		assertEquals("  ", StringFormatUtil.underscoresToSpaces(test3));
		assertEquals("this that ", StringFormatUtil.underscoresToSpaces(test4));
		assertEquals(" this that ", StringFormatUtil.underscoresToSpaces(test5));
	}
	
	@Test
	public void upperLowerFormat_allWords_true_test() {
		final String test1 = "  a dog  not a   cat is my number 1   pet.  ";
		assertEquals("A Dog  Not A   Cat Is My Number 1   Pet.", StringFormatUtil.upperLowerFormat(test1, true));
		
		final String test2 = "  ";
		assertEquals("", StringFormatUtil.upperLowerFormat(test2, true));
		
		final String test3 = "";
		assertEquals("", StringFormatUtil.upperLowerFormat(test3, true));
		
		final String test4 = " a  ";
		assertEquals("A", StringFormatUtil.upperLowerFormat(test4, true));
		
		final String test5 = "OTHER STREET AREA  ";
		assertEquals("Other Street Area", StringFormatUtil.upperLowerFormat(test5, true));
	}
	
	@Test
	public void upperLowerFormat_allWords_false_test() {
		final String test1 = "  a dog  not a   cat is my number 1   pet.  ";
		assertEquals("A dog  not a   cat is my number 1   pet.", StringFormatUtil.upperLowerFormat(test1, false));
		
		final String test2 = "  ";
		assertEquals("", StringFormatUtil.upperLowerFormat(test2, false));
		
		final String test3 = "";
		assertEquals("", StringFormatUtil.upperLowerFormat(test3, false));
		
		final String test4 = " a  ";
		assertEquals("A", StringFormatUtil.upperLowerFormat(test4, false));
		
		final String test5 = "OTHER STREET AREA.  ";
		assertEquals("Other street area.", StringFormatUtil.upperLowerFormat(test5, false));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void upperLowerFormat_null_value_test() {
		StringFormatUtil.upperLowerFormat(null, false);
	}


}
