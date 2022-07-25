package org.example.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import io.ebean.CallableSql;
import io.ebean.DB;
import io.ebean.Database;

class DeserializationIssueTest {


  /**
   * Get the "default database" and save().
   */
  @Test
  void insertCategoryAndDeserializeNotificationFromJson() {

    Database server = DB.byName("otherdb");
    CallableSql callableSql = server.createCallableSql("BEGIN;\n" +
      "DROP TABLE IF EXISTS notifications;\n" +
      "DROP TABLE IF EXISTS notification_categories;\n" +
      "CREATE TABLE notification_categories (\n" +
      "\tid serial not null,\n" +
      "\torganization_id bigint,\n" +
      "\tname character varying(255),\n" +
      "\tcreated_at timestamp with time zone NOT NULL,\n" +
      "\t\"version\" bigint NOT NULL,\n" +
      "\tPRIMARY KEY(id)\n" +
      ");\n" +
      "\n" +
      "CREATE TABLE notifications (\n" +
      "\tid serial NOT NULL,\n" +
      "\tmessage character varying(255),\n" +
      "\tcategory_id bigint,\n" +
      "\tcreated_at timestamp with time zone NOT NULL,\n" +
      "\t\"version\" bigint NOT NULL,\n" +
      "\tPRIMARY KEY(id)\n" +
      ");\n" +
      "\n" +
      "ALTER TABLE IF EXISTS notifications\n" +
      "\tADD CONSTRAINT fk_notifications_category_id\n" +
      "\tFOREIGN KEY (category_id)\n" +
      "\tREFERENCES notification_categories (id)\n" +
      "\tON DELETE RESTRICT\n" +
      "\tON UPDATE RESTRICT;\n" +
      "\t\n" +
      "COMMIT;\n" +
      "\n");
    server.execute(callableSql);

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
