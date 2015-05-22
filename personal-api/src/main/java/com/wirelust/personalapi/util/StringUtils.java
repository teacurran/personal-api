package com.wirelust.personalapi.util;

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

	/**
	 * Performs a wildcard matching for the text and pattern provided.
	 *
	 * method taken from http://www.adarshr.com/papers/wildcard
	 *
	 * @param text
	 *            the text to be tested for matches.
	 * @param pattern
	 *            the pattern to be matched for. This can contain the wildcard character '*'
	 *            (asterisk).
	 * @return <tt>true</tt> if a match is found, <tt>false</tt> otherwise.
	 *
	 */
	public static boolean wildCardMatch(
		String text,
		final String pattern ) {

		// Create the cards by splitting using a RegEx. If more speed
		// is desired, a simpler character based splitting can be done.
		final String[] cards = pattern.split( "\\*" );

		// Iterate over the cards.
		for ( final String card : cards ) {
			final int idx = text.indexOf( card );

			// Card not detected in the text.
			if ( idx == -1 ) {
				return false;
			}

			// Move ahead, towards the right of the text.
			text = text.substring( idx + card.length() );
		}

		return true;
	}

	public static boolean isEmpty(
		final String value ) {

		if ( value == null ) {
			return true;
		}

		return value.isEmpty();
	}

	public static String generateRandomString(
		final String pattern ) {

		if ( (pattern == null) || (pattern.length() == 0) ) {
			return "";
		}
		final char[] patternChars = pattern.toCharArray();
		final StringBuffer retVal = new StringBuffer( pattern.length() );
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
