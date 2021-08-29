package org.example.domain;

import io.ebean.DB;
import io.ebean.Database;
import org.junit.jupiter.api.Test;

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

    Database server = DB.getDefault();
    server.save(rob);

    assertNotNull(rob.getId());
  }

  /**
   * Use the Ebean singleton (effectively using the "default server").
   */
  @Test
  public void insert_via_model() {

    Customer jim = new Customer("Jim");
    jim.save();

    assertNotNull(jim.getId());
  }


  /**
   * Find and then update.
   */
  @Test
  public void updateRob() {

    Customer newBob = new Customer("Bob");
    newBob.save();

    Customer bob = DB.find(Customer.class)
      .where().eq("name", "Bob")
      .findOne();

    bob.setNotes("Doing an update");
    bob.save();
  }

  /**
   * Execute an update without a prior query.
   */
  @Test
  public void statelessUpdate() {

    Customer newMob = new Customer("Mob");
    newMob.save();

    Customer upd = new Customer();
    upd.setId(newMob.getId());
    upd.setNotes("Update without a fetch");

    upd.update();
  }

}
