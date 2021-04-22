package project.zzq.competition_epidemic_management_system.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.zzq.competition_epidemic_management_system.constant.CompetitionEpidemicManagementSystemConstant;
import project.zzq.competition_epidemic_management_system.data.ParticipantInfoDO;
import project.zzq.competition_epidemic_management_system.web.logic.ParticipantInfoLogic;

import java.util.List;

@RestController
@RequestMapping(CompetitionEpidemicManagementSystemConstant.API)
public class ParticipantInfoController {
    @Autowired
    private ParticipantInfoLogic participantInfoLogic;

    @PostMapping("/participant-info/create")
    public void create(@RequestBody ParticipantInfoDO participantInfoDO) {
        participantInfoLogic.create(participantInfoDO);
    }

    @GetMapping("/participant-info/infos")
    public List<ParticipantInfoDO> getParticipantInfoByUserIds(@RequestParam List<Long> userIds) {
        return participantInfoLogic.getParticipantInfoByUserIds(userIds);
    }

    @GetMapping("/participant-info/all-infos")
    public List<ParticipantInfoDO> getAllParticipantInfo() {
        return participantInfoLogic.getAllParticipantInfo();
    }
}
