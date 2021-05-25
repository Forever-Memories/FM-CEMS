package project.zzq.competition_epidemic_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.zzq.competition_epidemic_management_system.data.UserDO;
import project.zzq.competition_epidemic_management_system.storage.UserStorage;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserStorage userStorage;

    /**
     * 用户注册接口
     * @param userDO
     * @return
     */
    public Long signUpUser(UserDO userDO) {
        return userStorage.create(userDO);
    }

    public UserDO getUserById(Long userId) {
        Optional<UserDO> userOptional = userStorage.getUserByUserId(userId);

        if(!userOptional.isPresent()) {
            throw new RuntimeException(String.format("user is not exception, userId = %s", userId));
        }

        return userOptional.get();
    }

    public void delete(Long userId) {
        userStorage.delete(userId);
    }

    public boolean updatePassword(Long userId, String newPassword) {
        return userStorage.updatePassword(userId, newPassword);
    }

    public Optional<UserDO> getUserByPhoneNumber(String phoneNumber) {
        return userStorage.getUserByPhoneNumber(phoneNumber);
    }
}
