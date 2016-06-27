package com.springwheel.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * API方法拦截器
 * 1、签名验证（checkSign）
 * 2、权限拦截（checkAuthn）
 * 3、参数校验（checkParam）
 *
 * @author hjm
 * @Time 2016/5/2 10:56.
 */
@Component
public class ApiMethodInterceptor implements HandlerInterceptor {

    private Logger logger       = LoggerFactory.getLogger(getClass());

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static final String ENCODING = "UTF-8";


    //****************************************//
    //                                        //
    //            Override Method             //
    //                                        //
    //****************************************//

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        HandlerMethod method = (HandlerMethod) handler;
        byte[] buffer = IOUtils.toByteArray(request.getInputStream());
        String reqBody = new String(buffer, ENCODING);


        //验证签名
        checkSign(reqBody);

        //验证权限
        checkAuthn();


        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        logger.info("postHandle");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

    }



    //****************************************//
    //                                        //
    //            sign check                  //
    //                                        //
    //****************************************//

    private void checkSign(String reqBody) throws IOException {

        HashMap<String, Object> paramMap = objectMapper.readValue(reqBody, HashMap.class);

        logger.info("请求body:" + paramMap);
    }

    //****************************************//
    //                                        //
    //            authn check                 //
    //                                        //
    //****************************************//

    private void checkAuthn() {

    }



    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static void setObjectMapper(ObjectMapper objectMapper) {
        ApiMethodInterceptor.objectMapper = objectMapper;
    }

}
