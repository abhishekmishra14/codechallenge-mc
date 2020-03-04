package com.connectedcities.main;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.connectedcities.util.CCEnum;

class CitiesConnectionAppIntegrationTest extends BaseIntegrationTest {

	private static final String ENDPOINT = "/connected";
	private static final String INPUT_PARAM_ORIGIN = "origin";
	private static final String INPUT_PARAM_DESTINATION = "destination";
	private static final String NULL_STRING = "NULL";
	private static final String INVALID_STRING = "1234";

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
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
				.perform(get(ENDPOINT).param(INPUT_PARAM_ORIGIN, "New York").param(INPUT_PARAM_DESTINATION,
						"Philadelphia"))
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
