package com.scsx.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.scsx.domain.User;
import com.scsx.service.ExamRecordsService;
import com.scsx.service.UserService;
import com.scsx.util.DesUtil;

@Controller
public class SpringMVC {
	@RequestMapping("/goToRegisterPage.do")
	public String goToRegisterPage() {
		return "WEB-INF/register";
	}

	@RequestMapping("/Index.do")
	public String Index() {
		return "WEB-INF/ordinary_user/index";
	}
	
	@RequestMapping("/ChoosePaper.do")
	public String ChoosePaper() {
		return "WEB-INF/ordinary_user/choose_paper";
	}

	@RequestMapping("/Record.do")
	public String Record() {
		return "WEB-INF/ordinary_user/record";
	}
	
	@RequestMapping("/ListUsers.do")
	public String userList(HttpServletRequest request) throws IOException {// 管理员用于显示所有的用户
		String username = "";
		String password = "";
		// 得到客户端保存的Cookie数据
		Cookie[] cookies = request.getCookies();
		for (int i = 0; cookies != null && i < cookies.length; i++) {
			if ("username".equals(cookies[i].getName())) {
				username = cookies[i].getValue();
			}
			if ("password".equals(cookies[i].getName())) {
				password = cookies[i].getValue();
			}
		}
		User user = UserService.getUserServiceInstance().getUserFromUNAMEAndPW(username, password);
		System.out.println(user.getUNAME() + " " + user.getPW() + " " + user.getPOWER());
		if (user != null && user.getPOWER().equals("管理员")) {
			return "/WEB-INF/admin/users_list";
		}
		return "test";
	}
	
	@RequestMapping("/AllRecords.do")
	public String AllRecords() {
		return "WEB-INF/admin/records";
	}
	
	@RequestMapping("/QuestionsManager.do")
	public String QuestionsManager() {
		return "WEB-INF/admin/questions";
	}

	@RequestMapping("/hello.do")
	public String forword(Model model) {
		// model.addAttribute("message", "同学们好！");
		return "login";
	}

	@RequestMapping("/test.do")
	public void backword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setAttribute("hello12", "world");
		// return "test";
		response.getWriter().write("<h1>world</h1>");
	}

	@RequestMapping("/Register.do")
	public String register(Model model, User user) throws Exception{
		if(user==null || user.getUNAME()==null || user.getID() == null || user.getNAME() == null 
				|| user.getPW() == null || user.getUPHONE()==null){	
			System.out.println(user.getUNAME()+" "+ user.getID()+" "+ user.getNAME() +" "+ user.getPW() +" "+ user.getUPHONE());
			model.addAttribute("error", "填入信息信息不完整");
			return "test";
		}
		user.setPOWER("用户");
		user.setPW(DesUtil.getDesUtilInstance().encrypt(user.getPW()));	//将用户密码加密后保存
		if(UserService.getUserServiceInstance().isValidRegisterUNAME(user.getUNAME()) == false){
			model.addAttribute("error", "用户名已存在");
			return "test";
		}
		UserService.getUserServiceInstance().insertUser(user);
		model.addAttribute("user", user);
		return "login";
	}
	@RequestMapping("/Profile.do")
	public ModelAndView showPersonalInfo(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		String username ="";
		String password = "";
		//得到客户端保存的Cookie数据
		Cookie[] cookies = request.getCookies();
		for (int i = 0;cookies!=null && i < cookies.length; i++) {
			if("username".equals(cookies[i].getName())){
				username = cookies[i].getValue();
			}
			if("password".equals(cookies[i].getName())){
				password = cookies[i].getValue();
			}
		}
		User user = UserService.getUserServiceInstance().getUserFromUNAMEAndPW(username, password);
		if(user != null){
			request.setAttribute("user", user);
			modelAndView.addObject("user", user);
			modelAndView.setViewName("/WEB-INF/ordinary_user/profile");
			return modelAndView;
		}
		modelAndView.addObject("error", "后台验证错误");
		modelAndView.setViewName("test");
		return modelAndView;
	}
}
