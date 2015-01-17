package test.com.wirelust.personalapi.client.fitbit;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import com.wirelust.personalapi.client.fitbit.FitBitApiClient;
import com.wirelust.personalapi.client.fitbit.representations.BodyType;
import com.wirelust.personalapi.client.fitbit.representations.GoalsType;
import com.wirelust.personalapi.client.fitbit.representations.UserBodyDateResponseType;
import com.wirelust.personalapi.client.fitbit.representations.UserResponseType;
import com.wirelust.personalapi.client.fitbit.representations.UserType;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.com.wirelust.personalapi.client.fitbit.providers.JacksonConfigurationProvider;

/**
 * 1/15/15
 *
 * @Author T. Curran
 */
@RunWith(Arquillian.class)
public class EndpointTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(EndpointTest.class);

	static final String ROOT_URL = "http://localhost:8080/test";
	static final String DATE_FORMAT = "yyyy-MM-dd";

	ResteasyClient client;
	ResteasyWebTarget target;
	FitBitApiClient fitbitClient;
	SimpleDateFormat simpleDateFormat;

	@Deployment
	public static WebArchive create() {
		WebArchive testWar = ShrinkWrap.create(WebArchive.class, "test.war");
		testWar.addPackages(true, "com.wirelust.personalapi.client.fitbit");
		testWar.addPackage("test.com.wirelust.personalapi.client.fitbit.providers");

		testWar.addAsWebResource(new File("src/test/resources/mock_responses/user_profile.json"),
				"/1/user/-/profile.json");

		testWar.addAsWebResource(new File("src/test/resources/mock_responses/user_body_date.json"),
				"/1/user/-/body/date/2010-04-25.json");

		testWar.addAsWebResource(new File("src/test/resources/mock_responses/user_body_log_weight_date.json"),
				"/1/user/-/body/log/weight/date/2010-02-21.json");

		File dir = new File("src/test/resources/WEB-INF");
		addFilesToWebArchive(testWar, dir);

		System.out.println("test.war:" + testWar.toString(true));
		LOGGER.debug("test deployment: {}", testWar.toString(true));

		return testWar;
	}

	@Before
	public void init() {
		client = new ResteasyClientBuilder().build();
		client.register(JacksonConfigurationProvider.class);
		target = client.target(ROOT_URL);
		fitbitClient = target.proxy(FitBitApiClient.class);

		simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
		TimeZone zone = TimeZone.getTimeZone("GMT+0");
		simpleDateFormat.setTimeZone(zone);
	}

	@After
	public void destroy() {
	}

	@Test
	public void deserializeUserProfile() throws Exception {

		Response response = fitbitClient.getUserProfile();

		Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());

		UserResponseType userResponse = response.readEntity(UserResponseType.class);
		UserType userType = userResponse.getUser();

		Assert.assertEquals("I live in San Francisco.", userType.getAboutMe());

		// Make sure the client is deserializing dates properly
		Date usersBirthday = simpleDateFormat.parse("1970-02-18");
		Assert.assertEquals(usersBirthday, userType.getDateOfBirth());

		Date usersMemberSince = simpleDateFormat.parse("2010-02-07");
		Assert.assertEquals(usersMemberSince, userType.getMemberSince());

		// make sure it is converting the doubles correctly
		Double usersHeight = Double.parseDouble("176.75");
		Assert.assertEquals(usersHeight, userType.getHeight());

	}

	@Test
	public void deserializeUserBodyDate() throws Exception {

		Response response = fitbitClient.getuserBodyDate("2010-04-25");

		Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());

		UserBodyDateResponseType bodyResponse = response.readEntity(UserBodyDateResponseType.class);

		BodyType bodyType = bodyResponse.getBody();
		GoalsType goalsType = bodyResponse.getGoals();

		Assert.assertEquals(Double.parseDouble("16.14"), bodyType.getBmi());
		Assert.assertEquals(Double.parseDouble("75"), goalsType.getWeight());
	}

	private static void addFilesToWebArchive(WebArchive war, File dir) throws IllegalArgumentException {
		if (dir == null || !dir.isDirectory()) {
			throw new IllegalArgumentException("not a directory");
		}
		for (File f : dir.listFiles()) {
			if (f.isFile()) {
				war.addAsWebResource(f, f.getPath().replace("\\", "/").substring("src/test/resources/".length()));
			} else {
				addFilesToWebArchive(war, f);
			}
		}
	}

}
