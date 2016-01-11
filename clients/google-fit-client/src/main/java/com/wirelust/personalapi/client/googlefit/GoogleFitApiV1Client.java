package com.wirelust.personalapi.client.googlefit;

import com.wirelust.personalapi.client.googlefit.representations.DataSource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Date: 10-Sep-2015
 *
 * @author T. Curran
 */
public interface GoogleFitApiV1Client {

	@POST
	@Path("/{user-id}/dataSources")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postUserDatasource(DataSource dataSource);

	@GET
	@Path("/{user-id}/dataSources")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDataSources(
		@PathParam("user-id") String userId
	);

	@GET
	@Path("/{user-id}/dataSources/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDataSource(
		@PathParam("user-id") String userId
	);


}
