package com.spring.boot.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.hrservice.service.HRPersonalProfile;
import com.spring.boot.hrservice.service.HRPersonalService;

@Controller
public class HomeController {

	private final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Inject
	HRPersonalService hrService;
	
	
	@RequestMapping(value={"/", "/index"}, method=RequestMethod.GET)
	public ModelAndView getIndexPage() {
		logger.info("In index page");
		return new ModelAndView("index");
	}
	
	@GetMapping(value="/hrpersons", produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView getHRPersonals() {
		ModelAndView mv = new ModelAndView("index");
		List<HRPersonalProfile> personList = hrService.getHRPersonsList();
		if(null != personList) {
			logger.info("HRPerson list response " + personList.size());
			mv.addObject("personalProfileResponse", personList);
		}
		return mv;
	}
	
}
