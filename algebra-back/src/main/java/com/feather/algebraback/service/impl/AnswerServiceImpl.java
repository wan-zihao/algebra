package com.feather.algebraback.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feather.algebraback.domain.Answer;
import com.feather.algebraback.domain.VO.AnswerUserVO;
import com.feather.algebraback.mapper.AnswerMapper;
import com.feather.algebraback.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 惊鸿之羽
 */
@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements AnswerService {

    @Autowired
    AnswerMapper answerMapper;

    @Override
    public Page<AnswerUserVO> selectQuestionUserPage(Page<AnswerUserVO> answerUserVOPage, Integer id) {
        return answerUserVOPage.setRecords(this.baseMapper.selectQuestionUserPage(answerUserVOPage, id));
    }
}
