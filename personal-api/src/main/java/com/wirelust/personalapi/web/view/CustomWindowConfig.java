package com.wirelust.personalapi.web.view;

import javax.enterprise.inject.Specializes;
import javax.faces.context.FacesContext;

import org.apache.deltaspike.jsf.spi.scope.window.DefaultClientWindowConfig;

/**
 * Date: 29-01-2015
 *
 * @author T. Curran
 *
 * This class will remove the DeltaSpike URL Parameters that are added for the WindowScoped Views
 * ?dsRid=191&windowId=-8996
 *
 * This will disable WindowScoped but we don't use it in the project so it is okay.
 */
@Specializes
public class CustomWindowConfig extends DefaultClientWindowConfig {

	@Override
	public ClientWindowRenderMode getClientWindowRenderMode(FacesContext facesContext) {
		return ClientWindowRenderMode.NONE;
	}
}
