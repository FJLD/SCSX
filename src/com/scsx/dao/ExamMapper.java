package com.scsx.dao;

import java.io.IOException;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.scsx.domain.Exam;

@Mapper
public interface ExamMapper {
	public List<Exam> findExamsByUNO(@Param("UNO")int UNO, @Param("offset")int offset, @Param("rows")int rows) throws IOException;
}
