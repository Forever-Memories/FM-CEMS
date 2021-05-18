package project.zzq.competition_epidemic_management_system.storage;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import project.zzq.competition_epidemic_management_system.data.CompetitionInfoDO;

import java.lang.management.OperatingSystemMXBean;
import java.util.List;
import java.util.Optional;

@Repository
public class CompetitionInfoStorage {
    private final String ALL_COLUMNS = "`id`, `name`, `start_time`, `end_time`, `place_id`";

    private static final RowMapper<CompetitionInfoDO> ROW_MAPPER = (rs, rowNum) -> {
        CompetitionInfoDO competitionInfoDO = new CompetitionInfoDO();

        competitionInfoDO.setId(rs.getLong("id"));
        competitionInfoDO.setName(rs.getString("name"));
        competitionInfoDO.setStartTime(rs.getLong("start_time"));
        competitionInfoDO.setEndTime(rs.getLong("end_time"));
        competitionInfoDO.setPlaceId(rs.getLong("place_id"));

        return competitionInfoDO;
    };

    @Autowired
    private NamedParameterJdbcOperations db;

    public void create(CompetitionInfoDO competitionInfoDO) {
        SqlParameterSource source = new MapSqlParameterSource()
                .addValue("name", competitionInfoDO.getName())
                .addValue("start_time", competitionInfoDO.getStartTime())
                .addValue("end_time", competitionInfoDO.getEndTime())
                .addValue("place_id", competitionInfoDO.getPlaceId());

        String sql = "INSERT INTO competition_info (`name`, `start_time`, `end_time`, `place_id`) " +
                "VALUES (:name, :start_time, :end_time, :place_id)";

        db.update(sql, source);
    }

    public void edit(CompetitionInfoDO competitionInfoDO) {
        SqlParameterSource source = new MapSqlParameterSource()
                .addValue("name", competitionInfoDO.getName())
                .addValue("start_time", competitionInfoDO.getStartTime())
                .addValue("end_time", competitionInfoDO.getEndTime())
                .addValue("place_id", competitionInfoDO.getPlaceId())
                .addValue("id", competitionInfoDO.getId());

        String sql = "UPDATE competition_info SET name = :name," +
                "start_time = :start_time," +
                "end_time = :end_time," +
                "place_id = :place_id " +
                "WHERE id = :id";

        db.update(sql, source);
    }

    public List<CompetitionInfoDO> getAllCompetitionInfo() {
        String sql = "SELECT " + ALL_COLUMNS + "FROM `competition_info`";

        return db.query(sql, ROW_MAPPER);
    }

    public Optional<CompetitionInfoDO> getCompetitionById(Long competitionId) {
        String sql = "SELECT " + ALL_COLUMNS + "FROM `competition_info` WHERE `id` =:id";

        try {
            return Optional.of(db.queryForObject(sql, ImmutableMap.of("id", competitionId), ROW_MAPPER));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
