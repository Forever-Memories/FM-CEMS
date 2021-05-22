package project.zzq.competition_epidemic_management_system.web.data;

import lombok.Data;
import project.zzq.competition_epidemic_management_system.data.ParticipantInfoDO;
import project.zzq.competition_epidemic_management_system.data.UserDO;

@Data
public class ParticipantInfoVO {
    private Long userId;
    private String name;
    private String unit;
    private String idNumber;
    private String comeFrom;
    private String phoneNumber;
    private String type;

    public ParticipantInfoVO(ParticipantInfoDO participantInfoDO, UserDO userDO) {
        this.userId = participantInfoDO.getUserId();
        this.name = participantInfoDO.getName();
        this.unit = participantInfoDO.getUnit();
        this.idNumber = participantInfoDO.getIdNumber();
        this.comeFrom = participantInfoDO.getComeFrom();
        this.type = userDO.getType().toString();
        this.phoneNumber = userDO.getPhoneNumber();
    }
}
