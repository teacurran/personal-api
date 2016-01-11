package test.wirelust.personalapi.client.googlefit;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 11-Jan-2016
 *
 * @author T. Curran
 */
@WebServlet(urlPatterns = "/*")
public class MockApiEndpointServlet extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory.getLogger(MockApiEndpointServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = req.getRequestURI().substring(req.getContextPath().length());

		LOGGER.info("requesting path:{}", path);

		InputStream reqInputStream = req.getInputStream();
		String bodyContent = IOUtils.toString(reqInputStream, StandardCharsets.UTF_8.name());
		if (bodyContent != null && !bodyContent.isEmpty()) {
			LOGGER.info("request body content:{}", bodyContent);
		}

		String outputMime = null;
		OutputStream out = resp.getOutputStream();

		InputStream is = this.getClass().getResourceAsStream(path + ".json");
		if (is != null) {
			outputMime = MediaType.APPLICATION_JSON;
		} else {
			 is = this.getClass().getResourceAsStream(path + ".txt");
			if (is != null) {
				outputMime = MediaType.APPLICATION_OCTET_STREAM;
			}
		}

		if (outputMime != null) {
			resp.setContentType(outputMime);
		}

		if (is == null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		} else {
			IOUtils.copy(is, out);
			is.close();
		}

		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
