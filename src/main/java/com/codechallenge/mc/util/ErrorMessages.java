package com.codechallenge.mc.util;

public enum ErrorMessages {
	CC_INPUT_FILE_NOT_FOUND("Input file citi.txt not found."), CC_INPUT_FILE_EMPTY("Input file citi.txt is empty."),
	ORIGIN_CITY_INVALID("Origin city is invalid."), DESTINATION_CITY_INVALID("Destination city is invalid.");

	private String errorMsg;

	private ErrorMessages(String errString) {
		this.errorMsg = errString;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

}
