package com.scsx.controller;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.scsx.domain.Discussion;
import com.scsx.domain.User;
import com.scsx.service.DiscussionService;

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
	
	@RequestMapping(value="pushComment.do", method=RequestMethod.POST)
	public void pushComment(HttpServletRequest request, PrintWriter out){
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) return;
		request.getSession().setMaxInactiveInterval(3600);
		Discussion discussion = new Discussion();
		discussion.setTIME(new Date());
		discussion.setUNO(user.getUNO());
		discussion.setPNO((int)request.getSession().getAttribute("discussionPNO"));
		discussion.setDATA(request.getParameter("submitComments"));
		DiscussionService.getDiscussionServiceInstance().insertDiscussion(discussion);
		out.print("true");
	}
}
