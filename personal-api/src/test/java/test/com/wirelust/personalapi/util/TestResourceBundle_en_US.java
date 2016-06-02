package test.com.wirelust.personalapi.util;

import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * Date: 01-Jun-2016
 *
 * @author T. Curran
 */
public class TestResourceBundle_en_US extends ResourceBundle {

	@Override
	protected Object handleGetObject(String key) {
		return "value1";
	}

	@Override
	public Enumeration<String> getKeys() {
		Vector<String> resources = new Vector<>();
		resources.add("test1");
		return resources.elements();
	}
}
