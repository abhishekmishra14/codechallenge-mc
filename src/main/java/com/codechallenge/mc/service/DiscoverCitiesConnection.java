package com.codechallenge.mc.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.codechallenge.mc.domain.City;

@Service
public class DiscoverCitiesConnection {

	public boolean findCitiesConnection(String origin, String destination) {
		boolean isCitiesConnected = false;
		Map<String, City> connectedCitiesMap = ConnectedCitiesBuilder.getConnectedcitiesmap();
		String generateKey = origin + "~" + destination;
		if (connectedCitiesMap.containsKey(generateKey)) {
			City connectedCity = connectedCitiesMap.get(generateKey);
			isCitiesConnected = connectedCity.isConnected();
		}
		return isCitiesConnected;
	}
}
