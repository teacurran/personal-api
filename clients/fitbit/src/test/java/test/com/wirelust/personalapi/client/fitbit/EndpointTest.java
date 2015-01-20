package test.com.wirelust.personalapi.client.fitbit;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import com.wirelust.personalapi.client.fitbit.FitBitApiClient;
import com.wirelust.personalapi.client.fitbit.representations.ActivityType;
import com.wirelust.personalapi.client.fitbit.representations.BodyGoalType;
import com.wirelust.personalapi.client.fitbit.representations.BodyGoalsType;
import com.wirelust.personalapi.client.fitbit.representations.BodyType;
import com.wirelust.personalapi.client.fitbit.representations.DistanceType;
import com.wirelust.personalapi.client.fitbit.representations.FatType;
import com.wirelust.personalapi.client.fitbit.representations.FoodType;
import com.wirelust.personalapi.client.fitbit.representations.ActivityGoalsType;
import com.wirelust.personalapi.client.fitbit.representations.ActivitySummaryType;
import com.wirelust.personalapi.client.fitbit.representations.UserActivitiesDateResponseType;
import com.wirelust.personalapi.client.fitbit.representations.UserBodyDateResponseType;
import com.wirelust.personalapi.client.fitbit.representations.UserBodyLogFatResponseType;
import com.wirelust.personalapi.client.fitbit.representations.UserBodyLogGoalResponseType;
import com.wirelust.personalapi.client.fitbit.representations.UserBodyLogWeightResponseType;
import com.wirelust.personalapi.client.fitbit.representations.UserFoodLogDateResponseType;
import com.wirelust.personalapi.client.fitbit.representations.UserResponseType;
import com.wirelust.personalapi.client.fitbit.representations.UserType;
import com.wirelust.personalapi.client.fitbit.representations.WeightType;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
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
		fitbitClient = target.proxy(FitBitApiClient.class);

		simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
		TimeZone zone = TimeZone.getTimeZone("GMT+0");
		simpleDateFormat.setTimeZone(zone);
	}

	@After
	public void destroy() {
	}

	@Test
	public void deserializeUserActivitiesDate() throws Exception {

		Response response = fitbitClient.getUserActivitiesDate("2010-04-25");

		Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());

		UserActivitiesDateResponseType responseType = response.readEntity(UserActivitiesDateResponseType.class);

		List<ActivityType> activities = responseType.getActivities();

		Assert.assertEquals(2, activities.size());

		ActivityType activity1 = activities.get(0);
		Assert.assertEquals(51007L, activity1.getActivityId().longValue());

		ActivityType activity2 = activities.get(1);
		Assert.assertEquals(51002L, activity2.getActivityId().longValue());

		ActivityGoalsType goals = responseType.getGoals();
		Assert.assertEquals(2826, goals.getCaloriesOut().intValue());
		Assert.assertEquals(8.05, goals.getDistance());
		Assert.assertEquals(150, goals.getFloors().intValue());

		ActivitySummaryType summary = responseType.getSummary();
		Assert.assertEquals(8, summary.getDistances().size());

		DistanceType distance1 = summary.getDistances().get(0);
		Assert.assertEquals(Double.parseDouble("1.32"), distance1.getDistance());

	}

	@Test
	public void deserializeUserActivitiesGoalsDaily() throws Exception {

		Response response = fitbitClient.getUserActivitiesGoalsDaily();

		Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());

		UserActivitiesDateResponseType responseType = response.readEntity(UserActivitiesDateResponseType.class);

		ActivityGoalsType goals = responseType.getGoals();
		Assert.assertEquals(2500, goals.getCaloriesOut().intValue());
		Assert.assertEquals(8.05, goals.getDistance());
		Assert.assertEquals(15, goals.getFloors().intValue());
		Assert.assertEquals(10000, goals.getSteps().intValue());
	}

	@Test
	public void deserializeUserBodyDate() throws Exception {

		Response response = fitbitClient.getuserBody("2010-04-25");

		Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());

		UserBodyDateResponseType responseType = response.readEntity(UserBodyDateResponseType.class);

		BodyType bodyType = responseType.getBody();
		Assert.assertEquals(Double.parseDouble("16.14"), bodyType.getBmi());

		BodyGoalsType goalsType = responseType.getGoals();
		Assert.assertEquals(Double.parseDouble("75"), goalsType.getWeight());
	}

	@Test
	public void deserializeUserBodyLogFatDate() throws Exception {
		Response response = fitbitClient.getUserBodyLogFat("2010-04-25");

		Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());

		UserBodyLogFatResponseType responseType = response.readEntity(UserBodyLogFatResponseType.class);

		List<FatType> fats = responseType.getFat();
		Assert.assertEquals(2, fats.size());

		Date weightDate = simpleDateFormat.parse("2012-03-05");

		FatType fat1 = fats.get(0);
		Assert.assertEquals(14d, fat1.getFat());
		Assert.assertEquals(weightDate, fat1.getDate());
		Assert.assertEquals(1330991999000L, fat1.getLogId().longValue());
		Assert.assertEquals("23:59:59", fat1.getTime());

		FatType fat2 = fats.get(1);
		Assert.assertEquals(13.5, fat2.getFat());
		Assert.assertEquals(weightDate, fat2.getDate());
		Assert.assertEquals(1330991999000L, fat2.getLogId().longValue());
		Assert.assertEquals("21:20:59", fat2.getTime());
	}

	@Test
	public void deserializeUserBodyLogFatGoal() throws Exception {
		Response response = fitbitClient.getUserBodyLogFatGoal();
		Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());

		UserBodyLogGoalResponseType responseType = response.readEntity(UserBodyLogGoalResponseType.class);

		BodyGoalType goalType = responseType.getGoal();
		Assert.assertEquals(15d, goalType.getFat());
	}

	@Test
	public void deserializeUserBodyLogWeightDate() throws Exception {
		Response response = fitbitClient.getUserBodyLogWeight("2010-04-25");
		Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());

		UserBodyLogWeightResponseType responseType = response.readEntity(UserBodyLogWeightResponseType.class);

		List<WeightType> weights = responseType.getWeight();
		Assert.assertEquals(2, weights.size());

		Date weightDate = simpleDateFormat.parse("2012-03-05");

		WeightType weight1 = weights.get(0);
		Assert.assertEquals(23.57, weight1.getBmi());
		Assert.assertEquals(weightDate, weight1.getDate());
		Assert.assertEquals(1330991999000L, weight1.getLogId().longValue());
		Assert.assertEquals("23:59:59", weight1.getTime());
		Assert.assertEquals(73d, weight1.getWeight());

		WeightType weight2 = weights.get(1);
		Assert.assertEquals(22.57, weight2.getBmi());
		Assert.assertEquals(weightDate, weight2.getDate());
		Assert.assertEquals(1330991999000L, weight2.getLogId().longValue());
		Assert.assertEquals("21:10:59", weight2.getTime());
		Assert.assertEquals(72.5, weight2.getWeight());
	}

	@Test
	public void deserializeUserBodyLogWeightGoal() throws Exception {
		Response response = fitbitClient.getUserBodyLogWeightGoal();
		Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());

		Date goalDate = simpleDateFormat.parse("2012-03-05");

		UserBodyLogGoalResponseType responseType = response.readEntity(UserBodyLogGoalResponseType.class);

		BodyGoalType goalType = responseType.getGoal();
		Assert.assertEquals(goalDate, goalType.getStartDate());
		Assert.assertEquals(70d, goalType.getStartWeight());
		Assert.assertEquals(75d, goalType.getWeight());
	}

	@Test
	public void deserializeUserFoodsLogDate() throws Exception {

		Response response = fitbitClient.getUserFoodLog("2010-04-25");

		Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());

		UserFoodLogDateResponseType responseType = response.readEntity(UserFoodLogDateResponseType.class);

		List<FoodType> foods = responseType.getFoods();
		Assert.assertEquals(2, foods.size());

		FoodType food1 = foods.get(0);
		Assert.assertEquals("Chocolate, Milk", food1.getLoggedFood().getName());
		Assert.assertEquals(147L, food1.getLoggedFood().getUnit().getId().longValue());

		Assert.assertEquals(752, food1.getNutritionalValues().getCalories().intValue());
		Assert.assertEquals(66.5, food1.getNutritionalValues().getCarbs());
		Assert.assertEquals(49d, food1.getNutritionalValues().getFat());
		Assert.assertEquals(0.5, food1.getNutritionalValues().getFiber());
		Assert.assertEquals(12.5, food1.getNutritionalValues().getProtein());
		Assert.assertEquals(186d, food1.getNutritionalValues().getSodium());
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
