package com.feather.algebraback.domain.VO;

import lombok.Data;

/**
 * @author 惊鸿之羽
 */
@Data
public class AlgorithmUserVO {
    private Integer algorithmId;
    private String uuid;
    private Integer userId;
    private String algorithmContent;
    private String uploadTime;
    private String username;
}
