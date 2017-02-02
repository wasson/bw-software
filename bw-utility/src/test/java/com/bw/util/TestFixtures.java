package com.bw.util;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestFixtures {

	// resources
	protected static final String TF_TEXT_FILE = "tf_test-text.txt";
	protected static final String TF_TEXT_FILE_BIG = "tf_big_file.txt";

	protected static Path getTestFixtureFile(String tf) throws IOException {
		return Paths.get(String.valueOf(getResourcesPath()), tf);
	}

	protected static Path getResourcesPath() throws IOException {
		Path relTest = Paths
				.get("./src/test/resources/testinput/");
		Path resourceDir = relTest.toRealPath();
		return resourceDir;
	}

}
