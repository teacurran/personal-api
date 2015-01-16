package test.com.wirelust.personalapi.client.fitbit;

import java.io.File;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import com.wirelust.personalapi.client.fitbit.representations.UserType;
import junit.framework.Assert;
import org.apache.http.HttpResponse;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.com.wirelust.personalapi.client.fitbit.providers.JacksonConfigurationProvider;
import test.com.wirelust.personalapi.client.fitbit.providers.JacksonObjectMapper;

/**
 * 1/15/15
 *
 * @Author T. Curran
 */
@RunWith(Arquillian.class)
public class EndpointTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(EndpointTest.class);

	static final String ROOT_URL = "http://localhost:8080/test";

	@Deployment
	public static WebArchive create() {
		WebArchive testWar = ShrinkWrap.create(WebArchive.class, "test.war");
		testWar.addPackage("com.wirelust.personalapi.client.fitbit.representations");
		testWar.addPackage("test.com.wirelust.personalapi.client.fitbit.providers");

		File dir = new File("src/test/resources");
		addFiles(testWar, dir);

		System.out.println("test.war:" + testWar.toString(true));
		LOGGER.debug("test deployment: {}", testWar.toString(true));

		return testWar;
	}

	private static void addFiles(WebArchive war, File dir) throws IllegalArgumentException {
		if (dir == null || !dir.isDirectory()) {
			throw new IllegalArgumentException("not a directory");
		}
		for (File f : dir.listFiles()) {
			if (f.isFile()) {
				war.addAsWebResource(f, f.getPath().replace("\\", "/").substring("src/test/resources/".length()));
			} else {
				addFiles(war, f);
			}
		}
	}

	@Test
	public void shouldBeAbleToDeserializeUserProfile() throws Exception {

		ResteasyProviderFactory resteasyInstance = ResteasyProviderFactory.getInstance();
		RegisterBuiltin.register(resteasyInstance);
		resteasyInstance.registerProvider(JacksonConfigurationProvider.class);

		ClientRequest request = new ClientRequest(ROOT_URL + "/mock_responses/user_profile.json");

		ClientResponse<UserType> response = request.get(UserType.class);

		Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());

		UserType userType = response.getEntity();

		Assert.assertEquals("I live in San Francisco.", userType.getAboutMe());

	}

}
