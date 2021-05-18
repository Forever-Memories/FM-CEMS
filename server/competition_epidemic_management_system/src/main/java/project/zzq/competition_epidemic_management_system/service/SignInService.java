/**
 * @(#)SignInService.java, 4月 19, 2021.
 * <p>
 * Copyright 2021 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package project.zzq.competition_epidemic_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.zzq.competition_epidemic_management_system.data.ParticipantInfoDO;
import project.zzq.competition_epidemic_management_system.data.UserDO;
import project.zzq.competition_epidemic_management_system.storage.ParticipantInfoStorage;
import project.zzq.competition_epidemic_management_system.storage.UserStorage;
import project.zzq.competition_epidemic_management_system.web.data.SignInResultVO;
import project.zzq.competition_epidemic_management_system.web.data.SignInVO;

import java.util.Collections;
import java.util.Optional;

/**
 * @author zhuzheqing
 */
@Service
public class SignInService {
    @Autowired
    private UserStorage userStorage;

    @Autowired
    private ParticipantInfoStorage participantInfoStorage;

    /**
     * 根据phone登陆鉴权
     * 成功true失败false
     * @return
     */
    public SignInResultVO signIn(String phoneNumber, String password) {
        Optional<UserDO> userOptional = userStorage.getUserByPhoneNumber(phoneNumber);
        SignInResultVO result = new SignInResultVO();

        if (!userOptional.isPresent()) {
            result.setResult(false);
            return result;
        } else {
            if(userOptional.get().getPassword().equals(password)) {
                result.setResult(true);
                ParticipantInfoDO participantInfoDO = participantInfoStorage.getParticipantInfoByUserIds(Collections.singletonList(userOptional.get().getId())).get(0);
                result.setName(participantInfoDO.getName());
                result.setUserType(userOptional.get().getType().toInt());
                return result;
            } else {
                result.setResult(false);
                return result;
            }
        }
    }
}