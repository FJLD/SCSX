package com.scsx.service;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.google.gson.Gson;
import com.scsx.dao.ExamMapper;
import com.scsx.domain.Exam;
import com.scsx.util.MybatisUtil;

public class ExamRecordsService {
	private static ExamRecordsService examRecordsService;
	private static int ROWS_PER_PAGE = 10;
	
	private ExamRecordsService() {}
	
	public static ExamRecordsService getExamRecordsService() {
		if (examRecordsService == null) {
			examRecordsService = new ExamRecordsService();
		}
		return examRecordsService;
	}
	
	public String getExamRecords(int UNO, int page) throws Exception {
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		ExamMapper mapper = sqlSession.getMapper(ExamMapper.class);
		int offset = (page - 1) * ROWS_PER_PAGE;
		List<Exam> exams = mapper.findExamsByUNO(UNO, offset, ROWS_PER_PAGE);
		System.out.println("1st exam result: " + exams.get(0).getRESULT());
		Gson gson = new Gson();
		return gson.toJson(exams);
	}
}
