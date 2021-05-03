package project.zzq.competition_epidemic_management_system.web.data;

import lombok.Data;

@Data
public class BusVO {
    private Long id;
    private String placeName;
    private String time;
    private String arrangement;
    private String comment;
}
