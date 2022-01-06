package com.sms.service.impl;

import com.sms.mapper.NoticeMapper;
import com.sms.service.NoticeService;
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
