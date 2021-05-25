package project.zzq.competition_epidemic_management_system.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.zzq.competition_epidemic_management_system.constant.CompetitionEpidemicManagementSystemConstant;
import project.zzq.competition_epidemic_management_system.web.data.UpdatePasswordVO;
import project.zzq.competition_epidemic_management_system.web.data.UserSignUpVO;
import project.zzq.competition_epidemic_management_system.web.logic.UserLogic;

/**
 * @author zhuzheqing
 *
 * User的定义更接近于account,作用主要是登陆鉴权等
 */
@RestController
@RequestMapping(CompetitionEpidemicManagementSystemConstant.API)
public class UserController {
    @Autowired
    private UserLogic userLogic;

    @PostMapping("/user/sign-up")
    public Long signUpUser(@RequestBody UserSignUpVO userSignUpVO) {
        return userLogic.signUpUser(userSignUpVO.getPhoneNumber(), userSignUpVO.getPassword());
    }

    @PostMapping("/user/update-password")
    public boolean updatePassword(@RequestBody UpdatePasswordVO updatePasswordVO) {
        return userLogic.updatePassword(updatePasswordVO);
    }
}
