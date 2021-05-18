/**
 * @(#)SignInLogic.java, 4月 19, 2021.
 * <p>
 * Copyright 2021 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package project.zzq.competition_epidemic_management_system.web.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.zzq.competition_epidemic_management_system.service.SignInService;
import project.zzq.competition_epidemic_management_system.web.data.SignInResultVO;

/**
 * @author zhuzheqing
 */
@Service
public class SignInLogic {
    @Autowired
    private SignInService signInService;

    public SignInResultVO signIn(String phoneNumber, String password) {
        return signInService.signIn(phoneNumber, password);
    }
}