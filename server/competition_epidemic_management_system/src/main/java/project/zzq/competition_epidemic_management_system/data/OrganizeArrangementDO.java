package project.zzq.competition_epidemic_management_system.data;

import lombok.Data;

@Data
public class OrganizeArrangementDO {
    private Long id;
    private Long userId;
    private Long placeId;
    private Long startTime;
    private Long endTime;
}
