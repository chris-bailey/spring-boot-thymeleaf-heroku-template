package com.chrisbaileydeveloper.myapp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.chrisbaileydeveloper.myapp.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class SigninControllerTest {
	private MockMvc mockMvc;

	@Before
	public void setup() {
		SigninController signinController = new SigninController();
		this.mockMvc = MockMvcBuilders.standaloneSetup(signinController).build();
	}

	@Test
	public void testSignin() throws Exception {
		mockMvc.perform(get("/signin"))
			.andExpect(status().isOk())
			.andExpect(view().name("signin/signin"))
			.andExpect(forwardedUrl("signin/signin"));
	}
}
