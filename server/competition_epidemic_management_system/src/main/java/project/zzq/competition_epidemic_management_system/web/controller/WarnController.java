package project.zzq.competition_epidemic_management_system.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.zzq.competition_epidemic_management_system.constant.CompetitionEpidemicManagementSystemConstant;
import project.zzq.competition_epidemic_management_system.web.data.DeleteWarnParam;
import project.zzq.competition_epidemic_management_system.web.data.WarnVO;
import project.zzq.competition_epidemic_management_system.web.logic.WarnLogic;

import java.util.List;

@RestController
@RequestMapping(CompetitionEpidemicManagementSystemConstant.API)
public class WarnController {
    @Autowired
    private WarnLogic warnLogic;

    @GetMapping("/warn/all-info")
    public List<WarnVO> getAllWarn() {
        return warnLogic.getAllWarn();
    }

    @PostMapping("/warn/delete")
    public void delete(@RequestBody DeleteWarnParam deleteWarnParam) {
        warnLogic.delete(deleteWarnParam.getId());
    }
}
