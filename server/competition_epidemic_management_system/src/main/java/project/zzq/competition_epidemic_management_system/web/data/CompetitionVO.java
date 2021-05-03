package project.zzq.competition_epidemic_management_system.web.data;

import lombok.Data;

@Data
public class CompetitionVO {
    private Long id;
    private String name;
    private String placeName;
    private String startTime;
    private String endTime;
}
