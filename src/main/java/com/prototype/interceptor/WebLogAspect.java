package com.prototype.interceptor;

import com.prototype.web.PropComponent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author hjm
 * @Time 2016/6/4 23:36.
 */
@Aspect
@Component
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger("request");

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.prototype.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        if ("on".equals(PropComponent.getProp().getRequestSwitch())) {
            logger.info("********* HTTP 1.1 ********");
            logger.info("URL : " + request.getRequestURL().toString());
            logger.info("HTTP_METHOD : " + request.getMethod());
            logger.info("IP : " + request.getRemoteAddr());
            logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        }


    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        if ("on".equals(PropComponent.getProp().getRequestSwitch())) {
            // 处理完请求，返回内容
            logger.info("RESPONSE : " + ret);
            logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()) + "ms");
        }
    }

}
