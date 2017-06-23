package com.scsx.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.scsx.dao.UserDao;
import com.scsx.dao.UserDaoImpl;
import com.scsx.domain.User;

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
		String resource = "SqlMapConfig.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			UserDao UserDao = new UserDaoImpl(sqlSessionFactory);
			User getUser=UserDao.findUserByUNAME(UNAME);
			System.out.println("getUser"+getUser);
			if(getUser != null && UNAME.equals(getUser.getUNAME())){
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}	
		return true;
	}
	public boolean insertUser(User user){
		String resource = "SqlMapConfig.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			UserDao UserDao = new UserDaoImpl(sqlSessionFactory);
			UserDao.insterUser(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
