package com.scsx.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scsx.service.LoginService;

@Controller
public class SpringMVC {
	@RequestMapping("/goToRegisterPage.do")
	public String goToRegisterPage(){
		return "register";
	}
	
	@RequestMapping("/Login.do")
	public String Login(String username,String password,String power){
		LoginService loginservice = LoginService.getLoginService();
		System.out.println("username="+username+" password="+password+" power="+power);
		//User user()
		//loginservice.confirm()
		return "register";
	}
	@RequestMapping("/hello.do")
	public String forword(Model model){
		//model.addAttribute("message", "同学们好！");
		return "login";
	}
	
	@RequestMapping("/test.do")
	public void backword(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setAttribute("hello12", "world");
		//return "test";
		response.getWriter().write("<h1>world</h1>");
	}
}
