package java.test.wirelust.personalapi.client.googlefit.rest.v1;

import com.wirelust.personalapi.client.googlefit.GoogleFitApiV1Client;
import com.wirelust.personalapi.client.googlefit.representations.DataSource;

import javax.ws.rs.core.Response;

/**
 * Date: 10-Sep-2015
 *
 * @author T. Curran
 */
public class GoogleFitApiV1Resource implements GoogleFitApiV1Client {

	@Override
	public Response postUserDatasource(DataSource dataSource) {
		return null;
	}

	@Override
	public Response getDataSources() {
		return null;
	}

	@Override
	public Response getDataSource() {
		return null;
	}
}
