/**
 * @(#)SignInController.java, 4月 19, 2021.
 * <p>
 * Copyright 2021 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package project.zzq.competition_epidemic_management_system.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import project.zzq.competition_epidemic_management_system.constant.CompetitionEpidemicManagementSystemConstant;
import project.zzq.competition_epidemic_management_system.web.data.SignInVO;
import project.zzq.competition_epidemic_management_system.web.logic.SignInLogic;

/**
 * @author zhuzheqing
 */
@Controller
@RequestMapping(CompetitionEpidemicManagementSystemConstant.API)
public class SignInController {
    @Autowired
    private SignInLogic signInLogic;

    @PostMapping("/sign-in")
    @ResponseBody
    public boolean signIn(@RequestBody SignInVO signInVO) {
        return signInLogic.signIn(signInVO.getPhoneNumber(), signInVO.getPassword());
    }

}