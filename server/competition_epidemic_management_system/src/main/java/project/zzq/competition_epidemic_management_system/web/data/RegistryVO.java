package project.zzq.competition_epidemic_management_system.web.data;

import lombok.Data;

@Data
public class RegistryVO {
    private String userName;
    private String userUnit;
    private Long userId;
    private Long competitionId;
    private String competitionName;
    private String competitionStartTime;
}
