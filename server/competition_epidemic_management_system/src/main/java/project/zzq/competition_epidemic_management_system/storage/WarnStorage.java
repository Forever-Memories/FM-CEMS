package project.zzq.competition_epidemic_management_system.storage;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import project.zzq.competition_epidemic_management_system.data.WarnDO;

import java.util.List;
import java.util.Optional;

@Repository
public class WarnStorage {
    private static final RowMapper<WarnDO> ROW_MAPPER = (rs, rowNum) -> {
        WarnDO warnDO = new WarnDO();

        warnDO.setId(rs.getLong("id"));
        warnDO.setUserId(rs.getLong("user_id"));
        warnDO.setIsCough(rs.getBoolean("is_cough"));
        warnDO.setIsHistory(rs.getBoolean("is_history"));
        warnDO.setIsTouch(rs.getBoolean("is_touch"));

        return warnDO;
    };

    @Autowired
    private NamedParameterJdbcOperations db;

    public void create(WarnDO warnDO) {
        String sql = "INSERT INTO warn_info (user_id, is_cough, is_history, is_touch) " +
                "VALUES (:user_id, :is_cough, :is_history, :is_touch)";

        SqlParameterSource source = new MapSqlParameterSource()
                .addValue("user_id", warnDO.getUserId())
                .addValue("is_cough", warnDO.getIsCough())
                .addValue("is_history", warnDO.getIsHistory())
                .addValue("is_touch", warnDO.getIsTouch());

        db.update(sql, source);
    }

    public void update(WarnDO warnDO) {
        String sql = "UPDATE warn_info SET is_cough = :is_cough, is_history =:is_history, is_touch = :is_touch " +
                "WHERE id = :id";

        SqlParameterSource source = new MapSqlParameterSource()
                .addValue("id", warnDO.getId())
                .addValue("is_cough", warnDO.getIsCough())
                .addValue("is_history", warnDO.getIsHistory())
                .addValue("is_touch", warnDO.getIsTouch());

        db.update(sql, source);
    }

    public WarnDO getIdByUserId(Long userId) {
        try {
            return Optional.of(db.queryForObject("SELECT * FROM warn_info WHERE user_id = :user_id", ImmutableMap.of("user_id", userId), ROW_MAPPER)).get();
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void delete(Long id) {
        db.update("DELETE FROM warn_info WHERE id = :id", ImmutableMap.of("id", id));
    }

    public List<WarnDO> getAllWarn() {
        return db.query("SELECT *FROM warn_info", ROW_MAPPER);
    }

}
