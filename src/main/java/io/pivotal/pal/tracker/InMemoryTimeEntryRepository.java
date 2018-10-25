package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
  private HashMap<Long, TimeEntry> entries = new HashMap<>();
  private long currentId = 1L;

  @Override public void delete(long id) {
    entries.remove(id);
  }

  @Override public TimeEntry update(long id, TimeEntry expected) {
    if (id != expected.getId()) {
      expected.setId(id);
    }
    entries.put(id, expected);
    return entries.get(id);
  }

  @Override public List<TimeEntry> list() {
    return new ArrayList<>(entries.values());
  }

  @Override public TimeEntry find(long id) {
    return entries.get(id);
  }

  @Override public TimeEntry create(TimeEntry newEntry) {
    newEntry.setId(currentId);
    entries.put(currentId, newEntry);
    TimeEntry entry = entries.get(currentId);
    currentId++;
    return entry;
  }
}
