package com.scsx.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.scsx.dao.UserDao;
import com.scsx.dao.UserDaoImpl;
import com.scsx.domain.User;

public class LoginService {
	private static LoginService loginService;
	private LoginService(){}
	public static LoginService getLoginService(){
		if(loginService == null){
			loginService = new LoginService();
		}
		return loginService;
	}
	public boolean confirm(User user){
		String resource = "SqlMapConfig.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			UserDao UserDao = new UserDaoImpl(sqlSessionFactory);
			User getUser=UserDao.findUserByUNAME(user.getUNAME());
			if(getUser != null && getUser.getUNAME().equals(user.getUNAME()) && getUser.getPW().equals(user.getPW()) && getUser.getPOWER().equals(user.getPOWER())){
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
		return false;
		
	}
}
