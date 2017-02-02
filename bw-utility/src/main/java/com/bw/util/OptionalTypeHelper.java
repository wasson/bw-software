package com.bw.util;

import java.nio.file.Path;
import java.util.Date;

import com.google.common.base.Optional;

public class OptionalTypeHelper {
	
	public static final Optional<String> absentOptString = Optional.absent();
	
	public static final Optional<Path> absentOptPath = Optional.absent();
	
	public static final Optional<Double> absentOptDouble = Optional.absent();
	
	public static final Optional<Integer> absentOptInteger = Optional.absent();
	
	public static final Optional<Date> absentOptDate = Optional.absent();
	
	public static final Optional<Boolean> absentOptBoolean = Optional.absent();
	
}
