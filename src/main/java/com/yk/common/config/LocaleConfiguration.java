package com.yk.common.config;

import com.yk.common.constants.YKConstant;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

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
public class LocaleConfiguration extends WebMvcConfigurerAdapter {

    /**
     *  Thymeleaf LocaleResolver
     */
    @Bean(name = "localeResolver")
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.CANADA);
        return sessionLocaleResolver;
    }

    /**
     * 增加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }

    /**
     * 替换spring boot默认生成的messageSource
     * @return
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(YKConstant.I18N_MSG,YKConstant.I18N_CODE);
        messageSource.setDefaultEncoding(YKConstant.I18N_ENCODIND);
        return messageSource;
    }

}
