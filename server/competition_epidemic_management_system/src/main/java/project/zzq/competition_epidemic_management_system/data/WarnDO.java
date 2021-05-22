package project.zzq.competition_epidemic_management_system.data;

import lombok.Data;

@Data
public class WarnDO {
    private Long id;
    private Long userId;
    private Boolean isCough;
    private Boolean isHistory;
    private Boolean isTouch;
}
