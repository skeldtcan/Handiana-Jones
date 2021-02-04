package com.web.heritage.model.service;

import java.util.List;

import com.web.heritage.model.Favor;

public interface FavorService {

	public boolean getCtcd(Favor favor) throws Exception;
	public boolean getBcode(Favor favor) throws Exception;
	public boolean getCcce(Favor favor) throws Exception;
	public List<Favor> listRecommend(int user_no) throws Exception;

}
