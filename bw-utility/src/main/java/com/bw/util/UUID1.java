package com.bw.util;

import java.util.Date;
import java.util.UUID;

public abstract class UUID1 {

	static final long NUM_100NS_INTERVALS_SINCE_UUID_EPOCH = 0x01b21dd213814000L;

	public static UUID fromNow() {
		return UUID.fromString(new com.eaio.uuid.UUID().toString());
	}

	public static UUID fromString(String id) {
		UUID converted;
		converted = UUIDUtility.fromString(id);
		if (!isValid(converted)) {
			throw new IllegalArgumentException(
					"Passed id argument can not be converted to a version 1 UUID value. | id="
							+ id);
		}
		return converted;
	}

	public static long toTime(UUID uuid1) {
		if (!isUUID1(uuid1)) {
			throw new IllegalArgumentException(
					"Passed uuid1 argument is not a valid uuid1 uuid (missing timestamp). | uuid1="
							.concat(String.valueOf(uuid1)));
		}
		return (uuid1.timestamp() - NUM_100NS_INTERVALS_SINCE_UUID_EPOCH) / 10000;
	}

	public static Date toDate(UUID uuid1) {
		if (!isUUID1(uuid1)) {
			throw new IllegalArgumentException(
					"Passed uuid1 argument is not a valid uuid1 uuid (missing timestamp). | uuid1="
							.concat(String.valueOf(uuid1)));
		}
		return new Date(toTime(uuid1));
	}

	public static boolean isValid(UUID uuid1) {
		return isUUID1(uuid1);
	}

	public static boolean isValid(String uuid1) {
		boolean isValid = false;
		if (UUIDUtility.isValid(uuid1)) {
			isValid = isUUID1(UUID.fromString(uuid1));
		}
		return isValid;
	}

	public static boolean isUUID1(UUID uuid) {
		boolean isUUID1 = false;
		if (uuid != null) {
			try {
				isUUID1 = uuid.timestamp() > 0;
			} catch (UnsupportedOperationException e) {
				isUUID1 = false;
			}
		}
		return isUUID1;
	}

}
