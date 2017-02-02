package com.bw.util;

import java.util.UUID;

public abstract class UUIDUtility {
	private static final String UUID_DELIMITER = "-";
	private static final int UUID_HEX_CHAR_CNT = 32;
	private static final int UUID_HEX_CHAR_CNT_WITH_HYPH = 36;

	public static UUID fromString(String id) {
		UUID converted = null;
		String wrkUUIDHex = id;
		if (wrkUUIDHex == null || wrkUUIDHex.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"Passed id argument can not be null or empty.");
		}
		if (wrkUUIDHex.length() != UUID_HEX_CHAR_CNT) {
			if (wrkUUIDHex.length() != UUID_HEX_CHAR_CNT_WITH_HYPH) {
				throw new IllegalArgumentException(
						"Passed id argument can not be converted to a valid UUID type. | id="
								.concat(id));
			}
			converted = UUID.fromString(wrkUUIDHex);
		} else {
			String fmtUUID = "";
			fmtUUID = wrkUUIDHex.substring(0, 8)
					.concat(UUIDUtility.UUID_DELIMITER)
					.concat(wrkUUIDHex.substring(8, 12))
					.concat(UUIDUtility.UUID_DELIMITER)
					.concat(wrkUUIDHex.substring(12, 16))
					.concat(UUIDUtility.UUID_DELIMITER)
					.concat(wrkUUIDHex.substring(16, 20))
					.concat(UUIDUtility.UUID_DELIMITER)
					.concat(wrkUUIDHex.substring(20));
			converted = UUID.fromString(fmtUUID);
		}
		return converted;
	}

	public static boolean isValid(String uuid) {
		boolean isValid = false;
		if (uuid != null) {
			UUID u = null;
			try {
				u = UUID.fromString(uuid);
				isValid = (u != null);
			} catch (IllegalArgumentException e) {
				isValid = false;
			}
			if (isValid) {
				String uval = String.valueOf(u);
				// ensure that a terse uuid string was not passed (filling gaps
				// in
				// otherwise good uuid string)
				isValid = uval.equals(uuid);
			}
		}
		return isValid;
	}

}
