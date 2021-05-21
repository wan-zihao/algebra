package com.feather.algebraback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feather.algebraback.config.security.AdminDetailsServiceImpl;
import com.feather.algebraback.config.security.JwtTokenUtil;
import com.feather.algebraback.domain.Admin;
import com.feather.algebraback.domain.RespBean;
import com.feather.algebraback.mapper.AdminMapper;
import com.feather.algebraback.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 惊鸿之羽
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("adminDetailsServiceImpl")
    private AdminDetailsServiceImpl adminDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public Admin getUserByUserName(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>()
                .eq("admin_id", username));
    }

    @Override
    public RespBean login(String adminId, String password) {
        //登录
        UserDetails adminDetails = adminDetailsService.loadUserByUsername(adminId);
        if (adminDetails == null || !(adminDetails.getPassword().equals(password))) {
            return RespBean.error("管理员ID或密码不正确");
        }
        if (!adminDetails.isEnabled()) {
            return RespBean.error("账号被禁用，请联系超级管理员");
        }
        //更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(adminDetails, null, adminDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //生成token
        String token = jwtTokenUtil.generateToken(adminDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.success("登录成功", tokenMap);
    }
}
