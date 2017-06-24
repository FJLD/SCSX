package com.scsx.dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.scsx.domain.User;

public interface UserMapper {
	public User findUserByUNO(int UNO) throws IOException;

	public User findUserByUNAME(String UNAME) throws IOException;

	public void insterUser(User user) throws IOException;
	
	public List<User> findAllUsers(@Param("offset")int offset, @Param("row")int row) throws IOException;
}
