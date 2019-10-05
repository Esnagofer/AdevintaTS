package com.esnagofer.textsearch.lib;

/**
 * The Class Validate.
 */
public class Validate {

	/**
	 * Throw exception.
	 *
	 * @param operation the operation
	 * @param name the name
	 */
	private static void throwException(String operation, String name) {
		throw new IllegalStateException(String.format("'%s' %s", name, operation));
	}
	
	/**
	 * Checks if is not blank.
	 *
	 * @param value the value
	 * @param name the exception message
	 * @return true, if is not blank
	 */
	public static boolean isNotBlank(String value, String name) {
		if (value == null || "".trim().equals(value)) {
			Validate.throwException("is blank", name);
		}
		return true;
	}
	
	/**
	 * Checks if is not null.
	 *
	 * @param value the value
	 * @param name the exception message
	 * @return true, if is not null
	 */
	public static boolean isNotNull(Object value, String name) {
		if (value == null) {
			Validate.throwException("is null", name);
		}
		return true;
	}
	
	/**
	 * Checks if is true.
	 *
	 * @param trueValue the true value
	 * @param name the name
	 * @return true, if is true
	 */
	public static boolean isTrue(boolean trueValue, String name) {
		if (!trueValue) {
			Validate.throwException("is false", name);
		}
		return true;
	}

	public static boolean isPositive(Integer value, String name) {
		isNotNull(value, name);
		if (value < 0) {
			Validate.throwException("is not positive", name);
		}
		return true;
	}

}
