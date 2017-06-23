package com.scsx.service;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import com.scsx.dao.MybatisUtil;
import com.scsx.dao.UserMapper;
import com.scsx.domain.User;

public class LoginService {
	private static LoginService loginService;

	private LoginService() {
	}

	public static LoginService getLoginService() {
		if (loginService == null) {
			loginService = new LoginService();
		}
		return loginService;
	}

	public boolean confirm(User user) {
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);

		try {
			User getUser = mapper.findUserByUNAME(user.getUNAME());
			if (getUser != null && getUser.getUNAME().equals(user.getUNAME()) && getUser.getPW().equals(user.getPW())
					&& getUser.getPOWER().equals(user.getPOWER()))
				return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return false;

	}
}
