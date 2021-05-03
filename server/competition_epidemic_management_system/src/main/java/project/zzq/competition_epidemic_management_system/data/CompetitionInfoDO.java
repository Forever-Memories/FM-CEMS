package project.zzq.competition_epidemic_management_system.data;

import lombok.Data;

@Data
public class CompetitionInfoDO {
    private Long id;
    private String name;
    private Long placeId;
    private Long startTime;
    private Long endTime;
}
