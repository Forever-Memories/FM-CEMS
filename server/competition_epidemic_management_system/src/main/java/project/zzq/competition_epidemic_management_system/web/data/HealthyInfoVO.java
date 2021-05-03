package project.zzq.competition_epidemic_management_system.web.data;

import lombok.Data;

@Data
public class HealthyInfoVO {
    private String userName;
    private Double temperature;
    private String isCough;
    private String isHistory;
    private String isTough;
    private String time;
}
