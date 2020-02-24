package com.codechallenge.mc.main;

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

import com.codechallenge.mc.controller.ConnectedRestController;
import com.codechallenge.mc.util.CCEnum;

@SpringBootTest
@AutoConfigureMockMvc
class CodechallengeMcApplicationTests {

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
	public void testInputParamOriginCityEmpty() throws Exception {
		this.mockMvc.perform(get(ENDPOINT).param(INPUT_PARAM_ORIGIN, "").param(INPUT_PARAM_DESTINATION, "NYC"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CCEnum.NO.name())));
	}

	@Test
	public void testInputParamDestinationCityEmpty() throws Exception {
		this.mockMvc.perform(get(ENDPOINT).param(INPUT_PARAM_ORIGIN, "NJ").param(INPUT_PARAM_DESTINATION, ""))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CCEnum.NO.name())));
	}

	@Test
	public void testInputParamOriginCityNull() throws Exception {
		this.mockMvc.perform(get(ENDPOINT).param(INPUT_PARAM_ORIGIN, NULL_STRING).param(INPUT_PARAM_DESTINATION, "NYC"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CCEnum.NO.name())));
	}

	@Test
	public void testInputParamDestinationCityNull() throws Exception {
		this.mockMvc.perform(get(ENDPOINT).param(INPUT_PARAM_ORIGIN, "NJ").param(INPUT_PARAM_DESTINATION, NULL_STRING))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CCEnum.NO.name())));
	}

	@Test
	public void testInputParamOriginCityInvalidString() throws Exception {
		this.mockMvc
				.perform(get(ENDPOINT).param(INPUT_PARAM_ORIGIN, INVALID_STRING).param(INPUT_PARAM_DESTINATION, "NYC"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CCEnum.NO.name())));
	}

	@Test
	public void testInputParamDestinationCityInvalidString() throws Exception {
		this.mockMvc
				.perform(get(ENDPOINT).param(INPUT_PARAM_ORIGIN, "NJ").param(INPUT_PARAM_DESTINATION, INVALID_STRING))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CCEnum.NO.name())));
	}

	@Test
	public void testInputParamOriginAndDestinationCitySame() throws Exception {
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
	public void testPathParamOriginAndDestinationCitySame() throws Exception {
		this.mockMvc.perform(get(ENDPOINT + "/NJ/NJ")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CCEnum.NO.name())));
	}

}
