package com.bw.util;

import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import org.junit.Test;

import com.bw.util.UUID1;

public class UUID1Test {

	@Test
	public void fromNow_test() {
		UUID v1UUID = UUID1.fromNow();
		assertNotNull(v1UUID);
		System.out.println("v1: ".concat(String.valueOf(v1UUID)));
		System.out.println("std: ".concat(String.valueOf(UUID.randomUUID())));
	}

}
