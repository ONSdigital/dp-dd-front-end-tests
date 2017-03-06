package uk.gov.ons.dd.frontend.core;


import uk.gov.ons.dd.frontend.util.Helper;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class Configuration {
	public static final int DEFAULT_TIMEOUT_VALUE = 20;
	public static int DEFAULT_TIME_OUT = 5;
	private Logger log = Logger.getLogger(Configuration.class.getCanonicalName());
	private String baseURL;
	private String browser;
	private String backend;
	private String metadataEditor, fileuploader;



	public Configuration() {
		loadConfig("/files/local_config.properties");
		overrideConfigFromEnvironmentVariables();

		log.info("Test environment " + this.toString());
	}

	public String getBaseURL() {
		return baseURL;
	}

	public String getBrowser() {
		return browser;
	}

	public String getBackend() {
		return backend;
	}

	@SuppressWarnings("rawtypes")
	private void loadConfig(String filePath) {
		InputStream input = Configuration.class.getResourceAsStream(filePath);
		Properties config = new Properties();
		try {
			config.load(input);
		} catch (Exception ee) {
			ee.printStackTrace();
		}

		if (config.getProperty("base_url") != null) {
			baseURL = (String) config.get("base_url");
		}

		if (config.getProperty("browser") != null) {
			browser = (String) config.get("browser");
		}
		if (config.getProperty("backend") != null) {
			backend = (String) config.get("backend");
		}
		if (config.containsKey("metadata_editor")) {
			metadataEditor = (String) config.get("metadata_editor");
		}
		if (config.containsKey("fileUploader")) {
			fileuploader = (String) config.get("fileUploader");
		}
	}

	private void overrideConfigFromEnvironmentVariables() {

		String ons_url_value = Helper.getSetting("base_url");
		if (ons_url_value != null) {
			baseURL = ons_url_value;
		}
		String browser_value = Helper.getSetting("browser");
		if (browser_value != null) {
			browser = browser_value;
		}
		String backend_value = Helper.getSetting("backend");
		if (backend_value != null) {
			backend = backend_value;
		}
	}

	public String getMetadataEditor() {
		return metadataEditor;
	}

	public String getFileuploader() {
		return fileuploader;
	}

	@Override
	public String toString() {
		return "Configuration{" +
				"baseURL=" + baseURL +
				", browser=" + browser +
				", backend=" + backend +
				'}';
	}

}
