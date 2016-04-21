package org.example.domain;

import com.avaje.ebean.annotation.WhenCreated;
import com.avaje.ebean.annotation.WhenModified;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import java.time.OffsetDateTime;

@Entity
public class Customer {

  @Id
  Long id;

  String name;

  String notes;

  @Version
  Long version;

  @WhenCreated
  OffsetDateTime whenCreated;

  @WhenModified
  OffsetDateTime whenModified;

  public Customer(String name) {
    this.name = name;
  }

  public Customer() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public OffsetDateTime getWhenCreated() {
    return whenCreated;
  }

  public void setWhenCreated(OffsetDateTime whenCreated) {
    this.whenCreated = whenCreated;
  }

  public OffsetDateTime getWhenModified() {
    return whenModified;
  }

  public void setWhenModified(OffsetDateTime whenModified) {
    this.whenModified = whenModified;
  }
}
