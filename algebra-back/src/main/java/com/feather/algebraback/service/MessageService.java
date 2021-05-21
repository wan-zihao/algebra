package com.feather.algebraback.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.feather.algebraback.domain.Message;

/**
 * @author 惊鸿之羽
 */
public interface MessageService extends IService<Message> {

    /**
     * 查询日志分页
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<Message> selectMessagePage(Integer currentPage, Integer pageSize);
}
