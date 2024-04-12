package org.example.domain;

import io.ebean.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class Customer extends Model {

  @Id
  long id;

  String name;

  String notes;

  @Version
  long version;

  public Customer(String name) {
    this.name = name;
  }

  /**
   * Only used to demonstrate "stateless update".
   */
  public Customer() {

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
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

  public long getVersion() {
    return version;
  }

  public void setVersion(long version) {
    this.version = version;
  }

}
