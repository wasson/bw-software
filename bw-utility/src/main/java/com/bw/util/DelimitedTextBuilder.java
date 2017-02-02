package com.bw.util;

/**
 * This class allows for building multi-line delimited text strings. Text values
 * are added one value/column at a time and the row is ended as requested by the
 * consumer.
 */
public class DelimitedTextBuilder {

	private static final char CARRIAGE_RETURN = (char) 13;
	private static final char LINE_FEED = (char) 10;
	private static final String WIN_NEWLINE = new String(
			String.valueOf(DelimitedTextBuilder.CARRIAGE_RETURN)).concat(String
					.valueOf(DelimitedTextBuilder.LINE_FEED));
	public static final char DFT_DELIMITER = ',';
	public static final Character DFT_QUALIFIER = null;

	private final StringBuilder delimitedContent = new StringBuilder();
	private String delimiter = String
			.valueOf(DelimitedTextBuilder.DFT_DELIMITER);
	private Character qualifier = DelimitedTextBuilder.DFT_QUALIFIER;
	private boolean isEmptyRow = true;

	// Two defaults on initialized state depending on whether or not qualifiers
	// are being used.
	// 1. The most common is to not use qualifiers in which case we will strip
	// newLines with an empty sting by default (as defined here).
	// 2. If using qualifiers (passed on c'tor) then the default is to not strip
	// newLines by default. This will be controlled in the specific c'tor.
	private Character replaceNewLineChar = ' ';

	/**
	 * Creates an instance that will use the default delimiter text qualifier
	 * when building the text.
	 *
	 * - Default delimiter (','). - Default qualifier (none/null). - Default
	 * replaceNewLines (true).
	 *
	 */
	public DelimitedTextBuilder() {
		assert isReplacingNewLines() : "Default should be replacing new lines.";
	}

	/**
	 * Creates an instance that will use the specified delimiter and the default
	 * text qualifier when building the text.
	 *
	 * @param delimiter
	 *            Must be at least one character.
	 * @throws IllegalArgumentException
	 *             If delimiter is blank.
	 */
	public DelimitedTextBuilder(String delimiter) {
		if (delimiter == null || delimiter.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"Passed delimiter can not be null or empty.");
		}
		this.delimiter = delimiter;
		assert isReplacingNewLines() : "Default when passing delimiter should be replacing new lines.";
	}

	/**
	 * Creates an instance that will use the specified delimiter and text
	 * qualifier when building the text.
	 *
	 * @param delimiter
	 *            Must be at least one character.
	 * @param qualifier
	 *            Must be zero or one character.
	 *
	 * @throws IllegalArgumentException
	 *             If delimiter is blank or if qualifier is more than one
	 *             character.
	 */
	public DelimitedTextBuilder(String delimiter, Character qualifier) {
		if (delimiter == null || delimiter.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"Passed delimiter can not be null or empty.");
		}
		this.delimiter = delimiter;
		if (qualifier != null) {
			if (delimiter.equals(String.valueOf(qualifier))) {
				throw new IllegalArgumentException(
						"Passed delimiter and qualifier can not be equal values.");
			}
			// dft when using qualifiers is to not strip (preserve) newLines.
			this.replaceNewLineChar = null;
			this.qualifier = qualifier;
			assert isReplacingNewLines() == false : "Default when passing delimiter and qualifier should NOT be replacing new lines.";
		}
	}

	/**
	 * Appends a single String value to the end of the current row of delimited
	 * content, prepending the delimiter if necessary and qualifying the string
	 * if a text qualifier has been specified.
	 *
	 * This method returns the current instance allowing calls to be chained
	 * together.
	 *
	 * @param value
	 *            String value representing the text to add to the row.
	 * @return This instance of DelimitedTextBuilder to allow for method
	 *         chaining.
	 */
	public final DelimitedTextBuilder appendValue(String value) {
		String wrkValue = value;
		if (isUsingQualifier()) {
			wrkValue = String.valueOf(qualifier).concat(wrkValue)
					.concat(String.valueOf(qualifier));
		} else {
			// Ensure that our active delimiter is cleaned from our added value
			// since we are not using a qualifier
			wrkValue = wrkValue.replace(String.valueOf(delimiter), " ");
		}

		if (isReplacingNewLines()) {
			wrkValue = convertLineBreaks(wrkValue,
					String.valueOf(getReplaceNewLineChar()));
		}

		if (!isEmptyRow) {
			delimitedContent.append(delimiter);
		} else {
			isEmptyRow = false;
		}

		delimitedContent.append(wrkValue);

		return this;
	}

	public final boolean isReplacingNewLines() {
		return this.replaceNewLineChar != null;
	}

	public final Character getReplaceNewLineChar() {
		return this.replaceNewLineChar;
	}

	public final void setReplaceNewLineChar(Character replaceChar) {
		this.replaceNewLineChar = replaceChar;
	}

	/**
	 * Appends a new line to the delimited content thereby starting a new row.
	 */
	public final void newRow() {
		delimitedContent.append(DelimitedTextBuilder.WIN_NEWLINE);
		isEmptyRow = true;
	}

	/**
	 * Returns the delimited text content that has been built up.
	 *
	 * @return The delimited text content.
	 */
	public final String getContent() {
		return delimitedContent.toString();
	}

	/**
	 * Replaces any line feed, carriage return, or Windows new line characters
	 * from an input string.
	 *
	 * @param value
	 *            The String from which to remove the line breaks.
	 * @param replacementValue
	 *            The string to use as the replacement value for line breaks.
	 * @return New String instance with line breaks removed.
	 */
	private String convertLineBreaks(String value, String replacementValue) {
		String convertedValue = value;
		/*
		 * Replacement of the WIN_NEWLINE must come first to prevent adding
		 * double spaces since the new line contains both a carriage return and
		 * a line feed.
		 */
		convertedValue = convertedValue.replace(
				DelimitedTextBuilder.WIN_NEWLINE, replacementValue);
		convertedValue = convertedValue.replace(
				String.valueOf(DelimitedTextBuilder.CARRIAGE_RETURN),
				replacementValue);
		convertedValue = convertedValue.replace(
				String.valueOf(DelimitedTextBuilder.LINE_FEED),
				replacementValue);
		return convertedValue;
	}

	private boolean isUsingQualifier() {
		return this.qualifier != null;
	}
}
