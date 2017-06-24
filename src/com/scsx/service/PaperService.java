package com.scsx.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.scsx.dao.PaperMapper;
import com.scsx.domain.Paper;
import com.scsx.util.MybatisUtil;

public class PaperService {
	private static PaperService paperService;
	
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

}