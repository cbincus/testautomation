package test.core.hybrid;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
	public static Properties loadpropertiesFromFile(String file)
			throws FileNotFoundException, IOException {
		try (InputStream is = new FileInputStream(
				System.getProperty("user.dir") + file)) {
			Properties properties = new Properties();
			properties.load(is);
			return properties;
		}
	}
}
