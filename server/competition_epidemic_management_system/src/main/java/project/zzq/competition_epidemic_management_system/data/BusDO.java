package project.zzq.competition_epidemic_management_system.data;

import lombok.Data;

@Data
public class BusDO {
    private Long id;
    private String placeName;
    private Long time;
    private String arrangement;
    private String comment;
}
