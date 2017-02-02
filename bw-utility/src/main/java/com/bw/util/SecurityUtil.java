package com.bw.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.codec.binary.Base64;

import com.google.common.hash.Hashing;
import com.google.common.io.ByteStreams;

public class SecurityUtil {

	private static final String BASIC_AUTH_START_PART = "Basic ";

	public static String encodeBasicAuth(String name, String pw) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException(
					"Passed name can not be null or empty.");
		}
		if (pw == null || pw.trim().isEmpty()) {
			throw new IllegalStateException(
					"Passed pw can not be null or empty.");
		}
		String creds = name.concat(":").concat(pw);
		byte[] encoded = Base64.encodeBase64(creds.getBytes());
		String encStr = new String(encoded, StandardCharsets.UTF_8);
		encStr = "Basic ".concat(encStr);
		return encStr;
	}

	public static String decodeBasicAuthNamePart(String basicAuth) {
		return SecurityUtil.decodeBasicAuthParts(basicAuth)[0];
	}

	public static String[] decodeBasicAuthParts(String basicAuth) {
		if (basicAuth == null || basicAuth.isEmpty()) {
			throw new IllegalArgumentException(
					"Passed basicAuth can not be null or empty.");
		}
		if (!basicAuth.startsWith(SecurityUtil.BASIC_AUTH_START_PART)) {
			throw new IllegalArgumentException(
					"Passed basicAuth is not recognized as a valid basic auth string.  Must start with literal 'Basic '.");
		}
		String wrk = basicAuth
				.substring(SecurityUtil.BASIC_AUTH_START_PART.length(),
						basicAuth.length());
		wrk = new String(Base64.decodeBase64(wrk.getBytes()),
				StandardCharsets.UTF_8);
		String[] parts = wrk.split(":");
		if (parts.length != 2) {
			throw new IllegalArgumentException(
					"Passed basicAuth is not recognized as a valid basic auth string. Must produce two parts.");
		}
		return parts;
	}

	public static String getMD5(Path filePath) throws IOException {
		if (filePath == null) {
			throw new IllegalArgumentException(
					"Passed filePath can not be null.");
		}
		if (!Files.exists(filePath)) {
			throw new IOException("Passed filePath does not exist.");
		}
		if (!Files.isRegularFile(filePath)) {
			throw new IOException(
					"Passed filePath does not point to a regular file.");
		}
		byte[] bytes = Files.readAllBytes(filePath);
		return getMD5(bytes);
	}

	public static String getMD5(InputStream iStream) throws IOException {
		if (iStream == null) {
			throw new IllegalArgumentException(
					"Passed iStream can not be null.");
		}
		byte[] bytes = ByteStreams.toByteArray(iStream);
		return getMD5(bytes);
	}

	public static String getMD5(byte[] bytes) throws IOException {
		if (bytes == null || bytes.length == 0) {
			throw new IllegalArgumentException(
					"Passed bytes Array can not be null or empty.");
		}
		return Hashing.md5().hashBytes(bytes).toString();
	}

	public static String getMD5Base64(Path filePath) throws IOException {
		if (filePath == null) {
			throw new IllegalArgumentException(
					"Passed filePath can not be null.");
		}
		if (!Files.exists(filePath)) {
			throw new IOException("Passed filePath does not exist.");
		}
		if (!Files.isRegularFile(filePath)) {
			throw new IOException(
					"Passed filePath does not point to a regular file.");
		}
		byte[] bytes = Files.readAllBytes(filePath);
		return getMD5Base64(bytes);
	}

	public static String getMD5Base64(InputStream iStream) throws IOException {
		if (iStream == null) {
			throw new IllegalArgumentException(
					"Passed iStream can not be null.");
		}
		byte[] bytes = ByteStreams.toByteArray(iStream);
		return getMD5Base64(bytes);
	}

	public static String getMD5Base64(byte[] bytes) throws IOException {
		if (bytes == null || bytes.length == 0) {
			throw new IllegalArgumentException(
					"Passed bytes Array can not be null or empty.");
		}
		String hashStr = new String(Base64.encodeBase64(Hashing.md5()
				.hashBytes(bytes).asBytes()));
		return hashStr;
	}

}
