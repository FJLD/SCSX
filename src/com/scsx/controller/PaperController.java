package com.scsx.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scsx.domain.User;
import com.scsx.service.PaperService;
import com.scsx.service.UserService;

@Controller
public class PaperController {

	@RequestMapping("/goToAdminIndex.do")
	public String goToAdminIndex() {
		return "/WEB-INF/admin/index";
	}

	@RequestMapping(value = "/getAllPapers.do")
	public void getAllPapers(HttpServletResponse res) {
		try {
			String recordsJson = PaperService.getPaperService().getPapers();
			System.out.println(recordsJson);
			res.setHeader("Content-type", "text/html;charset=UTF-8");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(recordsJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @RequestMapping("/ListUsers.do")
	// public ModelAndView userList(HttpServletRequest request) throws
	// IOException{//管理员用于显示所有的用户
	// ModelAndView modelAndView = new ModelAndView();
	// String username ="";
	// String password = "";
	// //得到客户端保存的Cookie数据
	// Cookie[] cookies = request.getCookies();
	// for (int i = 0;cookies!=null && i < cookies.length; i++) {
	// if("username".equals(cookies[i].getName())){
	// username = cookies[i].getValue();
	// }
	// if("password".equals(cookies[i].getName())){
	// password = cookies[i].getValue();
	// }
	// }
	// User user =
	// UserService.getUserServiceInstance().getUserFromUNAMEAndPW(username,
	// password);
	// System.out.println(user.getUNAME()+" "+user.getPW()+" "+user.getPOWER());
	// if(user != null && user.getPOWER().equals("管理员")){
	// List<User> allUsers =UserService.getUserServiceInstance().findAllUsers();
	// modelAndView.addObject("user", allUsers);
	// modelAndView.setViewName("/WEB-INF/admin/users_list");
	// return modelAndView;
	// }
	// modelAndView.addObject("error", "后台验证错误");
	// modelAndView.setViewName("test");
	// return modelAndView;
	// }

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
}
