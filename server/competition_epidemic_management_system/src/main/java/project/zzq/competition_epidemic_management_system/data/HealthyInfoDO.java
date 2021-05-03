package project.zzq.competition_epidemic_management_system.data;

import lombok.Data;

@Data
public class HealthyInfoDO {
    private Long id;
    private Long userId;
    private Double temperature;
    private Boolean isCough;
    private Boolean isHistory;
    private Boolean isTouch;
    private Long time;
}
