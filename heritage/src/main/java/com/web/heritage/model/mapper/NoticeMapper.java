package com.web.heritage.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.heritage.model.NoticeDto;
import com.web.heritage.model.NoticeParameterDto;

@Mapper
public interface NoticeMapper {

	public int writeNoticepage(NoticeDto noticeDto) throws SQLException;

	public List<NoticeDto> listNoticepage(NoticeParameterDto noticeParameterDto) throws SQLException;

	public NoticeDto getNoticepage(int notice_no) throws SQLException;

	public int modifyNoticepage(NoticeDto noticeDto) throws SQLException;

	public int deleteNoticepage(int notice_no) throws SQLException;
//	public int getTotalCount(NoticeParameterDto noticeParameterDto) throws SQLException;

}
