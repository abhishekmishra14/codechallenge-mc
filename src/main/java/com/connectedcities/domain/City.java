package com.connectedcities.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class City {

	private String key;
	private String origin;
	private String destination;
	private boolean isConnected;

	public City(String key, String origin, String destination) {
		super();
		this.key = key;
		this.origin = origin;
		this.destination = destination;
		isConnected = true;
	}

	public String getKey() {
		return key;
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

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("key", key);
		builder.append("origin", origin);
		builder.append("destination", destination);
		return builder.toString();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
		hashCodeBuilder.append(origin);
		return hashCodeBuilder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof City)) {
			return false;
		}
		City other = (City) obj;
		EqualsBuilder equalsBuilder = new EqualsBuilder();
		equalsBuilder.append(origin, other.origin);
		return equalsBuilder.isEquals();
	}
}
