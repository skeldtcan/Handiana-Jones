package com.web.heritage.model.service;

import java.util.List;

import com.web.heritage.model.NoticeDto;
import com.web.heritage.model.NoticeParameterDto;
import com.web.util.PageNavigation;

public interface NoticeService {

	public boolean writeNoticepage(NoticeDto noticeDto) throws Exception;

	public List<NoticeDto> listNoticepage(NoticeParameterDto noticeParameterDto) throws Exception;
//	public PageNavigation makePageNavigation(NoticeParameterDto noticeParameterDto) throws Exception;

	public NoticeDto getNoticepage(int noticeno) throws Exception;

	public boolean modifyNoticepage(NoticeDto noticeDto) throws Exception;

	public boolean deleteNoticepage(int noticeno) throws Exception;

}
