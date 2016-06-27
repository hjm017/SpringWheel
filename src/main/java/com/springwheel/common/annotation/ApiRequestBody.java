package com.springwheel.common.annotation;

import java.lang.annotation.*;

/**
 * @author hjm
 * @Time 2016/6/25 8:21.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiRequestBody {

    boolean required() default true;
}
