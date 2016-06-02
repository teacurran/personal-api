package test.com.wirelust.personalapi.producers;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.inject.Inject;

import com.wirelust.personalapi.locales.UTF8ResourceBundleControl;
import com.wirelust.personalapi.producers.ResourceBundleProducer;
import com.wirelust.personalapi.qualifiers.ClasspathResource;
import com.wirelust.personalapi.qualifiers.Localization;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Date: 24-Apr-2016
 *
 * @author T. Curran
 */
@RunWith(CdiRunner.class)
@AdditionalClasses(ResourceBundleProducer.class)
public class ResourceBundleProducerTest {

	private static final String APPLICATION_NAME = "Personal API";

	@Inject
	@ClasspathResource("defaults.properties")
	Properties defaultProperties;

	@Inject
	@Localization
	ResourceBundle localization;

	@Inject
	@ClasspathResource("invalid.properties")
	Properties invalidProperties;

	@Test
	public void shouldBeAbleToLoadResource() {
		Assert.assertEquals("default", defaultProperties.getProperty("applicationSetting"));
	}

	@Test
	public void shouldBeAbleToReadLocalization() {
		Assert.assertEquals(APPLICATION_NAME, localization.getString("application.name"));
	}

	@Test
	public void invalidPropertiesShouldNotLoad() {
		Assert.assertNull(invalidProperties.getProperty("applicationSetting"));
	}

	@Test
	public void shouldBeAbleToloadLocaleWithReload() throws Exception {
		UTF8ResourceBundleControl utf8ResourceBundleControl = new UTF8ResourceBundleControl();
		ResourceBundle loadedLocale = utf8ResourceBundleControl.newBundle("locales.I18n", Locale.US, "java.properties",
			this.getClass().getClassLoader(), true);
		Assert.assertEquals(APPLICATION_NAME, loadedLocale.getString("application.name"));
	}

	@Test
	public void shouldBeAbleToLoadResourceBundleClass() throws Exception {
		UTF8ResourceBundleControl utf8ResourceBundleControl = new UTF8ResourceBundleControl();
		ResourceBundle loadedBUndle = utf8ResourceBundleControl
			.newBundle("test.com.wirelust.personalapi.util.TestResourceBundle", Locale.US, "java.class",
				this.getClass().getClassLoader(), false);

		Assert.assertEquals("value1", loadedBUndle.getString("test1"));
	}

	@Test(expected = ClassCastException.class)
	public void shouldThrowClassCastExceptionOnNonResourceBundle() throws Exception {
		UTF8ResourceBundleControl utf8ResourceBundleControl = new UTF8ResourceBundleControl();

		utf8ResourceBundleControl.newBundle("test.com.wirelust.personalapi.util.TestNonResourceBundle",
			Locale.US, "java.class", this.getClass().getClassLoader(), false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArguementException() throws Exception {
		UTF8ResourceBundleControl utf8ResourceBundleControl = new UTF8ResourceBundleControl();

		utf8ResourceBundleControl.newBundle("test.com.wirelust.personalapi.util.TestResourceBundle",
			Locale.US, "invalid.format", this.getClass().getClassLoader(), false);
	}

}
