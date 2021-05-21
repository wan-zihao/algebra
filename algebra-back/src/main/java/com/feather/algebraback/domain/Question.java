package com.feather.algebraback.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 惊鸿之羽
 */
@Data
@TableName("question")
public class Question {

    @TableId(value = "question_id",type = IdType.AUTO)
    private Integer questionId;
    private Integer userId;
    private String questionTitle;
    private String questionContent;
    private String time;

}
