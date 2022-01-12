package com.tams.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tams.domain.Schedule;
import com.tams.mapper.ScheduleMapper;
import com.tams.service.ScheduleService;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper , Schedule>
                                 implements ScheduleService {
}