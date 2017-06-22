package com.scsx.dao;
import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.scsx.domain.User;

public class UserDaoImpl implements UserDao {
	private SqlSessionFactory sqlSessionFactory;
	public UserDaoImpl(SqlSessionFactory sqlSessionFactory) throws IOException{
		this.sqlSessionFactory = sqlSessionFactory;
	}
	@Override
	public User findUserById(int id) throws IOException {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
				
		User user = sqlSession.selectOne("test.findUserByUNO", id);
		System.out.println(user.getNAME()+" password="+user.getPW());
				
		sqlSession.close();
		return user;
	}

}