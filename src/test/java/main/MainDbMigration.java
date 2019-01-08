package main;

import io.ebean.annotation.Platform;
import io.ebean.dbmigration.DbMigration;

/**
 * Generate the DB Migration.
 */
public class MainDbMigration {

  public static void main(String[] args) throws Exception {

    // optionally specify the version and name
    //System.setProperty("ddl.migration.version", "1.2_33");
    //System.setProperty("ddl.migration.name", "");

    // generate a migration using drops from a prior version
    //System.setProperty("ddl.migration.pendingDropsFor", "1.1");

    DbMigration dbMigration = DbMigration.create();
    dbMigration.setPlatform(Platform.POSTGRES);
    // generate the migration ddl and xml
    dbMigration.generateMigration();
  }
}
