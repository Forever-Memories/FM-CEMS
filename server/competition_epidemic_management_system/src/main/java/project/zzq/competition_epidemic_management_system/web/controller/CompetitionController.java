package project.zzq.competition_epidemic_management_system.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.zzq.competition_epidemic_management_system.constant.CompetitionEpidemicManagementSystemConstant;
import project.zzq.competition_epidemic_management_system.data.CompetitionInfoDO;
import project.zzq.competition_epidemic_management_system.data.PlaceInfoDO;
import project.zzq.competition_epidemic_management_system.web.data.CompetitionVO;
import project.zzq.competition_epidemic_management_system.web.logic.CompetitionLogic;

import java.util.List;

@RestController
@RequestMapping(CompetitionEpidemicManagementSystemConstant.API)
public class CompetitionController {
    @Autowired
    private CompetitionLogic competitionLogic;

    @PostMapping("/competition/create")
    public void create(@RequestBody CompetitionInfoDO competitionInfoDO) {
        competitionLogic.createCompetition(competitionInfoDO);
    }

    @PostMapping("/competition/create-place")
    public void createPlace(@RequestBody String placeName) {
        competitionLogic.createPlace(placeName);
    }

    @GetMapping("/competition/all-competition")
    public List<CompetitionVO> getAllCompetition() {
        return competitionLogic.getAllCompetition();
    }

    @GetMapping("/competition/all-place")
    public List<PlaceInfoDO> getAllPlace() {
        return competitionLogic.getAllPlace();
    }
}
