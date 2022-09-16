package com.vote.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 正则匹配HK身份证
 * @author qinxuening
 * @date 2022/9/14 23:38
 */
public class HkIDValidatorClass implements ConstraintValidator<HkIDValidator,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String regFormat = "^[A-Za-z]\\d{6}\\([\\d]\\)$";
        return Pattern.matches(regFormat, value);
    }
}
