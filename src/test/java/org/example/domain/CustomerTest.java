package org.example.domain;

import io.ebean.DB;
import io.ebean.Database;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * When running tests in the IDE install the "Enhancement plugin".
 * <p>
 * http://ebean-orm.github.io/docs/setup/enhancement#ide
 */
public class CustomerTest {


  /**
   * Get the "default database" and save().
   */
  @Test
  public void insert_via_database() {

    Customer rob = new Customer("Rob");
    rob.mydate(ZonedDateTime.now());

    Database server = DB.getDefault();
    server.save(rob);

    assertNotNull(rob.id());
  }

  /**
   * Use the Ebean singleton (effectively using the "default server").
   */
  @Test
  public void insert_via_model() {

    Customer jim = new Customer("Jim");
    jim.mydate(ZonedDateTime.now());
    jim.save();

    assertNotNull(jim.id());
  }


  /**
   * Find and then update.
   */
  @Test
  public void updateRob() {

    Customer newBob = new Customer("Bob");
    newBob.mydate(ZonedDateTime.now());
    newBob.save();

    Customer bob = DB.find(Customer.class)
      .where().eq("name", "Bob")
      .findOne();

    bob.notes("Doing an update");
    bob.save();
  }

  /**
   * Execute an update without a prior query.
   */
  @Test
  public void statelessUpdate() {

    Customer newMob = new Customer("Mob");
    newMob.mydate(ZonedDateTime.now());
    newMob.save();

    Customer upd = new Customer();
    upd.id(newMob.id());
    upd.notes("Update without a fetch");

    upd.update();
  }

}
