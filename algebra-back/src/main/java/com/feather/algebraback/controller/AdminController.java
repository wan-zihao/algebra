package com.feather.algebraback.controller;

import com.feather.algebraback.domain.Admin;
import com.feather.algebraback.domain.RespBean;
import com.feather.algebraback.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 惊鸿之羽
 */
@RestController
public class AdminController {

    @Autowired
    AdminServiceImpl adminService;

    /**
     * 管理员登录
     * @param admin 管理员登录信息封装
     * @return  返回封路信息
     */
    @RequestMapping("/admin/login")
    public RespBean login(Admin admin){
        return adminService.login(admin.getAdminId(),admin.getPassword());
    }

}
