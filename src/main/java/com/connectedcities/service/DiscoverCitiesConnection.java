package com.connectedcities.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connectedcities.domain.City;

@Service
public class DiscoverCitiesConnection {

	@Autowired
	Map<String, List<City>> connectedCities;

	public boolean findCitiesConnection(String origin, String destination) {
		boolean isCitiesConnected = false;
		if (connectedCities.containsKey(origin)) {
			List<City> cityList = connectedCities.get(origin);
			isCitiesConnected = cityList.stream().anyMatch(city -> city.getDestination().equalsIgnoreCase(destination));
		}
		return isCitiesConnected;
	}
}
