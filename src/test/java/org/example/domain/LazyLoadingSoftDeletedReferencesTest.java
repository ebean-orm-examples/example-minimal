package org.example.domain;

import static java.util.Objects.nonNull;

import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.ebean.DB;
import io.ebean.Database;

public class LazyLoadingSoftDeletedReferencesTest {

  @BeforeAll
  private static void setup() {
    insertRows(true);
    insertRows(false);
  }

  @Test
  public void lazy_loading_does_not_fetch_soft_deleted_references() {
    /* given: existing usage
    [
      { id: 1, account: { name: 'usage-1', deleted: false, environment: { name: 'env-1', deleted: false } },
      { id: 2, account: { name: 'usage-2', deleted: true, environment: { name: 'env-2', deleted: true } },
    ] */
    Database server = DB.getDefault();

    // when: lazily fetching
    List<UsageRaw> lazyUsage = server.find(UsageRaw.class)
      .setIncludeSoftDeletes()
      .findList();
    // then: soft-deleted environments not loaded
    assert(lazyUsage.size() == 2);
    assert(lazyUsage.stream().allMatch(accountLoaded()));
    assert(lazyUsage.stream().filter(environmentLoaded()).count() == 1);

    // when: eagerly fetching
    List<UsageRaw> eagerUsage = server.find(UsageRaw.class)
      .setIncludeSoftDeletes()
      .fetch("serviceAccount.environment")
      .findList();
    // then: soft-deleted environments are loaded
    assert(eagerUsage.size() == 2);
    assert(eagerUsage.stream().allMatch(accountLoaded()));
    assert(eagerUsage.stream().allMatch(environmentLoaded()));
  }

  private static Predicate<UsageRaw> accountLoaded() {
    return usage -> {
      boolean loaded = nonNull(usage.getServiceAccount())
        && nonNull(usage.getServiceAccount().getName());
      // put breakpoint here to see post-load state
      return loaded;
    };
  }

  private static Predicate<UsageRaw> environmentLoaded() {
    return usage -> {
      boolean loaded = nonNull(usage.getServiceAccount())
        && nonNull(usage.getServiceAccount().getEnvironment())
        && nonNull(usage.getServiceAccount().getEnvironment().getName());
      // put breakpoint here to see post-load state
      return loaded;
    };
  }

  private static void insertRows(boolean softDeleted) {
    Environment environment = new Environment("environment", softDeleted);
    ServiceAccount account = new ServiceAccount("account", environment, softDeleted);
    UsageRaw usage = new UsageRaw("usage", account);

    Database server = DB.getDefault();
    server.save(environment);
    server.save(account);
    server.save(usage);
  }
}
