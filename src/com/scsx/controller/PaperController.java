package com.scsx.controller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.scsx.service.PaperService;

@Controller
public class PaperController {

	@RequestMapping(value = "/getAllPapers.do")
	public void getAllPapers(HttpServletResponse res) {
		try {
			String recordsJson = PaperService.getPaperService().getPapers();
			System.out.println(recordsJson);
			res.getWriter().write(recordsJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}