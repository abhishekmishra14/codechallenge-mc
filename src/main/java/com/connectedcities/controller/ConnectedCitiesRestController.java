package com.connectedcities.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.connectedcities.service.DiscoverCitiesConnection;
import com.connectedcities.util.CCEnum;
import com.connectedcities.util.CCUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class ConnectedCitiesRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectedCitiesRestController.class);

	@Autowired
	DiscoverCitiesConnection discoverCityConnection;

	@ApiOperation(value = "findCitiesConnectedPathParam", notes = "Find origin/destination cities is connected by roads.")
	@GetMapping("/connected/{origin}/{destination}")
	public Enum findCitiesConnectedByPathPar(@ApiParam(value = "origin city") @PathVariable String origin,
			@ApiParam(value = "destination city") @PathVariable String destination) {

		StopWatch watch = new StopWatch();
		watch.start();
		CCEnum apiResponse = CCEnum.NO;
		boolean isCitiesValid = isCitiesValid(origin, destination);
		if (isCitiesValid) {
			origin = StringUtils.replace(origin, "%20", " ").toLowerCase();
			destination = StringUtils.replace(destination, "%20", " ").toLowerCase();
			boolean isCitiesConnected = discoverCityConnection.findCitiesConnection(origin, destination);
			if (isCitiesConnected) {
				apiResponse = CCEnum.YES;
			}
		}
		watch.stop();
		LOGGER.debug("Time elapsed : " + watch.getTotalTimeSeconds());
		return apiResponse;
	}

	@ApiOperation(value = "findCitiesConnectedByRequestParam", notes = "Find origin/destination cities is connected by roads.")
	@GetMapping("/connected")
	public Enum findCitiesConnectedByReqParam(
			@ApiParam(value = "origin city") @RequestParam(name = "origin") String origin,
			@ApiParam(value = "destination city") @RequestParam(name = "destination") String destination) {

		StopWatch watch = new StopWatch();
		watch.start();
		CCEnum apiResponse = CCEnum.NO;
		boolean isCitiesValid = isCitiesValid(origin, destination);
		if (isCitiesValid) {
			boolean isCitiesConnected = discoverCityConnection.findCitiesConnection(origin.toLowerCase(),
					destination.toLowerCase());
			if (isCitiesConnected) {
				apiResponse = CCEnum.YES;
			}
		}
		watch.stop();
		LOGGER.debug("Time elapsed : " + watch.getTotalTimeSeconds());
		return apiResponse;
	}

	private boolean isCitiesValid(String origin, String destination) {
		boolean isCitiesConnected = true;
		if (CCUtils.isEmptyRequestParam(origin, destination)) {
			LOGGER.warn(
					"Request param {} is null: origin city is: " + origin + ", destination city is :" + destination);
			isCitiesConnected = false;
		} else if (CCUtils.isRequestParamNotString(origin, destination)) {
			LOGGER.warn(
					"Request param {} is numeric: origin city is: " + origin + ", destination city is :" + destination);
			isCitiesConnected = false;
		} else if (CCUtils.isOriginAndDestinationSame(origin, destination)) {
			LOGGER.warn(
					"Request param {} is same: origin city is: " + origin + ", destination city is :" + destination);
			isCitiesConnected = false;
		}
		return isCitiesConnected;
	}
}
