package project.zzq.competition_epidemic_management_system.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.zzq.competition_epidemic_management_system.constant.CompetitionEpidemicManagementSystemConstant;
import project.zzq.competition_epidemic_management_system.data.BusDO;
import project.zzq.competition_epidemic_management_system.web.data.BusVO;
import project.zzq.competition_epidemic_management_system.web.data.DeleteBusVO;
import project.zzq.competition_epidemic_management_system.web.logic.BusLogic;

import java.util.List;

@RestController
@RequestMapping(CompetitionEpidemicManagementSystemConstant.API)
public class BusController {
    @Autowired
    private BusLogic busLogic;

    @PostMapping("/bus/create")
    public void create(@RequestBody BusDO busDO) {
        busLogic.create(busDO);
    }

    @GetMapping("/bus/all-info")
    public List<BusVO> getAllBus() {
        return busLogic.getAllBus();
    }

    @PostMapping("/bus/delete")
    public void deleteById(@RequestBody DeleteBusVO deleteBusVO) {
        busLogic.deleteById(deleteBusVO.getBusId());
    }
}
