package project.zzq.competition_epidemic_management_system.web.data;

import lombok.Data;
import project.zzq.competition_epidemic_management_system.data.UserDO;
import project.zzq.competition_epidemic_management_system.data.UserType;

/**
 * @author zhuzheqing
 * 专门用于普通用户注册的VO类
 */
@Data
public class UserSignUpVO {
    private String phoneNumber;
    private String password;

    public UserDO toUserDO() {
        UserDO userDO = new UserDO();
        userDO.setPhoneNumber(this.phoneNumber);
        userDO.setPassword(this.password);
        userDO.setType(UserType.PARTICIPANT);

        return userDO;
    }
}
