package com.wirelust.personalapi.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Date: 4/11/11
 *
 * @author T. Curran
 */
public class StringUtils {

	private static final char[] LOWER_ALPHA_CHARS = ("abcdefghijklmnopqrstuvwxyz").toCharArray();
	private static final char[] UPPER_ALPHA_CHARS = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
	private static final char[] NUMERIC_CHARS = ("0123456789").toCharArray();
	private static final char[] SPECIAL_CHARS = ("!@#$%^&*()").toCharArray();
	private static final char[] ALL_CHARS =
		("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789").toCharArray();

	private StringUtils() {
		// utility class shouldn't be instantiated
	}

	public static boolean matches(final String input, final String pattern) {
		Pattern regexPattern = Pattern.compile(wildcardToRegex(pattern));
		Matcher matcher = regexPattern.matcher(input);
		return matcher.matches();
	}

	public static String wildcardToRegex(final String input) {
		StringBuilder sb = new StringBuilder(input.length() + 10);
		sb.append('^');
		for (int i = 0; i < input.length(); ++i) {
			char c = input.charAt(i);
			if (c == '*') {
				sb.append(".*");
			} else if (c == '?') {
				sb.append('.');
			} else if ("\\.[]{}()+-^$|".indexOf(c) >= 0) {
				sb.append('\\');
				sb.append(c);
			} else {
				sb.append(c);
			}
		}
		sb.append('$');
		return sb.toString();
	}

	public static boolean isEmpty(
		final String value ) {

		if ( value == null ) {
			return true;
		}

		return value.isEmpty();
	}

	public static String generateRandomString(final String pattern) {

		if ((pattern == null) || (pattern.length() == 0)) {
			return "";
		}
		final char[] patternChars = pattern.toCharArray();
		final StringBuilder retVal = new StringBuilder(pattern.length());
		final java.util.Random rndGen = new java.util.Random();
		for (char patternChar : patternChars) {
			if (patternChar == '#') {
				retVal.append(NUMERIC_CHARS[rndGen.nextInt(NUMERIC_CHARS.length)]);
			} else if (patternChar == 'L') {
				retVal.append(LOWER_ALPHA_CHARS[rndGen.nextInt(LOWER_ALPHA_CHARS.length)]);
			} else if (patternChar == 'U') {
				retVal.append(UPPER_ALPHA_CHARS[rndGen.nextInt(UPPER_ALPHA_CHARS.length)]);
			} else if (patternChar == 'S') {
				retVal.append(SPECIAL_CHARS[rndGen.nextInt(SPECIAL_CHARS.length)]);
			} else if (patternChar == '*') {
				retVal.append(ALL_CHARS[rndGen.nextInt(ALL_CHARS.length)]);
			}
		}
		return retVal.toString();
	}

	/**
	 * generates a username derivative with the goal is lessening spoofed usernames. <br/>
	 * The following actions are taken:
	 * <ul>
	 *     <li>underscores are removed</li>
	 *     <li>the number 1 is replaced with lowercase L</li>
	 *     <li>uppercase I is replaced with lowercase l</li>
	 *     <li>zero is replaced with lowercase o</li>
	 *     <li>usernames are made lowercase</li>
	 * </ul>
	 * This means that the username Barack_Obama and Barack__0bama will both be transformed into: barackobama
	 * @param inUsername
	 * @return
	 */
	public static String normalizeUsername(final String inUsername) {

		if (inUsername == null || inUsername.isEmpty()) {
			return "";
		}

		String normalized = inUsername;

		// strip out all underscores
		normalized = normalized.replaceAll("_", "");

		// replace common similar characters so users have a harder time impersonating others.
		normalized = normalized.replaceAll("1", "l");
		normalized = normalized.replaceAll("I", "l");
		normalized = normalized.replaceAll("0", "O");

		normalized = normalized.toLowerCase();

		return normalized;
	}
}
