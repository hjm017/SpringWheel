package com.yk.api;

import com.yk.common.mapper.BeanMapper;
import com.yk.common.util.constants.MediaTypes;
import com.yk.data.domain.User;
import com.yk.api.dto.UserDto;
import com.yk.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hjm
 * @Time 2016/5/1 20:21.
 */
@RestController
public class UserEndpoint {

    private static Logger logger = LoggerFactory.getLogger(UserEndpoint.class);


    @Autowired
    private UserService userService;


    @RequestMapping(value = "api/user/users",produces = MediaTypes.JSON_UTF_8)
    public List<UserDto> getList(){
        List<User> users = userService.getList();
        return BeanMapper.mapList(users,UserDto.class);
    }


}
