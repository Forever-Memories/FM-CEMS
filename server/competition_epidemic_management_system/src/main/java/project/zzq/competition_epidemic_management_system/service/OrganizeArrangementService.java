package project.zzq.competition_epidemic_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.zzq.competition_epidemic_management_system.data.OrganizeArrangementDO;
import project.zzq.competition_epidemic_management_system.storage.OrganizeArrangementStorage;

import java.util.List;

@Service
public class OrganizeArrangementService {
    @Autowired
    private OrganizeArrangementStorage organizeArrangementStorage;

    public void create(OrganizeArrangementDO organizeArrangementDO) {
        organizeArrangementStorage.create(organizeArrangementDO);
    }

    public List<OrganizeArrangementDO> getOrganizeArrangementByUserId(Long userId) {
        return organizeArrangementStorage.getOrganizeArrangementByUserId(userId);
    }

    public List<OrganizeArrangementDO> getAllOrganizeArrangement() {
        return organizeArrangementStorage.getAllOrganizeArrangement();
    }

    public void delete(Long id) {
        organizeArrangementStorage.delete(id);
    }

    public void deleteByUserId(Long userId) {
        organizeArrangementStorage.deleteByUserId(userId);
    }
}
