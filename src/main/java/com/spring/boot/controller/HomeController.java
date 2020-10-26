package com.spring.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value={"/", "/index"}, method=RequestMethod.GET)
	public ModelAndView getIndexPage() {
		logger.info("In index page");
		return new ModelAndView("index");
	}
	
}
