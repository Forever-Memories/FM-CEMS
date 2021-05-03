package project.zzq.competition_epidemic_management_system.web.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.zzq.competition_epidemic_management_system.data.ParticipantInfoDO;
import project.zzq.competition_epidemic_management_system.data.UserDO;
import project.zzq.competition_epidemic_management_system.service.ParticipantInfoService;
import project.zzq.competition_epidemic_management_system.service.UserService;
import project.zzq.competition_epidemic_management_system.web.data.ParticipantCreateParam;
import project.zzq.competition_epidemic_management_system.web.data.ParticipantInfoVO;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipantInfoLogic {

    @Autowired
    private ParticipantInfoService participantInfoService;

    @Autowired
    private UserService userService;

    public void create(ParticipantCreateParam participantCreateParam) {
        Long userId = userService.signUpUser(participantCreateParam.getUserDO());
        ParticipantInfoDO participantInfoDO = participantCreateParam.getParticipantInfoDO();
        participantInfoDO.setUserId(userId);

        participantInfoService.create(participantInfoDO);
    }

    public List<ParticipantInfoVO> getParticipantInfoByUserIds(List<Long> userIds) {
        return participantInfoService.getParticipantInfoByUserIds(userIds).stream().map(this::participantInfoDO2VO).sorted(Comparator.comparingLong(ParticipantInfoVO::getUserId)).collect(Collectors.toList());
    }

    public List<ParticipantInfoVO> getAllParticipantInfo() {
        return participantInfoService.getAllParticipantInfo().stream().map(this::participantInfoDO2VO).sorted(Comparator.comparingLong(ParticipantInfoVO::getUserId)).collect(Collectors.toList());
    }

    private ParticipantInfoVO participantInfoDO2VO(ParticipantInfoDO participantInfoDO) {
        UserDO userDO = userService.getUserById(participantInfoDO.getUserId());

        return new ParticipantInfoVO(participantInfoDO, userDO);
    }
}
