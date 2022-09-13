package org.example.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import io.ebean.Model;
import io.ebean.annotation.SoftDelete;

@Entity
public class ServiceAccount extends Model {
  @Id
  private UUID id;

  @OneToOne
  private Environment environment;

  @SoftDelete
  private boolean deleted;

  private String name;

  public ServiceAccount(String name, Environment environment, boolean deleted) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.environment = environment;
    this.deleted = deleted;
  }

  public UUID getId() {
    return id;
  }

  public Environment getEnvironment() {
    return environment;
  }

  public String getName() {
    return name;
  }
}
