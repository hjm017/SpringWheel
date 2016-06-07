package com.prototype.config;

import com.prototype.interceptor.ApiMethodInterceptor;
import com.prototype.interceptor.ValidationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.validation.Validator;

/**
 * 所有有关springmvc 的配置只需要继承WebMvcConfigurerAdapter重写里面的方法即可
 *
 * 1、覆盖spring boot默认生成的bean @Bean
 * 2、增加拦截器  addInterceptors
 * 3、添加参数解析器 addArgumentResolvers
 * 4、配置路径映射  configurePathMatch
 * 5、参数格式化工具（用于接收参数） addFormatters
 * 6、配置消息转换器(用于@RequestBody和@ResponseBody) configureMessageConverters
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Autowired(required = false)
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @Autowired(required = false)
    private Validator validator;

    /**
     * 增加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加国际化拦截器
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);

        //添加API拦截器
        ApiMethodInterceptor apiMethodInterceptor =new ApiMethodInterceptor();
        registry.addInterceptor(apiMethodInterceptor).addPathPatterns("/rest/*");

        //添加参数校验器
        ValidationInterceptor validatorInterceptor = new ValidationInterceptor(requestMappingHandlerAdapter);
        validatorInterceptor.setValidator(validator);
        registry.addInterceptor(validatorInterceptor).excludePathPatterns("/error");
    }


}
