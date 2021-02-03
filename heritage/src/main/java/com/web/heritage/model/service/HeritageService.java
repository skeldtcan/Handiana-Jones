package com.web.heritage.model.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.heritage.model.Heritage;

public interface HeritageService {

	public List<Heritage> listHeritage(String word) throws SQLException;

	public Heritage getHeritage(@Param("kdcd") String kdcd, @Param("ctcd") String ctcd, @Param("asno") String asno)
		throws SQLException;
}