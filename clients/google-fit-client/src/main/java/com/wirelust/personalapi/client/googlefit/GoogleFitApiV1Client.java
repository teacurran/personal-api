package com.wirelust.personalapi.client.googlefit;

import com.wirelust.personalapi.client.googlefit.annotations.PATCH;
import com.wirelust.personalapi.client.googlefit.representations.DataSet;
import com.wirelust.personalapi.client.googlefit.representations.DataSource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
	@Path("/fitness/v1/users/{user-id}/dataSources")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postUserDatasource(DataSource dataSource);

	@GET
	@Path("/fitness/v1/users/{user-id}/dataSources")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDataSources(
		@PathParam("user-id") String userId
	);

	@GET
	@Path("/fitness/v1/users/{user-id}/dataSources/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDataSource(
		@PathParam("user-id") String userId,
		@PathParam("id") String id
	);

	@POST
	@Path("/fitness/v1/users/{user-id}/dataSources/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response putDataSource(
		@PathParam("user-id") String userId,
		@PathParam("id") String id
	);

	@DELETE
	@Path("/fitness/v1/users/{user-id}/dataSources/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteDataSource(
		@PathParam("user-id") String userId,
		@PathParam("id") String id
	);

	@GET
	@Path("/fitness/v1/users/{user-id}/dataSources/{datasource-id}/datasets/{dataset-id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDataSet(
		@PathParam("user-id") String userId,
		@PathParam("datasource-id") String datasourceId,
		@PathParam("dataset-id") String datasetId
	);

	@PATCH
	@Path("/fitness/v1/users/{user-id}/dataSources/{datasource-id}/datasets/{dataset-id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response patchDataSet(
		@PathParam("user-id") String userId,
		@PathParam("datasource-id") String datasourceId,
		@PathParam("dataset-id") String datasetId,
		DataSet dataset
	);
}
