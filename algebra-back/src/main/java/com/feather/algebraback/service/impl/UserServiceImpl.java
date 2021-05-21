package com.feather.algebraback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feather.algebraback.config.security.JwtTokenUtil;
import com.feather.algebraback.config.security.UserDetailsServiceImpl;
import com.feather.algebraback.domain.Admin;
import com.feather.algebraback.domain.Message;
import com.feather.algebraback.domain.RespBean;
import com.feather.algebraback.domain.User;
import com.feather.algebraback.mapper.AdminMapper;
import com.feather.algebraback.mapper.UserMapper;
import com.feather.algebraback.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 惊鸿之羽
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    MessageServiceImpl messageService;

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsServiceImpl userDetailsService;

    /**
     * 密码解析器
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * jwt工具类
     */
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * token头
     */
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 发送邮件的对象
     */
    @Autowired
    JavaMailSenderImpl mailSender;

    /**
     * 发邮件的邮箱地址
     */
    @Value("${spring.mail.username}")
    private String form;

    /**
     * Lock锁
     */
    private Lock lock = new ReentrantLock();

    /**
     * 存放邮箱和验证码的键值对
     */
    public Map<String, String> codeMap = new HashMap<>();

    /**
     * 登录之后返回token
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public RespBean login(String username, String password) {
        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确");
        }
        if (!userDetails.isEnabled()) {
            return RespBean.error("账号被禁用，请联系管理员");
        }
        //更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.success("登录成功", tokenMap);
    }

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    @Override
    public User getUserByUserName(String username) {
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .eq("username", username));
        return user;
    }

    /**
     * 添加用户，并且密码将被加密
     *
     * @param user
     * @return
     */
    @Override
    public boolean addUser(User user) {
        try {
            String encodePassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodePassword);
            userMapper.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        messageService.save(new Message("添加了用户名为" + user.getUsername() + "的用户"));
        return true;
    }

    /**
     * 注册时判断用户名是否已存在
     *
     * @param username
     * @return
     */
    @Override
    public boolean existUsername(String username) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("admin_id", username));
        if (user != null || admin != null) {
            return true;
        }
        return false;
    }

    /**
     * 分页查询用户对象
     *
     * @param currentPage
     * @return
     */
    @Override
    public Page<User> selectUserPage(int currentPage, int pageSize, String search) {
        //分页查询
        Page<User> userPage = new Page<>(currentPage, pageSize);
        if (search == null || search.length() == 0){
            return this.page(userPage, null);
        }
        return this.page(userPage, new QueryWrapper<User>().like("username",search));
    }

    /**
     * 邮件发送
     *
     * @param email
     * @return
     */
    @Override
    public RespBean mail(String email) {
        //加锁
        lock.lock();
        try {
            Random random = new Random();
            int code = random.nextInt(899999) + 100000;
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("注册验证码");
            message.setText("欢迎您的到来，您的注册验证码是：" + code);
            message.setFrom(form);
            message.setTo(email);
            codeMap.put(email, code + "");
            mailSender.send(message);
        } catch (Exception e) {
            return RespBean.error("邮件发送失败,请检查邮箱是否正确");
        } finally {
            //无论是否出现异常都必须解锁
            lock.unlock();
        }
        return RespBean.success("邮件发送成功");
    }

    /**
     * 注册
     *
     * @param user
     * @param code
     * @return
     */
    @Override
    public RespBean register(User user, String code) {
        if (codeMap.get(user.getEmail()) == null) {
            return RespBean.error("邮箱错误，请重新填写");
        }
        if (!code.equals(codeMap.get(user.getEmail()))) {
            return RespBean.error("验证码错误，请重新填写");
        }
        if (this.existUsername(user.getUsername())) {
            return RespBean.error("用户名已存在，请重新填写");
        }
        if (this.addUser(user)) {
            return RespBean.success("注册成功");
        }
        return RespBean.error("出现错误，请重新尝试");
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @Override
    public RespBean updateUser(User user) {
        //密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (update(user, new UpdateWrapper<User>().eq("user_id", user.getUserId()))) {
            //记录操作日志
            messageService.save(new Message("id为" + user.getUserId() + "的用户信息被更新了"));
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
