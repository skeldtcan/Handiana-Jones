package com.web.heritage.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.web.heritage.model.NoticeDto;
import com.web.heritage.model.NoticeParameterDto;
import com.web.heritage.model.service.NoticeService;

import io.swagger.annotations.*;

@Api("NoticeController V1")
@RestController
@RequestMapping("/noticepage")
public class NoticeController {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private NoticeService noticeService;
	
	@ApiOperation(value = "공지사항 작성", notes = "새로운 공지사항글 입력", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeNoticepage(@RequestBody @ApiParam(value = "공지사항 정보.", required = true) NoticeDto noticeDto) throws Exception{
		logger.info("writeNoticepage - 호출");
		if(noticeService.writeNoticepage(noticeDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);				
	}
	
	@ApiOperation(value = "공지사항 글목록", notes = "모든 공지사항글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<NoticeDto>> listNoticepage(@ApiParam(value = "공지사항글을 얻기위한 부가정보.", required = true) NoticeParameterDto noticeParameterDto) throws Exception {
		logger.info("listNoticepage - 호출");
		return new ResponseEntity<List<NoticeDto>>(noticeService.listNoticepage(noticeParameterDto), HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지사항 글검색", notes = "번호에 해당하는 공지사항의 정보를 반환한다.", response = NoticeDto.class)
	@GetMapping("{notice_no}")
	public ResponseEntity<NoticeDto> getNoticepage(@PathVariable("notice_no") @ApiParam(value = "얻어올 글의 글번호.", required = true) int notice_no) throws Exception {
		logger.info("getNoticepage - 호출");
		return new ResponseEntity<NoticeDto>(noticeService.getNoticepage(notice_no), HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지사항 글수정", notes = "기존에 존재하던 공지사항글을 수정한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyNoticepage(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) NoticeDto noticeDto) throws Exception {
		logger.info("modifyNoticepage- 호출");
		
		if (noticeService.modifyNoticepage(noticeDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지사항 글삭제", notes = "번호에 해당하는 공지사항글 정보를 삭제한다. ", response = String.class)
	@DeleteMapping("{notice_no}")
	public ResponseEntity<String> deleteNoticepage(@PathVariable("notice_no") @ApiParam(value = "삭제할 글의 글번호.", required = true) int notice_no) throws Exception {
		logger.info("deleteNoticepage - 호출");
		if (noticeService.deleteNoticepage(notice_no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}	
}
