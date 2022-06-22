package com.test.springsecurityjwt.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.test.springsecurityjwt.system.entity.User;
import com.test.springsecurityjwt.system.web.representation.UserRepresentation;
import com.test.springsecurityjwt.system.web.request.UserRegisterRequest;

import java.util.Optional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ***
 * @since 2022-06-01
 */
public interface UserService extends IService<User> {


    void save(UserRegisterRequest userRegisterRequest);

    Page<User> getAll(Page page);

    void deleteUser(String username);

    Optional<User> findByUserName(String userName);

    User find(String userName);

    boolean check(String currentPassword, String password);
}
