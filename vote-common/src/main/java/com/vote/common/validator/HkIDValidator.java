package com.vote.common.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 香港身份证校验注解
 * @author qinxuening
 * @date 2022/9/14 23:35
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = HkIDValidatorClass.class)
public @interface HkIDValidator {
    String value() default "";

    String message() default "ID card number is incorrect";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
