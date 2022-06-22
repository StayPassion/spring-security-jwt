package com.test.springsecurityjwt.system.web.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.test.springsecurityjwt.system.entity.User;
import com.test.springsecurityjwt.system.service.UserService;
import com.test.springsecurityjwt.system.web.representation.UserRepresentation;
import com.test.springsecurityjwt.system.web.request.UserRegisterRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * UserController
 * @author fukua
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/users")
@Api(tags = "用户")
public class UserController {

    private final UserService userService;


    @PostMapping("/sign-up")
    @ApiOperation("用户注册")
    public ResponseEntity<Void> signUp(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        userService.save(userRegisterRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @ApiOperation("获取所有用户的信息（分页）")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
    public ResponseEntity<Page<UserRepresentation>> getAllUser(Page page) {
        Page<User> allUser = userService.getAll(page);
        List<UserRepresentation> list = BeanUtil.copyToList(allUser.getRecords(), UserRepresentation.class);
        Page<UserRepresentation> data = new Page<>(allUser.getCurrent(),allUser.getSize(),allUser.getTotal());
        data.setRecords(list);
        return ResponseEntity.ok().body(data);
    }

//    @PutMapping
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
//    @ApiOperation("更新用户")
//    public ResponseEntity<Void> update(@RequestBody @Valid UserUpdateRequest userUpdateRequest) {
//        userService.update(userUpdateRequest);
//        return ResponseEntity.ok().build();
//    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ApiOperation("根据用户名删除用户")
    public ResponseEntity<Void> deleteUserByUserName(@RequestParam("username") String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok().build();
    }
}
