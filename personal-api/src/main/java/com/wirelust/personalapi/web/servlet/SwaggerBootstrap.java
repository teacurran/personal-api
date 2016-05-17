package com.wirelust.personalapi.web.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.DefaultJaxrsConfig;

public class SwaggerBootstrap extends DefaultJaxrsConfig {

	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);

		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.0");
		beanConfig.setTitle("Personal API");
		beanConfig.setSchemes(new String[]{"http", "https"});
		beanConfig.setResourcePackage("com.wirelust.personalapi.api.v1");

		String contextPath = config.getServletContext().getContextPath();
		beanConfig.setBasePath(contextPath + "/api/v1");
		beanConfig.setScan(true);
	}
}
