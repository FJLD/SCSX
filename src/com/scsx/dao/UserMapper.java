package com.scsx.dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.scsx.domain.User;

public interface UserMapper {
	public User findUserByUNO(int UNO) throws IOException;

	public User findUserByUNAME(String UNAME) throws IOException;

	public void insterUser(User user) throws IOException;
	
	public void updateUserPHONE(@Param("UNO")int UNO, @Param("UPHONE")String UPHONE) throws IOException;
	
	public void updateUserPW(@Param("UNO")int UNO, @Param("PW")String PW) throws IOException;
	
	public void updateUserHEADIMAGE(@Param("UNO")int UNO, @Param("HEADIMAGE")String HEADIMAGE) throws IOException;
	
	public List<User> findAllUsers(@Param("offset")int offset, @Param("row")int row) throws IOException;
}
