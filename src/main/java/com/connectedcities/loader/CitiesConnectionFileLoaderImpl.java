package com.connectedcities.loader;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.connectedcities.domain.City;
import com.connectedcities.service.CitiesBuilder;
import com.connectedcities.util.CCUtils;

@Component
public class CitiesConnectionFileLoaderImpl implements ICitiesConnectionLoader<File, City> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CitiesConnectionFileLoaderImpl.class);

	@Override
	public List<City> loaddata(File inputFile) throws Exception {
		LOGGER.info("Load data from File.");
		ByteBuffer byteBuffer = null;
		FileChannel channel = null;
		String record = null;
		List<City> cities = new ArrayList<>();
		try {
			boolean isInputFileValid = CCUtils.isValidCCFile(inputFile);
			if (isInputFileValid) {
				try (FileInputStream inputStream = new FileInputStream(inputFile)) {
					channel = inputStream.getChannel();
					byteBuffer = channel.map(MapMode.READ_ONLY, 0, channel.size());
					byte[] buffer = new byte[byteBuffer.limit()];
					byteBuffer.get(buffer);
					ByteArrayInputStream byteInputStream = new ByteArrayInputStream(buffer);
					InputStreamReader inputReader = new InputStreamReader(byteInputStream);
					BufferedReader bufferReader = new BufferedReader(inputReader);
					while ((record = bufferReader.readLine()) != null) {
						String[] connecteditiesRecord = record.split(CCUtils.DELIMETER);
						String originCity = connecteditiesRecord[0].toLowerCase();
						String destinationCity = connecteditiesRecord[1].toLowerCase();
						City city = new CitiesBuilder(originCity).setOriginCityName(originCity)
								.setDestinationCityName(destinationCity).build();
						cities.add(city);
					}
				}
			}
		} catch (Exception ex) {
			LOGGER.error("Exception loading data from file", ex);
			throw new Exception("Exception loading data", ex);
		}
		return cities;
	}

}
