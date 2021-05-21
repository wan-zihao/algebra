package com.feather.algebraback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feather.algebraback.domain.Answer;
import com.feather.algebraback.domain.VO.AnswerUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 惊鸿之羽
 */
@Mapper
public interface AnswerMapper extends BaseMapper<Answer> {

    /**
     * 查询带有发表者的评论
     *
     * @param answerUserVOPage
     * @param id
     * @return
     */
    @Select("select answer.*, user.username from user, answer, question where user.user_id = answer.user_id and question.question_id = answer.question_id and question.question_id = #{id} order by time desc")
    List<AnswerUserVO> selectQuestionUserPage(Page<AnswerUserVO> answerUserVOPage, Integer id);
}
