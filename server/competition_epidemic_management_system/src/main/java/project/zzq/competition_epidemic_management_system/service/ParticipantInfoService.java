package project.zzq.competition_epidemic_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.zzq.competition_epidemic_management_system.data.ParticipantInfoDO;
import project.zzq.competition_epidemic_management_system.storage.ParticipantInfoStorage;

import java.util.List;

@Service
public class ParticipantInfoService {
    @Autowired
    private ParticipantInfoStorage participantInfoStorage;

    public void create(ParticipantInfoDO participantInfoDO) {
        participantInfoStorage.create(participantInfoDO);
    }

    public List<ParticipantInfoDO> getParticipantInfoByUserIds(List<Long> userIds) {
        return participantInfoStorage.getParticipantInfoByUserIds(userIds);
    }

    public List<ParticipantInfoDO> getParticipantInfoByUserName(String name) {
        return participantInfoStorage.getParticipantInfoByUserName(name);
    }

    public List<ParticipantInfoDO> getAllParticipantInfo() {
        return participantInfoStorage.getAllParticipantInfo();
    }

    public void edit(ParticipantInfoDO participantInfoDO) {
        participantInfoStorage.edit(participantInfoDO);
    }
}
