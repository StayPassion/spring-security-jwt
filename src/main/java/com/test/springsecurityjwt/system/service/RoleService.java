package com.test.springsecurityjwt.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.springsecurityjwt.system.entity.Role;

import java.util.Optional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ***
 * @since 2022-06-01
 */
public interface RoleService extends IService<Role> {

    Optional<Role> findByName(String roleName);

}
