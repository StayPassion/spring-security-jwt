package com.test.springsecurityjwt.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.springsecurityjwt.system.entity.Role;
import com.test.springsecurityjwt.system.mapper.RoleMapper;
import com.test.springsecurityjwt.system.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ***
 * @since 2022-06-01
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public Optional<Role> findByName(String roleName) {
        LambdaQueryWrapper<Role> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Role::getName,roleName);
        return Optional.ofNullable(this.getOne(lambdaQueryWrapper));
    }
}
