package project.zzq.competition_epidemic_management_system.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.zzq.competition_epidemic_management_system.constant.CompetitionEpidemicManagementSystemConstant;
import project.zzq.competition_epidemic_management_system.data.RegistryDO;
import project.zzq.competition_epidemic_management_system.web.data.RegistryVO;
import project.zzq.competition_epidemic_management_system.web.data.SearchRegistryParam;
import project.zzq.competition_epidemic_management_system.web.logic.RegistryLogic;

import java.util.List;

@RestController
@RequestMapping(CompetitionEpidemicManagementSystemConstant.API)
public class RegistryController {
    @Autowired
    private RegistryLogic registryLogic;

    @PostMapping("/registry/create")
    public void create(@RequestBody RegistryDO registryDO) {
        registryLogic.create(registryDO);
    }

    @GetMapping("/registry/all-info")
    public List<RegistryVO> getAllRegistry() {
        return registryLogic.getAllRegistry();
    }

    @PostMapping("/registry/search")
    public List<RegistryVO> searchRegistry(@RequestBody SearchRegistryParam searchRegistryParam) {
        return registryLogic.searchRegistry(searchRegistryParam);
    }

    @PostMapping("/registry/delete")
    public void delete(@RequestBody RegistryDO registryDO) {
        registryLogic.delete(registryDO);
    }
}
