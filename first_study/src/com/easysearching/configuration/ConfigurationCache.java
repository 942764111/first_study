package com.easysearching.configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigurationCache {

	private static Map<String, String> configurationCache = new HashMap<String, String>();

	private static Properties properties = new Properties();

	private static String realWebPath;

	static {
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("easysearching.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ConfigurationCache() {

	}

	public static void initConfiguration(String realPath) {
		realWebPath = realPath;
		configurationCache.put(ConfigurationConstant.FILE_PATH, realPath + properties.getProperty(ConfigurationConstant.FILE_PATH));
		configurationCache.put(ConfigurationConstant.INDEX_PATH, realPath + properties.getProperty(ConfigurationConstant.INDEX_PATH));
		configurationCache.put(ConfigurationConstant.FILE_UPLOAD_ENABLE_TYPE, properties.getProperty(ConfigurationConstant.FILE_UPLOAD_ENABLE_TYPE));
		configurationCache.put(ConfigurationConstant.FILE_UPLOAD_MAX_SIZE, properties.getProperty(ConfigurationConstant.FILE_UPLOAD_MAX_SIZE));
		configurationCache.put(ConfigurationConstant.FILE_UPLOAD_PATH, realPath + properties.getProperty(ConfigurationConstant.FILE_UPLOAD_PATH));
		configurationCache.put(ConfigurationConstant.FILE_UPLOAD_THRESHOLD_SIZE, properties
				.getProperty(ConfigurationConstant.FILE_UPLOAD_THRESHOLD_SIZE));
		configurationCache.put(ConfigurationConstant.FILE_UPLOAD_TEMP_PATH, realPath
				+ properties.getProperty(ConfigurationConstant.FILE_UPLOAD_TEMP_PATH));
		configurationCache.put(ConfigurationConstant.PAGING_SPLIT_PAGESIZE, properties.getProperty(ConfigurationConstant.PAGING_SPLIT_PAGESIZE));
		configurationCache.put(ConfigurationConstant.EASYSEARCHING_INDEXSOURCE_GENERATE_MODE, properties
				.getProperty(ConfigurationConstant.EASYSEARCHING_INDEXSOURCE_GENERATE_MODE));
	}

	public static String getConfiguration(String configurationName) {
		return configurationCache.get(configurationName);
	}

	public static void clearCache() {
		configurationCache.clear();
	}

	public static void reloadCache() {
		clearCache();
		initConfiguration(realWebPath);
	}

}
