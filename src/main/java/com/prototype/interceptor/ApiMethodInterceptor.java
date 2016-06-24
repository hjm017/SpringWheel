package com.prototype.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prototype.common.annotation.ParamCheck;
import com.prototype.common.exception.ErrorCode;
import com.prototype.common.exception.ParamCheckException;
import com.prototype.common.validation.ValidationResult;
import com.prototype.common.validation.ValidationUtils;
import org.apache.commons.io.IOUtils;
import org.hibernate.validator.internal.engine.ValidatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    @Autowired
    private Validator validator;

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

        //参数校验
        ParamCheck paramCheck = method.getMethodAnnotation(ParamCheck.class);
        if (paramCheck != null) {
            checkParam(request, response, method);
        }

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

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


    //****************************************//
    //                                        //
    //            param check                 //
    //                                        //
    //****************************************//

    private void checkParam(HttpServletRequest request, HttpServletResponse response,
                            HandlerMethod method) throws Exception {

        ServletWebRequest webRequest = new ServletWebRequest(request, response);
        LocalValidatorFactoryBean localValidatorFactoryBean = (LocalValidatorFactoryBean)validator;
        ValidatorImpl validatorImpl = (ValidatorImpl) localValidatorFactoryBean.getValidator();

        Class<?>[] groups = new Class<?>[0];
        MethodParameter[] parameters = method.getMethodParameters();
        Object[] parameterValues = new Object[parameters.length];
        ValidationResult validationResult = validate(validatorImpl, parameterValues, method,
            groups);

        if (validationResult.isHasErrors()) {
            throw new ParamCheckException(validationResult.getErrorMsg().toString(),
                ErrorCode.BAD_REQUEST);
        }
    }

    private ValidationResult validate(ValidatorImpl validatorImpl, Object[] parameterValues,
                                      HandlerMethod method, Class<?>[] groups) {
        Set<ConstraintViolation<Object>> violations = null;
        Map<String, String> errorMsg = new HashMap<String, String>();
        //检验对象
        for (Object obj : parameterValues) {
            if (obj != null) {
                violations = validatorImpl.validate(obj, Default.class);
                ValidationResult result = ValidationUtils.getValidationResult(violations);
                if (result.isHasErrors()) {
                    errorMsg.putAll(result.getErrorMsg());
                }
            }
        }

        //检验方法参数
        violations = validatorImpl.validateParameters(method.getBean(), method.getMethod(),
            parameterValues, groups);
        ValidationResult result = ValidationUtils.getValidationResult(violations);
        if (result.isHasErrors()) {
            errorMsg.putAll(result.getErrorMsg());
        }

        if (!errorMsg.isEmpty()) {
            ValidationResult validationResult = new ValidationResult();
            validationResult.setHasErrors(true);
            validationResult.setErrorMsg(errorMsg);
            return validationResult;
        }

        //如果都没有异常，返回默认值
        return new ValidationResult(false);

    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static void setObjectMapper(ObjectMapper objectMapper) {
        ApiMethodInterceptor.objectMapper = objectMapper;
    }

    public Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }
}
