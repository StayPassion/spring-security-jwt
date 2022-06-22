package com.test.springsecurityjwt.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.springsecurityjwt.system.entity.UserRole;
import com.test.springsecurityjwt.system.mapper.UserRoleMapper;
import com.test.springsecurityjwt.system.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ***
 * @since 2022-06-01
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
