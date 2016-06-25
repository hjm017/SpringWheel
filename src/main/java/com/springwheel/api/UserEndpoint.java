package com.springwheel.api;

import com.springwheel.common.annotation.ApiController;
import com.springwheel.common.annotation.ApiRequestBody;
import com.springwheel.common.exception.ApiException;
import com.springwheel.common.exception.ErrorCode;
import com.springwheel.common.mapper.BeanMapper;
import com.springwheel.common.util.constants.MediaTypes;
import com.springwheel.data.domain.User;
import com.springwheel.api.dto.UserDto;
import com.springwheel.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author hjm
 * @Time 2016/5/1 20:21.
 */
@ApiController
public class UserEndpoint {

    private static Logger logger = LoggerFactory.getLogger(UserEndpoint.class);


    @Autowired
    private UserService userService;


    @RequestMapping(value = "rest/users", produces = MediaTypes.JSON_UTF_8)
    public List<UserDto> getList() {
        List<User> users = userService.getList();
        if (false) {
            throw new ApiException("这是一个测试例子", ErrorCode.INTERNAL_SERVER_ERROR);
        }
        return BeanMapper.mapList(users, UserDto.class);
    }



    @RequestMapping(value = "rest/user/{id}/modify", produces = MediaTypes.JSON_UTF_8)
    public List<UserDto> getList(@ApiRequestBody UserDto userDto) {
        List<User> users = userService.getList();
        return BeanMapper.mapList(users, UserDto.class);
    }


}
