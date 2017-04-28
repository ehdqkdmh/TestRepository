package com.mycompany.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jsp")
public class Exam07JspController {
	@RequestMapping("/exam01")
	public String exam01() {
		return "jsp/exam01";
	}
	
	@RequestMapping("/exam02")
	public String exam02() {
		return "jsp/exam02";
	}	
}
