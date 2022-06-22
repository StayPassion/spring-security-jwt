package com.test.springsecurityjwt;

import com.test.springsecurityjwt.system.service.RoleService;
import com.test.springsecurityjwt.system.service.UserRoleService;
import com.test.springsecurityjwt.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringSecurityJwtApplication {

    private final RoleService roleService;
    private final UserService userService;
    private final UserRoleService userRoleService;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtApplication.class, args);
    }


//    @Override
//    public void run(String... args) throws Exception {
//        //初始化角色信息
//        for (RoleType roleType : RoleType.values()) {
//            roleService.save(new Role(roleType.getName(), roleType.getDescription()));
//        }
//        //初始化一个 admin 用户
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        User user = User.builder().enable(true).fullName("admin").userName("root").password(bCryptPasswordEncoder.encode("root")).build();
//        userService.save(user);
//        Role role = roleService.findByName(RoleType.ADMIN.getName()).get();
//        userRoleService.save(new UserRole(user.getId(), role.getId()));
//    }
}
