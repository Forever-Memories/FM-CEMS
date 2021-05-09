package project.zzq.competition_epidemic_management_system.web.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.zzq.competition_epidemic_management_system.data.CompetitionInfoDO;
import project.zzq.competition_epidemic_management_system.data.ParticipantInfoDO;
import project.zzq.competition_epidemic_management_system.data.RegistryDO;
import project.zzq.competition_epidemic_management_system.service.CompetitionService;
import project.zzq.competition_epidemic_management_system.service.ParticipantInfoService;
import project.zzq.competition_epidemic_management_system.service.RegistryService;
import project.zzq.competition_epidemic_management_system.web.data.RegistryVO;
import project.zzq.competition_epidemic_management_system.web.data.SearchRegistryParam;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistryLogic {
    @Autowired
    private RegistryService registryService;

    @Autowired
    private ParticipantInfoService participantInfoService;

    @Autowired
    private CompetitionService competitionService;

    public void create(RegistryDO registryDO) {
        registryService.create(registryDO);
    }

    public List<RegistryVO> getAllRegistry() {
        return registryService.getAllRegistry().stream().map(this::do2VO).collect(Collectors.toList());
    }

    public List<RegistryVO> searchRegistry(SearchRegistryParam searchRegistryParam) {
        List<RegistryVO> allRegistry = getAllRegistry();
        if (searchRegistryParam.getCompetitionName() != null) {
            allRegistry = allRegistry.stream().filter(o -> o.getCompetitionName().equals(searchRegistryParam.getCompetitionName())).collect(Collectors.toList());
        }

        if (searchRegistryParam.getParticipantName() != null) {
            allRegistry = allRegistry.stream().filter(o -> o.getUserName().equals(searchRegistryParam.getParticipantName())).collect(Collectors.toList());
        }

        return allRegistry;
    }

    private RegistryVO do2VO(RegistryDO registryDO) {
        ParticipantInfoDO participantInfoDO = participantInfoService.getParticipantInfoByUserIds(Collections.singletonList(registryDO.getUserId())).get(0);
        if(participantInfoDO == null) {
            throw new RuntimeException("user is not exist");
        }

        Optional<CompetitionInfoDO> competitionInfoDOOptional = competitionService.getCompetitionById(registryDO.getCompetitionId());
        if(!competitionInfoDOOptional.isPresent()) {
            throw new RuntimeException("competition is not exist");
        }
        CompetitionInfoDO competitionInfoDO = competitionInfoDOOptional.get();
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm");

        RegistryVO registryVO = new RegistryVO();
        registryVO.setUserName(participantInfoDO.getName());
        registryVO.setUserUnit(participantInfoDO.getUnit());
        registryVO.setCompetitionName(competitionInfoDO.getName());
        registryVO.setCompetitionStartTime(format.format(competitionInfoDO.getStartTime()));

        return registryVO;
    }
}
