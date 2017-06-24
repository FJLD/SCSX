package com.scsx.service;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;

import com.scsx.dao.UserMapper;
import com.scsx.domain.User;
import com.scsx.util.MybatisUtil;

public class UserService {
	private static UserService userService;
	private UserService(){}
	public static UserService getUserServiceInstance(){
		if(userService == null){
			userService = new UserService();
		}
		return userService;
	}
	//由用户名返回用户对象，调用前提用户一定存在
	public User getUserFromUNAME(String UNAME){
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		try {
			return userMapper.findUserByUNAME(UNAME);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//判断用户是否存在于数据库，如果UNAME,PW,POWER匹配成功返回true否则返回false
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
	//判断注册用户是否合法
	public boolean isValidRegisterUNAME(String UNAME){
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
	//由用户名和密码验证用户身份
	public boolean isValidUNAMEAndPW(String UNAME,String PW){
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user_d;
		try {
			user_d = userMapper.findUserByUNAME(UNAME);
			if(user_d != null && user_d.getPW().equals(PW)){//只有用户名和密码都一样才返回正确
				return true;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
		return false;
	}
	//在数据库中插入注册用户信息
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
