package com.web.heritage.model.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.heritage.model.Heritage;
import com.web.heritage.model.mapper.HeritageMapper;

@Service
public class HeritageServiceImpl implements HeritageService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Heritage> listHeritage(String word) throws SQLException {
		return sqlSession.getMapper(HeritageMapper.class).listHeritage(word);
	}

	@Override
	public Heritage getHeritage(String kdcd, String ctcd, String asno) throws SQLException {
		return sqlSession.getMapper(HeritageMapper.class).getHeritage(kdcd, ctcd, asno);
	}

}
