package util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	/**
	 * This method is used to load the properties from config.properties file
	 * @return it returns Properties prop object
	 */
	private static Properties projectProperties = null;

	// To get the data from project.properties file
	public static String getProperties(String key) {
		if (projectProperties == null) {
			try {
				projectProperties = new Properties();
				projectProperties.load(new FileReader("env.properties"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return projectProperties.getProperty(key);
	}

}

