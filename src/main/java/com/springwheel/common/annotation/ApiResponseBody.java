package com.springwheel.common.annotation;

import java.lang.annotation.*;

/**
 * @author hjm
 * @Time 2016/6/25 8:22.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiResponseBody {

    boolean required() default true;
}
