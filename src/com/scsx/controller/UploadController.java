package com.scsx.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.scsx.domain.User;
import com.scsx.service.UserService;
import com.scsx.util.Commons;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

@Controller
@MultipartConfig
public class UploadController {
	
	@RequestMapping(value = "/getHeadImage.do")
    public void upload(HttpServletRequest request, HttpServletResponse response) {  
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print("{\"HEADIMAGE\":\""+Commons.PIC_HOST + user.getHEADIMAGE()+"\"}");
		} catch (IOException e) {
			e.printStackTrace();
		}
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
        		
        		byte[] fbytes = file.getBytes();
        		Client client = new Client();
        		
        		WebResource resource = client.resource(Commons.PIC_HOST+"images/"+newFileName+suffix);
        		resource.put(String.class, fbytes);
        		
        		String result="{\"fullPath\":\""+Commons.PIC_HOST+"images/"+newFileName+suffix+"\""+"}";
        		HttpSession session = request.getSession();
        		User user =  (User) session.getAttribute("user");
        		if(!user.getHEADIMAGE().equals(Commons.DefualtHeadImageName)){
        			resource = client.resource(Commons.PIC_HOST+user.getHEADIMAGE());
        			resource.delete();
        		}
        		user.setHEADIMAGE("images/"+newFileName+suffix);
        		session.setAttribute("user", user);
        		UserService.getUserServiceInstance().updateUserHEADIMAGE(user.getUNO(),"images/"+newFileName+suffix);
        		out.print(result);
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        
    }  
}
