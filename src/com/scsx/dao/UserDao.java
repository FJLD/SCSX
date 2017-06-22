package com.scsx.dao;

import java.io.IOException;

import com.scsx.domain.User;


public interface UserDao {
	public User findUserById(int UNO) throws IOException;
	
}
