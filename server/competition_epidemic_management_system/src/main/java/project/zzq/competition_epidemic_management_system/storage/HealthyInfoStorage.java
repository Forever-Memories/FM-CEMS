package project.zzq.competition_epidemic_management_system.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import project.zzq.competition_epidemic_management_system.data.HealthyInfoDO;

import java.util.List;

@Repository
public class HealthyInfoStorage {
    private final String ALL_COLUMNS = "`id`, `user_id`, `temperature`, `is_cough`, `is_history`, `is_touch`, `time`";

    private static final RowMapper<HealthyInfoDO> ROW_MAPPER = (rs, rowNum) -> {
        HealthyInfoDO healthyInfoDO = new HealthyInfoDO();

        healthyInfoDO.setId(rs.getLong("id"));
        healthyInfoDO.setUserId(rs.getLong("user_id"));
        healthyInfoDO.setTemperature(rs.getDouble("temperature"));
        healthyInfoDO.setIsCough(rs.getBoolean("is_cough"));
        healthyInfoDO.setIsHistory(rs.getBoolean("is_history"));
        healthyInfoDO.setIsTouch(rs.getBoolean("is_touch"));
        healthyInfoDO.setTime(rs.getLong("time"));

        return healthyInfoDO;
    };

    @Autowired
    private NamedParameterJdbcOperations db;

    public void create(HealthyInfoDO healthyInfoDO) {
        SqlParameterSource source = new MapSqlParameterSource()
                .addValue("user_id", healthyInfoDO.getUserId())
                .addValue("temperature", healthyInfoDO.getTemperature())
                .addValue("is_cough", healthyInfoDO.getIsCough())
                .addValue("is_history", healthyInfoDO.getIsHistory())
                .addValue("is_touch", healthyInfoDO.getIsTouch())
                .addValue("time", System.currentTimeMillis());

        String sql = "INSERT INTO healthyInfo (`user_id`, `temperature`, `is_cough`, `is_history`, `is_touch`, `time`) " +
                "VALUES (:user_id, :temperature, :is_cough, :is_history, :is_touch, :time)";

        db.update(sql, source);
    }

    public List<HealthyInfoDO> getAllHealthyInfo() {
        String sql = "SELECT " + ALL_COLUMNS + "FROM `healthyInfo`";

        return db.query(sql, ROW_MAPPER);
    }

}
