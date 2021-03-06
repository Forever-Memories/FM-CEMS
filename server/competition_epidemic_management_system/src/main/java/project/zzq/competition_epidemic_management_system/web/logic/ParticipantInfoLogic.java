package project.zzq.competition_epidemic_management_system.web.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import project.zzq.competition_epidemic_management_system.data.ParticipantInfoDO;
import project.zzq.competition_epidemic_management_system.data.UserDO;
import project.zzq.competition_epidemic_management_system.service.OrganizeArrangementService;
import project.zzq.competition_epidemic_management_system.service.ParticipantInfoService;
import project.zzq.competition_epidemic_management_system.service.RegistryService;
import project.zzq.competition_epidemic_management_system.service.UserService;
import project.zzq.competition_epidemic_management_system.web.data.ParticipantCreateParam;
import project.zzq.competition_epidemic_management_system.web.data.ParticipantInfoVO;
import project.zzq.competition_epidemic_management_system.web.data.SearchParticipantParam;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipantInfoLogic {

    @Autowired
    private ParticipantInfoService participantInfoService;

    @Autowired
    private UserService userService;

    @Autowired
    private RegistryService registryService;

    @Autowired
    private OrganizeArrangementService organizeArrangementService;

    public void create(ParticipantCreateParam participantCreateParam) {
        Long userId = userService.signUpUser(participantCreateParam.getUserDO());
        ParticipantInfoDO participantInfoDO = participantCreateParam.getParticipantInfoDO();
        participantInfoDO.setUserId(userId);

        participantInfoService.create(participantInfoDO);
    }

    public List<ParticipantInfoVO> getParticipantInfoByUserIds(List<Long> userIds) {
        return participantInfoService.getParticipantInfoByUserIds(userIds).stream().map(this::participantInfoDO2VO).sorted(Comparator.comparingLong(ParticipantInfoVO::getUserId)).collect(Collectors.toList());
    }

    public List<ParticipantInfoVO> getAllParticipantInfo() {
        return participantInfoService.getAllParticipantInfo().stream().map(this::participantInfoDO2VO).sorted(Comparator.comparingLong(ParticipantInfoVO::getUserId)).collect(Collectors.toList());
    }

    public List<ParticipantInfoVO> searchParticipant(SearchParticipantParam searchParticipantParam) {
        List<ParticipantInfoVO> allParticipant = getAllParticipantInfo();
        if(!StringUtils.isEmpty(searchParticipantParam.getName())) {
            allParticipant = allParticipant.stream().filter(o -> o.getName().equals(searchParticipantParam.getName())).collect(Collectors.toList());
        }

        if(!StringUtils.isEmpty(searchParticipantParam.getUnit())) {
            allParticipant = allParticipant.stream().filter(o -> o.getUnit().equals(searchParticipantParam.getUnit())).collect(Collectors.toList());
        }

        return allParticipant;
    }

    public void edit(ParticipantInfoDO participantInfoDO) {
        participantInfoService.edit(participantInfoDO);
    }

    /**
     * 删除一个用户：同时删除账号，参赛详情和报名信息
     * @param userId
     */
    public void deleteByUserId(Long userId) {
        userService.delete(userId);
        participantInfoService.delete(userId);
        registryService.deleteByUserId(userId);
        organizeArrangementService.deleteByUserId(userId);
    }

    private ParticipantInfoVO participantInfoDO2VO(ParticipantInfoDO participantInfoDO) {
        UserDO userDO = userService.getUserById(participantInfoDO.getUserId());

        return new ParticipantInfoVO(participantInfoDO, userDO);
    }
}
