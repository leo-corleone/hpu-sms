package com.tams.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tams.domain.Notice;
import com.tams.mapper.NoticeMapper;
import com.tams.service.NoticeService;
import org.springframework.stereotype.Service;

/**
 * @author swichen
 * @data 2021-6-22
 */

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper , Notice>
		               implements NoticeService {



}
