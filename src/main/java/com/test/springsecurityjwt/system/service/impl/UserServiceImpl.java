package com.test.springsecurityjwt.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.ImmutableMap;
import com.test.springsecurityjwt.system.entity.Role;
import com.test.springsecurityjwt.system.entity.User;
import com.test.springsecurityjwt.system.entity.UserRole;
import com.test.springsecurityjwt.system.enums.RoleType;
import com.test.springsecurityjwt.system.exception.RoleNotFoundException;
import com.test.springsecurityjwt.system.exception.UserNameAlreadyExistException;
import com.test.springsecurityjwt.system.exception.UserNameNotFoundException;
import com.test.springsecurityjwt.system.mapper.UserMapper;
import com.test.springsecurityjwt.system.service.RoleService;
import com.test.springsecurityjwt.system.service.UserRoleService;
import com.test.springsecurityjwt.system.service.UserService;
import com.test.springsecurityjwt.system.web.representation.UserRepresentation;
import com.test.springsecurityjwt.system.web.request.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ***
 * @since 2022-06-01
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    public static final String USERNAME = "username:";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;
    private final UserRoleService userRoleService;

    @Override
    public void save(UserRegisterRequest userRegisterRequest) {
        ensureUserNameNotExist(userRegisterRequest.getUserName());
        User user = userRegisterRequest.toUser();
        user.setPassword(bCryptPasswordEncoder.encode(userRegisterRequest.getPassword()));
        this.save(user);
        //给用户绑定两个角色：用户和管理者
        Role studentRole = roleService.findByName(RoleType.USER.getName()).orElseThrow(() -> new RoleNotFoundException(ImmutableMap.of("roleName", RoleType.USER.getName())));
        Role managerRole = roleService.findByName(RoleType.MANAGER.getName()).orElseThrow(() -> new RoleNotFoundException(ImmutableMap.of("roleName", RoleType.MANAGER.getName())));
        userRoleService.save(new UserRole(user.getId(), studentRole.getId()));
        userRoleService.save(new UserRole(user.getId(), managerRole.getId()));

    }


    private void ensureUserNameNotExist(String userName) {
        Optional<User> userOptional = findByUserName(userName);
        if (userOptional.isPresent()) {
            throw new UserNameAlreadyExistException(ImmutableMap.of(USERNAME, userName));
        }
    }

    @Override
    public Page<User> getAll(Page page) {
        return this.page(page);
    }

    @Override
    public void deleteUser(String username) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUserName, username);
        this.remove(userLambdaQueryWrapper);

    }

    @Override
    public Optional<User> findByUserName(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userName);
        User user = this.getOne(queryWrapper);
        if (null!=user){
            List<UserRole> userRoleList = userRoleService.lambdaQuery().eq(UserRole::getUserId, user.getId() ).list();
            List<Long> roleIdList = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList());
            List<Role> roleList = roleService.lambdaQuery().in(Role::getId, roleIdList).list();
            user.setRoles(roleList);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public User find(String userName) {
        return findByUserName(userName).orElseThrow(() -> new UserNameNotFoundException(ImmutableMap.of(USERNAME, userName)));
    }

    @Override
    public boolean check(String currentPassword, String password) {
        return this.bCryptPasswordEncoder.matches(currentPassword,password);
    }
}
