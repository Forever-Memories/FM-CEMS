package project.zzq.competition_epidemic_management_system.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.zzq.competition_epidemic_management_system.constant.CompetitionEpidemicManagementSystemConstant;
import project.zzq.competition_epidemic_management_system.data.OrganizeArrangementDO;
import project.zzq.competition_epidemic_management_system.web.data.DeleteArrangementParam;
import project.zzq.competition_epidemic_management_system.web.data.OrganizeArrangementVO;
import project.zzq.competition_epidemic_management_system.web.logic.OrganizeArrangementLogic;

import java.util.List;

@RestController
@RequestMapping(CompetitionEpidemicManagementSystemConstant.API)
public class OrganizeArrangementController {
    @Autowired
    private OrganizeArrangementLogic organizeArrangementLogic;

    @GetMapping("/organize-arrangement/all-info")
    public List<OrganizeArrangementVO> getAllOrganizeArrangement() {
        return organizeArrangementLogic.getAllOrganizeArrangement();
    }

    @PostMapping("/organize-arrangement/create")
    public void create(@RequestBody OrganizeArrangementDO organizeArrangementDO) {
        organizeArrangementLogic.create(organizeArrangementDO);
    }

    @PostMapping("/organize-arrangement/delete")
    public void delete(@RequestBody DeleteArrangementParam deleteArrangementParam) {
        organizeArrangementLogic.delete(deleteArrangementParam.getId());
    }

}
