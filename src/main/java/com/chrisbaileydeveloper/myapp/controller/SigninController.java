package com.chrisbaileydeveloper.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SigninController {
	final Logger logger = LoggerFactory.getLogger(SigninController.class);
	
	/**
	 * Sign in page.
	 */
	@RequestMapping("/signin")
	public String signin() {
		logger.info("Showing sign in page");
		
		return "signin/signin";
	}
}
