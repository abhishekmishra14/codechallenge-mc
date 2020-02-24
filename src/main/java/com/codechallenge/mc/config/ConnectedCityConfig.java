package com.codechallenge.mc.config;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.codechallenge.mc.domain.City;
import com.codechallenge.mc.service.ConnectedCitiesLoaderService;
import com.codechallenge.mc.util.CCUtils;

@Configuration
public class ConnectedCityConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectedCityConfig.class);
	private static final Map<String, City> connectedCitiesMap = new ConcurrentHashMap<>();

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private ConnectedCitiesLoaderService loaderService;

	@PostConstruct
	public void init() {
		loadConfig();
	}

	private void loadConfig() {
		loadConnectedCitiesFromInputFile();
	}

	private void loadConnectedCitiesFromInputFile() {
		try {
			Resource resource;
			resource = resourceLoader.getResource("classpath:" + CCUtils.CONNECTED_CITIES_FILENAME);
			File inputCCInputFile = resource.getFile();
			if (!CCUtils.isValidCCFile(inputCCInputFile)) {
				LOGGER.error("Input file for connected cities is invalid or empty.");
				System.exit(1);
			}
			loaderService.loadFileData(inputCCInputFile);
		} catch (Exception ex) {
			LOGGER.error("Error loading config data.", ex);
			System.exit(1);
		}
	}
}
