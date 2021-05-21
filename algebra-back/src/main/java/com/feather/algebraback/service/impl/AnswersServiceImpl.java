package com.feather.algebraback.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feather.algebraback.domain.Answers;
import com.feather.algebraback.mapper.AnswersMapper;
import com.feather.algebraback.service.AnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 惊鸿之羽
 */
@Service
public class AnswersServiceImpl extends ServiceImpl<AnswersMapper, Answers> implements AnswersService {
    @Autowired
    AnswersMapper answersMapper;
}
