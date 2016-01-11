package test.wirelust.personalapi.client.googlefit;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import com.wirelust.personalapi.client.googlefit.GoogleFitApiV1Client;
import com.wirelust.personalapi.client.googlefit.representations.DataSourceList;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.wirelust.personalapi.client.googlefit.providers.JacksonConfigurationProvider;

/**
 * Date: 10-Jan-2016
 *
 * @author T. Curran
 */
@RunWith(Arquillian.class)
public class EndpointTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(EndpointTest.class);

	static final String ROOT_URL = "http://localhost:8080/fitbit-client-test";
	static final String MOCK_USER_ID = "me";
	static final String DATE_FORMAT = "yyyy-MM-dd";
	static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";

	SimpleDateFormat simpleDateFormat;
	SimpleDateFormat simpleDateTimeFormat;

	ResteasyClient client;
	ResteasyWebTarget target;
	GoogleFitApiV1Client apiClient;

	@Deployment
	public static WebArchive create() {
		WebArchive testWar = ShrinkWrap.create(WebArchive.class, "googlefit-client-test.war");
		testWar.addPackages(true, "com.wirelust.personalapi.client.googlefit");
		testWar.addPackage("test.wirelust.personalapi.client.googlefit.providers");

		File dir = new File("src/test/resources");
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
		apiClient = target.proxy(GoogleFitApiV1Client.class);

		TimeZone gmtZone = TimeZone.getTimeZone("GMT+0");

		simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
		simpleDateFormat.setTimeZone(gmtZone);

		simpleDateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
		simpleDateTimeFormat.setTimeZone(gmtZone);
	}

	@After
	public void destroy() {
	}

	public void shouldBeAbleToDeseralizeDataSources() throws Exception {

		Response response = apiClient.getDataSources(MOCK_USER_ID);
		Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());

		DataSourceList dataSources = response.readEntity(DataSourceList.class);

		Assert.assertEquals(40, dataSources.getDataSources().size());
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
