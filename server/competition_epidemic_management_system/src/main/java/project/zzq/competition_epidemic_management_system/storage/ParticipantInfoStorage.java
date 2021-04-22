package project.zzq.competition_epidemic_management_system.storage;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import project.zzq.competition_epidemic_management_system.data.ParticipantInfoDO;

import java.nio.file.OpenOption;
import java.util.List;


/**
 * @author zhuzheqing
 */
@Repository
public class ParticipantInfoStorage {
    private final String ALL_COLUMNS = "`user_id`, `name`, `unit`, `id_number` ,`come_from`";

    private static final RowMapper<ParticipantInfoDO> ROW_MAPPER = (rs, rowNum) -> {
        ParticipantInfoDO participantInfoDO = new ParticipantInfoDO();

        participantInfoDO.setUserId(rs.getLong("user_id"));
        participantInfoDO.setName(rs.getString("name"));
        participantInfoDO.setUnit(rs.getString("unit"));
        participantInfoDO.setIdNumber(rs.getString("id_number"));
        participantInfoDO.setComeFrom(rs.getString("come_from"));

        return participantInfoDO;
    };

    @Autowired
    private NamedParameterJdbcOperations db;

    public void create(ParticipantInfoDO participantInfoDO) {
        SqlParameterSource source = new MapSqlParameterSource()
                .addValue("user_id", participantInfoDO.getUserId())
                .addValue("name", participantInfoDO.getName())
                .addValue("unit", participantInfoDO.getUnit())
                .addValue("id_number", participantInfoDO.getIdNumber())
                .addValue("come_from", participantInfoDO.getComeFrom());

        String sql = "INSERT INTO `participants_info` (`user_id`, `name`, `unit`, `id_number`, `come_from`) " +
                "VALUES (:user_id, :name, :unit, :id_number, :come_from)";

        db.update(sql, source);
    }

    public List<ParticipantInfoDO> getParticipantInfoByUserIds(List<Long> userIds) {
        String sql = "SELECT " + ALL_COLUMNS + "FROM participants_info WHERE user_id IN (:userIds)";

        return db.query(sql, ImmutableMap.of("userIds", userIds), ROW_MAPPER);
    }

    public List<ParticipantInfoDO> getAllParticipantInfo() {
        String sql = "SELECT " + ALL_COLUMNS + "FROM participants_info";

        return db.query(sql, ROW_MAPPER);
    }
}
