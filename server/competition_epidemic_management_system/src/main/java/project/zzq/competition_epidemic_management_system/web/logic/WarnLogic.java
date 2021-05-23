package project.zzq.competition_epidemic_management_system.web.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.zzq.competition_epidemic_management_system.data.ParticipantInfoDO;
import project.zzq.competition_epidemic_management_system.data.WarnDO;
import project.zzq.competition_epidemic_management_system.service.ParticipantInfoService;
import project.zzq.competition_epidemic_management_system.storage.WarnStorage;
import project.zzq.competition_epidemic_management_system.web.data.WarnVO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarnLogic {
    @Autowired
    private WarnStorage warnStorage;

    @Autowired
    private ParticipantInfoService participantInfoService;

    public void create(WarnDO warnDO) {
        warnStorage.create(warnDO);
    }

    public void delete(Long id) {
        warnStorage.delete(id);
    }

    public List<WarnVO> getAllWarn() {
        return warnStorage.getAllWarn().stream().map(this::do2VO).collect(Collectors.toList());
    }


    private WarnVO do2VO(WarnDO warnDO) {
        WarnVO warnVO = new WarnVO();
        ParticipantInfoDO participantInfoDO = participantInfoService.getParticipantInfoByUserIds(Collections.singletonList(warnDO.getUserId())).get(0);

        warnVO.setId(warnDO.getId());
        warnVO.setUserId(warnDO.getUserId());
        warnVO.setUserName(participantInfoDO.getName());
        warnVO.setUnit(participantInfoDO.getUnit());
        warnVO.setIsCough(bool2String(warnDO.getIsCough()));
        warnVO.setIsHistory(bool2String(warnDO.getIsHistory()));
        warnVO.setIsTough(bool2String(warnDO.getIsTouch()));


        return warnVO;
    }

    private String bool2String(Boolean bo) {
        return bo ? "是" : "否";
    }

}
