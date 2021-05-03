package project.zzq.competition_epidemic_management_system.storage;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import project.zzq.competition_epidemic_management_system.data.PlaceInfoDO;

import java.util.List;
import java.util.Optional;

@Repository
public class PlaceInfoStorage {
    private final String ALL_COLUMNS = "`id`, `name`";

    private static final RowMapper<PlaceInfoDO> ROW_MAPPER = (rs, rowNum) -> {
        PlaceInfoDO placeInfoDO = new PlaceInfoDO();

        placeInfoDO.setId(rs.getLong("id"));
        placeInfoDO.setName(rs.getString("name"));

        return placeInfoDO;
    };

    @Autowired
    private NamedParameterJdbcOperations db;

    public void create(String name) {
        String sql = "INSERT INTO place_info (`name`) " +
                "VALUES (:name)";

        db.update(sql, ImmutableMap.of("name", name));
    }

    public List<PlaceInfoDO> getAllPlaceInfo() {
        String sql = "SELECT " + ALL_COLUMNS + "FROM `place_info`";

        return db.query(sql, ROW_MAPPER);
    }

    public Optional<PlaceInfoDO> getPlaceInfoById(Long placeId) {
        String sql = "SELECT "+ALL_COLUMNS+"FROM `place_info` WHERE `id` =:id";

        try {
            return Optional.of(db.queryForObject(sql, ImmutableMap.of("id", placeId), ROW_MAPPER));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
