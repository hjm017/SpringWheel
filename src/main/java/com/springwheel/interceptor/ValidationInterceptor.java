package com.springwheel.interceptor;

import com.springwheel.common.annotation.ParamCheck;
import com.springwheel.common.exception.ErrorCode;
import com.springwheel.common.exception.ParamCheckException;
import com.springwheel.common.validation.ValidationResult;
import com.springwheel.common.validation.ValidationUtils;
import org.hibernate.validator.internal.engine.ValidatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
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
import java.util.*;

/**
 * 参数校验拦截器
 *
 * @author hjm
 * @Time 2016/5/2 10:56.
 */
public class ValidationInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());


    private Validator validator;



    //****************************************//
    //                                        //
    //            Override Method             //
    //                                        //
    //****************************************//
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        logger.info("param check");

        if(handler instanceof HandlerMethod){
            HandlerMethod method = (HandlerMethod) handler;
            //参数校验
            ParamCheck paramCheck = method.getMethodAnnotation(ParamCheck.class);
            if (paramCheck != null) {
                checkParam(request, response, method);
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

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

    public Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }


}
