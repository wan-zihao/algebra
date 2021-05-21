package com.feather.algebraback.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 惊鸿之羽
 */
@Data
@TableName("file")
public class Algorithm {
    @TableId(value = "algorithm_id",type = IdType.AUTO)
    private Integer algorithmId;
    private String uuid;
    private Integer userId;
    private String algorithmContent;
    private String uploadTime;
    
    public Algorithm(String uuid, Integer userId, String algorithmContent){
        this.uuid = uuid;
        this.userId = userId;
        this.algorithmContent = algorithmContent;
    }
}
