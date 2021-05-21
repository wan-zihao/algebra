package com.feather.algebraback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feather.algebraback.domain.Message;
import com.feather.algebraback.domain.User;
import com.feather.algebraback.mapper.MessageMapper;
import com.feather.algebraback.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 惊鸿之羽
 */
@Slf4j
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Override
    public Page<Message> selectMessagePage(Integer currentPage, Integer pageSize) {
        //分页查询
        Page<Message> messagePage = new Page<>(currentPage, pageSize);
        return this.page(messagePage, new QueryWrapper<Message>().orderByDesc("message_id"));
    }
}
