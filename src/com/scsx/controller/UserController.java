package com.scsx.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping; 
import com.scsx.service.UserService;
  
@Controller  
public class UserController {
	
	@RequestMapping(value = "/getAllUsers.do")
	public void getAllUsers(HttpServletRequest req, HttpServletResponse res) {
		// TODO: 判断 session 中当前用户是否为管理员
		try {
			String usersJson = UserService.getUserServiceInstance().findAllUsers();
			System.out.println(usersJson);
			res.setHeader("Content-type", "text/html;charset=UTF-8");
			res.setCharacterEncoding("UTF-8");  
			res.getWriter().write(usersJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}  
