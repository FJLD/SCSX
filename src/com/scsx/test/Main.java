package com.scsx.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.scsx.dao.UserMapper;
import com.scsx.dao.UserMapper;
import com.scsx.domain.User;

public class Main {

	public static void main(String[] args) throws IOException {
		String resource = "SqlMapConfig.xml";
		InputStream inputStream;
		inputStream = Resources.getResourceAsStream(resource);

		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		UserMapper UserDao = new UserDaoImpl(sqlSessionFactory);
		User user = UserDao.findUserByUNO(2);
		System.out.println(user.getUNAME() + " " + user.getNAME() + " " + user.getPW());
		User user2 = UserDao.findUserByUNAME("fanzhen");
		System.out.println(user2.getUNAME() + " " + user2.getNAME() + " " + user2.getPW());
		/*
		 * User user3 = new User(); user3.setUNAME("中文"); user3.setPW("中文");
		 * user3.setNAME("中文"); user3.setID(user.getID());
		 * user3.setUPHONE(user.getUPHONE()); user3.setPOWER(user.getPOWER());
		 * UserDao.insterUser(user3);
		 */
		// System.out.println(user.getNAME());
		User user4 = UserDao.findUserByUNAME("中文");
		System.out.println(user4.getUNAME() + " " + user4.getNAME() + " " + user4.getPW());
	}
}
