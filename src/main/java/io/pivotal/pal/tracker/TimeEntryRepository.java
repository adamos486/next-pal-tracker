package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository {

  void delete(long id);

  TimeEntry update(long id, TimeEntry expected);

  List<TimeEntry> list();

  TimeEntry find(long id);

  TimeEntry create(TimeEntry newEntry);
}
