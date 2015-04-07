package test.com.wirelust.personalapi;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wirelust.personalapi.api.v1.V1ApplicationClient;
import com.wirelust.personalapi.client.fitbit.FitBitApiClient;
import com.wirelust.personalapi.data.model.ApiApplication;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.jboss.shrinkwrap.api.asset.FileAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 17-03-2015
 *
 * @Author T. Curran
 */
@RunWith(Arquillian.class)
public class ApiTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiTest.class);

	private static final String ROOT_URL = "http://127.0.0.1:8080/test";

	ResteasyClient client;
	ResteasyWebTarget target;
	V1ApplicationClient v1ApplicationClient;

	@Inject
	EntityManager em;

	@Inject
	UserTransaction utx;

	@Deployment
	public static WebArchive create() {
		WebArchive testWar = ShrinkWrap.create(WebArchive.class, "test.war");
		testWar.addPackages(true, "com.approachingpi");
		testWar.addPackages(true,
				Filters.exclude(".*com/wirelust/personalapi/client/.*"),
				"com.wirelust.personalapi");
		testWar.addPackage("test.com.wirelust.personalapi");

		testWar.addAsWebInfResource(new FileAsset(new File("src/main/webapp/WEB-INF/beans.xml")), "beans.xml");
		testWar.addAsWebInfResource(new FileAsset(new File("src/main/webapp/WEB-INF/web.xml")), "web.xml");
		testWar.addAsWebInfResource(new FileAsset(new File("src/main/webapp/WEB-INF/jboss-deployment-structure.xml")),
				"jboss-deployment-structure.xml");

		testWar.addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
				.importDependencies(ScopeType.RUNTIME)
				.resolve()
				.withTransitivity().asFile());
		testWar.addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
				.importDependencies(ScopeType.COMPILE)
				.resolve()
				.withTransitivity().asFile());

		File dir = new File("src/main/resources");
		addFilesToWebArchive(testWar, dir, "/WEB-INF/classes/");

		testWar.addAsResource("persistence-test.xml", "META-INF/persistence.xml");
		testWar.addAsWebInfResource("personalapi-test-ds.xml");

		// change the persistence to the test persistence
		//testWar.addAsResource("persistence-test.xml", "META-INF/persistence.xml");

		System.out.println("test.war:" + testWar.toString(true));
		LOGGER.debug("test deployment: {}", testWar.toString(true));

		return testWar;
	}

	@Before
	public void init() throws Exception {
		client = new ResteasyClientBuilder().build();
		target = client.target(ROOT_URL);
		v1ApplicationClient = target.proxy(V1ApplicationClient.class);

	}

	@After
	public void commitTransaction() throws Exception {
	    //utx.commit();
	}

	@Test
	public void shouldBeAbleToCreateAccount() throws Exception {

		utx.begin();

		ApiApplication apiApplication = new ApiApplication();
		em.persist(apiApplication);
		em.flush();

		utx.commit();

		Response response = v1ApplicationClient.register(
			apiApplication.getUuid(),
			apiApplication.getUuid(),
			"tea",
			"tea@grilledcheese.com",
			"Pas5w0rd!",
			"Terrence Curran",
			"1234"
		);

		Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());

		// This will always fail because we are passing in an invalid authCode
		//Assert.assertEquals(response.getStatus(), Status.OK.getStatusCode());

	}

	private static void addFilesToWebArchive(WebArchive war, File dir, String root) throws IllegalArgumentException {
		if (dir == null || !dir.isDirectory()) {
			throw new IllegalArgumentException("not a directory");
		}
		for (File f : dir.listFiles()) {
			if (f.isFile()) {
				war.addAsWebResource(f, root + f.getPath().replaceAll("\\\\", "/").substring("src/main/resources/".length())
				);
			} else {
				addFilesToWebArchive(war, f, root);
			}
		}
	}
}
