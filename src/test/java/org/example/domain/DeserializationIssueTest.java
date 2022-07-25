package org.example.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import io.ebean.DB;
import io.ebean.Database;

class DeserializationIssueTest {


  /**
   * Get the "default database" and save().
   */
  @Test
  void insertCategoryAndDeserializeNotificationFromJson() {

    Database server = DB.getDefault();

    NotificationCategory category = new NotificationCategory();
    category.setName("testCategory");
    category.setOrganizationId(1L);

    server.save(category);

    assertNotNull(category.getOrganizationId());

    String notificationJson = "{ \"message\": \"test message\", \"category\": { \"id\" : 1 } }";
    Notification notification = server.json().toBean(Notification.class, notificationJson);

    assertNull(notification.getCategory().getOrganizationId());
  }

}
