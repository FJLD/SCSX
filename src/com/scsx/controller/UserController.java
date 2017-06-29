package com.scsx.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import com.google.gson.Gson;
import com.scsx.domain.User;
import com.scsx.service.UserService;
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
	
	@RequestMapping(value = "/upload.do")
    public void upload(@RequestParam("file") MultipartFile file,HttpServletRequest request,PrintWriter out) {  
        // 判断文件是否为空  
        if (!file.isEmpty()) {  
            try {  
              //文件名称在服务器有可能重复？
        		String newFileName="";
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        		newFileName = sdf.format(new Date());
        		
        		Random r = new Random();
        		
        		for(int i =0 ;i<3;i++){
        			newFileName=newFileName+r.nextInt(10);
        		}
        		
                
              //获取文件扩展名
        		String originalFilename = file.getOriginalFilename();
        		String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        		String filePath = request.getSession().getServletContext().getRealPath("/") + "images/" + newFileName+suffix;
        		file.transferTo(new File(filePath));
  
        		String result="{\"fullPath\":\""+"images/"+newFileName+suffix+"\""+"}";
        		HttpSession session = request.getSession();
        		User user =  (User) session.getAttribute("user");
        		user.setHEADIMAGE("images/"+newFileName+suffix);
        		session.setAttribute("user", user);
        		UserService.getUserServiceInstance().updateUserHEADIMAGE(user.getUNO(),"images/"+newFileName+suffix);
        		out.print(result);
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        
    }  
	@RequestMapping(value = "/getHeadImage.do")
    public void upload(HttpServletRequest request, HttpServletResponse response) {  
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Gson gson = new Gson();
		String userJson = gson.toJson(user);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(userJson);
			System.out.println("get user:" +userJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		System.out.println("session password: " + user.getPW()
				+ "\nnew password: " + PW);
		boolean success = UserService.getUserServiceInstance().updateUserPW(UNO, PW);
		out.write(success? "true" : "false");
	}
}  
