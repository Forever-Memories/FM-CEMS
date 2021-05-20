package project.zzq.competition_epidemic_management_system.storage;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import project.zzq.competition_epidemic_management_system.data.OrganizeArrangementDO;

import java.util.List;

@Repository
public class OrganizeArrangementStorage {
    private static final RowMapper<OrganizeArrangementDO> ROW_MAPPER = (rs, rowNum) -> {
        OrganizeArrangementDO organizeArrangementDO = new OrganizeArrangementDO();

        organizeArrangementDO.setId(rs.getLong("id"));
        organizeArrangementDO.setUserId(rs.getLong("userId"));
        organizeArrangementDO.setPlaceId(rs.getLong("place_id"));
        organizeArrangementDO.setStartTime(rs.getLong("start_time"));
        organizeArrangementDO.setEndTime(rs.getLong("end_time"));

        return organizeArrangementDO;
    };

    @Autowired
    private NamedParameterJdbcOperations db;

    public void create(OrganizeArrangementDO organizeArrangementDO) {
        String sql = "INSERT INTO organize_arrangement (userId, place_id, start_time, end_time) " +
                "VALUES (:user_id, :place_id, :start_time, :end_time)";

        SqlParameterSource source = new MapSqlParameterSource()
                .addValue("user_id", organizeArrangementDO.getUserId())
                .addValue("place_id", organizeArrangementDO.getPlaceId())
                .addValue("start_time", organizeArrangementDO.getStartTime())
                .addValue("end_time", organizeArrangementDO.getEndTime());

        db.update(sql, source);
    }

    public List<OrganizeArrangementDO> getOrganizeArrangementByUserId(Long userId) {
        String sql = "SELECT *FROM `organize_arrangement` WHERE userId = :userId";

        return db.query(sql, ImmutableMap.of("userId", userId),ROW_MAPPER);
    }

    public List<OrganizeArrangementDO> getAllOrganizeArrangement() {
        String sql = "SELECT *FROM `organize_arrangement`";

        return db.query(sql, ROW_MAPPER);
    }

    public void delete(Long id) {
        db.update("DELETE FROM organize_arrangement WHERE id = :id", ImmutableMap.of("id", id));
    }

    public void deleteByUserId(Long userId) {
        db.update("DELETE FROM organize_arrangement WHERE userId = :userId", ImmutableMap.of("userId", userId));
    }
}
