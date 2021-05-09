package project.zzq.competition_epidemic_management_system.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.zzq.competition_epidemic_management_system.constant.CompetitionEpidemicManagementSystemConstant;
import project.zzq.competition_epidemic_management_system.data.HealthyInfoDO;
import project.zzq.competition_epidemic_management_system.data.ParticipantInfoDO;
import project.zzq.competition_epidemic_management_system.web.data.DeleteHealthyParam;
import project.zzq.competition_epidemic_management_system.web.data.HealthyInfoVO;
import project.zzq.competition_epidemic_management_system.web.data.ParticipantInfoVO;
import project.zzq.competition_epidemic_management_system.web.data.SearchHealthyInfoParam;
import project.zzq.competition_epidemic_management_system.web.logic.HealthyInfoLogic;

import java.util.List;

@RestController
@RequestMapping(CompetitionEpidemicManagementSystemConstant.API)
@Slf4j
public class HealthyController {
    @Autowired
    private HealthyInfoLogic healthyInfoLogic;

    @PostMapping("/healthy-info/create")
    public void create(@RequestBody HealthyInfoDO healthyInfoDO) {
        healthyInfoLogic.create(healthyInfoDO);
    }

    @GetMapping("/healthy-info/all-infos")
    public List<HealthyInfoVO> getAllParticipantInfo() {
        return healthyInfoLogic.getAllHealthyInfo();
    }

    @PostMapping("/healthy-info/search")
    public List<HealthyInfoVO> searchHealthyByUserIds(@RequestBody SearchHealthyInfoParam searchHealthyInfoParam) {
        return healthyInfoLogic.searchHealthyByUserName(searchHealthyInfoParam.getName());
    }

    @PostMapping("healthy-info/delete")
    public void deleteById(@RequestBody DeleteHealthyParam deleteHealthyParam) {
        healthyInfoLogic.deleteById(deleteHealthyParam.getId());
    }
}
