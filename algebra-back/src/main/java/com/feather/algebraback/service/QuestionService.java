package com.feather.algebraback.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.feather.algebraback.domain.Question;
import com.feather.algebraback.domain.VO.QuestionUserVO;

/**
 * @author 惊鸿之羽
 */
public interface QuestionService extends IService<Question> {

    /**
     * 分页查询问题
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<Question> selectQuestionPage(Integer currentPage, int pageSize, String search);

    Page<QuestionUserVO> selectQuestionUserPage(Page<QuestionUserVO> page, String search);
}
