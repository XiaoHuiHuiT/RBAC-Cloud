package com.xhh.rbac.server.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.xhh.rbac.common.entity.QueryRequest;
import com.xhh.rbac.common.entity.RbacResponse;
import com.xhh.rbac.common.entity.system.SystemUser;
import com.xhh.rbac.common.exception.RbacException;
import com.xhh.rbac.common.utils.RbacUtil;
import com.xhh.rbac.server.system.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @title: 请求参数分为普通参数和实体对象参数，所以它们的校验也分为两种。@Valid对应实体对象传参时的参数校验；@Validated对应普通参数的校验。
 * @projectName rbac-register
 * @描述: TODO
 * @作者: 小灰灰
 * @创建时间 2020-05-1511:24
 */
@Slf4j
@Validated// @Validated的作用是让@NotBlank注解生效
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:view')")
    public RbacResponse userList(QueryRequest queryRequest, SystemUser user) {
        Map<String, Object> dataTable = RbacUtil.getDataTable(userService.findUserDetail(user, queryRequest));
        return new RbacResponse().data(dataTable);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:add')")
    public void addUser(@Valid SystemUser user) throws RbacException {
        try {
            this.userService.createUser(user);
        } catch (Exception e) {
            String message = "新增用户失败";
            log.error(message, e);
            throw new RbacException(message);
        }
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('user:update')")
    public void updateUser(@Valid SystemUser user) throws RbacException {
        try {
            this.userService.updateUser(user);
        } catch (Exception e) {
            String message = "修改用户失败";
            log.error(message, e);
            throw new RbacException(message);
        }
    }

    @DeleteMapping("/{userIds}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public void deleteUsers(@NotBlank(message = "{required}") @PathVariable String userIds) throws RbacException {
        try {
            String[] ids = userIds.split(StringPool.COMMA);
            this.userService.deleteUsers(ids);
        } catch (Exception e) {
            String message = "删除用户失败";
            log.error(message, e);
            throw new RbacException(message);
        }
    }
}
