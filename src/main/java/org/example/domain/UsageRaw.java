package org.example.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import io.ebean.Model;

@Entity
public class UsageRaw extends Model {
  @Id
  private UUID id;

  @ManyToOne
  private ServiceAccount serviceAccount;

  private String name;

  public UsageRaw(String name, ServiceAccount serviceAccount) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.serviceAccount = serviceAccount;
  }

  public UUID getId() {
    return id;
  }

  public ServiceAccount getServiceAccount() {
    return serviceAccount;
  }

  public String getName() {
    return name;
  }
}
