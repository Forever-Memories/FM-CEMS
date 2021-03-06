package project.zzq.competition_epidemic_management_system.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.zzq.competition_epidemic_management_system.constant.CompetitionEpidemicManagementSystemConstant;
import project.zzq.competition_epidemic_management_system.data.ParticipantInfoDO;
import project.zzq.competition_epidemic_management_system.web.data.DeleteUserParam;
import project.zzq.competition_epidemic_management_system.web.data.ParticipantCreateParam;
import project.zzq.competition_epidemic_management_system.web.data.ParticipantInfoVO;
import project.zzq.competition_epidemic_management_system.web.data.SearchParticipantParam;
import project.zzq.competition_epidemic_management_system.web.logic.ParticipantInfoLogic;

import java.util.List;

@RestController
@RequestMapping(CompetitionEpidemicManagementSystemConstant.API)
public class ParticipantInfoController {
    @Autowired
    private ParticipantInfoLogic participantInfoLogic;

    @PostMapping("/participant-info/create")
    public void create(@RequestBody ParticipantCreateParam participantCreateParam) {
        participantInfoLogic.create(participantCreateParam);
    }

    @PostMapping("/participant-info/infos")
    public List<ParticipantInfoVO> getParticipantInfoByUserIds(@RequestBody List<Long> userIds) {
        return participantInfoLogic.getParticipantInfoByUserIds(userIds);
    }

    @GetMapping("/participant-info/all-infos")
    public List<ParticipantInfoVO> getAllParticipantInfo() {
        return participantInfoLogic.getAllParticipantInfo();
    }

    @PostMapping("/participant-info/search")
    public List<ParticipantInfoVO> searchParticipantInfo(@RequestBody SearchParticipantParam searchParticipantParam) {
        return participantInfoLogic.searchParticipant(searchParticipantParam);
    }

    @PostMapping("/participant-info/edit")
    public void edit(@RequestBody ParticipantInfoDO participantInfoDO) {
        participantInfoLogic.edit(participantInfoDO);
    }

    @PostMapping("/participant-info/delete")
    public void deleteByUserId(@RequestBody DeleteUserParam deleteUserParam) {
        participantInfoLogic.deleteByUserId(deleteUserParam.getUserId());
    }
}
