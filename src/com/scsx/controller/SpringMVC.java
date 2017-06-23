package com.scsx.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scsx.domain.User;
import com.scsx.service.LoginService;
import com.scsx.service.RegisterService;

@Controller
public class SpringMVC {
	@RequestMapping("/goToRegisterPage.do")
	public String goToRegisterPage(){
		return "WEB-INF/register";
	}
	
	@RequestMapping("/Login.do")
	public String Login(Model model, String username,String password,String power) throws IOException{
		User user = new User(username,password,power);
		if(LoginService.getLoginService().confirm(user)){
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
	public String Register(Model model, String username,String password,String password_again,String name,String id_no, String phone) throws IOException{
		System.out.println("from register username="+username);
		if(RegisterService.getRegisterService().isValidUNAME(username) == false){
			model.addAttribute("error", "用户名已存在");
			return "test";
		}
		System.out.println("from Register username="+username+" poassword="+password);
		User user = new User(username,password,name,id_no,phone,"用户");
		RegisterService.getRegisterService().insertUser(user);
		username = password = password_again = name = id_no = phone = null;
		System.out.println("注册成功");
		return "login";
	}
}
