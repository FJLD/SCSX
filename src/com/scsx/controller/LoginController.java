package com.scsx.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scsx.domain.User;
import com.scsx.service.UserService;

@Controller
public class LoginController {
	@RequestMapping("DoLogin")
	public String DoLogin(Model model, HttpServletResponse response, User user){
		Cookie ck1 = new Cookie("username",user.getUNAME());
		Cookie ck2 = new Cookie("password",user.getPW());
		ck1.setPath("/");
		ck2.setPath("/");
		System.out.println("username="+user.getUNAME()+" password="+user.getPW());
		//处理业务逻辑
		if(UserService.getUserServiceInstance().confirm(user)){
			model.addAttribute("user", user);
			if(user.getPOWER().equals("用户")){
				ck1.setMaxAge(Integer.MAX_VALUE);//设置Cookie的有效保存时间
				ck2.setMaxAge(Integer.MAX_VALUE);//设置Cookie的有效保存时间
				response.addCookie(ck1);
				response.addCookie(ck2);
				return "WEB-INF/ordinary_user/index";
			} else {
				ck1.setMaxAge(Integer.MAX_VALUE);//设置Cookie的有效保存时间
				ck2.setMaxAge(Integer.MAX_VALUE);//设置Cookie的有效保存时间
				response.addCookie(ck1);
				response.addCookie(ck2);
				return "WEB-INF/admin/index";
			}
		}
		model.addAttribute("error", "用户名或密码错误或者以错误的身份登录");
		return "test";
	}
}