package project.zzq.competition_epidemic_management_system.web.data;

import lombok.Data;

@Data
public class SignInResultVO {
    private Long userId;
    private Boolean result;
    private String name;
    private Integer userType;
}
