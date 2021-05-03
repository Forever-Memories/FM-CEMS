package project.zzq.competition_epidemic_management_system.storage;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import project.zzq.competition_epidemic_management_system.data.RegistryDO;

import java.util.List;

@Repository
public class RegistryStorage {
    private final String ALL_COLUMNS = "`user_id`, `competition_id`";

    private static final RowMapper<RegistryDO> ROW_MAPPER = (rs, rowNum) -> {
        RegistryDO registryDO = new RegistryDO();

        registryDO.setCompetitionId(rs.getLong("competition_id"));
        registryDO.setUserId(rs.getLong("user_id"));

        return registryDO;
    };

    @Autowired
    private NamedParameterJdbcOperations db;

    public void create(RegistryDO registryDO) {
        String sql = "INSERT INTO registry (`user_id`, `competition_id`) " +
                "VALUES (:user_id, :competition_id)";

        db.update(sql, ImmutableMap.of("user_id", registryDO.getUserId(), "competition_id", registryDO.getCompetitionId()));
    }

    public List<RegistryDO> getAllRegistry() {
        String sql = "SELECT " + ALL_COLUMNS + "FROM `registry`";

        return db.query(sql, ROW_MAPPER);
    }
}
