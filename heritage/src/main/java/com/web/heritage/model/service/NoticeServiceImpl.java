package com.web.heritage.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.heritage.model.NoticeDto;
import com.web.heritage.model.NoticeParameterDto;
import com.web.heritage.model.mapper.NoticeMapper;
import com.web.util.PageNavigation;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	/* 공지사항 작성 */
	public boolean writeNoticepage(NoticeDto noticeDto) throws Exception {
		if (noticeDto.getNotice_title() == null || noticeDto.getNotice_content() == null) { // 공지사항 작성시 제목 또는 내용이 비었을 시
			throw new Exception();
		}
		return sqlSession.getMapper(NoticeMapper.class).writeNoticepage(noticeDto) == 1;
	}

	@Override
	public List<NoticeDto> listNoticepage(NoticeParameterDto noticeParameterDto) throws Exception {
		int start = noticeParameterDto.getPg() == 0 ? 0
				: (noticeParameterDto.getPg() - 1) * noticeParameterDto.getSpp();
		noticeParameterDto.setStart(start);
		return sqlSession.getMapper(NoticeMapper.class).listNoticepage(noticeParameterDto);
	}

	@Override
	public NoticeDto getNoticepage(int noticeno) throws Exception {
		return sqlSession.getMapper(NoticeMapper.class).getNoticepage(noticeno);
	}

	@Override
	@Transactional
	public boolean modifyNoticepage(NoticeDto noticeDto) throws Exception {
		return sqlSession.getMapper(NoticeMapper.class).modifyNoticepage(noticeDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteNoticepage(int noticeno) throws Exception {
		return sqlSession.getMapper(NoticeMapper.class).deleteNoticepage(noticeno) == 1;
	}

//	@Override
//	public PageNavigation makePageNavigation(NoticeParameterDto noticeParameterDto) throws Exception {
//		int naviSize = 5;
//		PageNavigation pageNavigation = new PageNavigation();
//		pageNavigation.setCurrentPage(noticeParameterDto.getPg());
//		pageNavigation.setNaviSize(naviSize);
//		int totalCount = sqlSession.getMapper(NoticeMapper.class).getTotalCount(noticeParameterDto);
//		pageNavigation.setTotalCount(totalCount);  
//		int totalPageCount = (totalCount - 1) / noticeParameterDto.getSpp() + 1;//27
//		pageNavigation.setTotalPageCount(totalPageCount);
//		boolean startRange = noticeParameterDto.getPg() <= naviSize;
//		pageNavigation.setStartRange(startRange);
//		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < noticeParameterDto.getPg();
//		pageNavigation.setEndRange(endRange);
//		pageNavigation.makeNavigator();
//		return pageNavigation;
//	}

}
