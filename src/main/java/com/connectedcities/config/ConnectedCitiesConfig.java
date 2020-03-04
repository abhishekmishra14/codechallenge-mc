package com.connectedcities.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.connectedcities.domain.City;
import com.connectedcities.loader.ICitiesConnectionLoader;
import com.connectedcities.util.CCUtils;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ConnectedCitiesConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectedCitiesConfig.class);

	private Map<String, List<City>> connectedCitiesMap = new ConcurrentHashMap<>();

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private ICitiesConnectionLoader<File, City> fileLoader;
	
	@Bean(name = "connectedCities")
	Map<String, List<City>> connectedCitiesMap() {
		List<City> citiesList = loadCityConnections();
		for (City city : citiesList) {
			String key = city.getKey().toLowerCase();
			if (!connectedCitiesMap.containsKey(key)) {
				connectedCitiesMap.computeIfAbsent(key, list -> new ArrayList<City>()).add(city);
			} else {
				List<City> list = connectedCitiesMap.get(key);
				list.add(city);
				connectedCitiesMap.computeIfPresent(key, (k, v) -> list);
			}
		}
		return connectedCitiesMap;
	}

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.connectedcities.controller")).paths(PathSelectors.any())
				.build();
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title("Cities Connection App")
				.description("This app discover if two cities are connected.").version("1.0.0").build();
	}

	private List<City> loadCityConnections() {
		List<City> listCitiesConnected = null;
		try {
			Resource resource;
			resource = resourceLoader.getResource("classpath:" + CCUtils.CONNECTED_CITIES_FILENAME);
			File inputCCInputFile = resource.getFile();
			LOGGER.debug("File name to load data : " + inputCCInputFile);
			listCitiesConnected = fileLoader.loaddata(inputCCInputFile);
		} catch (Exception ex) {
			LOGGER.error("Error loading config data.", ex);
			System.exit(1);
		}
		return listCitiesConnected;
	}
}
