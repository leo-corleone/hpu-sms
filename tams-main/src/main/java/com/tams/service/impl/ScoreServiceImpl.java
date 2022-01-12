package com.tams.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tams.domain.Score;
import com.tams.mapper.ScoreMapper;
import com.tams.service.ScoreService;
import org.springframework.stereotype.Service;

/**
 * @author swiChen
 * @date 2022/1/12
 **/

@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper , Score> implements ScoreService {
}
