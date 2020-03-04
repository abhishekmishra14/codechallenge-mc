package com.connectedcities.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.connectedcities.domain.City;

public class CitiesBuilder {

	private static final Logger LOGGER = LoggerFactory.getLogger(CitiesBuilder.class);

	private String key;
	private String originCityName;
	private String destinationCityName;

	public CitiesBuilder(String key) {
		super();
		this.key = key;
	}

	public CitiesBuilder setOriginCityName(String originCityName) {
		this.originCityName = originCityName;
		return this;
	}

	public CitiesBuilder setDestinationCityName(String destinationCityName) {
		this.destinationCityName = destinationCityName;
		return this;
	}

	public City build() {
		return new City(key, originCityName, destinationCityName);
	}

}
