package test.com.wirelust.personalapi;

import java.io.File;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import javax.ws.rs.core.Response;

import com.wirelust.personalapi.api.v1.V1ApplicationClient;
import com.wirelust.personalapi.api.v1.representations.AccountType;
import com.wirelust.personalapi.api.v1.representations.ApplicationErrorType;
import com.wirelust.personalapi.api.v1.representations.AuthType;
import com.wirelust.personalapi.api.v1.representations.EnumErrorCode;
import com.wirelust.personalapi.data.model.ApiApplication;
import com.wirelust.personalapi.data.model.RestrictedUsername;
import com.wirelust.personalapi.util.StringUtils;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.FileAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 17-03-2015
 *
 * @author T. Curran
 */
@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApiTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiTest.class);

	private static final String ROOT_URL = "http://127.0.0.1:8080/test";

	private static final String REG_INVITE_CODE = "1234";
	private static final String REG_USER_1_USERNAME = "tea";
	private static final String REG_USER_1_EMAIL = "spam+1@grilledcheese.com";
	private static final String REG_USER_1_PASS = "Pas5w0rd!";

	private static final String REG_USER_2_USERNAME = "tea2";
	private static final String REG_USER_2_EMAIL = "spam+2@grilledcheese.com";

	ApiApplication application;

	ResteasyClient client;
	ResteasyWebTarget target;
	V1ApplicationClient v1ApplicationClient;

	static AuthType authorization;
	static AccountType user2Account;

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

		utx.begin();

		application = new ApiApplication();
		em.persist(application);
		em.flush();

		utx.commit();
	}

	@After
	public void commitTransaction() throws Exception {
	    //utx.commit();
	}

	@Test
	public void shouldBeAbleToCreateAccount() throws Exception {

		Response response = v1ApplicationClient.register(
			application.getUuid(),
			application.getUuid(),
			REG_USER_1_USERNAME,
			REG_USER_1_EMAIL,
			"Pas5w0rd!",
			"Terrence Curran",
			REG_INVITE_CODE
		);

		Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
		// save the authorization to use later
		authorization = response.readEntity(AuthType.class);
		Assert.assertNotNull(authorization);
		Assert.assertNotNull(authorization.getToken());

		// create another account
		response = v1ApplicationClient.register(
			application.getUuid(),
			application.getUuid(),
			REG_USER_2_USERNAME,
			REG_USER_2_EMAIL,
			"Pas5w0rd!",
			"Terrence Curran",
			REG_INVITE_CODE
		);
		AuthType user2Auth = response.readEntity(AuthType.class);
		Assert.assertNotNull(user2Auth);
		Assert.assertNotNull(user2Auth.getToken());
		Assert.assertNotNull(user2Auth.getAccount());
		user2Account = user2Auth.getAccount();
	}

	/**
	 * Tests that a user cannot create an account with an existing username or email address
	 * @throws Exception
	 */
	@Test
	public void shouldNotBeAbleToCreateDuplicateAccount() throws Exception {

		Response response = v1ApplicationClient.register(
				application.getUuid(),
				application.getUuid(),
				REG_USER_1_USERNAME,
				REG_USER_1_EMAIL,
				"Pas5w0rd!",
				"Terrence Curran",
				REG_INVITE_CODE);

		Assert.assertEquals(HttpServletResponse.SC_BAD_REQUEST, response.getStatus());

		ApplicationErrorType error = response.readEntity(ApplicationErrorType.class);
		Assert.assertEquals(error.getCode().value(), EnumErrorCode.USERNAME_EXISTS.value());
	}

	/**
	 * Tests that a user cannot create an account with an existing username or email address
	 * @throws Exception
	 */
	@Test
	public void shouldNotBeAbleToCreateAccountWithBadUsernameOrEmail() throws Exception {

		// Validate that we are unable ot pass in a bad username
		Response badUsernameResponse = v1ApplicationClient.register(
				application.getUuid(),
				application.getUuid(),
				"xx",
				REG_USER_1_EMAIL,
				REG_USER_1_PASS,
				"Terrence Curran",
				REG_INVITE_CODE);
		Assert.assertEquals(HttpServletResponse.SC_BAD_REQUEST, badUsernameResponse.getStatus());
		ApplicationErrorType accountError = badUsernameResponse.readEntity(ApplicationErrorType.class);
		Assert.assertEquals(accountError.getCode().value(), EnumErrorCode.ILLEGAL_ARGUMENT_ERROR.value());
		Assert.assertEquals(accountError.getParameterErrors().size(), 1);
		Assert.assertEquals(accountError.getParameterErrors().get(0).getParameter(), "username");

		// validate that we are not able to pass in an email
		Response badEmailResponse = v1ApplicationClient.register(
				application.getUuid(),
				application.getUuid(),
				REG_USER_1_USERNAME,
				"invalid-email",
				REG_USER_1_PASS,
				"Terrence Curran",
				REG_INVITE_CODE);
		Assert.assertEquals(HttpServletResponse.SC_BAD_REQUEST, badUsernameResponse.getStatus());
		ApplicationErrorType emailError = badEmailResponse.readEntity(ApplicationErrorType.class);

		Assert.assertEquals(emailError.getCode().value(), EnumErrorCode.ILLEGAL_ARGUMENT_ERROR.value());
		Assert.assertEquals(emailError.getParameterErrors().size(), 1);
		Assert.assertEquals(emailError.getParameterErrors().get(0).getParameter(), "email");

	}

	@Test
	public void shouldBeAbleToLogIn() throws Exception {
		Response responseWrongPassword = v1ApplicationClient.login(application.getUuid(),
				application.getUuid(),
				REG_USER_1_USERNAME,
				"Invalid password");
		Assert.assertEquals(HttpServletResponse.SC_BAD_REQUEST, responseWrongPassword.getStatus());
		ApplicationErrorType error = responseWrongPassword.readEntity(ApplicationErrorType.class);
		Assert.assertEquals(error.getCode().value(), EnumErrorCode.ACCOUNT_NOT_FOUND.value());

		Response responseCorrectPassword = v1ApplicationClient.login(application.getUuid(),
				application.getUuid(),
				REG_USER_1_USERNAME,
				REG_USER_1_PASS);
		Assert.assertEquals(HttpServletResponse.SC_OK, responseCorrectPassword.getStatus());

		AuthType loginAuth = responseCorrectPassword.readEntity(AuthType.class);
		Assert.assertNotNull(loginAuth);
		Assert.assertNotNull(loginAuth.getToken());

		Response logoutResponse = v1ApplicationClient.logout(loginAuth.getToken());
		Assert.assertEquals(HttpServletResponse.SC_NO_CONTENT, logoutResponse.getStatus());

	}

	@Test
	public void shouldBeAbleToGetInfo() throws Exception {
		Response response = v1ApplicationClient.info(authorization.getToken(), "me");

		Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());

		AccountType accountType = response.readEntity(AccountType.class);
		Assert.assertEquals(accountType.getEmail(), REG_USER_1_EMAIL);
	}

	/**
	 * authenticated user should be able to get info about another user, but that info should only include
	 * publically accessible info. At this time it means that the API won't return the user's email address.
	 * @throws Exception
	 */
	@Test
	public void shouldNotSeeOtherUsersPersonalInfo() throws Exception {
		Response response = v1ApplicationClient.info(authorization.getToken(), Long.toString(user2Account.getId()));

		Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());

		AccountType accountType = response.readEntity(AccountType.class);
		Assert.assertNull(accountType.getEmail());
	}

	@Test
	public void shouldNotAllowRestrictedUsername() throws Exception {
		// insert a restricted username
		utx.begin();
		RestrictedUsername restrictedUsername = new RestrictedUsername();
		restrictedUsername.setUsername("restricted_username");
		restrictedUsername.setUsernameNormalized(StringUtils.normalizeUsername("restricted_username"));
		em.persist(restrictedUsername);
		em.flush();
		utx.commit();

		Response validResponse = v1ApplicationClient.checkUsername("valid_username");
		Object entity = validResponse.getEntity();
		System.out.println(entity);
		Assert.assertEquals(HttpServletResponse.SC_NO_CONTENT, validResponse.getStatus());

		Response invalidResponse = v1ApplicationClient.checkUsername("restricted_username");
		Assert.assertEquals(HttpServletResponse.SC_BAD_REQUEST, invalidResponse.getStatus());
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
