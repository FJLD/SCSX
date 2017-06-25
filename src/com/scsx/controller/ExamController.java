package com.scsx.controller;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping(value = "/markingPaper.do", method=RequestMethod.POST)
	public String markingPaper(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		List<Question> questions = (List<Question>) session.getAttribute("questions");
		int cnt = 0;
		for(int i=1;i<= questions.size();i++){
			Question quesion = questions.get(i-1);
			System.out.println(quesion.getANS());
			String answer = request.getParameter(quesion.getQNO()+"");
			System.out.println(answer);
			switch(answer){
			case "a":
				if(quesion.getANS()==1000){
					cnt++;
					quesion.setUserANS(1000);
				}
				break;
			case "b":
				if(quesion.getANS()==100){
					cnt++;
					quesion.setUserANS(100);
				}
				break;
			case "c":
				if(quesion.getANS()==10){
					cnt++;
					quesion.setUserANS(1000);
				}
				break;
			case "d":
				if(quesion.getANS()==1){
					cnt++;
					quesion.setUserANS(1000);
				}
				break;
			}
		}
		session.setAttribute("questions", questions);
		model.addAttribute("questions", questions);
		model.addAttribute("score", 100*cnt/questions.size());
		System.out.println(100*cnt/questions.size());
		return "test";
	}
	
}
