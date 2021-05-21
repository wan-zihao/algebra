package com.feather.algebraback.controller;

import com.feather.algebraback.domain.Message;
import com.feather.algebraback.domain.RespBean;
import com.feather.algebraback.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 惊鸿之羽
 */
@RestController
public class MessageController {

    static MessageServiceImpl messageService;

    //构造注入静态成员变量
    @Autowired
    public MessageController(MessageServiceImpl messageService) {
        MessageController.messageService = messageService;
    }

    /**
     * 分页查询所有日志
     *
     * @param currentPage 查询的当前页码
     * @return 返回查询的日志数据
     */
    @GetMapping("/message")
    public RespBean selectMessagePage(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                      @RequestParam(value = "pageSize", defaultValue = "7") Integer pageSize) {
        return RespBean.success(null, messageService.selectMessagePage(currentPage, pageSize));
    }

    /**
     * 添加日志
     *
     * @param message 添加的日志信息封装对象
     * @return 返回添加信息
     */
    @PostMapping("/message")
    public void insertMessage(Message message) {
        messageService.save(message);
    }

}
