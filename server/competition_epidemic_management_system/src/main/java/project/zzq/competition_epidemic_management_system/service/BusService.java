package project.zzq.competition_epidemic_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.zzq.competition_epidemic_management_system.data.BusDO;
import project.zzq.competition_epidemic_management_system.storage.BusStorage;

import java.util.List;

@Service
public class BusService {
    @Autowired
    private BusStorage busStorage;

    public void create(BusDO busDO) {
        busStorage.create(busDO);
    }

    public List<BusDO> getAllBus() {
        return busStorage.getAllBus();
    }

    public void deleteById(Long busId) {
        busStorage.deleteById(busId);
    }

    public void edit(BusDO busDO) {
        busStorage.edit(busDO);
    }
}
