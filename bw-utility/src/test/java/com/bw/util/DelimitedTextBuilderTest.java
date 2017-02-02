package com.bw.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.bw.util.DelimitedTextBuilder;

public class DelimitedTextBuilderTest {

	private static final char CARRIAGE_RETURN = (char) 13;
	private static final char LINE_FEED = (char) 10;
	private static final String WIN_NEWLINE = new String(
			String.valueOf(DelimitedTextBuilderTest.CARRIAGE_RETURN))
			.concat(String.valueOf(DelimitedTextBuilderTest.LINE_FEED));

	@Test
	public void content_is_empty_on_init() {
		DelimitedTextBuilder builder = new DelimitedTextBuilder();
		assertTrue(builder.getContent().equals(""));
	}

	@Test
	public void default_delimiter_and_qualifiers_are_used_when_not_specified() {
		DelimitedTextBuilder builder = new DelimitedTextBuilder();
		builder.appendValue("one").appendValue("two").appendValue("three");
		String content = builder.getContent();
		assertTrue(content.equals("one,two,three"));
	}

	@Test
	public void delimiter_can_be_specified() {
		DelimitedTextBuilder builder = new DelimitedTextBuilder("|");
		builder.appendValue("one").appendValue("two").appendValue("three");
		String content = builder.getContent();
		assertTrue(content.equals("one|two|three"));
	}

