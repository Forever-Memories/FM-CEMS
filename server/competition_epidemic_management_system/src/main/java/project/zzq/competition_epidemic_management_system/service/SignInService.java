/**
 * @(#)SignInService.java, 4月 19, 2021.
 * <p>
 * Copyright 2021 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package project.zzq.competition_epidemic_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.zzq.competition_epidemic_management_system.data.UserDO;
import project.zzq.competition_epidemic_management_system.storage.UserStorage;
import project.zzq.competition_epidemic_management_system.web.data.SignInVO;

import java.util.Optional;

/**
 * @author zhuzheqing
 */
@Service
public class SignInService {
    @Autowired
    private UserStorage userStorage;

    /**
     * 根据phone登陆鉴权
     * 成功true失败false
     * @return
     */
    public boolean signIn(String phoneNumber, String password) {
        Optional<UserDO> userOptional = userStorage.getUserByPhoneNumber(phoneNumber);

        if (!userOptional.isPresent()) {
            return false;
        } else {
            return userOptional.get().getPassword().equals(password);
        }
    }
}