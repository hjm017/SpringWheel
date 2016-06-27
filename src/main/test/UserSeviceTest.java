import com.fasterxml.jackson.databind.ObjectMapper;
import com.springwheel.Application;
import com.springwheel.api.dto.UserDto;
import com.springwheel.common.util.HttpClientUtil;
import com.springwheel.common.validation.ValidationResult;
import com.springwheel.common.validation.ValidationUtils;
import com.springwheel.common.validation.simple.SimpleEntity;
import com.springwheel.domain.User;
import com.springwheel.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author hjm
 * @Time 2016/5/1 19:28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext
public class UserSeviceTest {
    private static Logger log = LoggerFactory.getLogger(UserSeviceTest.class);

    @Autowired
    private UserService userService;

    private final String host = "http://127.0.0.1:8080/yk/api";

    private ObjectMapper mapper = new ObjectMapper();


    @Test
    public void testFindByUserName() throws Exception {
        List<User> userList = userService.getList();
        for (User user : userList) {
            System.out.println("id:" + user.getId() + " user_name:" + user.getUserName());
        }
    }

    @Test
    public void testGetList() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId("1");
        String json = mapper.writeValueAsString(userDto);
        String body = HttpClientUtil.post(host + "/user/users", json);
        System.out.println(body);

    }

    @Test
    public void testLogJdbc() throws Exception {
        log.info("开始");
        log.info("完成");

    }

    @Test
    public void testValidation(){
        SimpleEntity simpleEntity = new SimpleEntity();
        ValidationResult validationResult  = ValidationUtils.validateEntity(simpleEntity);
        if (validationResult.isHasErrors()){
            System.out.println(validationResult.getErrorMsg());
        }

    }
}
