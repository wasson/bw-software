package com.bw.util;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.bw.util.SecurityUtil;

public class SecurityUtilTest {

	@Test
	public void getBasicAuth_test() {
		assertEquals(
				"Basic YW5vbnltb3VzOjUyMDJiOGMzLThiZjktNDgwOS05MTg0LWI1ZjEwYTAyZTQwZg==",
				SecurityUtil.encodeBasicAuth("anonymous",
						"5202b8c3-8bf9-4809-9184-b5f10a02e40f"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void getNameFromBasicAuth_invalidBasicAuth_test() {
		SecurityUtil.decodeBasicAuthNamePart("Basiclakjsfjafjaf");
	}

	@Test
	public void decodeBasicAuthNamePart_test() {
		String name = SecurityUtil
				.decodeBasicAuthNamePart("Basic YW5vbnltb3VzOjUyMDJiOGMzLThiZjktNDgwOS05MTg0LWI1ZjEwYTAyZTQwZg==");
		assertEquals("anonymous", name);

	}

	@Test
	public void getMD5_smallFile_test() throws IOException {
		String md5 = SecurityUtil.getMD5(TestFixtures
				.getTestFixtureFile(TestFixtures.TF_TEXT_FILE));
		assertEquals("6c81424422251ecd31fea8c45650016d", md5);
	}

	@Test
	public void getMD5_bigFile_test() throws IOException {
		String md5 = SecurityUtil.getMD5(TestFixtures
				.getTestFixtureFile(TestFixtures.TF_TEXT_FILE_BIG));
		assertEquals("a556c3c7003ee815505b724c79c3f997", md5);
	}

}
