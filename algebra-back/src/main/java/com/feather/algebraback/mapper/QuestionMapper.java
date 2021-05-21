package com.feather.algebraback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feather.algebraback.domain.Question;
import com.feather.algebraback.domain.VO.QuestionUserVO;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 惊鸿之羽
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

    /**
     * 返回带有发布者的文章数据
     *
     * @param questionUserPage
     * @param search
     * @return
     */
    @Select("select question.*,user.username from user,question where user.user_id = question.user_id and question_title like '%${search}%'")
    List<QuestionUserVO> selectQuestionUserPage(Page<QuestionUserVO> questionUserPage, String search);
}
