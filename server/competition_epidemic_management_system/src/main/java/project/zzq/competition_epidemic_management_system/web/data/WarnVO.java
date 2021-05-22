package project.zzq.competition_epidemic_management_system.web.data;

import lombok.Data;

@Data
public class WarnVO {
    private Long id;
    private Long userId;
    private String userName;
    private String unit;
    private String comeFrom;
    private String isCough;
    private String isHistory;
    private String isTouch;
}
