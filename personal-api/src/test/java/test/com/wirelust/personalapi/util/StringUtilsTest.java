package test.com.wirelust.personalapi.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import com.wirelust.personalapi.api.helpers.AccountHelper;
import com.wirelust.personalapi.util.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Date: 13-Jul-2016
 *
 * @author T. Curran
 */
public class StringUtilsTest {

	private static final String SEARCH_STRING = "The quick fox jumped over the lazy dog.";

	/**
	 * This method simply instantiates a private constructor to ensure code coverage for it so the
	 * coverage reports aren't diminished
	 */
	@Test
	public void testConstantsConstructorIsPrivate() throws Exception {
		Constructor<StringUtils> constructor = StringUtils.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test
	public void shouldBeAbleToMatchWildcard() {
		assertTrue(StringUtils.matches(SEARCH_STRING, "*lazy dog*"));
		assertTrue(StringUtils.matches(SEARCH_STRING, "*lazy dog."));
		assertTrue(StringUtils.matches(SEARCH_STRING, "The quick ?ox jumped over ?he lazy dog."));

		assertFalse(StringUtils.matches(SEARCH_STRING, "*lazy do.."));
		assertFalse(StringUtils.matches(SEARCH_STRING, "*lazy dog"));
		assertFalse(StringUtils.matches(SEARCH_STRING, "The quick [a-z]ox jumped over [a-z]he lazy dog."));
	}

	@Test
	public void shouldBeAbleToGenerateRandomString() {
		String randomNumber = StringUtils.generateRandomString("######");
		assertTrue(randomNumber.matches("\\d\\d\\d\\d\\d\\d"));

		String randomString = StringUtils.generateRandomString("#LUS*");
		assertTrue(randomString.matches("\\d[a-z][A-Z][!@#$%^&*()]."));

		randomString = StringUtils.generateRandomString("#LUS*XXXXXXXXX");
		assertTrue(randomString.matches("\\d[a-z][A-Z][!@#$%^&*()]."));

		assertEquals("", StringUtils.generateRandomString(null));
		assertEquals("", StringUtils.generateRandomString(""));
	}

	@Test
	public void shouldBeAbleToNormalizeUsername() {
		assertEquals("", StringUtils.normalizeUsername(null));
		assertEquals("", StringUtils.normalizeUsername(""));

		assertEquals("lonley", StringUtils.normalizeUsername("1onley"));
		assertEquals("lodine", StringUtils.normalizeUsername("Iodine"));
		assertEquals("lopht", StringUtils.normalizeUsername("l0pht"));
	}

	@Test
	public void shouldBeAbleToTestForEmptyString() {
		assertTrue(StringUtils.isEmpty(""));
		assertTrue(StringUtils.isEmpty(null));
		assertFalse(StringUtils.isEmpty("string"));
	}
}
