package com.scsx.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpringMVC {
	
	@RequestMapping("/hello.do")
	public String forword(Model model){
		model.addAttribute("message", "同学们好！");
		return "Hello123";
	}
	
	@RequestMapping("/test.do")
	public void backword(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setAttribute("hello12", "world");
		//return "test";
		response.getWriter().write("<h1>world</h1>");
	}
}
