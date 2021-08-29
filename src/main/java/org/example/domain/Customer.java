package org.example.domain;

import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import java.time.ZonedDateTime;

@Entity
public class Customer extends Model {

  @Id
  Long id;

  String name;

  String notes;

  @Column(name = "MyDate", nullable = false)
  ZonedDateTime mydate;

  @Version
  Long version;

  public Customer(String name) {
    this.name = name;
  }

  public Customer() {
  }

  public Long id() {
    return id;
  }

  public void id(Long id) {
    this.id = id;
  }

  public void notes(String notes) {
    this.notes = notes;
  }

  public ZonedDateTime mydate() {
    return mydate;
  }

  public Customer mydate(ZonedDateTime mydate) {
    this.mydate = mydate;
    return this;
  }

}
