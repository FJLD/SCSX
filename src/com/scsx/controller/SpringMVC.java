package com.scsx.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.scsx.domain.User;
import com.scsx.service.ExamRecordsService;
import com.scsx.service.UserService;

@Controller
public class SpringMVC {
	@RequestMapping("/goToRegisterPage.do")
	public String goToRegisterPage() {
		return "WEB-INF/register";
	}

	@RequestMapping("/Login.do")
	public String login(Model model, User user) throws IOException{
		if(UserService.getUserServiceInstance().confirm(user)){
			model.addAttribute("user", user);
			if(user.getPOWER().equals("用户")){
				return "WEB-INF/ordinary_user/index";
			} else {
				return "WEB-INF/admin/index";
			}
		}
		model.addAttribute("error", "用户名或密码错误或者以错误的身份登录");
		return "test";
	}
	
	@RequestMapping(value="/getExamRecords.do", method=RequestMethod.GET)
	public void getExamRecords(HttpServletRequest req, HttpServletResponse res) {
		int UNO = Integer.parseInt(req.getParameter("uno"));
		int page = Integer.parseInt(req.getParameter("page"));
		System.out.println("uno:" + UNO + ", page:" + page);
		try {
			String recordsJson = ExamRecordsService.getExamRecordsService().getExamRecords(UNO, page);
			res.setHeader("Content-type", "text/html;charset=UTF-8");
			res.setCharacterEncoding("UTF-8");  
			res.getWriter().write(recordsJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/Index.do")
	public String Index() {
		return "WEB-INF/ordinary_user/index";
	}

	@RequestMapping("/Profile.do")
	public String Profile() {
		return "WEB-INF/ordinary_user/profile";
	}
	
	@RequestMapping("/ChoosePaper.do")
	public String ChoosePaper() {
		return "WEB-INF/ordinary_user/choose_paper";
	}

	@RequestMapping("/Record.do")
	public String Record() {
		return "WEB-INF/ordinary_user/record";
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
