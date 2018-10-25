package io.pivotal.pal.tracker;

import java.time.LocalDate;

public class TimeEntry {

  private long id = -1;
  private long projectId = -1;
  private long userId = -1;
  private LocalDate date;
  private int hours;

  public TimeEntry(long projectId, long userId, LocalDate date, int hours) {
    this.projectId = projectId;
    this.userId = userId;
    this.date = date;
    this.hours = hours;
  }

  public TimeEntry(long id, long projectId, long userId, LocalDate date, int hours) {
    this.id = id;
    this.projectId = projectId;
    this.userId = userId;
    this.date = date;
    this.hours = hours;
  }

  public TimeEntry() {}

  public long getId() {
    return this.id;
  }

  protected void setId(long id) {
    this.id = id;
  }

  public long getProjectId() {
    return this.projectId;
  }

  public void setProjectId(long projectId) {
    this.projectId = projectId;
  }

  public long getUserId() {
    return this.userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public LocalDate getDate() {
    return this.date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public int getHours() {
    return this.hours;
  }

  public void setHours(int hours) {
    this.hours = hours;
  }

  protected void print() {
    System.out.println("id:: " + this.id);
    System.out.println("projectId:: " + this.projectId);
    System.out.println("userId:: " + this.userId);
    System.out.println("date:: " + ((this.date == null) ? "null" : this.date.toString()));
    System.out.println("hours:: " + this.hours);
  }

  @Override public boolean equals(Object obj) {
    TimeEntry incoming = (TimeEntry)obj;
    return (this.id == incoming.id) &&
        (this.projectId == incoming.projectId) &&
        (this.userId == incoming.userId) &&
        (this.date.equals(incoming.date)) &&
        (this.hours == incoming.hours);
  }
}
