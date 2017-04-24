package com.mycompany.myapp;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class HomeController {
	@RequestMapping("/")
	public String home() {
		System.out.println("home");
		return "home";
	}
}
