package project.zzq.competition_epidemic_management_system.data;

import java.util.Optional;

/**
 * @author zhuzheqing
 * 用户类型
 */
public enum UserType {

    PARTICIPANT(1, "参赛人员"),
    ORGANIZER(2,"组织人员"),
    ADMINISTRATOR(3, "管理员"),
    SUPER_ADMINISTRATOR(4, "超级管理员")
    ;

    private int value;

    private String name;

    UserType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Optional<UserType> findByInt(int value) {
        for (UserType item : UserType.values()) {
            if (item.value == value) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }

    public static Optional<UserType> findByString(String name) {
        for (UserType item : UserType.values()) {
            if (item.name.equals(name)) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }


    @Override
    public String toString() {
        return this.name;
    }

    public int toInt() {
        return this.value;
    }
}
