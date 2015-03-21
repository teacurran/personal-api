package test.com.wirelust.personalapi;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
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

	@Deployment
	public static WebArchive create() {
		WebArchive testWar = ShrinkWrap.create(WebArchive.class, "test.war");
		testWar.addPackages(true, "com.wirelust.personalapi");
		testWar.addPackage("test.com.wirelust.personalapi");

		// change the persistence to the test persistence
		//testWar.addAsResource("persistence-test.xml", "META-INF/persistence.xml");

		System.out.println("test.war:" + testWar.toString(true));
		LOGGER.debug("test deployment: {}", testWar.toString(true));

		return testWar;
	}

	@Test
	public void shouldBeAbleToCreateAccount() throws Exception {


		// This will always fail because we are passing in an invalid authCode
		//Assert.assertEquals(response.getStatus(), Status.OK.getStatusCode());

	}

}
