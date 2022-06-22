package com.test.springsecurityjwt.system.validator;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @ClassName FullName
 * @Author FuKua
 **/

@Documented
@Target({FIELD, PARAMETER})
@Constraint(validatedBy = FullNameValidator.class)
@Retention(RUNTIME)
public @interface FullName {
    String message() default "姓名格式错误";

    Class[] groups() default {};

    Class[] payload() default {};
}
