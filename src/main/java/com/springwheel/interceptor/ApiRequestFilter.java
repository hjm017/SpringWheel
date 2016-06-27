package com.springwheel.interceptor;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.springwheel.common.helper.BodyReaderHttpServletRequestWrapper;
import com.springwheel.common.helper.HttpHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 *  API方法过滤器
 * 1、签名验证（checkSign）
 * 2、权限拦截（checkAuthn）
 * 3、参数校验（checkParam）
 *
 * @author hjm
 * @Time 2016/6/28 0:10.
 */
@WebFilter(filterName="apiFilter",urlPatterns="/rest/*")
public class ApiRequestFilter implements Filter {

    private Logger logger       = LoggerFactory.getLogger(getClass());

    private static ObjectMapper objectMapper = new ObjectMapper();

    private ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();

    static {
        //空对象不出错
        //carmelMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        //忽略未知属性
//         objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //驼峰属性转下划线属性
        objectMapper
                .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        //空格转空字符
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {

            @Override
            public void serialize(Object paramT, JsonGenerator paramJsonGenerator,
                                  SerializerProvider paramSerializerProvider) throws IOException,
                    JsonProcessingException {
                paramJsonGenerator.writeString("");
            }
        });

    }

    //****************************************//
    //                                        //
    //            Override Method             //
    //                                        //
    //****************************************//
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // 防止流读取一次后就没有了, 所以需要将流继续写出去
        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
        String reqBody = HttpHelper.getBodyString(requestWrapper);
        HashMap<String, Object> paramMap = objectMapper.readValue(reqBody, HashMap.class);
        logger.info("请求body："+reqBody);

        threadLocal.set(reqBody);
        chain.doFilter(requestWrapper, response);


    }

    @Override
    public void destroy() {

    }

    //****************************************//
    //                                        //
    //            define Method               //
    //                                        //
    //****************************************//
}
