package project.zzq.competition_epidemic_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.zzq.competition_epidemic_management_system.data.RegistryDO;
import project.zzq.competition_epidemic_management_system.storage.RegistryStorage;

import java.util.List;

@Service
public class RegistryService {
    @Autowired
    private RegistryStorage registryStorage;

    public void create(RegistryDO registryDO) {
        registryStorage.create(registryDO);
    }

    public List<RegistryDO> getAllRegistry() {
        return registryStorage.getAllRegistry();
    }

    public void delete(RegistryDO registryDO) {
        registryStorage.delete(registryDO);
    }

    public void deleteByUserId(Long userId) {
        registryStorage.deleteByUserId(userId);
    }

    public void deleteByCompetitionId(Long competitionId) {
        registryStorage.deleteByCompetitionId(competitionId);
    }

}
