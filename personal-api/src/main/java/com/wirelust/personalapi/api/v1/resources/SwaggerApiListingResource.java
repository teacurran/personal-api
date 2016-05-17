package com.wirelust.personalapi.api.v1.resources;

import javax.servlet.ServletConfig;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import io.swagger.annotations.ApiOperation;
import io.swagger.jaxrs.listing.ApiListingResource;

/**
 * Date: 17-May-2016
 *
 * @author T. Curran
 *
 * This class is created because resteasy.media.type.mappings will do content negociation
 * based on the extension in the url so a resource of /swagger.json won't work.
 */
@Path("/")
public class SwaggerApiListingResource extends ApiListingResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("swagger")
	@ApiOperation(value = "The swagger definition in JSON", hidden = true)
	public Response getListingJson(@Context Application app,
								   @Context ServletConfig sc,
								   @Context HttpHeaders headers,
								   @Context UriInfo uriInfo) {
		return super.getListingJson(app, sc, headers, uriInfo);
	}
}
