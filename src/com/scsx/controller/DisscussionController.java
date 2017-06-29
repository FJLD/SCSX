package com.scsx.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.scsx.domain.Discussion;
import com.scsx.domain.Question;
import com.scsx.domain.User;
import com.scsx.service.DiscussionService;
import com.scsx.service.ExamRecordsService;
import com.scsx.service.PaperService;
import com.scsx.util.Commons;

@Controller
public class DisscussionController {
	
	@RequestMapping("/ChooseDiscussion.do")
	public String ChooseDiscussion() {
		return "WEB-INF/discussion/choose_discussion";
	}
	
	@RequestMapping(value = "goToDiscussion", method=RequestMethod.GET)
	public ModelAndView goToDiscussion(HttpServletRequest req){
		int PNO = Integer.parseInt(req.getParameter("paper"));
		HttpSession session  = req.getSession();
		session.setAttribute("discussionPNO", PNO);
		session.setAttribute("discussionPage", 1);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/discussion/discussion");
		return mav;
	}
	@RequestMapping(value = "getDiscussion", method=RequestMethod.GET)
	public void getDiscussion(HttpServletRequest req, HttpServletResponse res){
		int PNO = (int) req.getSession().getAttribute("discussionPNO");
		int page = Integer.parseInt(req.getParameter("page"));
		try {
			String recordsJson = DiscussionService.getDiscussionServiceInstance().getPartDiscussionByPNOAndDiscussionPaper(PNO, page);
			res.setHeader("Content-type", "text/html;charset=UTF-8");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(recordsJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
