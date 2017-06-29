package com.scsx.dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.scsx.domain.Discussion;
import com.scsx.domain.Exam;

@Mapper
public interface DiscussionMapper {
	public List<Discussion> getAllDiscussionByPNO(int PNO) throws IOException;
	public List<Discussion> findPartDiscussionByPNOAndDiscussionPaper(@Param("PNO") int PNO, @Param("offset")int offset, @Param("rows")int rows) throws IOException;
}
