package project.zzq.competition_epidemic_management_system.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.zzq.competition_epidemic_management_system.constant.CompetitionEpidemicManagementSystemConstant;
import project.zzq.competition_epidemic_management_system.data.HealthyInfoDO;
import project.zzq.competition_epidemic_management_system.data.ParticipantInfoDO;
import project.zzq.competition_epidemic_management_system.web.data.HealthyInfoVO;
import project.zzq.competition_epidemic_management_system.web.data.ParticipantInfoVO;
import project.zzq.competition_epidemic_management_system.web.logic.HealthyInfoLogic;

import java.util.List;

@RestController
@RequestMapping(CompetitionEpidemicManagementSystemConstant.API)
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
}
