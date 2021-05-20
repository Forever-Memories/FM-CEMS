package project.zzq.competition_epidemic_management_system.web.data;

import lombok.Data;

@Data
public class OrganizeArrangementVO {
    private Long id;
    private Long userId;
    private String userName;
    private Long placeId;
    private String placeName;
    private String startTime;
    private String endTime;
}
