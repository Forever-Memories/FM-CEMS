package project.zzq.competition_epidemic_management_system.web.data;

import lombok.Data;

@Data
public class UpdatePasswordVO {
    private String phoneNumber;
    private String oldPassword;
    private String newPassword;
}
