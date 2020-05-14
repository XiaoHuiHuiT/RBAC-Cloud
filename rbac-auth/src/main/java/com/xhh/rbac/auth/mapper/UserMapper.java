package com.xhh.rbac.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xhh.rbac.common.entity.system.SystemUser;

public interface UserMapper extends BaseMapper<SystemUser> {
    /**
     　　* @描述: 方法用于通过用户名查找用户信息
     　　* @参数 ${tags}
     　　* @返回值 ${return_type}
     　　* @throws
     　　* @作者: 小灰灰
     　　* @时间 2020-05-15 1:33
     */
    SystemUser findByName(String username);
}