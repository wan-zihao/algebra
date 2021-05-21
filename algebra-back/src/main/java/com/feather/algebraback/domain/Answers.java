package com.feather.algebraback.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 惊鸿之羽
 */
@Data
@TableName("answers")
public class Answers {
    @TableId(value = "answers_id",type = IdType.AUTO)
    private Integer answersId;
    private Integer answerId;
    private Integer userId;
    private String answersContent;
    private String time;
}
