package com.scsx.controller;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scsx.service.UserService;

import cn.dsna.util.images.ValidateCode;
  
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
	
	@RequestMapping(value = "/codeServlet.do", method = RequestMethod.GET)
	public void codeServlet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		ValidateCode vc = new ValidateCode(110, 25, 4, 9);
		//向session中保存验证码
		request.getSession().setAttribute("scode", vc.getCode());
		vc.write(response.getOutputStream());
	}
	
}  
