package com.bw.util;



public final class StringFormatUtil {

	
	public static String underscoreToSpace(String value) {
		if (value == null) {
			throw new IllegalArgumentException("Passed value can not be null.");
		}
		return underscoresToSpaces(value, false);
	}
	
	public static String underscoresToSpaces(String value) {
		if (value == null) {
			throw new IllegalArgumentException("Passed value can not be null.");
		}
		return underscoresToSpaces(value, true);
	}
	
	private static String underscoresToSpaces(String value, boolean all) {
		if (value == null) {
			throw new IllegalArgumentException("Passed value can not be null.");
		}
		if (all) {
			return value.replace("_", " ");
		} else {
			return value.replaceFirst("_", " ");
		}
	}
	
	
	
	/**
	 * Capitalize the first discovered word. Words delimited by spaces. all remaining
	 * characters will be converted to lower case values.
	 * 
	 * @param value : String value to operate on.  Will be trimmed prior to any operations.
	 * @return
	 */
	public static String upperLowerFormat(String value) {
		return upperLowerFormat(value, false);
	}
		
	/**
	 * Capitalize discovered word(s). Words delimited by spaces. all remaining
	 * characters will be converted to lower case values.
	 * 
	 * @param value : String value to operate on.  Will be trimmed prior to any operations.
	 * @param allWords : Optionally perform capitalization on all discovered words.
	 * @return
	 */
	public static String upperLowerFormat(String value, boolean allWords) {
		if (value == null) {
			throw new IllegalArgumentException("Passed value can not be null.");
		}
		
		if (StringUtil.isBlank(value)) {
			return "";
		}
		
		final String[] valueParts = value.trim().split(" ");
		String formatted = "";
		boolean firstFormatted = false;
		
		for (String i : valueParts) {
			if (i.length() == 0) {
				formatted = formatted.concat(" ");
				continue;
			}
			if (allWords || (!allWords && !firstFormatted)) {
				final String firstChar = i.substring(0, 1).toUpperCase();
				final String remainderChars = (i.length() == 1 ? "" : i.substring(1).toLowerCase());
				formatted = formatted.concat(firstChar).concat(remainderChars).concat(" ");
				firstFormatted = true;
			} else {
				formatted = formatted.concat(i.toLowerCase()).concat(" ");
			}
		}

		return formatted.trim();
	}
		
}
