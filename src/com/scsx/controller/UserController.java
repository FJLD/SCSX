package com.scsx.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scsx.domain.User;
import com.scsx.service.UserService;

import cn.dsna.util.images.ValidateCode;
  
@Controller  
public class UserController {
	
	@RequestMapping(value = "/getAllUsers.do")
	public void getAllUsers(HttpServletRequest req, HttpServletResponse res) {
		User user = (User) req.getSession().getAttribute("user");
		res.setHeader("Content-type", "text/html;charset=UTF-8");
		res.setCharacterEncoding("UTF-8");  
		try {
			if (!UserService.getUserServiceInstance().confirmAdmin(user)){
				res.getWriter().write("err");
			}
			int page = Integer.parseInt(req.getParameter("page"));
			String usersJson = UserService.getUserServiceInstance().findAllUsers(page);
			System.out.println(usersJson);
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
