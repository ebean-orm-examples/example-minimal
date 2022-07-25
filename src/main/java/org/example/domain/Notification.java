package org.example.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import io.ebean.annotation.WhenCreated;

@Entity
@Table(name = "notifications")
public class Notification {

  @Id
  private Long id;

  @Column
  private String message;

  @WhenCreated
  private Date createdAt;

  @ManyToOne
  private NotificationCategory category;

  @Version
  private Long version;

  public final Long getId() {
    return id;
  }

  public final void setId(Long id) {
    this.id = id;
  }

  public final Date getCreatedAt() {
    return createdAt;
  }

  public final void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public final NotificationCategory getCategory() {
    return category;
  }

  public final void setCategory(NotificationCategory category) {
    this.category = category;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
