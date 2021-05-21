package com.feather.algebraback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feather.algebraback.domain.Question;
import com.feather.algebraback.domain.VO.QuestionUserVO;
import com.feather.algebraback.mapper.QuestionMapper;
import com.feather.algebraback.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 惊鸿之羽
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    @Override
    public Page<Question> selectQuestionPage(Integer currentPage, int pageSize, String search) {
        Page<Question> questionPage = new Page<>(currentPage, pageSize);
        //默认不带参数查询
        if (search == null || search.length() == 0){
            return this.page(questionPage, null);
        }
        return this.page(questionPage, new QueryWrapper<Question>().like("question_title",search).or().like("question_content",search));
    }

    @Override
    public Page<QuestionUserVO> selectQuestionUserPage(Page<QuestionUserVO> page, String search) {
        return page.setRecords(this.baseMapper.selectQuestionUserPage(page, search));
    }
}
