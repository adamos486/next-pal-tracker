package io.pivotal.pal.tracker;

import java.util.List;
import org.springframework.http.ResponseEntity;

public class TimeEntryController {

  private TimeEntryRepository repository;

  public TimeEntryController(TimeEntryRepository repo) {
    this.repository = repo;
  }

  public ResponseEntity<TimeEntry> delete(long id) {
    return null;
  }

  public ResponseEntity<TimeEntry> update(long id, TimeEntry toBeUpdated) {
    return null;
  }

  public ResponseEntity<List<TimeEntry>> list() {
    return null;
  }

  public ResponseEntity<TimeEntry> read(long id) {
    return null;
  }

  public ResponseEntity<TimeEntry> create(TimeEntry newEntry) {
    return null;
  }
}
