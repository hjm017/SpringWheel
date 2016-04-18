package com.zmt.controller;

import com.zmt.common.utils.constants.MediaTypes;
import org.javasimon.aop.Monitored;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping(value = "/hello", produces = MediaTypes.TEXT_PLAIN)
	@Monitored
	public String hello(@RequestParam("name") String name) {
		return "hello " + name;
	}

}
