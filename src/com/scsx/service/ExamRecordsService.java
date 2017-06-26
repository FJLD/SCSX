package com.scsx.service;

import java.io.IOException;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.google.gson.Gson;
import com.scsx.dao.ExamMapper;
import com.scsx.util.MybatisUtil;
import com.scsx.domain.Exam;

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
		if (exams.isEmpty()) {
			System.out.println("null");
		} else {
			System.out.println("1st exam result: " + exams.get(0).getRESULT());
		}
		Gson gson = new Gson();
		return gson.toJson(exams);
	}
	
	public String getAllExamRecords(int page) throws Exception {
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		ExamMapper mapper = sqlSession.getMapper(ExamMapper.class);
		int offset = (page - 1) * ROWS_PER_PAGE;
		List<Exam> exams = mapper.findAllExams(offset, ROWS_PER_PAGE);
		if (exams.isEmpty()) {
			System.out.println("null");
		} else {
			System.out.println("1st exam result: " + exams.get(0).getRESULT());
		}
		Gson gson = new Gson();
		return gson.toJson(exams);
	}
	
	public void insertExamRecord(Exam exam) {
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		ExamMapper mapper = sqlSession.getMapper(ExamMapper.class);
		try {
			mapper.insertExam(exam);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
