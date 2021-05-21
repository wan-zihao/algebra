package com.feather.algebraback.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.feather.algebraback.domain.RespBean;
import com.feather.algebraback.domain.User;

/**
 * @author 惊鸿之羽
 */
public interface UserService extends IService<User> {

    /**
     * 登录方法
     * @param username
     * @param password
     * @return
     */
    RespBean login(String username, String password);

    /**
     * 根据用户名获得用户
     * @param username
     * @return
     */
    User getUserByUserName(String username);

    /**
     * 添加用户，并且密码加密
     * @param entity
     * @return
     */
    public boolean addUser(User entity);

    /**
     * 判断当前用户名是否已经存在
     * @param username
     * @return
     */
    public boolean existUsername(String username);

    /**
     * 根据页数查询用户
     *
     * @param currentPage
     * @return
     */
    public Page<User> selectUserPage(int currentPage, int pageSize, String search);

    /**
     * 发送邮件
     *
     * @param email
     * @return
     */
    RespBean mail(String email);

    /**
     * 注册
     *
     * @param user
     * @param code
     * @return
     */
    RespBean register(User user, String code);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    RespBean updateUser(User user);
}
