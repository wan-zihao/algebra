package com.feather.algebraback.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 惊鸿之羽
 */
@Data
@TableName("answer")
public class Answer {
    @TableId(value = "answer_id",type = IdType.AUTO)
    private Integer answerId;
    private Integer questionId;
    private Integer userId;
    private String answerContent;
    private String time;

}
