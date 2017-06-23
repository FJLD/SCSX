package com.scsx.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.scsx.dao.UserDao;
import com.scsx.dao.UserDaoImpl;
import com.scsx.domain.User;



public class Main {
	
	public static void main(String[] args) throws IOException {
		String resource = "SqlMapConfig.xml";
		InputStream inputStream;
		inputStream = Resources.getResourceAsStream(resource);
						
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		UserDao UserDao = new UserDaoImpl(sqlSessionFactory);
		User user= UserDao.findUserByUNO(2);
		System.out.println(user.getUNAME()+" "+user.getNAME()+" "+user.getPW());
		User user2=UserDao.findUserByUNAME("fanzhen");
		System.out.println(user2.getUNAME()+" "+user2.getNAME()+" "+user2.getPW());
	}

}
