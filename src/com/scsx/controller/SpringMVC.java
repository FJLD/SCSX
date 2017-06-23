package com.scsx.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scsx.domain.User;
import com.scsx.service.UserService;

@Controller
public class SpringMVC {
	@RequestMapping("/goToRegisterPage.do")
	public String goToRegisterPage(){
		return "WEB-INF/register";
	}
	
	@RequestMapping("/Login.do")
	public String login(Model model, String username,String password,String power) throws IOException{
		User user = new User(username,password,power);
		if(UserService.getUserServiceInstance().confirm(user)){
			if(power.equals("用户")){
				return "WEB-INF/ordinary_user/index";
			}
			else{
				return "WEB-INF/admin/index";
			}
		}
		model.addAttribute("error", "用户名或密码错误或者以错误的身份登录");
		return "test";
	}
	@RequestMapping("/Register.do")
	public String register(Model model, String username,String password,
				String password_again,String name,String id_no, String phone) throws IOException{
		if(UserService.getUserServiceInstance().isValidRegisterUNAME(username) == false){
			model.addAttribute("error", "用户名已存在");
			return "test";
		}
		User user = new User(username,password,name,id_no,phone,"用户");
		UserService.getUserServiceInstance().insertUser(user);
		model.addAttribute("username", username);
		model.addAttribute("password", password);
		return "login";
	}
	@RequestMapping("/PersonalInfo")
	public String showPersonalInfo(Model model,String username,String password){
		return "/WEB-INF/ordinary_user/profile";
	}
}
