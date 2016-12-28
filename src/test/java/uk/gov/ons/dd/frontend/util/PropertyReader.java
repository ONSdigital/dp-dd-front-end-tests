package uk.gov.ons.dd.frontend.util;

import uk.gov.ons.dd.frontend.core.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class PropertyReader {
	public static HashMap <String, String> elementMap = new HashMap <String, String>();
	Properties props = new Properties();

	public PropertyReader(Configuration configuration) {
		loadLocators(configuration.getBackend().equals("stub"));
	}

	public void loadLocators(boolean stub) {
		String propertyFile = null;
		if (stub) {
			propertyFile = "stub_locators.properties";
		} else {
			propertyFile = "backend_locators.properties";
		}
		InputStream input = Configuration.class.getResourceAsStream("/files/" + propertyFile);
		try {
			props.load(input);
			Set <Object> keys = props.keySet();
			for (Object key : keys) {
				elementMap.put((String) key, props.getProperty((String) key));
			}

		} catch (IOException ee) {
			ee.printStackTrace();
		}
	}

	public HashMap <String, String> getElementMap() {
		return elementMap;

	}

	public String getValue(String locator) {
		return elementMap.get(locator);
	}

}
