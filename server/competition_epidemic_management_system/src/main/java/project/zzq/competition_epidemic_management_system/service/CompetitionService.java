package project.zzq.competition_epidemic_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.zzq.competition_epidemic_management_system.data.CompetitionInfoDO;
import project.zzq.competition_epidemic_management_system.data.PlaceInfoDO;
import project.zzq.competition_epidemic_management_system.storage.CompetitionInfoStorage;
import project.zzq.competition_epidemic_management_system.storage.PlaceInfoStorage;

import java.util.List;
import java.util.Optional;

@Service
public class CompetitionService {
    @Autowired
    private CompetitionInfoStorage competitionInfoStorage;

    @Autowired
    private PlaceInfoStorage placeInfoStorage;

    public void createCompetition(CompetitionInfoDO competitionInfoDO) {
        competitionInfoStorage.create(competitionInfoDO);
    }

    public List<CompetitionInfoDO> getAllCompetitionInfo() {
        return competitionInfoStorage.getAllCompetitionInfo();
    }

    public void createPlace(String name) {
        placeInfoStorage.create(name);
    }

    public void edit(CompetitionInfoDO competitionInfoDO) {
        competitionInfoStorage.edit(competitionInfoDO);
    }

    public List<PlaceInfoDO> getAllPlace() {
        return placeInfoStorage.getAllPlaceInfo();
    }

    public Optional<PlaceInfoDO> getPlaceById(Long placeId) {
        return placeInfoStorage.getPlaceInfoById(placeId);
    }

    public Optional<CompetitionInfoDO> getCompetitionById(Long competitionId) {
        return competitionInfoStorage.getCompetitionById(competitionId);
    }

    public void delete(Long competitionId) {
        competitionInfoStorage.delete(competitionId);
    }
}
