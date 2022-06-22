package com.test.springsecurityjwt.system.exception;

import java.util.Map;

/**
 * @ClassName UserNameAlreadyExistException
 * @Author FuKua
 **/
public class UserNameAlreadyExistException extends BaseException{

    public UserNameAlreadyExistException(Map<String, Object> data) {
        super(ErrorCode.USER_NAME_ALREADY_EXIST, data);
    }
}
