package com.stehno.cola.util;

import org.apache.commons.lang.ArrayUtils;

import com.stehno.cola.AllowedStates;
import com.stehno.cola.SessionState;
import com.stehno.cola.command.Pop3Command;

public final class Utils {

	private Utils(){super();}

	public static final SessionState[] extractAllowedStates(final Pop3Command command){
		return command.getClass().getAnnotation(AllowedStates.class).value();
	}

	public static final boolean isAllowedState(final Pop3Command command, final SessionState state){
		return ArrayUtils.contains(extractAllowedStates(command), state);
	}
}
