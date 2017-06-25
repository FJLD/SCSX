package com.scsx.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.scsx.domain.Question;
import com.scsx.service.PaperService;

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
	
	@RequestMapping(value="/goToExam.do", method=RequestMethod.GET)
	public ModelAndView goToExam(HttpServletRequest req) {
		int PNO = Integer.parseInt(req.getParameter("paper"));
		HttpSession session  = req.getSession();
		session.setAttribute("paper", PNO);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/ordinary_user/exam_now");
		try {
			List<Question> questions = PaperService.getPaperService().getQuestions(PNO);
			session.setAttribute("questions", questions);
			for (Question q : questions) {
				System.out.println(q);
			}
			mav.addObject("questions", questions);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value="/getAllQuestions.do", method=RequestMethod.GET)
	public void getAllQuestions(HttpServletRequest req, HttpServletResponse res) {
		int page = Integer.parseInt(req.getParameter("page"));
		try {
			PaperService.getPaperService().getAllQuestions(page);
			String questionsJson = PaperService.getPaperService().getAllQuestions(page);
			res.setHeader("Content-type", "text/html;charset=UTF-8");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(questionsJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/updateQuestion.do", method=RequestMethod.POST)
	public void updateQuestion(HttpServletRequest req, HttpServletResponse res) {
		int QNO = Integer.parseInt(req.getParameter("QNO"));
		String BANK = req.getParameter("BANK");
		String OPTION1 = req.getParameter("OPTION1");
		String OPTION2 = req.getParameter("OPTION2");
		String OPTION3 = req.getParameter("OPTION3");
		String OPTION4 = req.getParameter("OPTION4");
		String ans = req.getParameter("ans");
		Question question = new Question(QNO, BANK, OPTION2, OPTION3, OPTION3, OPTION4, ans);
		try {
			PaperService.getPaperService().updateQuestion(question);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
