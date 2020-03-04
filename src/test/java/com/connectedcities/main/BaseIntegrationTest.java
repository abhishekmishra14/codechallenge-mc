package com.connectedcities.main;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.connectedcities.controller.ConnectedCitiesRestController;

@ExtendWith(SpringExtension.class)
@SpringBootTest 
@AutoConfigureMockMvc
public class BaseIntegrationTest {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	private ConnectedCitiesRestController ConnectedRestController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(ConnectedRestController).isNotNull();
	}

}
