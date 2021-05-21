package com.feather.algebraback.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.feather.algebraback.domain.Answer;
import com.feather.algebraback.domain.VO.AnswerUserVO;
import com.feather.algebraback.domain.VO.QuestionUserVO;

/**
 * @author 惊鸿之羽
 */
public interface AnswerService extends IService<Answer> {

    /**
     * 查询带有发表者的评论
     *
     * @param answerUserVOPage
     * @param id
     * @return
     */
    Page<AnswerUserVO> selectQuestionUserPage(Page<AnswerUserVO> answerUserVOPage, Integer id);
}
