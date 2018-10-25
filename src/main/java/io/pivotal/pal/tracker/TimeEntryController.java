package io.pivotal.pal.tracker;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController public class TimeEntryController {

  @Autowired private TimeEntryRepository repository;

  public TimeEntryController(TimeEntryRepository repo) {
    this.repository = repo;
  }

  @DeleteMapping("/time-entries/{id}")
  public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
    repository.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/time-entries/{id}") public ResponseEntity<TimeEntry> update(@PathVariable long id,
      @RequestBody TimeEntry toBeUpdated) {
    TimeEntry entry = repository.update(id, toBeUpdated);
    if (entry == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(entry, HttpStatus.OK);
  }

  @GetMapping("/time-entries") public ResponseEntity<List<TimeEntry>> list() {
    return new ResponseEntity<>(repository.list(), HttpStatus.OK);
  }

  @GetMapping("/time-entries/{id}") public ResponseEntity<TimeEntry> read(@PathVariable long id) {
    TimeEntry entry = repository.find(id);
    if (entry == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(entry, HttpStatus.OK);
  }

  @PostMapping("/time-entries")
  public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry newEntry) {
    return new ResponseEntity<>(repository.create(newEntry), HttpStatus.CREATED);
  }
}
