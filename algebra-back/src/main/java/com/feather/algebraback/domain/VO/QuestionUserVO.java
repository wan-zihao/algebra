package com.feather.algebraback.domain.VO;

import lombok.Data;

/**
 * @author 惊鸿之羽
 */
@Data
public class QuestionUserVO {
    private Integer questionId;
    private Integer userId;
    private String questionTitle;
    private String questionContent;
    private String time;
    private String username;
}
