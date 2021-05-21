package com.feather.algebraback.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feather.algebraback.domain.Admin;
import com.feather.algebraback.domain.RespBean;

/**
 * @author 惊鸿之羽
 */
public interface AdminService extends IService<Admin> {

    /**
     * 管理员登录
     * @param adminId   前端传递管理员id
     * @param password  前端传递管理员密码
     * @return
     */
    RespBean login(String adminId, String password);
}
