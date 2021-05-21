package com.feather.algebraback.config.security;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feather.algebraback.domain.Admin;
import com.feather.algebraback.domain.User;
import com.feather.algebraback.service.impl.AdminServiceImpl;
import com.feather.algebraback.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("adminDetailsServiceImpl")
public class AdminDetailsServiceImpl  implements UserDetailsService {
    @Autowired
    private AdminServiceImpl adminService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Admin admin = adminService.getUserByUserName(s);
        if (admin != null){
            return admin;
        }
        return null;
    }
}
