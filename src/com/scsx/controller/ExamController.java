package com.scsx.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.scsx.domain.Question;
import com.scsx.service.PaperService;

@Controller
public class ExamController {

	@RequestMapping(value="/goToExam.do", method=RequestMethod.GET)
	public ModelAndView goToExam(HttpServletRequest req) {
		int PNO = Integer.parseInt(req.getParameter("paper"));
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/ordinary_user/exam_now");
		try {
			List<Question> questions = PaperService.getPaperService().getQuestions(PNO);
			for (Question q : questions) {
				System.out.println(q);
			}
			mav.addObject("questions", questions);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
}
