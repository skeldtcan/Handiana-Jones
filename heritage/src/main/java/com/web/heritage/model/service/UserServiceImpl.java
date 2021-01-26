package com.web.heritage.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.heritage.model.User;
import com.web.heritage.model.UserParameter;
import com.web.heritage.model.mapper.UserMapper;
import com.web.util.PageNavigation;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public User login(User user) throws Exception {
		if(user.getUser_id() == null || user.getUser_password() == null) {
			return null;
		}
		return sqlSession.getMapper(UserMapper.class).login(user);
	}

	@Override
	public User userInfo(String userid) throws Exception {
		return sqlSession.getMapper(UserMapper.class).userInfo(userid);
	}

	@Override
	public boolean signUp(User user) throws Exception {
		if(user.getUser_id() == null || user.getUser_name() == null || user.getUser_password() == null || user.getUser_phone() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(UserMapper.class).signUp(user) == 1;
	}

	@Override
	public List<User> listUser(UserParameter userParameter) throws Exception {
		int start = userParameter.getPg() == 0 ? 0 : (userParameter.getPg() - 1) * userParameter.getSpp();
		userParameter.setStart(start);
		return sqlSession.getMapper(UserMapper.class).listUser(userParameter);
	}

	@Override
	public PageNavigation makePageNavigation(UserParameter userParameter) throws Exception {
	    int naviSize = 5;
	    PageNavigation pageNavigation = new PageNavigation();
	    pageNavigation.setCurrentPage(userParameter.getPg());
	    pageNavigation.setNaviSize(naviSize);
	    int totalCount = sqlSession.getMapper(UserMapper.class).getTotalCount(userParameter);
	    pageNavigation.setTotalCount(totalCount);
	    int totalPageCount = (totalCount - 1) / userParameter.getSpp() + 1;//27
	    pageNavigation.setTotalPageCount(totalPageCount);
	    boolean startRange = userParameter.getPg() <= naviSize;
	    pageNavigation.setStartRange(startRange);
	    boolean endRange = (totalPageCount - 1) / naviSize * naviSize < userParameter.getPg();
	    pageNavigation.setEndRange(endRange);
	    pageNavigation.makeNavigator();
	    return pageNavigation;
	}

	@Override
	@Transactional
	public boolean modifyUser(User user) throws Exception {
		return sqlSession.getMapper(UserMapper.class).modifyUser(user) == 1;
	}

	@Override
	@Transactional
	public boolean deleteUser(int userno) throws Exception {
		return sqlSession.getMapper(UserMapper.class).deleteUser(userno) == 1;
	}

}
