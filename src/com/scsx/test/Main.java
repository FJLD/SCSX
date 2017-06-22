package com.scsx.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.scsx.dao.UserDao;
import com.scsx.dao.UserDaoImpl;



public class Main {
	
	public static void main(String[] args) throws IOException {
		String resource = "SqlMapConfig.xml";
		InputStream inputStream;
		inputStream = Resources.getResourceAsStream(resource);
						
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		UserDao UserDao = new UserDaoImpl(sqlSessionFactory);
		UserDao.findUserById(2);
	}

}
