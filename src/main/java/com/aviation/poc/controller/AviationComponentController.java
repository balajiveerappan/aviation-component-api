package com.aviation.poc.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aviation.poc.entity.Component;
import com.aviation.poc.service.AviationComponentService;
import com.aviation.poc.util.ComponentConstants;

@RestController
public class AviationComponentController {

	@Autowired
	private AviationComponentService componentService;
	
	@RequestMapping(value = "/splashScreen", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Object> showSplashScreen(@RequestParam(required=false) String componentType) {
		String pattern = ComponentConstants.DATEFORMATNEW;
		Date sDate=null;
		Date eDate=null;
		try {
			sDate =  new SimpleDateFormat(pattern).parse("2014-08-10");
			 eDate =  new SimpleDateFormat(pattern).parse("2016-08-10");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Object> componentRemovalRept =  componentService.getRemovedComponents(sDate, eDate, componentType);
		return componentRemovalRept;
	}
	
	@RequestMapping(value = "/loadComponent", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Component> loadComponentData(@RequestParam(required = false) @DateTimeFormat(pattern = ComponentConstants.DATEFORMATNEW) Date start,@RequestParam(required = false) @DateTimeFormat(pattern = ComponentConstants.DATEFORMATNEW) Date end)
			throws ParseException {
		
		return componentService.getComponent(start, end);
	}

}
