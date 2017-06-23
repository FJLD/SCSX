package com.scsx.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.scsx.dao.UserMapper;
import com.scsx.domain.User;
import com.scsx.util.MybatisUtil;

public class RegisterService {
	private static RegisterService registerService;
	private RegisterService(){}
	public static RegisterService getRegisterService(){
		if(registerService == null){
			registerService = new RegisterService();
		}
		return registerService;
	}
	
	public boolean isValidUNAME(String UNAME){
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user_d;
		try {
			user_d = userMapper.findUserByUNAME(UNAME);
			if(user_d != null){	//已经存在该注册名字
				return false;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return true;
		}
		return true;
	}
	
	public boolean insertUser(User user){	
		try {
			SqlSession sqlSession = MybatisUtil.getSqlSession(true);
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userMapper.insterUser(user);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
