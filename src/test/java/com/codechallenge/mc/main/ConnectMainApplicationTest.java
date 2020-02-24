package com.codechallenge.mc.main;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codechallenge.mc.controller.ConnectedRestController;

@SpringBootTest
class ConnectMainApplicationTest {

	@Autowired
	private ConnectedRestController ConnectedRestController;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	public void contextLoads() throws Exception {
		assertThat(ConnectedRestController).isNotNull();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

}
