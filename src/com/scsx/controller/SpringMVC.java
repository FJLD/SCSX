package com.scsx.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scsx.domain.User;
import com.scsx.service.LoginService;

@Controller
public class SpringMVC {
	@RequestMapping("/goToRegisterPage.do")
	public String goToRegisterPage(){
		return "register";
	}
	
	@RequestMapping("/Login.do")
	public String Login(String username,String password,String power) throws IOException{
		User user = new User(username,password,power);
		if(LoginService.getLoginService().confirm(user)){
			if(power.equals("用户")){
				return "ordinary_user/index";
			}
			else{
				return "admin/index";
			}
		}
		System.out.println("username="+username+" password="+password+" power="+power+" ...");
		//User user()
		//loginservice.confirm()
		return "../test";
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
