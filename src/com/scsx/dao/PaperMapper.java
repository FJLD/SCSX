package com.scsx.dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.scsx.domain.Paper;

@Mapper
public interface PaperMapper {
	public Paper getPaperByPNO(int PNO) throws IOException;
	public List<Paper> findPapers() throws IOException;
}
