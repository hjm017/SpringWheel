package com.springwheel.common.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * @author hjm
 * @Time 2016/6/25 8:23.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@ApiResponseBody
public @interface ApiController {

    String value() default "";
}
