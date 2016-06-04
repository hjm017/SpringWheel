package com.prototype.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.AnnotatedElement;
import java.util.ResourceBundle;

/**
 * 参数校验拦截器
 *
 * @author hjm
 * @Time 2016/5/2 10:56.
 */
public class ValidatorInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final String DEFAULT_MESSAGE = "validator";


    private String message = DEFAULT_MESSAGE;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        logger.info("进入ValidatorInterceptor拦截器");

        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            MethodParameter[] params = method.getMethodParameters();
            for (MethodParameter param : params) {
                AnnotatedElement element = param.getAnnotatedElement();
                System.out.println(param);
            }
        }

        ResourceBundle resourceBundle = ResourceBundle.getBundle(message);
        resourceBundle.getString("NOT_NULL");


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {

    }


}
