package project.zzq.competition_epidemic_management_system.storage;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import project.zzq.competition_epidemic_management_system.data.BusDO;

import java.util.List;


@Repository
public class BusStorage {
    private final String ALL_COLUMNS = "`id`, `place_name`, `time`, `arrangement`, `comment`";

    private static final RowMapper<BusDO> ROW_MAPPER = (rs, rowNum) -> {
        BusDO busDO = new BusDO();

        busDO.setId(rs.getLong("id"));
        busDO.setPlaceName(rs.getString("place_name"));
        busDO.setTime(rs.getLong("time"));
        busDO.setArrangement(rs.getString("arrangement"));
        busDO.setComment(rs.getString("comment"));

        return busDO;
    };

    @Autowired
    private NamedParameterJdbcOperations db;

    public void create(BusDO busDO) {
        String sql = "INSERT INTO bus (`place_name`, `time`, `arrangement`, `comment`) " +
                "VALUES (:place_name, :time, :arrangement, :comment)";

        SqlParameterSource source = new MapSqlParameterSource()
                .addValue("place_name", busDO.getPlaceName())
                .addValue("time", busDO.getTime())
                .addValue("arrangement", busDO.getArrangement())
                .addValue("comment", busDO.getComment());

        db.update(sql, source);
    }

    public void edit(BusDO busDO) {
        String sql = "UPDATE bus SET place_name = :place_name," +
                "time = :time," +
                "arrangement = :arrangement," +
                "comment = :comment " +
                "WHERE id = :id";

        SqlParameterSource source = new MapSqlParameterSource()
                .addValue("place_name", busDO.getPlaceName())
                .addValue("time", busDO.getTime())
                .addValue("arrangement", busDO.getArrangement())
                .addValue("comment", busDO.getComment())
                .addValue("id", busDO.getId());

        db.update(sql, source);
    }

    public List<BusDO> getAllBus() {
        String sql = "SELECT " + ALL_COLUMNS + "FROM `bus`";

        return db.query(sql, ROW_MAPPER);
    }

    public void deleteById(Long busId) {
        db.update("DELETE FROM bus WHERE id = :id", ImmutableMap.of("id", busId));
    }
}
