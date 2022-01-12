package com.tams.service.impl;

import com.tams.mapper.NoticeMapper;
import com.tams.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author swichen
 * @data 2021-6-22
 */

@Service
public class NoticeServiceImpl implements NoticeService {

	@Resource
	private NoticeMapper noticeMapper;


}
