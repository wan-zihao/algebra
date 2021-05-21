package com.feather.algebraback.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.feather.algebraback.domain.Message;
import com.feather.algebraback.domain.RespBean;
import com.feather.algebraback.domain.User;
import com.feather.algebraback.service.impl.MessageServiceImpl;
import com.feather.algebraback.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @author 惊鸿之羽
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    MessageServiceImpl messageService;

    /**
     * 用户登录
     *
     * @param user 登录输入的用户名和密码封装的用户对象
     * @return 登录信息
     */
    @RequestMapping("/user/login")
    public RespBean login(User user) {
        return userService.login(user.getUsername(), user.getPassword());
    }

    /**
     * 邮件发送
     *
     * @param email
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/mail")
    public RespBean mail(String email) throws InterruptedException {
        return userService.mail(email);
    }

    /**
     * 用户注册
     *
     * @param user 用户对象
     * @param code 用户输入的验证码
     * @return
     */

    @RequestMapping("/register")
    public RespBean register(User user, String code) {
        return userService.register(user, code);
    }

    /**
     * 获得当前用户信息
     *
     * @param principal
     * @return
     */
    @GetMapping("/user/info")
    public RespBean getUserInfo(Principal principal) {
        if (principal == null) {
            return RespBean.error("尚未登录,请先登录",null);
        }
        User user = userService.getUserByUserName(principal.getName());
        //不返回密码
        user.setPassword(null);
        return RespBean.success(null,user);
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping("/user/exit")
    public RespBean exit() {
        return RespBean.success("注销成功！");
    }

    /**
     * 分页查询所有用户
     *
     * @param currentPage 查询的当前页码
     * @return 返回查询的用户数据
     */
    @GetMapping(value = "/user")
    public RespBean selectUserPage(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                   @RequestParam(value = "pageSize", defaultValue = "7") Integer pageSize,
                                   @RequestParam(value = "search") String search) {
        return RespBean.success(null,userService.selectUserPage(currentPage,pageSize,search));
    }

    /**
     * 添加用户
     *
     * @param user 添加的用户信息封装对象
     * @return 返回添加信息
     */
    @PostMapping("/user")
    public RespBean insertUser(User user) {
        if (userService.addUser(user)) {
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    /**
     * 更新用户信息
     *
     * @param user 更新后数据封装
     * @param id   路径传递更新的用户id
     * @return 返回更新信息
     */
    @PutMapping("/user/{id}")
    public RespBean updateUser(User user,
                               @PathVariable("id") Integer id) {
        user.setUserId(id);
        return userService.updateUser(user);
    }

    /**
     * 批量删除用户
     *
     * @param ids 前端传递来的id集合
     * @return 返回删除信息
     */
    @Transactional
    @DeleteMapping("/user")
    public RespBean deleteUser(@RequestParam("multipleSelection") List<String> ids) {
        StringBuffer sb = new StringBuffer();
        sb.append("id为");
        for(String id : ids){
            //依次删除用户
            if (!userService.remove(new QueryWrapper<User>().eq("user_id", id))) {
                //删除失败手动回滚事务
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return RespBean.error("删除失败");
            }
            sb.append(" "+id);
        }
        sb.append("的用户被删除了");
        //记录操作日志
        messageService.save(new Message(new String(sb)));
        return RespBean.success("删除成功");
    }
}
