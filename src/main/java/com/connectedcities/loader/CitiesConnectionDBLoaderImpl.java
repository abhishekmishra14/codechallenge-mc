package com.connectedcities.loader;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.connectedcities.domain.City;

@Component
public class CitiesConnectionDBLoaderImpl implements ICitiesConnectionLoader<String, City> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CitiesConnectionDBLoaderImpl.class);

	@Override
	public List<City> loaddata(String sqlQuery) throws Exception {
		LOGGER.info("Load data from DB.");
		return null;
	}

}
