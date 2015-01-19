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
	@Path("/1/user/-/body/date/{date}.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getuserBodyDate(@PathParam("date") String date);

	@GET
	@Path("/1/user/-/body/log/weight/date/{date}.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserBodyLogWeightDate(@PathParam("date") String date);

	@GET
	@Path("/1/user/-/foods/log/date/{date}.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserFoodLogDate(@PathParam("date") String date);

	@GET
	@Path("/1/user/-/profile.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserProfile();
}
