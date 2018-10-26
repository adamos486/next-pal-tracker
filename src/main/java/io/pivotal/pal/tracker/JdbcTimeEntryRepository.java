package io.pivotal.pal.tracker;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class JdbcTimeEntryRepository implements TimeEntryRepository {

  private JdbcTemplate jdbcTemplate;

  public JdbcTimeEntryRepository(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  @Override public void delete(long id) {
    jdbcTemplate.update("DELETE FROM time_entries WHERE id = ?", id);
  }

  @Override public TimeEntry update(long id, TimeEntry expected) {
    jdbcTemplate.update("UPDATE time_entries SET project_id = ?, user_id = ?, date = ?, hours = ? WHERE id = ?",
        expected.getProjectId(),
        expected.getUserId(),
        expected.getDate(),
        expected.getHours(),
        id);
    return (TimeEntry) jdbcTemplate.queryForObject("SELECT id, project_id, user_id, date, hours FROM time_entries WHERE id = ?",
        new TimeEntryRowMapper(), id);
  }

  @Override public List<TimeEntry> list() {
    return jdbcTemplate.query("SELECT id, project_id, user_id, date, hours FROM time_entries", new TimeEntryRowMapper());
  }

  @Override public TimeEntry find(long id) {
    try {
      TimeEntry entry = (TimeEntry) jdbcTemplate.queryForObject("SELECT id, project_id, user_id, date, hours FROM time_entries WHERE id = ?",
          new TimeEntryRowMapper(), id);
      return entry;
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  @Override public TimeEntry create(TimeEntry newEntry) {
    KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(connection -> {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO time_entries (project_id, user_id, date, hours) " +
              "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
      );
      statement.setLong(1, newEntry.getProjectId());
      statement.setLong(2, newEntry.getUserId());
      statement.setDate(3, Date.valueOf(newEntry.getDate()));
      statement.setInt(4, newEntry.getHours());

      return statement;
    }, generatedKeyHolder);

    return find(generatedKeyHolder.getKey().longValue());
  }
}
