package com.scsx.dao;

import java.io.IOException;
import java.util.List;

import com.scsx.domain.User;

public interface UserMapper {
	public User findUserByUNO(int UNO) throws IOException;

	public User findUserByUNAME(String UNAME) throws IOException;

	public void insterUser(User user) throws IOException;
	
	public List<User> findAllUsers() throws IOException;
}
