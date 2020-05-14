package com.xhh.rbac.auth.service;

import com.xhh.rbac.auth.manager.UserManager;
import com.xhh.rbac.common.entity.RbacAuthUser;
import com.xhh.rbac.common.entity.system.SystemUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/*
主要的逻辑就是通过用户名从数据库中获取用户信息SystemUser和用户权限集合，
然后通过transSystemUserToAuthUser方法将SystemUser里的值拷贝到RbacAuthUser中并返回。
*/
@Service
public class RbacUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserManager userManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser systemUser = userManager.findByName(username);
        if (systemUser != null) {
            String permissions = userManager.findUserPermissions(systemUser.getUsername());
            boolean notLocked = false;
            if (StringUtils.equals(SystemUser.STATUS_VALID, systemUser.getStatus()))
                notLocked = true;
            RbacAuthUser authUser = new RbacAuthUser(systemUser.getUsername(), systemUser.getPassword(), true, true, true, notLocked,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));

            BeanUtils.copyProperties(systemUser, authUser);
            return authUser;
        } else {
            throw new UsernameNotFoundException("");
        }
    }

    private RbacAuthUser transSystemUserToAuthUser(RbacAuthUser authUser, SystemUser systemUser) {
        authUser.setAvatar(systemUser.getAvatar());
        authUser.setDeptId(systemUser.getDeptId());
        authUser.setDeptName(systemUser.getDeptName());
        authUser.setEmail(systemUser.getEmail());
        authUser.setMobile(systemUser.getMobile());
        authUser.setRoleId(systemUser.getRoleId());
        authUser.setRoleName(systemUser.getRoleName());
        authUser.setSex(systemUser.getSex());
        authUser.setUserId(systemUser.getUserId());
        authUser.setLastLoginTime(systemUser.getLastLoginTime());
        authUser.setDescription(systemUser.getDescription());
        authUser.setStatus(systemUser.getStatus());
        return authUser;
    }
}