package com.web.heritage.model.service;

import java.util.List;

import com.web.heritage.model.User;
import com.web.heritage.model.UserParameter;
import com.web.util.PageNavigation;

public interface UserService {

	public User login(User user) throws Exception;
	public User userInfo(String userid) throws Exception;
	public boolean signUp(User user) throws Exception;

	public List<User> listUser(UserParameter userparameter) throws Exception;
	public PageNavigation makePageNavigation(UserParameter userParameter) throws Exception;

}
