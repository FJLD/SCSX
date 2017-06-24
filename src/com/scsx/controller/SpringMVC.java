package com.scsx.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.scsx.domain.User;
import com.scsx.service.ExamRecordsService;
import com.scsx.service.UserService;

@Controller
public class SpringMVC {
	@RequestMapping("/goToRegisterPage.do")
	public String goToRegisterPage() {
		return "WEB-INF/register";
	}
	
	@RequestMapping(value="/getExamRecords.do", method=RequestMethod.GET)
	public void getExamRecords(HttpServletRequest req, HttpServletResponse res) {
		int UNO = Integer.parseInt(req.getParameter("uno"));
		int page = Integer.parseInt(req.getParameter("page"));
		System.out.println("uno:" + UNO + ", page:" + page);
		try {
			String recordsJson = ExamRecordsService.getExamRecordsService().getExamRecords(UNO, page);
			res.getWriter().write(recordsJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/Index.do")
	public String Index() {
		return "WEB-INF/ordinary_user/index";
	}

	@RequestMapping("/Record.do")
	public String Record() {
		return "WEB-INF/ordinary_user/record";
	}

	@RequestMapping("/ExamNow.do")
	public String ExamNow() {
		return "WEB-INF/ordinary_user/exam_now";
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
