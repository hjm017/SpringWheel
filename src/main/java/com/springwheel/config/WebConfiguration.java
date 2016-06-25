package com.springwheel.config;

import com.springwheel.common.annotation.ApiHttpMessageConverter;
import com.springwheel.common.annotation.ApiRequestResponseBodyMethodProcessor;
import com.springwheel.common.constants.ApplicationConstant;
import com.springwheel.interceptor.ApiMethodInterceptor;
import com.springwheel.interceptor.ValidationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

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
    private Validator validator;



    @Bean
    public Validator validator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(ApplicationConstant.I18N_VALIDATION, ApplicationConstant.I18N_CODE);
        localValidatorFactoryBean.setValidationMessageSource(messageSource);
        return localValidatorFactoryBean;
    }

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
        ValidationInterceptor validatorInterceptor = new ValidationInterceptor();
        validatorInterceptor.setValidator(validator);
        registry.addInterceptor(validatorInterceptor).excludePathPatterns("/error");
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        List<HttpMessageConverter<?>> messageConverters = getHttpMessageConverters();
        argumentResolvers.add(new ApiRequestResponseBodyMethodProcessor(messageConverters));
    }


    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        List<HttpMessageConverter<?>> messageConverters = getHttpMessageConverters();
        returnValueHandlers.add(new ApiRequestResponseBodyMethodProcessor(messageConverters));
    }

    private List<HttpMessageConverter<?>> getHttpMessageConverters(){
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        ApiHttpMessageConverter apiHttpMessageConverter = new ApiHttpMessageConverter();
        messageConverters.add(apiHttpMessageConverter);
        return messageConverters;
    }

}
