package com.codechallenge.mc.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.codechallenge.mc.main.ConnectMainApplication;
import com.codechallenge.mc.util.CCEnum;

@SpringBootTest(classes = { ConnectMainApplication.class })
@AutoConfigureMockMvc
class ConnectedRestControllerTest {

	private static final String ENDPOINT = "/connected";
	private static final String INPUT_PARAM_ORIGIN = "origin";
	private static final String INPUT_PARAM_DESTINATION = "destination";
	private static final String NULL_STRING = "NULL";
	private static final String INVALID_STRING = "1234";
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ConnectedRestController ConnectedRestController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(ConnectedRestController).isNotNull();
	}

	@Test
	public void testCitiesConnectedByRequestParamEndpointStatus() throws Exception {
		this.mockMvc.perform(get(ENDPOINT).param(INPUT_PARAM_ORIGIN, "NJ").param(INPUT_PARAM_DESTINATION, "NYC"))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testInputRequestParamOriginCityEmpty() throws Exception {
		this.mockMvc.perform(get(ENDPOINT).param(INPUT_PARAM_ORIGIN, "").param(INPUT_PARAM_DESTINATION, "NYC"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CCEnum.NO.name())));
	}

	@Test
	public void testInputRequestParamDestinationCityEmpty() throws Exception {
		this.mockMvc.perform(get(ENDPOINT).param(INPUT_PARAM_ORIGIN, "NJ").param(INPUT_PARAM_DESTINATION, ""))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CCEnum.NO.name())));
	}

	@Test
	public void testInputRequestParamOriginCityNull() throws Exception {
		this.mockMvc.perform(get(ENDPOINT).param(INPUT_PARAM_ORIGIN, NULL_STRING).param(INPUT_PARAM_DESTINATION, "NYC"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CCEnum.NO.name())));
	}

	@Test
	public void testInputRequestParamDestinationCityNull() throws Exception {
		this.mockMvc.perform(get(ENDPOINT).param(INPUT_PARAM_ORIGIN, "NJ").param(INPUT_PARAM_DESTINATION, NULL_STRING))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CCEnum.NO.name())));
	}

	@Test
	public void testInputRequestParamOriginCityInvalidString() throws Exception {
		this.mockMvc
				.perform(get(ENDPOINT).param(INPUT_PARAM_ORIGIN, INVALID_STRING).param(INPUT_PARAM_DESTINATION, "NYC"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CCEnum.NO.name())));
	}

	@Test
	public void testInputRequestParamDestinationCityInvalidString() throws Exception {
		this.mockMvc
				.perform(get(ENDPOINT).param(INPUT_PARAM_ORIGIN, "NJ").param(INPUT_PARAM_DESTINATION, INVALID_STRING))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CCEnum.NO.name())));
	}
	
	@Test
	public void testInputRequestParamOriginCityContainsWhiteSpace() throws Exception {
		this.mockMvc
				.perform(get(ENDPOINT).param(INPUT_PARAM_ORIGIN, "New York").param(INPUT_PARAM_DESTINATION, "Philadelphia"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CCEnum.YES.name())));
	}

	@Test
	public void testInputRequestParamDestinationCityContainsWhiteSpace() throws Exception {
		this.mockMvc
				.perform(get(ENDPOINT).param(INPUT_PARAM_ORIGIN, "Boston").param(INPUT_PARAM_DESTINATION, "New York"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CCEnum.YES.name())));
	}

	
	@Test
	public void testInputRequestParamOriginAndDestinationCitySame() throws Exception {
		this.mockMvc.perform(get(ENDPOINT).param(INPUT_PARAM_ORIGIN, "NJ").param(INPUT_PARAM_DESTINATION, "NJ"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CCEnum.NO.name())));
	}
	
	
	@Test
	public void testCitiesConnectedByPathtParamEndpointStatus() throws Exception {
		this.mockMvc.perform(get(ENDPOINT + "/NJ/NYC")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testPathParamOriginCityNull() throws Exception {
		this.mockMvc.perform(get(ENDPOINT + "/NULL/NYC")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CCEnum.NO.name())));
	}

	@Test
	public void testPathParamDestinationCityNull() throws Exception {
		this.mockMvc.perform(get(ENDPOINT + "/NJ/NULL/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CCEnum.NO.name())));
	}

	@Test
	public void testPathParamOriginCityInvalid() throws Exception {
		this.mockMvc.perform(get(ENDPOINT + "/" + INVALID_STRING + "/NYC")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CCEnum.NO.name())));
	}

	@Test
	public void testPathParamDestinationCityInvalid() throws Exception {
		this.mockMvc.perform(get(ENDPOINT + "/NJ/" + INVALID_STRING)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CCEnum.NO.name())));
	}
	
	@Test
	public void testPathParamOriginCityContainsWhiteSpace() throws Exception {
		this.mockMvc.perform(get(ENDPOINT + "/New York/Philadelphia")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CCEnum.YES.name())));
	}

	@Test
	public void testPathParamDestinationCityContainsWhiteSpace() throws Exception {
		this.mockMvc.perform(get(ENDPOINT + "/Boston/New York")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CCEnum.YES.name())));
	}

	
	@Test
	public void testPathParamOriginAndDestinationCitySame() throws Exception {
		this.mockMvc.perform(get(ENDPOINT + "/NJ/NJ")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CCEnum.NO.name())));
	}

}
