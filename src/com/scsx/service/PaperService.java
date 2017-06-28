package com.scsx.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.scsx.dao.PaperMapper;
import com.scsx.dao.QuestionMapper;
import com.scsx.domain.Paper;
import com.scsx.domain.Question;
import com.scsx.util.MybatisUtil;

public class PaperService {
	private static PaperService paperService;
	private static int ROWS_PER_PAGE = 10;
	
	private PaperService() {}
	
	public static PaperService getPaperService() {
		if (paperService == null) {
			paperService = new PaperService();
		}
		return paperService;
	}
	
	public String getPapers() throws Exception {
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		PaperMapper mapper = sqlSession.getMapper(PaperMapper.class);
		List<Paper> papers = mapper.findPapers();
		for (Paper p : papers) {
			System.out.println(p.getPNO() + ", " + p.getPNAME());
		}
		Gson gson = new Gson();
		return gson.toJson(papers);
	}
	
	public List<Question> getQuestions(int PNO) throws Exception {
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);
		List<Question> questions = mapper.findQuestionByPNO(PNO);
		return questions;
	}
	
	public String getAllQuestions(int page) throws Exception {
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);
		int offset = (page - 1) * ROWS_PER_PAGE;
		List<Question> questions = mapper.findQuestions(offset, ROWS_PER_PAGE);
		for (Question q : questions) {
			System.out.println(q);
			q.setANSString();
		}
		Gson gson = new Gson();
		return gson.toJson(questions);
	}
	
	public void updateQuestion(Question question) throws Exception {
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);
		mapper.updateQuestion(question);
		System.out.println("updated" + question);
	}

}
