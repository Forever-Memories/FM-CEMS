package project.zzq.competition_epidemic_management_system.web.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import project.zzq.competition_epidemic_management_system.data.HealthyInfoDO;
import project.zzq.competition_epidemic_management_system.data.ParticipantInfoDO;
import project.zzq.competition_epidemic_management_system.data.WarnDO;
import project.zzq.competition_epidemic_management_system.service.HealthyInfoService;
import project.zzq.competition_epidemic_management_system.service.ParticipantInfoService;
import project.zzq.competition_epidemic_management_system.storage.NoticeStorage;
import project.zzq.competition_epidemic_management_system.storage.WarnStorage;
import project.zzq.competition_epidemic_management_system.web.data.HealthyInfoVO;
import project.zzq.competition_epidemic_management_system.web.data.SearchHealthyInfoParam;

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

    @Autowired
    private NoticeStorage noticeStorage;

    @Autowired
    private WarnStorage warnStorage;

    public void create(HealthyInfoDO healthyInfoDO) {
        healthyInfoService.create(healthyInfoDO);

        ParticipantInfoDO participantInfoDO = participantInfoService.getParticipantInfoByUserIds(Collections.singletonList(healthyInfoDO.getUserId())).get(0);

        if(healthyInfoDO.getIsCough() || healthyInfoDO.getIsHistory() || healthyInfoDO.getIsTouch()) {
            WarnDO warnDO = warnStorage.getIdByUserId(healthyInfoDO.getUserId());

            if (warnDO == null) {
                warnDO = new WarnDO();
                warnDO.setUserId(healthyInfoDO.getUserId());
                warnDO.setIsCough(healthyInfoDO.getIsCough());
                warnDO.setIsHistory(healthyInfoDO.getIsHistory());
                warnDO.setIsTouch(healthyInfoDO.getIsTouch());

                warnStorage.create(warnDO);
            } else {
                warnDO.setIsCough(healthyInfoDO.getIsCough());
                warnDO.setIsHistory(healthyInfoDO.getIsHistory());
                warnDO.setIsTouch(healthyInfoDO.getIsTouch());

                warnStorage.update(warnDO);
            }

            noticeStorage.create(String.format("来自%s的%s健康状态发生异常，请管理人员及时处理突发状况!", participantInfoDO.getUnit(), participantInfoDO.getName()), System.currentTimeMillis());
        }
    }

    public List<HealthyInfoVO> getAllHealthyInfo() {
        return healthyInfoService.getAllHealthyInfo()
                .stream()
                .map(this::dO2VO)
                .collect(Collectors.toList());
    }

    public List<HealthyInfoVO> searchHealthyByUserName(String userName) {
        if(StringUtils.isEmpty(userName)) {
            return getAllHealthyInfo();
        }

        List<ParticipantInfoDO> participantInfoDOS = participantInfoService.getParticipantInfoByUserName(userName);
        if (participantInfoDOS.isEmpty()) {
            return Collections.emptyList();
        }

        return healthyInfoService.searchHealthyByUserIds(participantInfoDOS.stream().map(ParticipantInfoDO::getUserId).collect(Collectors.toList()))
                .stream()
                .map(this::dO2VO)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        healthyInfoService.deleteById(id);
    }

    private HealthyInfoVO dO2VO(HealthyInfoDO healthyInfoDO) {
        HealthyInfoVO healthyInfoVO = new HealthyInfoVO();
        ParticipantInfoDO participantInfoDO = participantInfoService.getParticipantInfoByUserIds(Collections.singletonList(healthyInfoDO.getUserId())).get(0);
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        healthyInfoVO.setId(healthyInfoDO.getId());
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
