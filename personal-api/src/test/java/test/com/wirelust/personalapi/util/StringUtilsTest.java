package test.com.wirelust.personalapi.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import com.wirelust.personalapi.api.helpers.AccountHelper;
import com.wirelust.personalapi.util.StringUtils;
import org.junit.Test;

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
		Constructor<AccountHelper> constructor = AccountHelper.class.getDeclaredConstructor();
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

}
