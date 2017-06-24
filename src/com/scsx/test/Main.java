package com.scsx.test;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.scsx.dao.UserMapper;
import com.scsx.domain.User;
import com.scsx.util.MybatisUtil;

public class Main {

	public static void main(String[] args) throws IOException {
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<User> list = userMapper.findAllUsers();
		for (int i = 0; i < list.size(); i++) {
		    System.out.println(list.get(i));
		}
	}
}
