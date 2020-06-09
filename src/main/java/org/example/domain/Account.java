package org.example.domain;

import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Account extends Model {

  @Id @Column(name = "ID")
  Long id;

  String name;

  String notes;

  @Version
  Long version;

  public Account(String name) {
    this.name = name;
  }

  public Account() {
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

}
