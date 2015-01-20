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
 * @Author T. Curran
 */
public interface FitBitApiClient {


	@GET
	@Path("/1/user/-/activities/date/{date}.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserActivitiesDate(@PathParam("date") String date);

	@GET
	@Path("/1/user/-/activities/goals/daily.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserActivitiesGoalsDaily();

	@GET
	@Path("/1/user/-/activities/goals/weekly.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserActivitiesGoalsWeekly();

	@GET
	@Path("/1/user/-/body/date/{date}.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getuserBody(@PathParam("date") String date);

	@GET
	@Path("/1/user/-/body/log/fat/date/{date}.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserBodyLogFat(@PathParam("date") String date);

	@GET
	@Path("/1/user/-/body/log/fat/date/{date}/{period}.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserBodyLogFat(@PathParam("date") String date, @PathParam("date") String period);

	@GET
	@Path("/1/user/-/body/log/weight/date/{date}.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserBodyLogWeight(@PathParam("date") String date);

	@GET
	@Path("/1/user/-/body/log/weight/date/{date}/{period}.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserBodyLogWeight(@PathParam("date") String date, @PathParam("date") String period);

	@GET
	@Path("/1/user/-/body/log/fat/goal.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserBodyLogFatGoal();

	@GET
	@Path("/1/user/-/body/log/weight/goal.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserBodyLogWeightGoal();

	@GET
	@Path("/1/user/-/foods/log/date/{date}.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserFoodLog(@PathParam("date") String date);

	@GET
	@Path("/1/user/-/profile.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserProfile();
}
