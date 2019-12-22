package org.example.domain;

import io.ebean.DB;
import io.ebean.Database;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;


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
  public void insert_via_server() {

    Customer rob = new Customer("Rob");

    Database server = DB.getDefault();
    server.save(rob);

    assertNotNull(rob.getId());
  }

  /**
   * Use the Ebean singleton (effectively using the "default server").
   */
  @Test
  public void insert_via_DB() {

    Customer jim = new Customer("Jim");
    DB.save(jim);

    assertNotNull(jim.getId());
  }


  /**
   * Find and then update.
   */
  @Test
  public void updateRob() {

    Customer newBob = new Customer("Bob");
    DB.save(newBob);

    Customer bob = DB.find(Customer.class)
      .where().eq("name", "Bob")
      .findOne();

    bob.setNotes("Doing an update");
    DB.save(bob);
  }

  /**
   * Execute an update without a prior query.
   */
  @Test
  public void statelessUpdate() {

    Customer newMob = new Customer("Mob");
    DB.save(newMob);

    Customer upd = new Customer();
    upd.setId(newMob.getId());
    upd.setNotes("Update without a fetch");

    DB.update(upd);
  }

}
