package project.zzq.competition_epidemic_management_system.data;

import lombok.Data;

/**
 * @author zhuzheqing
 */
@Data
public class ParticipantInfoDO {
    private Long userId;
    private String name;
    private String unit;
    private String idNumber;
    private String comeFrom;
}
