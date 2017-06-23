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
import com.scsx.service.LoginService;
import com.scsx.service.RegisterService;

@Controller
public class SpringMVC {
	@RequestMapping("/goToRegisterPage.do")
	public String goToRegisterPage() {
		return "WEB-INF/register";
	}

	@RequestMapping("/Login.do")
	public String Login(String username, String password, String power) throws IOException {
		User user = new User(username, password, power);
		if (LoginService.getLoginService().confirm(user)) {
			if (power.equals("用户")) {
				return "WEB-INF/ordinary_user/index";
			} else {
				return "WEB-INF/admin/index";
			}
		}
		return "test";
	}

	@RequestMapping(value="/Register.do", method=RequestMethod.POST)
	public String Register(Model model, String username, String password, String password_again, String name,
			String id_no, String phone) throws IOException {
		System.out.println("from register username=" + username);
		if (RegisterService.getRegisterService().isValidUNAME(username) == false) {
			model.addAttribute("error", "用户名已存在");
			return "test";
		}
		System.out.println("from Register username=" + username + " poassword=" + password);
		User user = new User(username, password, name, id_no, phone, "用户");
		RegisterService.getRegisterService().insertUser(user);
		username = password = password_again = name = id_no = phone = null;
		System.out.println("注册成功");
		return "login";
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

	@RequestMapping("/Profile.do")
	public String Profile() {
		return "WEB-INF/ordinary_user/profile";
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
}
