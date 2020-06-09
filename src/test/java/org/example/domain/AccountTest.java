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
public class AccountTest
{


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
  public void insert_via_model() {

    Account jimAccount = new Account("Jim");
    jimAccount.save();

    assertNotNull(jimAccount.getId());
  }


  /**
   * Find and then update.
   */
  @Test
  public void updateRob() {

    Account rob = new Account("Rob");
    rob.save();

    Account bob = DB.find(Account.class)
      .where().eq("name", "Rob")
      .findOne();

    bob.setName("newRob");
    bob.save();
  }

  /**
   * Execute an update without a prior query.
   */
  @Test
  public void statelessUpdate() {

    Account newMob = new Account("Mob");
    newMob.save();

    Account upd = new Account();
    upd.setId(newMob.getId());
    upd.setName("Update without a fetch");

    upd.update();
  }

}
