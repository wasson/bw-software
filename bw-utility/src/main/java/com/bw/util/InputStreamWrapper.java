package com.bw.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.util.SecurityUtil;



public class InputStreamWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InputStreamWrapper.class);
	
	public final InputStream inputStream;
	public final String etag;

	private InputStreamWrapper(InputStreamWrapperBuilder builder) {
		this.inputStream = builder.inputStream;
		this.etag = builder.etag;
	}
	
	public static class InputStreamWrapperBuilder {
		
		private final byte[] bytes;
		private InputStream inputStream;
		private String etag = "";
		
		public InputStreamWrapperBuilder(byte[] b) {
			this.bytes = b;
		}
		
		public InputStreamWrapper build() {
			// closed implementation (only a c'tor) so should be no threading issues with state as build is occurring in this case.
			try {
				this.etag = SecurityUtil.getMD5(this.bytes);
			} catch (IOException e) {
				LOGGER.error("Error producing md5 hash. Etag will not be assigned.", e);
			}
			this.inputStream = new ByteArrayInputStream(this.bytes);
			InputStreamWrapper inputStreamWrapper = new InputStreamWrapper(this);
			return inputStreamWrapper;
		}
		
	}

}