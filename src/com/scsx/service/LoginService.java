package com.scsx.service;

import java.io.IOException;
import org.apache.ibatis.session.SqlSession;
import com.scsx.dao.UserMapper;
import com.scsx.domain.User;
import com.scsx.util.MybatisUtil;

public class LoginService {
	private static LoginService loginService;
	private LoginService(){}
	public static LoginService getLoginService(){
		if(loginService == null){
			loginService = new LoginService();
		}
		return loginService;
	}
	//判断用户是否存在与数据库，如果UNAME,PW,POWER匹配成功返回true否则返回false
	public boolean confirm(User user){
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		UserMapper leagueMapper = sqlSession.getMapper(UserMapper.class);
		User user_d;
		try {
			user_d = leagueMapper.findUserByUNAME(user.getUNAME());
			if(user_d != null && user_d.getUNAME().equals(user.getUNAME()) 
					&& user_d.getPW().equals(user.getPW()) && user_d.getPOWER().equals(user.getPOWER())){
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
