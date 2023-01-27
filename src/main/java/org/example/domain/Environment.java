package org.example.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.ebean.Model;
import io.ebean.annotation.SoftDelete;

@Entity
public class Environment extends Model {
  @Id
  private UUID id;

  @SoftDelete
  private boolean deleted;

  private String name;

  public Environment(String name, boolean deleted) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.deleted = deleted;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
