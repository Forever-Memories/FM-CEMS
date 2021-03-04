package project.zzq.competition_epidemic_management_system.web.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.zzq.competition_epidemic_management_system.service.UserService;
import project.zzq.competition_epidemic_management_system.web.data.UserSignUpVO;

@Service
public class UserLogic {

    @Autowired
    private UserService userService;

    /**
     * 用户注册接口
     */
    public Long signUpUser(String phoneNumber, String password) {
        UserSignUpVO userSignUpVO = new UserSignUpVO();
        userSignUpVO.setPhoneNumber(phoneNumber);
        userSignUpVO.setPassword(password);

        return userService.signUpUser(userSignUpVO.toUserDO());
    }

}
