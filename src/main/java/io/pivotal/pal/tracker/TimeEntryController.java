package io.pivotal.pal.tracker;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TimeEntryController {

  private TimeEntryRepository repository;

  public TimeEntryController(TimeEntryRepository repo) {
    this.repository = repo;
  }

  public ResponseEntity<TimeEntry> delete(long id) {
    repository.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  public ResponseEntity<TimeEntry> update(long id, TimeEntry toBeUpdated) {
    TimeEntry entry = repository.update(id, toBeUpdated);
    if (entry == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(entry, HttpStatus.OK);
  }

  public ResponseEntity<List<TimeEntry>> list() {
    return new ResponseEntity<>(repository.list(), HttpStatus.OK);
  }

  public ResponseEntity<TimeEntry> read(long id) {
    TimeEntry entry = repository.find(id);
    if (entry == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(entry, HttpStatus.OK);
  }

  public ResponseEntity<TimeEntry> create(TimeEntry newEntry) {
    return new ResponseEntity<>(repository.create(newEntry), HttpStatus.CREATED);
  }
}
