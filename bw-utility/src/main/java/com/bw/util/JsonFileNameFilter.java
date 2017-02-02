package com.bw.util;

import java.io.File;
import java.io.FilenameFilter;

public class JsonFileNameFilter implements FilenameFilter {

	@Override
	public boolean accept(File file, String name) {
		if (file.isDirectory()) {
			if (name.endsWith(".json")) {
				return true;
			}
		}
		return false;
	}

}
