package com.scsx.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.scsx.domain.User;
import com.scsx.service.UserService;
import com.scsx.util.Commons;
import com.scsx.util.DesUtil;

import cn.dsna.util.images.ValidateCode;
  
@Controller
@MultipartConfig
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
	
	
	
	@Bean
	public MultipartResolver multipartResolver() {
	    return new StandardServletMultipartResolver();
	}
	
	@RequestMapping("/updateUserInfo.do")
	public void updateUserInfo(HttpServletRequest req, PrintWriter out) throws Exception {
		User user = (User) req.getSession().getAttribute("user");
		int UNO = user.getUNO();
		String UPHONE = req.getParameter("UPHONE");
		System.out.println("session phone: " + user.getUPHONE()
				+ "\nnew phone: " + UPHONE);
		boolean success = UserService.getUserServiceInstance().updateUserPHONE(UNO, UPHONE);
		out.write(success? "true" : "false");
	}
	
	@RequestMapping("/updateUserPW.do")
	public void updateUserPW(HttpServletRequest req, PrintWriter out) throws Exception {
		User user = (User) req.getSession().getAttribute("user");
		int UNO = user.getUNO();
		String PW = DesUtil.getDesUtilInstance().encrypt(req.getParameter("PW"));
		String PW2 = DesUtil.getDesUtilInstance().encrypt(req.getParameter("PW2"));
		if (!PW.equals(PW2)) {
			out.write("not same");
			return;
		} else {
			System.out.println("session password: " + user.getPW()
					+ "\nnew password: " + PW);
			boolean success = UserService.getUserServiceInstance().updateUserPW(UNO, PW);
			out.write(success? "true" : "false");
		}
	}
	
	@RequestMapping("/Profile.do")
	public ModelAndView showPersonalInfo(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user != null){
			session.setMaxInactiveInterval(3600);
			request.setAttribute("user", user);
			modelAndView.addObject("user", user);
			modelAndView.setViewName("/WEB-INF/ordinary_user/profile");
			return modelAndView;
		}
		modelAndView.addObject("error", "后台验证错误。");
		modelAndView.setViewName("test");
		return modelAndView;
	}
	
	@RequestMapping("/Register.do")
	public void register(Model model, HttpServletRequest req, PrintWriter out) throws Exception{
		String UNAME = req.getParameter("UNAME");
		String PW = req.getParameter("PW");
		String PW2 = req.getParameter("PW2");
		String ID = req.getParameter("ID");
		String NAME = req.getParameter("NAME");
		String UPHONE = req.getParameter("UPHONE");
		if (!PW.equals(PW2)) {
			out.write("not same pw");
			return;
		}
		User user = new User(UNAME, PW, NAME, ID, UPHONE, "用户");
		if (user.getUNAME().isEmpty() || user.getID().isEmpty() || user.getNAME().isEmpty()
				|| user.getPW().isEmpty() || user.getUPHONE().isEmpty()){	
			System.out.println(user.getUNAME()+" "+ user.getID()+" "+ user.getNAME() +" "+ user.getPW() +" "+ user.getUPHONE());
			out.write("empty info");
			return;
		}
		user.setHEADIMAGE(Commons.DefualtHeadImageName);
		user.setPW(DesUtil.getDesUtilInstance().encrypt(user.getPW()));	//将用户密码加密后保存
		if (UserService.getUserServiceInstance().isValidRegisterUNAME(user.getUNAME()) == false){
			out.write("Username already existed");
			return;
		}
		UserService.getUserServiceInstance().insertUser(user);
		model.addAttribute("user", user);
		out.write("true");
		return;
	}

	@RequestMapping("/ListUsers.do")
	public String userList(HttpServletRequest request) throws IOException {// 管理员用于显示所有的用户
		User user = (User) request.getSession().getAttribute("user");
		if (UserService.getUserServiceInstance().confirm(user)) {
			return "/WEB-INF/admin/users_list";
		}
		return "test";
	}
	
	@RequestMapping("/AllRecords.do")
	public String AllRecords(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (UserService.getUserServiceInstance().confirm(user)) {
			return "WEB-INF/admin/records";
		}
		return "test";
	}
	
	@RequestMapping("/QuestionsManager.do")
	public String QuestionsManager(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (UserService.getUserServiceInstance().confirm(user)) {
			return "WEB-INF/admin/questions";
		}
		return "test";
	}

	@RequestMapping("/test.do")
	public void backword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setAttribute("hello12", "world");
		// return "test";
		response.getWriter().write("<h1>world</h1>");
	}
}  
