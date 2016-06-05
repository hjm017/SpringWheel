package com.prototype.config;

import com.prototype.common.constants.YKConstant;
import com.prototype.interceptor.GlobalExceptionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 所有有关springmvc 的配置只需要继承WebMvcConfigurerAdapter重写里面的方法即可
 * <p/>
 * 1、覆盖spring boot默认生成的bean @Bean
 * 2、增加拦截器  addInterceptors
 * 3、添加参数解析器 addArgumentResolvers
 * 4、配置路径映射  configurePathMatch
 * 5、参数格式化工具（用于接收参数） addFormatters
 * 6、配置消息转换器(用于@RequestBody和@ResponseBody) configureMessageConverters
 */
@Configuration
public class BeanConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    private ServerProperties properties;

    /**
     * 替换spring boot默认生成的messageSource
     *
     * @return
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(YKConstant.I18N_MSG, YKConstant.I18N_CODE);
        messageSource.setDefaultEncoding(YKConstant.I18N_ENCODIND);
        return messageSource;
    }


    /**
     * 替换默认的DefaultAnnotationHandlerMapping
     *
     * @return
     */
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        return new RequestMappingHandlerMapping();
    }


    /**
     * 增加错误提示页面
     *
     * @return
     */
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
            }
        };
    }


    @Bean
    @ConditionalOnMissingBean(value = ErrorController.class, search = SearchStrategy.CURRENT)
    public BasicErrorController basicErrorController(ErrorAttributes errorAttributes) {
        return new GlobalExceptionController(errorAttributes, this.properties.getError());
    }

}
