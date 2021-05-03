package project.zzq.competition_epidemic_management_system.web.data;

import lombok.Data;
import project.zzq.competition_epidemic_management_system.data.ParticipantInfoDO;
import project.zzq.competition_epidemic_management_system.data.UserDO;

@Data
public class ParticipantCreateParam {
    private ParticipantInfoDO participantInfoDO;
    private UserDO userDO;
}
