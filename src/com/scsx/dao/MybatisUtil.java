package com.scsx.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.InputStream;

public class MybatisUtil {

	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		try {
			String str = "SqlMapConfig.xml";
			InputStream is = Resources.getResourceAsStream(str);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		} catch (Exception e) {
			
		}
	}
	
	public static SqlSession getSqlSession(boolean autocommit) {
		return sqlSessionFactory.openSession(autocommit);
	}
}
