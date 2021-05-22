package project.zzq.competition_epidemic_management_system.storage;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import project.zzq.competition_epidemic_management_system.data.NoticeDO;

import java.util.List;

@Repository
public class NoticeStorage {

    private static final RowMapper<NoticeDO> ROW_MAPPER = (rs, rowNum) -> {
        NoticeDO noticeDO = new NoticeDO();

        noticeDO.setId(rs.getLong("id"));
        noticeDO.setNotice(rs.getString("notice"));
        noticeDO.setTime(rs.getLong("time"));

        return noticeDO;
    };

    @Autowired
    private NamedParameterJdbcOperations db;

    public void create(String notice, Long time) {
        db.update("INSERT INTO notice (notice, time) VALUES (:notice, :time)", ImmutableMap.of("notice", notice, "time", time));
    }

    public List<NoticeDO> getAllNotice() {
        return db.query("SELECT *FROM notice", ROW_MAPPER);
    }
}
