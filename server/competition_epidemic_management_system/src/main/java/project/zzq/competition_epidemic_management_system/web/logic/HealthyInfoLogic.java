package project.zzq.competition_epidemic_management_system.web.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.zzq.competition_epidemic_management_system.data.HealthyInfoDO;
import project.zzq.competition_epidemic_management_system.data.ParticipantInfoDO;
import project.zzq.competition_epidemic_management_system.service.HealthyInfoService;
import project.zzq.competition_epidemic_management_system.service.ParticipantInfoService;
import project.zzq.competition_epidemic_management_system.web.data.HealthyInfoVO;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HealthyInfoLogic {
    @Autowired
    private HealthyInfoService healthyInfoService;

    @Autowired
    private ParticipantInfoService participantInfoService;

    public void create(HealthyInfoDO healthyInfoDO) {
        healthyInfoService.create(healthyInfoDO);
    }

    public List<HealthyInfoVO> getAllHealthyInfo() {
        return healthyInfoService.getAllHealthyInfo()
                .stream()
                .map(this::dO2VO)
                .collect(Collectors.toList());
    }

    private HealthyInfoVO dO2VO(HealthyInfoDO healthyInfoDO) {
        HealthyInfoVO healthyInfoVO = new HealthyInfoVO();
        ParticipantInfoDO participantInfoDO = participantInfoService.getParticipantInfoByUserIds(Collections.singletonList(healthyInfoDO.getUserId())).get(0);
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        healthyInfoVO.setUserName(participantInfoDO.getName());
        healthyInfoVO.setTemperature(healthyInfoDO.getTemperature());
        healthyInfoVO.setIsCough(bool2String(healthyInfoDO.getIsCough()));
        healthyInfoVO.setIsHistory(bool2String(healthyInfoDO.getIsHistory()));
        healthyInfoVO.setIsTough(bool2String(healthyInfoDO.getIsTouch()));
        healthyInfoVO.setTime(format.format(healthyInfoDO.getTime()));

        return healthyInfoVO;
    }

    private String bool2String(Boolean bo) {
        return bo ? "是" : "否";
    }
}
