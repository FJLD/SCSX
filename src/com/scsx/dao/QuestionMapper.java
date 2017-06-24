package com.scsx.dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.scsx.domain.Question;

@Mapper
public interface QuestionMapper {
	public List<Question> findQuestionByPNO(@Param("PNO")int PNO) throws IOException;
	public List<Question> findQuestions(@Param("offset")int offset, @Param("rows")int rows) throws IOException;
}
