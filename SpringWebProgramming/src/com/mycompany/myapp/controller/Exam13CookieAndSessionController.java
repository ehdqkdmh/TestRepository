package com.mycompany.myapp.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Exam13CookieAndSessionController {
	@RequestMapping("/cookie/exam01")
	public String exam01(HttpServletResponse response) throws Exception {
		//쿠기 생성
		Cookie cookie1 = new Cookie("name1", "hongkildong");
		
		String name2 = "홍길동";
		name2 = URLEncoder.encode(name2, "UTF-8");
		Cookie cookie2 = new Cookie("name2", name2);
		cookie2.setMaxAge(30*24*60*60);
		
		//쿠기를 응답 헤더에 추가
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		
		return "cookie/exam01";
	}
	/*
	@RequestMapping("/cookie/exam02")
	public String exam02(HttpServletRequest request, Model model) throws Exception {
		String name1 = null;
		String name2 = null;
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("name1")) {
				name1 = cookie.getValue();
			} else if(cookie.getName().equals("name2")) {
				name2 = cookie.getValue();
				name2 = URLDecoder.decode(name2, "UTF-8");
			}
		}
		model.addAttribute("name1", name1);
		model.addAttribute("name2", name2);
		return "cookie/exam02";
	}
	*/
	
	@RequestMapping("/cookie/exam02")
	public String exam02(
			@CookieValue(defaultValue="") String name1, 
			@CookieValue(defaultValue="") String name2, 
			Model model) throws Exception {
		model.addAttribute("name1", name1);
		model.addAttribute("name2", name2);
		return "cookie/exam02";
	}
	
	@RequestMapping("/cookie/exam03")
	public String exam03(HttpServletResponse response) {
		Cookie cookie1 = new Cookie("name1", "");
		Cookie cookie2 = new Cookie("name2", "");
		
		//쿠기 삭제
		cookie1.setMaxAge(0);
		cookie2.setMaxAge(0);
		
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		
		return "redirect:/";
	}
}





