package com.prototype.common.annotation;

import java.lang.annotation.*;

/**
 * User: hejm
 * Date: 2016/6/24
 * Time: 17:47
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiResponseBody {

    boolean required() default true;
}
