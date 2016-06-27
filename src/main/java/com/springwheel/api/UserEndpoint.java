package com.springwheel.api;

import com.springwheel.api.dto.user.UserDto;
import com.springwheel.api.support.ApiCode;
import com.springwheel.api.support.ApiResult;
import com.springwheel.common.annotation.ApiController;
import com.springwheel.common.annotation.ApiRequestBody;
import com.springwheel.common.exception.ApiException;
import com.springwheel.common.mapper.BeanMapper;
import com.springwheel.common.util.constants.MediaTypes;
import com.springwheel.domain.User;
import com.springwheel.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 用户接口
 *
 * 后缀
 * 1、增删改   *。do
 * 2、查询     *。json  *。xml
 *
 * @author hjm
 * @Time 2016/5/1 20:21.
 */
@ApiController
public class UserEndpoint {

    private static Logger logger = LoggerFactory.getLogger(UserEndpoint.class);


    @Autowired
    private UserService userService;

    /**
     * 查询用户列表记录
     *
     * @return
     */
    @RequestMapping(value = "rest/users.json", produces = MediaTypes.JSON_UTF_8)
    public List<UserDto> getList() {
        List<User> users = userService.getList();
        if (false) {
            throw new ApiException("这是一个测试例子");
        }
        return BeanMapper.mapList(users, UserDto.class);
    }


    /**
     * 修改
     *
     * @param userDto
     * @return
     */
    @RequestMapping(value = "rest/user/{id}/modify.do", produces = MediaTypes.JSON_UTF_8)
    public List<UserDto> getList(@ApiRequestBody UserDto userDto) {
        List<User> users = userService.getList();
        return BeanMapper.mapList(users, UserDto.class);
    }


    /**
     * 新增
     *
     * @param userDto
     * @return
     */
    @RequestMapping(value = "rest/user/create.do", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public ApiResult create(@ApiRequestBody UserDto userDto) {
        User user = BeanMapper.map(userDto,User.class);
        userService.save(user);
        if (true){
            throw new ApiException("test");
        }
        return new ApiResult(ApiCode.SUCCESS);
    }


}
