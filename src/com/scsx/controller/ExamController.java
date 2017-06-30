package com.scsx.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.scsx.domain.Exam;
import com.scsx.domain.Question;
import com.scsx.domain.User;
import com.scsx.service.ExamRecordsService;
import com.scsx.service.UserService;

@Controller
public class ExamController {

	@RequestMapping(value = "/getExamRecords.do", method = RequestMethod.GET)
	public void getExamRecords(HttpServletRequest req, HttpServletResponse res) {
		int UNO = ((User) req.getSession().getAttribute("user")).getUNO();
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

	@RequestMapping(value = "/getAllExamRecords.do", method = RequestMethod.GET)
	public void getAllExamRecords(HttpServletRequest req, HttpServletResponse res) { // for
																						// admin
		User user = (User) req.getSession().getAttribute("user");
		int page = Integer.parseInt(req.getParameter("page"));
		try {
			if (!UserService.getUserServiceInstance().confirmAdmin(user)) {
				res.getWriter().write("err");
				return;
			}
			String recordsJson = ExamRecordsService.getExamRecordsService().getAllExamRecords(page);
			res.setHeader("Content-type", "text/html;charset=UTF-8");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(recordsJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/markingPaper.do", method = RequestMethod.POST)
	public String markingPaper(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Question> questions = (List<Question>) session.getAttribute("questions");
		System.out.println(questions);
		int cnt = 0;
		for (int i = 1; i <= questions.size(); i++) {
			Question question = questions.get(i - 1);
			System.out.println(question.getANS());
			String answer = request.getParameter(question.getQNO() + "");
			if (answer == null) answer = "";
			System.out.println("answer for " + question.getQNO() + ": " + answer);
			int userAnswer = Question.answerString2Int(answer);
			question.setUserANS(userAnswer);
			System.out.println("userAnswer: " + userAnswer);
			if (userAnswer == question.getANS()) {
				cnt++;
			}
		}
		int score = 100 * cnt / questions.size();
		session.setAttribute("questions", questions);
		model.addAttribute("questions", questions);
		model.addAttribute("score", score);
		
		// insert exam record
		int UNO = ((User)session.getAttribute("user")).getUNO();
		int PNO = (int) session.getAttribute("paper");
		String RESULT = String.valueOf(score);
		Date TIME = new Date();
		Exam exam = new Exam(UNO, PNO, RESULT, TIME);
		ExamRecordsService.getExamRecordsService().insertExamRecord(exam);
		
		return "redirect:/Record.do";
	}

}
