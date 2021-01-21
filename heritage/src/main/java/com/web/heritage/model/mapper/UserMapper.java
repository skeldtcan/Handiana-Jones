package com.web.heritage.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.heritage.model.User;
import com.web.heritage.model.UserParameter;

@Mapper
public interface UserMapper {

	public User login(User user) throws SQLException;
	public User userInfo(String userid) throws SQLException;
	public int signUp(User user) throws SQLException;

	public List<User> listUser(UserParameter userParameter) throws SQLException;
	public int getTotalCount(UserParameter userParameter) throws SQLException;

}
