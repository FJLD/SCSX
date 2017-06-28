package com.scsx.controller;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.scsx.domain.User;
import com.scsx.service.UserService;
import com.scsx.util.DesUtil;

@Controller
public class LoginController {
	@RequestMapping("DoLogin.do")
	public ModelAndView DoLogin(ModelAndView modelAndView, HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(UserService.getUserServiceInstance().confirm(user)){
			if(user.getPOWER().equals("用户")){
				modelAndView.addObject("headImage", user.getHEADIMAGE());
				modelAndView.setViewName("WEB-INF/ordinary_user/index");
				return modelAndView;
			} else {
				modelAndView.addObject("headImage", user.getHEADIMAGE());
				modelAndView.setViewName("WEB-INF/admin/index");
				return modelAndView;
			}
		}
		modelAndView.addObject("error", "用户名或密码错误。");
		modelAndView.setViewName("test");
		return modelAndView;
	}
	
	@RequestMapping(value = "Login.do",method=RequestMethod.POST)
	public void Login(HttpServletRequest request,HttpServletResponse response, User user, PrintWriter out) throws Exception{
		String code = request.getParameter("code");
		if(code==null){
			out.print("{\"status\":1000}");
			return;
		}
		if(user == null || user.getUNAME() == null || user.getPW()==null){
			out.print("{\"status\":1001}");
			return;
		}
		//从session中获取验证码
		String scode = (String) request.getSession().getAttribute("scode");
		if(!scode.equalsIgnoreCase(code)){
			out.print("{\"status\":1002}");
			return;
		}
		user.setPW(DesUtil.getDesUtilInstance().encrypt(user.getPW()));	//将登录的用户密码加密
		if(UserService.getUserServiceInstance().confirm(user)){
			if(user.getPOWER().equals("用户") || user.getPOWER().equals("管理员")){
				user = UserService.getUserServiceInstance().getUserFromUNAME(user.getUNAME());
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				out.print("{\"status\":200}");
				return;
			}
		}
		else{
			out.print("{\"status\":1003}");
			return;
		}
	}
}