package com.springwheel.api;

import com.springwheel.api.dto.user.AccountDto;
import com.springwheel.api.dto.user.UserDto;
import com.springwheel.api.support.ApiCode;
import com.springwheel.api.support.ApiResult;
import com.springwheel.common.annotation.ParamCheck;
import com.springwheel.common.exception.ApiException;
import com.springwheel.common.mapper.BeanMapper;
import com.springwheel.common.util.constants.MediaTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账户接口
 *
 * 后缀
 * 1、增删改   *。do
 * 2、查询     *。json  *。xml
 *
 * @author hjm
 * @Time 2016/5/1 20:21.
 */
@RestController
public class AccountEndpoint {

    private static Logger logger = LoggerFactory.getLogger(AccountEndpoint.class);



    @ParamCheck
    @RequestMapping(value = "rest/account/create.do", produces = MediaTypes.JSON_UTF_8)
    public Object create(@RequestBody AccountDto accountDto) {
        if(accountDto==null){
            throw new ApiException("传入参数为空");
        }
        logger.info("userName:"+accountDto.getUserName());
        logger.info("password:"+accountDto.getPassword());
        return new ApiResult(ApiCode.SUCCESS);
    }





}
