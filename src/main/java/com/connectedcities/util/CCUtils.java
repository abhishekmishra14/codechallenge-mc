package com.connectedcities.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CCUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(CCUtils.class);

	public static final String CONNECTED_CITIES_FILENAME = "citi.txt";
	public static final String DELIMETER = ",";

	public static boolean isEmptyRequestParam(String originCity, String destinationCity) {
		boolean isNullRequestParam = false;
		if (isNullOrEmpty(originCity) || isNullOrEmpty(destinationCity)) {
			isNullRequestParam = true;
		}
		return isNullRequestParam;
	}

	public static boolean isRequestParamNotString(String originCity, String destinationCity) {
		boolean isRequestParamNumeric = false;
		if (CCUtils.isNumeric(originCity) || CCUtils.isNumeric(destinationCity)) {
			isRequestParamNumeric = true;
		}
		return isRequestParamNumeric;
	}

	public static boolean isOriginAndDestinationSame(String originCity, String destinationCity) {
		boolean isOriginAndDestSame = false;
		if (originCity.trim().equalsIgnoreCase(destinationCity.trim())) {
			isOriginAndDestSame = true;
		}
		return isOriginAndDestSame;
	}

	public static boolean isNumeric(final String inputParam) {
		if (isNullOrEmpty(inputParam)) {
			return false;
		}
		return inputParam.chars().allMatch(Character::isDigit);
	}

	public static boolean isNullOrEmpty(String inputString) {
		if (inputString.trim() == "" || inputString == null || inputString.trim().equalsIgnoreCase("NULL")
				|| inputString.trim().isEmpty())
			return true;
		return false;
	}

	public static boolean isValidCCFile(File inputCCFile) {
		boolean isValidFile = true;
		if (inputCCFile != null && !inputCCFile.exists()) {
			LOGGER.error("Input file is not valid." + ErrorMessages.CC_INPUT_FILE_NOT_FOUND);
			isValidFile = false;
		} else if (inputCCFile != null && inputCCFile.length() == 0) {
			LOGGER.error("Input file is empty." + ErrorMessages.CC_INPUT_FILE_EMPTY);
			isValidFile = false;
		}
		return isValidFile;
	}
}
