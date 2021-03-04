package project.zzq.competition_epidemic_management_system.data;

import lombok.Data;

@Data
public class UserDO {

    private Long id;
    private String phoneNumber;
    private String password;
    private UserType type;
}
