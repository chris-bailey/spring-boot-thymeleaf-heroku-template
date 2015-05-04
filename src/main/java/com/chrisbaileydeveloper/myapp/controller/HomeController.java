package com.chrisbaileydeveloper.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/")
@Controller
public class HomeController { 

	final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Show home page.
     */
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		logger.info("Showing home page");

		return "home/index";
	}
}
