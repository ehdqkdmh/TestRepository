package com.mycompany.myapp.controller;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mycompany.myapp.dto.Exam13Member;

@Controller
@SessionAttributes({"name1", "name2", "member"})
public class Exam13CookieAndSessionController {
	private Logger logger = LoggerFactory.getLogger(Exam13CookieAndSessionController.class);
	
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
	
	/*@RequestMapping("/session/exam04")
	public String exam04(HttpSession session) {
		//세션에 문자열 정보를 저장
		session.setAttribute("name1", "hongkildong");
		session.setAttribute("name2", "홍길동");
		//세션에 객체를 저장
		Exam13Member member = new Exam13Member();
		member.setMname("스프링");
		session.setAttribute("member", member);
		return "redirect:/";
	}*/

	@RequestMapping("/session/exam04")
	public String exam04(Model model) {
		model.addAttribute("name1", "hongkildong");
		model.addAttribute("name2", "홍길동");
		
		Exam13Member member = new Exam13Member();
		member.setMname("스프링");
		model.addAttribute("member", member);
		return "redirect:/";
	}
	
	/*@RequestMapping("/session/exam05")
	public String exam05(HttpSession session) {
		//세션에서 문자열 정보 가져오기
		String name1 = (String) session.getAttribute("name1");
		String name2 = (String) session.getAttribute("name2");
		Exam13Member member = (Exam13Member) session.getAttribute("member");
		
		logger.info(name1);
		logger.info(name2);
		logger.info(member.getMname());
		
		return "session/exam05";
	}*/
	
	@RequestMapping("/session/exam05")
	public String exam05(
			@ModelAttribute String name1, 
			@ModelAttribute String name2, 
			@ModelAttribute Exam13Member member) {
		logger.debug(name1);
		logger.debug(name2);
		logger.debug(member.getMname());
		
		return "session/exam05";
	}
	
	/*@RequestMapping("/session/exam06")
	public String exam06(HttpSession session) {
		//세션에서 삭제해도 Model 에는 남아있을 수 있다.
		//@SessionAttributes 대신 HttpSession 만 이용할 경우에 사용
		session.removeAttribute("name1");
		session.removeAttribute("name2");
		session.removeAttribute("member");
		return "redirect:/";
	}*/
	
	@RequestMapping("/session/exam06")
	public String exam06(SessionStatus sessionStatus) {
		//세션에 있는 모든 정보를 삭제
		//@SessionAttributes를 사용할 경우 이용
		sessionStatus.setComplete();
		return "redirect:/";
	}
}





