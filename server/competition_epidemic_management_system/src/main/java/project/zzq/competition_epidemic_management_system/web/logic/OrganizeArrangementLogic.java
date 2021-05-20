package project.zzq.competition_epidemic_management_system.web.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.zzq.competition_epidemic_management_system.data.OrganizeArrangementDO;
import project.zzq.competition_epidemic_management_system.data.ParticipantInfoDO;
import project.zzq.competition_epidemic_management_system.data.PlaceInfoDO;
import project.zzq.competition_epidemic_management_system.service.CompetitionService;
import project.zzq.competition_epidemic_management_system.service.OrganizeArrangementService;
import project.zzq.competition_epidemic_management_system.service.ParticipantInfoService;
import project.zzq.competition_epidemic_management_system.web.data.OrganizeArrangementVO;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizeArrangementLogic {
    @Autowired
    private OrganizeArrangementService organizeArrangementService;

    @Autowired
    private ParticipantInfoService participantInfoService;

    @Autowired
    private CompetitionService competitionService;

    public void create(OrganizeArrangementDO organizeArrangementDO) {
        organizeArrangementService.create(organizeArrangementDO);
    }

    public List<OrganizeArrangementVO> getOrganizeArrangementByUserId(Long userId) {
        return organizeArrangementService.getOrganizeArrangementByUserId(userId)
                .stream()
                .sorted(Comparator.comparingLong(OrganizeArrangementDO::getId))
                .map(this::do2VO)
                .collect(Collectors.toList());
    }

    public List<OrganizeArrangementVO> getAllOrganizeArrangement() {
        return organizeArrangementService.getAllOrganizeArrangement()
                .stream()
                .sorted(Comparator.comparingLong(OrganizeArrangementDO::getId))
                .map(this::do2VO)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        organizeArrangementService.delete(id);
    }

    public void deleteByUserId(Long userId) {
        organizeArrangementService.deleteByUserId(userId);
    }

    private OrganizeArrangementVO do2VO(OrganizeArrangementDO organizeArrangementDO) {
        OrganizeArrangementVO organizeArrangementVO = new OrganizeArrangementVO();
        ParticipantInfoDO participantInfoDO = participantInfoService.getParticipantInfoByUserIds(Collections.singletonList(organizeArrangementDO.getUserId())).get(0);
        PlaceInfoDO placeInfoDO = competitionService.getPlaceById(organizeArrangementDO.getPlaceId()).get();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        organizeArrangementVO.setId(organizeArrangementDO.getId());
        organizeArrangementVO.setUserId(organizeArrangementDO.getUserId());
        organizeArrangementVO.setUserName(participantInfoDO.getName());
        organizeArrangementVO.setPlaceId(organizeArrangementDO.getPlaceId());
        organizeArrangementVO.setPlaceName(placeInfoDO.getName());
        organizeArrangementVO.setStartTime(format.format(organizeArrangementDO.getStartTime()));
        organizeArrangementVO.setEndTime(format.format(organizeArrangementDO.getEndTime()));

        return organizeArrangementVO;
    }
}
