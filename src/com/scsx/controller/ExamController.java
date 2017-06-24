package com.scsx.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.scsx.domain.Question;
import com.scsx.domain.User;
import com.scsx.service.ExamRecordsService;
import com.scsx.service.PaperService;
import com.scsx.service.UserService;

@Controller
public class ExamController {
	
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
	
	@RequestMapping(value="/getAllExamRecords.do", method=RequestMethod.GET)
	public void getAllExamRecords(HttpServletRequest req, HttpServletResponse res) {	// for admin
		// TODO: 
		User user = (User) req.getSession().getAttribute("user");
		int page = Integer.parseInt(req.getParameter("page"));
		try {
			String recordsJson = ExamRecordsService.getExamRecordsService().getAllExamRecords(page);
			res.setHeader("Content-type", "text/html;charset=UTF-8");
			res.setCharacterEncoding("UTF-8");  
			res.getWriter().write(recordsJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
