package io.pivotal.pal.tracker;
import org.springframework.boot.actuate.metrics.CounterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.GaugeService;
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
  private final CounterService counter;
  private final GaugeService gauge;

  public TimeEntryController(
      TimeEntryRepository repo,
      CounterService counter,
      GaugeService gauge
  ) {
    this.repository = repo;
    this.counter = counter;
    this.gauge = gauge;
  }

  @DeleteMapping("/time-entries/{id}")
  public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
    repository.delete(id);
    counter.increment("TimeEntry.deleted");
    gauge.submit("timeEntries.count", repository.list().size());
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/time-entries/{id}") public ResponseEntity<TimeEntry> update(@PathVariable long id,
      @RequestBody TimeEntry toBeUpdated) {
    TimeEntry entry = repository.update(id, toBeUpdated);
    if (entry == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    counter.increment("TimeEntry.updated");
    return new ResponseEntity<>(entry, HttpStatus.OK);
  }

  @GetMapping("/time-entries") public ResponseEntity<List<TimeEntry>> list() {
    counter.increment("TimeEntry.listed");
    return new ResponseEntity<>(repository.list(), HttpStatus.OK);
  }

  @GetMapping("/time-entries/{id}") public ResponseEntity<TimeEntry> read(@PathVariable long id) {
    TimeEntry entry = repository.find(id);
    if (entry == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    counter.increment("TimeEntry.read");
    return new ResponseEntity<>(entry, HttpStatus.OK);
  }

  @PostMapping("/time-entries")
  public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry newEntry) {
    counter.increment("TimeEntry.created");
    gauge.submit("timeEntries.count", repository.list().size());
    return new ResponseEntity<>(repository.create(newEntry), HttpStatus.CREATED);
  }
}
