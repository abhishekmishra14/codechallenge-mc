package com.connectedcities.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CityLoaderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CityLoaderService.class);

	/**
	 * 
	 * @param inputCCFile
	 */
	public void loadFileData(File inputCCFile) {
		MappedByteBuffer mBytebuffer = null;
		FileChannel fChannel = null;
		String line;
		try (FileInputStream fInputStream = new FileInputStream(inputCCFile)) {
			fChannel = fInputStream.getChannel();
			mBytebuffer = fChannel.map(MapMode.READ_ONLY, 0, fChannel.size());
			byte[] buffer = new byte[mBytebuffer.limit()];
			mBytebuffer.get(buffer);
			ByteArrayInputStream isr = new ByteArrayInputStream(buffer);
			InputStreamReader ip = new InputStreamReader(isr);
			BufferedReader br = new BufferedReader(ip);
			while ((line = br.readLine()) != null) {
				new ConnectedCitiesBuilder(line).build();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
