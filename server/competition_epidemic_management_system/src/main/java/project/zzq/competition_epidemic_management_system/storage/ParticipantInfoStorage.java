package project.zzq.competition_epidemic_management_system.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import project.zzq.competition_epidemic_management_system.data.ParticipantInfoDO;

import java.nio.file.OpenOption;


/**
 * @author zhuzheqing
 */
@Repository
public class ParticipantInfoStorage {
    private final String ALL_COLUMNS = "`user_id`, `name`, `unit`, `id_number` ";

    private static final RowMapper<ParticipantInfoDO> ROW_MAPPER = (rs, rowNum) -> {
        ParticipantInfoDO participantInfoDO = new ParticipantInfoDO();

        participantInfoDO.setUserId(rs.getLong("user_id"));
        participantInfoDO.setName(rs.getString("name"));
        participantInfoDO.setUnit("unit");
        participantInfoDO.setIdNumber("id_number");

        return participantInfoDO;
    };

    @Autowired
    private NamedParameterJdbcOperations db;

    public void create(ParticipantInfoDO participantInfoDO) {
        SqlParameterSource source = new MapSqlParameterSource()
                .addValue("user_id", participantInfoDO.getUserId())
                .addValue("name", participantInfoDO.getName())
                .addValue("unit", participantInfoDO.getUnit())
                .addValue("id_number", participantInfoDO.getIdNumber());

        String sql = "INSERT INTO `participants_info` (`user_id`, `name`, `unit`, `id_number`) " +
                "VALUES (:user_id, :name, :unit, :id_number)";

        db.update(sql, source);
    }

}
