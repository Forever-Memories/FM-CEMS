package project.zzq.competition_epidemic_management_system.storage;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import project.zzq.competition_epidemic_management_system.data.UserDO;
import project.zzq.competition_epidemic_management_system.data.UserType;

import java.util.Optional;


@Repository
@Slf4j
public class UserStorage {
    private final String ALL_COLUMNS = "`id`, `phone_number`, `password`, `type` ";

    private static final RowMapper<UserDO> ROW_MAPPER = (rs, rowNum) -> {
        UserDO userDO = new UserDO();

        Optional<UserType> userTypeOp = UserType.findByInt(rs.getInt("type"));
        if (!userTypeOp.isPresent()) {
            throw new IllegalStateException("illegal userType");
        }

        userDO.setId(rs.getLong("id"));
        userDO.setPhoneNumber(rs.getString("phone_number"));
        userDO.setPassword(rs.getString("password"));
        userDO.setType(userTypeOp.get());

        return userDO;
    };

    @Autowired
    private NamedParameterJdbcOperations db;

    public Long create(UserDO userDO) {
        SqlParameterSource source = new MapSqlParameterSource()
                .addValue("phone_number", userDO.getPhoneNumber())
                .addValue("password", userDO.getPassword())
                .addValue("type", userDO.getType().toInt());

        String sql = "INSERT INTO `user` (`phone_number`, `password`, `type`) " +
                "VALUES (:phone_number, :password, :type)";

        KeyHolder kh = new GeneratedKeyHolder();

        try {
            db.update(sql, source, kh);
        } catch (DuplicateKeyException e) {
            log.error("create user failed:{}", userDO.toString());
            return 0L;
        }

        return kh.getKey().longValue();
    }


    public Optional<UserDO> getUserByPhoneNumber(String phoneNumber) {
        String sql = "SELECT "+ALL_COLUMNS+"FROM `user` WHERE `phone_number` =:phoneNumber";

        try {
            return Optional.of(db.queryForObject(sql, ImmutableMap.of("phoneNumber", phoneNumber), ROW_MAPPER));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<UserDO> getUserByUserId(Long userId) {
        String sql = "SELECT "+ALL_COLUMNS+"FROM `user` WHERE `id` =:userId";

        try {
            return Optional.of(db.queryForObject(sql, ImmutableMap.of("userId", userId), ROW_MAPPER));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

}
