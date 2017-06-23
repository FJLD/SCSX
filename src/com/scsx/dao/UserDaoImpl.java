//package com.scsx.dao;
//import java.io.IOException;
//
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//
//import com.scsx.domain.User;
//
//public class UserDaoImpl implements UserDao {
//	private SqlSessionFactory sqlSessionFactory;
//	public UserDaoImpl(SqlSessionFactory sqlSessionFactory) throws IOException{
//		this.sqlSessionFactory = sqlSessionFactory;
//	}
//	@Override
//	public User findUserByUNO(int UNO) throws IOException {
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//		User user = sqlSession.selectOne("test.findUserByUNO", UNO);
//		sqlSession.close();
//		return user;
//	}
//	@Override
//	public User findUserByUNAME(String UNAME) throws IOException {
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//		User user = sqlSession.selectOne("test.findUserByUNAME", UNAME);
//		sqlSession.close();
//		return user;
//	}
//	@Override
//	public void insterUser(User user) throws IOException {
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//		sqlSession.insert("test.insterUser", user);
//		sqlSession.commit();
//		sqlSession.close();
//		
//	}
//	
//}