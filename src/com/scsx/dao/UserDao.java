package com.scsx.dao;

import java.io.IOException;

import com.scsx.domain.User;


public interface UserDao {
	public User findUserByUNO(int UNO) throws IOException;
	public User findUserByUNAME(String UNAME) throws IOException;
}
