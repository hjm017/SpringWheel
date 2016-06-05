package com.prototype.interceptor;

import com.prototype.common.annotation.ParamCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
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
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Class<?> clazz = handlerMethod.getBeanType();
            Method[] methods = clazz.getMethods();
            String uri = httpServletRequest.getRequestURI(); //获取的URI如：/user/getuser
            String finalURI = uri.substring(uri.lastIndexOf("/"));
            boolean needValid = needValid(finalURI, methods);
            if (needValid) {
                logger.info("进行参数校验");
                checkParam(methods, finalURI);
            }
        }

        ResourceBundle resourceBundle = ResourceBundle.getBundle(message);
        resourceBundle.getString("NOT_NULL");


        return true;
    }

    /**
     * 检验参数
     *
     * @param methods
     * @param finalURI
     */
    private void checkParam(Method[] methods, String finalURI) {
    }

    /**
     * 检验该方法是否需要验证
     *
     * @return
     */
    private boolean needValid(String finalURI, Method[] methods) throws Exception {
        for (Method method : methods) {
            if (method.isAnnotationPresent(RequestMapping.class)) {
                Annotation p = method.getAnnotation(RequestMapping.class);
                Method m = p.getClass().getDeclaredMethod("value", null);
                String[] strs = (String[]) m.invoke(p, null);
                if (strs[0].equals(finalURI)) {
                    ParamCheck needVerify = method.getAnnotation(ParamCheck.class);
                    //如果没有@ParamCheck注解,不进行参数验证
                    if (needVerify == null) {
                        return false;
                    } else {
                        break;
                    }
                } else {
                    continue;
                }
            }
        }

        return true;
    }



    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {

    }


}
