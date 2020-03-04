package com.connectedcities.main;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.connectedcities.controller.ConnectedCitiesRestController;
import com.connectedcities.service.DiscoverCitiesConnection;



public class ConnectedCitiesRestControllerTest extends BaseIntegrationTest{
	
	@Autowired
	private MockMvc connectedCitiesMvc;
	
	@InjectMocks
	ConnectedCitiesRestController controller;
	
	@Mock
	DiscoverCitiesConnection discoverCityConnection;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	//@Test
	void testFindCitiesConnectedByPathPar() {
		fail("Not yet implemented");
	}

	@Test
	void testFindCitiesConnectedByReqParam() {
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(discoverCityConnection.findCitiesConnection("NEWARK", "CT")).thenReturn(true);
        Enum apiResponse = controller.findCitiesConnectedByReqParam("NEWARK", "CT");
        System.out.println(apiResponse.name());
	}

}
