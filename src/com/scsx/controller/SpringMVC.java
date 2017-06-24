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
	public String login(Model model, User user) throws IOException{
		if(UserService.getUserServiceInstance().confirm(user)){
			model.addAttribute("user", user);
			if(user.getPOWER().equals("用户")){
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
	public String register(Model model, User user) throws IOException{
		if(user==null || user.getUNAME()==null || user.getID() == null || user.getNAME() == null 
				|| user.getPW() == null || user.getUPHONE()==null){	
			System.out.println(user.getUNAME()+" "+ user.getID()+" "+ user.getNAME() +" "+ user.getPW() +" "+ user.getUPHONE());
			model.addAttribute("error", "填入信息信息不完整");
			return "test";
		}
		user.setPOWER("用户");
		if(UserService.getUserServiceInstance().isValidRegisterUNAME(user.getNAME()) == false){
			model.addAttribute("error", "用户名已存在");
			return "test";
		}
		UserService.getUserServiceInstance().insertUser(user);
		model.addAttribute("user", user);
		return "login";
	}
	@RequestMapping("/PersonalInfo")
	public String showPersonalInfo(Model model, String username, String password){
		if(UserService.getUserServiceInstance().isValidUNAMEAndPW(username, password)){
			User user = UserService.getUserServiceInstance().getUserFromUNAME(username);
			model.addAttribute("user", user);
			return "/WEB-INF/ordinary_user/profile";
		}
		model.addAttribute("error", "后台验证错误");
		return "test";
	}
}
