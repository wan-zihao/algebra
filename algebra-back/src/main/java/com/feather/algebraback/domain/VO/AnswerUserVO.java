package com.feather.algebraback.domain.VO;

import lombok.Data;

/**
 * @author 惊鸿之羽
 */
@Data
public class AnswerUserVO {
    private Integer answerId;
    private Integer questionId;
    private Integer userId;
    private String answerContent;
    private String time;
    private String username;
}
