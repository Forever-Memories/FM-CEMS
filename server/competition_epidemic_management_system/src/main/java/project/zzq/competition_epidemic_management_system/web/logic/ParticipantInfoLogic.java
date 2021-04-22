package project.zzq.competition_epidemic_management_system.web.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.zzq.competition_epidemic_management_system.data.ParticipantInfoDO;
import project.zzq.competition_epidemic_management_system.service.ParticipantInfoService;

import java.util.List;

@Service
public class ParticipantInfoLogic {

    @Autowired
    private ParticipantInfoService participantInfoService;

    public void create(ParticipantInfoDO participantInfoDO) {
        participantInfoService.create(participantInfoDO);
    }

    public List<ParticipantInfoDO> getParticipantInfoByUserIds(List<Long> userIds) {
        return participantInfoService.getParticipantInfoByUserIds(userIds);
    }

    public List<ParticipantInfoDO> getAllParticipantInfo() {
        return participantInfoService.getAllParticipantInfo();
    }
}
