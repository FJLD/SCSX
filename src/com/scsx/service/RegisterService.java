package com.scsx.service;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;

import com.scsx.dao.MybatisUtil;
import com.scsx.dao.UserMapper;
import com.scsx.domain.User;

public class RegisterService {
	private static RegisterService registerService;

	private RegisterService() {
	}

	public static RegisterService getRegisterService() {
		if (registerService == null) {
			registerService = new RegisterService();
		}
		return registerService;
	}

	public boolean isValidUNAME(String UNAME) {
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);

		try {
			User getUser = mapper.findUserByUNAME(UNAME);
			System.out.println("getUser" + getUser);
			if (getUser != null && UNAME.equals(getUser.getUNAME())) {
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return true;
		}
		return true;
	}

	public boolean insertUser(User user) {
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);

		try {
			mapper.insterUser(user);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
