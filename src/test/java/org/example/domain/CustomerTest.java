package org.example.domain;

import io.ebean.DB;
import io.ebean.Database;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * When running tests in the IDE install the "Enhancement plugin".
 * <p>
 * http://ebean-orm.github.io/docs/setup/enhancement#ide
 */
class CustomerTest {


  /**
   * Get the "default database" and save().
   */
  @Test
  void insert_via_database() {

    Customer rob = new Customer("Rob");

    Database server = DB.getDefault();
    server.save(rob);

    assertThat(rob.getId()).isGreaterThan(0);
  }

  /**
   * Use the Ebean singleton (effectively using the "default server").
   */
  @Test
  void insert_via_model() {

    Customer jim = new Customer("Jim");
    jim.save();

    assertThat(jim.getId()).isGreaterThan(0);
  }


  /**
   * Find and then update.
   */
  @Test
  void updateRob() {

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
  void statelessUpdate() {

    Customer newMob = new Customer("Mob");
    newMob.save();

    Customer upd = new Customer();
    upd.setId(newMob.getId());
    upd.setNotes("Update without a fetch");

    upd.update();
  }

}
