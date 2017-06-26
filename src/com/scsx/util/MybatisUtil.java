package com.scsx.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.scsx.dao.UserMapper;
import com.scsx.domain.User;

public class MybatisUtil {
private static SqlSessionFactory sqlSessionFactory;
	
	static{
		try {
			String str = "SqlMapConfig.xml";
			InputStream is = Resources.getResourceAsStream(str);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static SqlSession getSqlSession(boolean autocommit){
		return sqlSessionFactory.openSession(autocommit);
	}
	public static void main(String[] args) throws IOException {
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		UserMapper leagueMapper = sqlSession.getMapper(UserMapper.class);
		User user = leagueMapper.findUserByUNAME("小李");
		System.out.println(user.getUNAME()+" "+user.getNAME()+" "+user.getPW());
	}
}