	@Test
	public void delimiter_can_be_multiple_characters() {
		DelimitedTextBuilder builder = new DelimitedTextBuilder(":|;");
		builder.appendValue("one").appendValue("two").appendValue("three");
		String content = builder.getContent();
		assertTrue(content.equals("one:|;two:|;three"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void delimiter_cannot_be_blank() {
		@SuppressWarnings("unused")
		DelimitedTextBuilder builder = new DelimitedTextBuilder("");
		fail("Did not receive expected exception.");
	}

	@Test
	public void delimiter_and_qualifier_can_be_specified() {
		DelimitedTextBuilder builder = new DelimitedTextBuilder("|", '\'');
		builder.appendValue("one").appendValue("two").appendValue("three");
		String content = builder.getContent();
		assertTrue(content.equals("'one'|'two'|'three'"));
	}

	@Test
	public void qualifier_can_be_null() {
		DelimitedTextBuilder builder = new DelimitedTextBuilder(",", null);
		builder.appendValue("one").appendValue("two").appendValue("three");
		String content = builder.getContent();
		assertTrue(content.equals("one,two,three"));
	}

	@Test
	public void empty_string_can_be_added_to_row() {
		DelimitedTextBuilder builder = new DelimitedTextBuilder();
		builder.appendValue("");
		String content = builder.getContent();
		assertTrue(content.equals(""));
	}

	@Test
	public void single_value_can_be_added_to_row() {
		DelimitedTextBuilder builder = new DelimitedTextBuilder();
		builder.appendValue("one");
		String content = builder.getContent();
		assertTrue(content.equals("one"));
	}

	@Test
	public void delimiter_is_added_when_multiple_values_added_to_row() {
		DelimitedTextBuilder builder = new DelimitedTextBuilder();
		builder.appendValue("one").appendValue("two");
		String content = builder.getContent();
		assertTrue(content.equals("one,two"));
	}

	@Test
	public void delimiters_in_input_value_are_replaced_before_adding_to_row() {
		DelimitedTextBuilder builder = new DelimitedTextBuilder("|");
		builder.appendValue("this|has|a|pipe|delimiter");
		String content = builder.getContent();
		assertTrue(content.equals("this has a pipe delimiter"));
	}

	@Test
	public void content_can_be_qualified_when_added_to_row() {
		DelimitedTextBuilder builder = new DelimitedTextBuilder(",", '"');
		builder.appendValue("one").appendValue("two").appendValue("three");
		assertTrue(builder.getContent().equals("\"one\",\"two\",\"three\""));
	}

	@Test
	public void new_row_adds_newline() {
		DelimitedTextBuilder builder = new DelimitedTextBuilder();
		String newLine = new Character((char) 13).toString()
				+ new Character((char) 10).toString();
		builder.appendValue("one").appendValue("two").appendValue("three")
		.newRow();
		String content = builder.getContent();
		assertTrue(content.equals("one,two,three" + newLine));
	}

	@Test
	public void creating_multiple_rows_adds_newlines() {
		DelimitedTextBuilder builder = new DelimitedTextBuilder();
		String newLine = new Character((char) 13).toString()
				+ new Character((char) 10).toString();

		builder.appendValue("One").appendValue("Two").appendValue("Three")
		.appendValue("Four").newRow();
		builder.appendValue("Five").appendValue("Six").appendValue("Seven")
		.appendValue("Eight").newRow();
		builder.appendValue("Nine").appendValue("Ten").appendValue("Eleven")
		.appendValue("Twelve").newRow();

		String expected = "One,Two,Three,Four" + newLine
				+ "Five,Six,Seven,Eight" + newLine + "Nine,Ten,Eleven,Twelve"
				+ newLine;
		String content = builder.getContent();

		assertTrue(content.equals(expected));
	}

	@Test
	public void line_feeds_are_replaced_with_space() {
		DelimitedTextBuilder builder = new DelimitedTextBuilder();
		String lineFeed = new Character((char) 10).toString();

		builder.appendValue("One" + lineFeed + "Two" + lineFeed + "Three");
		String expected = "One Two Three";
		String content = builder.getContent();

		assertTrue(content.equals(expected));
	}

	@Test
	public void carriage_returns_are_replaced_with_space() {
		DelimitedTextBuilder builder = new DelimitedTextBuilder();
		String carriageReturn = new Character((char) 13).toString();

		builder.appendValue("One" + carriageReturn + "Two" + carriageReturn
				+ "Three");
		String expected = "One Two Three";
		String content = builder.getContent();

		assertTrue(content.equals(expected));
	}

	@Test
	public void newlines_are_replaced_with_space() {
		DelimitedTextBuilder builder = new DelimitedTextBuilder();
		String newLine = new Character((char) 13).toString()
				+ new Character((char) 10).toString();

		builder.appendValue("One" + newLine + "Two" + newLine + "Three");
		String expected = "One Two Three";
		String content = builder.getContent();

		assertTrue(content.equals(expected));
	}

	@Test
	public void appendValue_newLines_test() {

		String field1Text = "Tesing with some winNewLines.. "
				.concat(DelimitedTextBuilderTest.WIN_NEWLINE);
		String field2Text = "Tesing with some carriage returns .. "
				.concat(String
						.valueOf(DelimitedTextBuilderTest.CARRIAGE_RETURN));
		String field3Text = "Tesing with some line feeds .. ".concat(String
				.valueOf(DelimitedTextBuilderTest.LINE_FEED));

		// new lines should be replaced with ' ' char for dft empty c'tor (by
		// dft)
		DelimitedTextBuilder builder = new DelimitedTextBuilder();
		assertTrue(field1Text.contains(DelimitedTextBuilderTest.WIN_NEWLINE));
		builder.appendValue(field1Text);
		assertFalse(builder.getContent().contains(
				DelimitedTextBuilderTest.WIN_NEWLINE));
		assertTrue(field2Text.contains(String
				.valueOf(DelimitedTextBuilderTest.CARRIAGE_RETURN)));
		builder.appendValue(field2Text);
		assertFalse(builder.getContent().contains(
				DelimitedTextBuilderTest.WIN_NEWLINE));
		assertFalse(builder.getContent().contains(
				String.valueOf(DelimitedTextBuilderTest.CARRIAGE_RETURN)));
		assertTrue(field3Text.contains(String
				.valueOf(DelimitedTextBuilderTest.LINE_FEED)));
		builder.appendValue(field3Text);
		assertFalse(builder.getContent().contains(
				DelimitedTextBuilderTest.WIN_NEWLINE));
		assertFalse(builder.getContent().contains(
				String.valueOf(DelimitedTextBuilderTest.CARRIAGE_RETURN)));
		assertFalse(builder.getContent().contains(
				String.valueOf(DelimitedTextBuilderTest.LINE_FEED)));
		// modify the default and ensure property overrides work (stop
		// replacing)
		builder.setReplaceNewLineChar(null);
		builder.appendValue(field1Text);
		assertTrue(builder.getContent().contains(
				DelimitedTextBuilderTest.WIN_NEWLINE));

		// new lines should be replaced with ' ' char when only a delimiter is
		// passed on c'tor (by dft)
		builder = new DelimitedTextBuilder(",");
		assertTrue(field1Text.contains(DelimitedTextBuilderTest.WIN_NEWLINE));
		builder.appendValue(field1Text);
		assertFalse(builder.getContent().contains(
				DelimitedTextBuilderTest.WIN_NEWLINE));
		assertTrue(field2Text.contains(String
				.valueOf(DelimitedTextBuilderTest.CARRIAGE_RETURN)));
		builder.appendValue(field2Text);
		assertFalse(builder.getContent().contains(
				DelimitedTextBuilderTest.WIN_NEWLINE));
		assertFalse(builder.getContent().contains(
				String.valueOf(DelimitedTextBuilderTest.CARRIAGE_RETURN)));
		assertTrue(field3Text.contains(String
				.valueOf(DelimitedTextBuilderTest.LINE_FEED)));
		builder.appendValue(field3Text);
		assertFalse(builder.getContent().contains(
				DelimitedTextBuilderTest.WIN_NEWLINE));
		assertFalse(builder.getContent().contains(
				String.valueOf(DelimitedTextBuilderTest.CARRIAGE_RETURN)));
		assertFalse(builder.getContent().contains(
				String.valueOf(DelimitedTextBuilderTest.LINE_FEED)));
		// modify the default and ensure property overrides work (stop
		// replacing)
		builder.setReplaceNewLineChar(null);
		builder.appendValue(field2Text);
		assertTrue(builder.getContent().contains(
				String.valueOf(DelimitedTextBuilderTest.CARRIAGE_RETURN)));

		// new lines should NOT be replaced when passing a qualifier (by dft).
		builder = new DelimitedTextBuilder(",", '"');
		assertTrue(field1Text.contains(DelimitedTextBuilderTest.WIN_NEWLINE));
		builder.appendValue(field1Text);
		assertTrue(builder.getContent().contains(
				DelimitedTextBuilderTest.WIN_NEWLINE));
		assertTrue(field2Text.contains(String
				.valueOf(DelimitedTextBuilderTest.CARRIAGE_RETURN)));
		builder.appendValue(field2Text);
		assertTrue(builder.getContent().contains(
				DelimitedTextBuilderTest.WIN_NEWLINE));
		assertTrue(builder.getContent().contains(
				String.valueOf(DelimitedTextBuilderTest.CARRIAGE_RETURN)));
		assertTrue(field3Text.contains(String
				.valueOf(DelimitedTextBuilderTest.LINE_FEED)));
		builder.appendValue(field3Text);
		assertTrue(builder.getContent().contains(
				DelimitedTextBuilderTest.WIN_NEWLINE));
		assertTrue(builder.getContent().contains(
				String.valueOf(DelimitedTextBuilderTest.CARRIAGE_RETURN)));
		assertTrue(builder.getContent().contains(
				String.valueOf(DelimitedTextBuilderTest.LINE_FEED)));
		// modify the default and ensure property overrides work (start
		// replacing). Reconstruct for clean contains tests here.
		builder = new DelimitedTextBuilder(",", '"');
		assertFalse(builder.isReplacingNewLines());
		builder.setReplaceNewLineChar(' ');
		assertTrue(builder.isReplacingNewLines());
		builder.appendValue(field2Text);
		assertFalse(builder.getContent().contains(
				String.valueOf(DelimitedTextBuilderTest.CARRIAGE_RETURN)));
		builder.appendValue(field3Text);
		assertFalse(builder.getContent().contains(
				String.valueOf(DelimitedTextBuilderTest.LINE_FEED)));
		builder.appendValue(field1Text);
		assertFalse(builder.getContent().contains(
				DelimitedTextBuilderTest.WIN_NEWLINE));

	}

	@Test
	public void appendValue_qualifiers_with_embededdelimiterinvalue_test() {
		DelimitedTextBuilder builder = new DelimitedTextBuilder(",", '"');
		builder.appendValue("one,two,three");
		builder.appendValue("four,five,six");
		assertEquals("\"one,two,three\",\"four,five,six\"",
				builder.getContent());
	}
}
