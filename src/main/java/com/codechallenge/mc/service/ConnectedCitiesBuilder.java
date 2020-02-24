package com.codechallenge.mc.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.codechallenge.mc.domain.City;

public class ConnectedCitiesBuilder {

	private String record;
	private static final Map<String, City> connectedCitiesMap = new ConcurrentHashMap<>();

	public ConnectedCitiesBuilder() {
	}

	public ConnectedCitiesBuilder(String record) {
		super();
		this.record = record;
	}

	public void build() {
		String[] connecteditiesRecord = record.split(",");
		String originCity = connecteditiesRecord[0].toLowerCase();
		String destinationCity = connecteditiesRecord[1].toLowerCase();
		City city = new City(originCity, destinationCity);
		String key = originCity + "~" + destinationCity;
		if (!connectedCitiesMap.containsKey(key)) {
			connectedCitiesMap.put(key, city);
		}
	}

	public static Map<String, City> getConnectedcitiesmap() {
		return connectedCitiesMap;
	}
}
