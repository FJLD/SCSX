package com.scsx.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scsx.domain.User;
import com.scsx.service.UserService;
import com.scsx.util.DesUtil;

@Controller
public class LoginController {
	@RequestMapping("DoLogin")
	public ModelAndView DoLogin(ModelAndView modelAndView, HttpServletRequest request,HttpServletResponse response, User user) throws Exception{
		String code = request.getParameter("code");
		if(user == null || code == null){
			modelAndView.addObject("error", "请输入登录信息");
			modelAndView.setViewName("test");
			return modelAndView;
		}
		//从session中获取验证码
		String scode = (String) request.getSession().getAttribute("scode");
		System.out.println(user.getUNAME()+" "+user.getPW()+" "+scode+" "+code);
		if(!scode.equalsIgnoreCase(code)){
			modelAndView.addObject("message", "验证码错误。");
			modelAndView.setViewName("login");
			return modelAndView;
		}
		user.setPW(DesUtil.getDesUtilInstance().encrypt(user.getPW()));	//将登录的用户密码加密
		System.out.println(user.getUNAME()+" "+user.getPW()+" "+scode+" "+code);
		Cookie ck1 = new Cookie("username",user.getUNAME());
		Cookie ck2 = new Cookie("password",user.getPW());
		ck1.setPath("/");
		ck2.setPath("/");
		System.out.println("username="+user.getUNAME()+" password="+user.getPW());
		//处理业务逻辑
		if(UserService.getUserServiceInstance().confirm(user)){
			if(user.getPOWER().equals("用户")){
				user = UserService.getUserServiceInstance().getUserFromUNAME(user.getUNAME());
				ck1.setMaxAge(Integer.MAX_VALUE);//设置Cookie的有效保存时间
				ck2.setMaxAge(Integer.MAX_VALUE);//设置Cookie的有效保存时间
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				response.addCookie(ck1);
				response.addCookie(ck2);
				System.out.println(user.getHEADIMAGE());
				modelAndView.addObject("headImage", user.getHEADIMAGE());
				modelAndView.setViewName("WEB-INF/ordinary_user/index");
				return modelAndView;
			} else {
				user = UserService.getUserServiceInstance().getUserFromUNAME(user.getUNAME());
				ck1.setMaxAge(Integer.MAX_VALUE);//设置Cookie的有效保存时间
				ck2.setMaxAge(Integer.MAX_VALUE);//设置Cookie的有效保存时间
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				response.addCookie(ck1);
				response.addCookie(ck2);
				modelAndView.addObject("headImage", user.getHEADIMAGE());
				modelAndView.setViewName("WEB-INF/admin/index");
				return modelAndView;
			}
		}
		modelAndView.addObject("headImage", user.getHEADIMAGE());
		modelAndView.addObject("message", "用户名或密码错误。");
		modelAndView.setViewName("login");
		return modelAndView;
	}
}