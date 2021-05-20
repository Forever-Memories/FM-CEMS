package project.zzq.competition_epidemic_management_system.web.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import project.zzq.competition_epidemic_management_system.data.CompetitionInfoDO;
import project.zzq.competition_epidemic_management_system.data.PlaceInfoDO;
import project.zzq.competition_epidemic_management_system.service.CompetitionService;
import project.zzq.competition_epidemic_management_system.service.RegistryService;
import project.zzq.competition_epidemic_management_system.web.data.CompetitionVO;
import project.zzq.competition_epidemic_management_system.web.data.SearchCompetitionParam;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompetitionLogic {
    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private RegistryService registryService;

    public void createCompetition(CompetitionInfoDO competitionInfoDO) {
        competitionService.createCompetition(competitionInfoDO);
    }

    public void createPlace(String placeName) {
        competitionService.createPlace(placeName);
    }

    public List<PlaceInfoDO> getAllPlace() {
        return competitionService.getAllPlace().stream().sorted(Comparator.comparingLong(PlaceInfoDO::getId)).collect(Collectors.toList());
    }

    public List<CompetitionVO> searchCompetition(SearchCompetitionParam searchCompetitionParam) {
        List<CompetitionInfoDO> allCompetitions = competitionService.getAllCompetitionInfo();
        if(searchCompetitionParam.getPlaceId() != null) {
            allCompetitions = allCompetitions.stream().filter(o -> o.getPlaceId().equals(searchCompetitionParam.getPlaceId())).collect(Collectors.toList());
        }

        if(!StringUtils.isEmpty(searchCompetitionParam.getCompetitionName())) {
            allCompetitions = allCompetitions.stream().filter(o -> o.getName().equals(searchCompetitionParam.getCompetitionName())).collect(Collectors.toList());
        }

        return allCompetitions.stream().map(this::competitionDO2VO).collect(Collectors.toList());
    }

    public List<CompetitionVO> getAllCompetition() {
        return competitionService.getAllCompetitionInfo().stream().map(this::competitionDO2VO).collect(Collectors.toList());
    }

    public void edit(CompetitionInfoDO competitionInfoDO) {
        competitionService.edit(competitionInfoDO);
    }

    public void deleteByCompetitionId(Long competitionId) {
        competitionService.delete(competitionId);
        registryService.deleteByCompetitionId(competitionId);
    }

    private CompetitionVO competitionDO2VO(CompetitionInfoDO competitionInfoDO) {
        CompetitionVO competitionVO = new CompetitionVO();
        Optional<PlaceInfoDO> placeInfoOptional = competitionService.getPlaceById(competitionInfoDO.getPlaceId());
        if (!placeInfoOptional.isPresent()) {
            throw new RuntimeException("place is not exist.");
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        competitionVO.setId(competitionInfoDO.getId());
        competitionVO.setPlaceId(competitionInfoDO.getPlaceId());
        competitionVO.setPlaceName(placeInfoOptional.get().getName());
        competitionVO.setName(competitionInfoDO.getName());
        competitionVO.setStartTime(format.format(competitionInfoDO.getStartTime()));
        competitionVO.setEndTime(format.format(competitionInfoDO.getEndTime()));

        return competitionVO;
    }
}
