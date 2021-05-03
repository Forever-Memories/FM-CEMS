package project.zzq.competition_epidemic_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.zzq.competition_epidemic_management_system.data.HealthyInfoDO;
import project.zzq.competition_epidemic_management_system.storage.HealthyInfoStorage;

import java.util.List;

@Service
public class HealthyInfoService {
    @Autowired
    HealthyInfoStorage healthyInfoStorage;

    public void create(HealthyInfoDO healthyInfoDO) {
        healthyInfoStorage.create(healthyInfoDO);
    }

    public List<HealthyInfoDO> getAllHealthyInfo() {
        return healthyInfoStorage.getAllHealthyInfo();
    }
}
