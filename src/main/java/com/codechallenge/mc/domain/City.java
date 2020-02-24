package com.codechallenge.mc.domain;

public class City {

	private String origin;
	private String destination;
	private boolean isConnected;

	public City(String origin, String destination) {
		super();
		this.origin = origin;
		this.destination = destination;
		isConnected = true;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public boolean isConnected() {
		return isConnected;
	}
}
