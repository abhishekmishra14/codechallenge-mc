package com.codechallenge.mc.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.codechallenge.mc.util.CCEnum;

@SpringBootTest(classes = { ConnectedRestController.class })
@AutoConfigureMockMvc
class ConnectedRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
	}

	// @Test
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/connected").param("origin", "NJ").param("destination", "NYC")).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(containsStringIgnoringCase(CCEnum.YES.name())));
	}

	// @Test
	public void testInputParamEmpty() throws Exception {
		this.mockMvc.perform(get("/connected").param("origin", "null").param("destination", "NYC")).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(containsString(CCEnum.YES.name())));
	}

	@AfterEach
	void tearDown() throws Exception {
	}

}
