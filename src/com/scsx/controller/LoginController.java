package com.scsx.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scsx.domain.User;
import com.scsx.service.UserService;
import com.scsx.util.DesUtil;

@Controller
public class LoginController {
	@RequestMapping("DoLogin")
	public String DoLogin(Model model, HttpServletRequest request,HttpServletResponse response, User user) throws Exception{
		//登录做的事情，将用户密码加密后和数据库中已经加密的密码比对，同时判断用户的权限，如果一切正常跳到主页
		if(user == null){
			model.addAttribute("error", "请输入登录信息");
		}
		user.setPW(DesUtil.getDesUtilInstance().decrypt(user.getPW()));	//将登录的用户密码加密
		Cookie ck1 = new Cookie("username",user.getUNAME());
		Cookie ck2 = new Cookie("password",user.getPW());
		ck1.setPath("/");
		ck2.setPath("/");
		System.out.println("username="+user.getUNAME()+" password="+user.getPW());
		//处理业务逻辑
		if(UserService.getUserServiceInstance().confirm(user)){
			if(user.getPOWER().equals("用户")){
				ck1.setMaxAge(Integer.MAX_VALUE);//设置Cookie的有效保存时间
				ck2.setMaxAge(Integer.MAX_VALUE);//设置Cookie的有效保存时间
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				response.addCookie(ck1);
				response.addCookie(ck2);
				return "WEB-INF/ordinary_user/index";
			} else {
				ck1.setMaxAge(Integer.MAX_VALUE);//设置Cookie的有效保存时间
				ck2.setMaxAge(Integer.MAX_VALUE);//设置Cookie的有效保存时间
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				response.addCookie(ck1);
				response.addCookie(ck2);
				return "WEB-INF/admin/index";
			}
		}
		model.addAttribute("error", "用户名或密码错误或者以错误的身份登录");
		return "test";
	}
}