package project.zzq.competition_epidemic_management_system.web.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.zzq.competition_epidemic_management_system.data.CompetitionInfoDO;
import project.zzq.competition_epidemic_management_system.data.PlaceInfoDO;
import project.zzq.competition_epidemic_management_system.service.CompetitionService;
import project.zzq.competition_epidemic_management_system.web.data.CompetitionVO;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompetitionLogic {
    @Autowired
    private CompetitionService competitionService;

    public void createCompetition(CompetitionInfoDO competitionInfoDO) {
        competitionService.createCompetition(competitionInfoDO);
    }

    public void createPlace(String placeName) {
        competitionService.createPlace(placeName);
    }

    public List<PlaceInfoDO> getAllPlace() {
        return competitionService.getAllPlace().stream().sorted(Comparator.comparingLong(PlaceInfoDO::getId)).collect(Collectors.toList());
    }

    public List<CompetitionVO> getAllCompetition() {
        return competitionService.getAllCompetitionInfo().stream().map(this::competitionDO2VO).collect(Collectors.toList());
    }

    private CompetitionVO competitionDO2VO(CompetitionInfoDO competitionInfoDO) {
        CompetitionVO competitionVO = new CompetitionVO();
        Optional<PlaceInfoDO> placeInfoOptional = competitionService.getPlaceById(competitionInfoDO.getPlaceId());
        if (!placeInfoOptional.isPresent()) {
            throw new RuntimeException("place is not exist.");
        }

        SimpleDateFormat format =  new SimpleDateFormat("yyyy-mm-dd HH:mm");

        competitionVO.setId(competitionInfoDO.getId());
        competitionVO.setPlaceName(placeInfoOptional.get().getName());
        competitionVO.setName(competitionInfoDO.getName());
        competitionVO.setStartTime(format.format(competitionInfoDO.getStartTime()));
        competitionVO.setEndTime(format.format(competitionInfoDO.getEndTime()));

        return competitionVO;
    }
}
