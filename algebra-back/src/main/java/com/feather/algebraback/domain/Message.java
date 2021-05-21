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
@TableName("message")
public class Message {
    @TableId(value = "message_id",type = IdType.AUTO)
    private Integer messageId;
    private String messageContent;
    private String time;

    public Message(String messageContent){
        this.messageContent = messageContent;
    }
}
