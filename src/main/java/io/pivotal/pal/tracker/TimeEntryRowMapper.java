package io.pivotal.pal.tracker;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class TimeEntryRowMapper implements RowMapper {
  @Override public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    TimeEntry entry = new TimeEntry();
    entry.setId(rs.getLong("id"));
    entry.setProjectId(rs.getLong("project_id"));
    entry.setUserId(rs.getLong("user_id"));
    entry.setDate(rs.getDate("date").toLocalDate());
    entry.setHours(rs.getInt("hours"));
    return entry;
  }
}
