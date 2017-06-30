package com.scsx.service;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.scsx.dao.DiscussionMapper;
import com.scsx.domain.Discussion;
import com.scsx.util.Commons;
import com.scsx.util.MybatisUtil;

public class DiscussionService {
	private static DiscussionService discussionService;

	private DiscussionService() {
	}

	public static DiscussionService getDiscussionServiceInstance() {
		if (discussionService == null) {
			discussionService = new DiscussionService();
		}
		return discussionService;
	}
	public List<Discussion> getAllDiscussionByPNO(int PNO) throws IOException{
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		DiscussionMapper discussionMapper = sqlSession.getMapper(DiscussionMapper.class);
		List<Discussion> discussions = discussionMapper.getAllDiscussionByPNO(PNO);
		return discussions;
	}
	
	public String getPartDiscussionByPNOAndDiscussionPaper(int PNO,int page) throws IOException{
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		DiscussionMapper mapper = sqlSession.getMapper(DiscussionMapper.class);
		int offset = page;
		List<Discussion> discussions = mapper.findPartDiscussionByPNOAndDiscussionPaper(PNO, offset, offset + Discussion.paperInv);
		for(Discussion discussion:discussions){
			discussion.setHEADIMAGE(Commons.PIC_HOST+discussion.getHEADIMAGE());
		}
		Gson gson = new Gson();
		return gson.toJson(discussions);
	}
	public void insertDiscussion(Discussion discussion){
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		DiscussionMapper mapper = sqlSession.getMapper(DiscussionMapper.class);
		mapper.insertDiscussion(discussion);
	}
}
