package com.wirelust.personalapi.client.fitbit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * 1/16/15
 *
 * @author T. Curran
 */
public interface FitBitApiClient {

	@GET
	@Path("/1/user/{user-id}/activities/date/{date}.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserActivitiesDate(
			@PathParam("user-id") String userId,
			@PathParam("date") String date);

	@GET
	@Path("/1/user/{user-id}/activities/goals/daily.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserActivitiesGoalsDaily(@PathParam("user-id") String userId);

	@GET
	@Path("/1/user/{user-id}/activities/goals/weekly.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserActivitiesGoalsWeekly(@PathParam("user-id") String userId);

	@GET
	@Path("/1/user/{user-id}/body/date/{date}.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getuserBody(
			@PathParam("user-id") String userId,
			@PathParam("date") String date);

	@GET
	@Path("/1/user/{user-id}/body/log/fat/date/{date}.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserBodyLogFat(
			@PathParam("user-id") String userId,
			@PathParam("date") String date);

	@GET
	@Path("/1/user/{user-id}/body/log/fat/date/{date}/{period}.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserBodyLogFat(
			@PathParam("user-id") String userId,
			@PathParam("date") String date,
			@PathParam("period") String period);

	@GET
	@Path("/1/user/{user-id}/body/log/weight/date/{date}.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserBodyLogWeight(
			@PathParam("user-id") String userId,
			@PathParam("date") String date);

	@GET
	@Path("/1/user/{user-id}/body/log/weight/date/{date}/{period}.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserBodyLogWeight(
			@PathParam("user-id") String userId,
			@PathParam("date") String date,
			@PathParam("period") String period);

	@GET
	@Path("/1/user/{user-id}/body/log/fat/goal.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserBodyLogFatGoal(@PathParam("user-id") String userId);

	@GET
	@Path("/1/user/{user-id}/bp/date/{date}.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserBloodPressure(
			@PathParam("user-id") String userId,
			@PathParam("date") String date);

	@GET
	@Path("/1/user/{user-id}/body/log/weight/goal.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserBodyLogWeightGoal(@PathParam("user-id") String userId);

	@GET
	@Path("/1/user/{user-id}/foods/log/date/{date}.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserFoodLog(
			@PathParam("user-id") String userId,
			@PathParam("date") String date);

	@GET
	@Path("/1/user/{user-id}/foods/log/goal.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserFoodLogGoal(@PathParam("user-id") String userId);

	@GET
	@Path("/1/user/{user-id}/foods/log/water/date/{date}.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserWaterLog(
			@PathParam("user-id") String userId,
			@PathParam("date") String date);

	@GET
	@Path("/1/user/{user-id}/foods/log/water/goal.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserWaterLogGoal(@PathParam("user-id") String userId);

	@GET
	@Path("/1/user/{user-id}/glucose/date/{date}.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserGlucose(
			@PathParam("user-id") String userId,
			@PathParam("date") String date);

	@GET
	@Path("/1/user/{user-id}/heart/date/{date}.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserHeartRate(
			@PathParam("user-id") String userId,
			@PathParam("date") String date);

	@GET
	@Path("/1/user/{user-id}/profile.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserProfile(@PathParam("user-id") String userId);

	@GET
	@Path("/1/user/{user-id}/sleep/date/{date}.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserSleepDate(
			@PathParam("user-id") String userId,
			@PathParam("date") String date);
}
