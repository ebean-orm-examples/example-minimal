package org.example.domain;

import static java.util.Objects.nonNull;

import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.ebean.DB;
import io.ebean.Database;

public class LazyLoadingSoftDeletedReferencesTest {
  @BeforeEach
  private void setup() {
    insertRows(true);
    insertRows(false);
  }

  @AfterEach
  private void cleanup() {
    Database server = DB.getDefault();
    server.deleteAll(server.find(UsageRaw.class).findList());
  }

  /**
   * Demonstrates that default fetching behaves differently as of 13.6.6.
   *
   * Also demonstrates workaround, using fetchLazy() which yields desired results.
   */
  @Test
  public void setIncludeSoftDeletes_does_not_fetch_deep_relations_that_are_softdeleted() {
    // given: existing usage
    /*
    [
      { id: 1, account: { name: 'usage-1', deleted: false, environment: { name: 'env-1', deleted: false } },
      { id: 2, account: { name: 'usage-2', deleted: true, environment: { name: 'env-2', deleted: true } },
    ] */
    Database server = DB.getDefault();

    // demonstrates new behaviour in 13.6.6
    // when: selecting without fetch variations
    List<UsageRaw> usage = server.find(UsageRaw.class)
      .setIncludeSoftDeletes()
      .findList();
    // then: soft-deleted environments not loaded
    assert(usage.size() == 2);
    assert(usage.stream().allMatch(accountLoaded()));
    assert(usage.stream().filter(environmentLoaded()).count() == 1);

    // demonstrates workaround, use fetchLazy()
    // when: lazy fetching
    List<UsageRaw> lazyUsage = server.find(UsageRaw.class)
      .setIncludeSoftDeletes()
      .fetchLazy("serviceAccount.environment")
      .findList();
    // then: soft-deleted environments are loaded
    assert(lazyUsage.size() == 2);
    assert(lazyUsage.stream().allMatch(accountLoaded()));
    assert(lazyUsage.stream().allMatch(environmentLoaded()));
  }

  /**
   * Demonstrates that .fetchLazy() with multiple paths behaves differently in 13.6.6
   * and does not behave the same as .fetch().
   *
   * Also demonstrates workaround that yield desired results.
   */
  @Test
  public void fetchLazy_with_multiple_paths_does_not_fetch_deep_relations_that_are_softdeleted() {
    // given: existing usage
    /*
    [
      { id: 1, account: { name: 'usage-1', deleted: false, environment: { name: 'env-1', deleted: false } },
      { id: 2, account: { name: 'usage-2', deleted: true, environment: { name: 'env-2', deleted: true } },
    ] */
    Database server = DB.getDefault();

    // demonstrates new behaviour in 13.6.6
    // when: lazy fetching serviceAccount AND serviceAccount.environment
    List<UsageRaw> lazyUsageMultiplePaths = server.find(UsageRaw.class)
      .setIncludeSoftDeletes()
      .fetchLazy("serviceAccount")
      .fetchLazy("serviceAccount.environment")
      .findList();
    // then: soft-deleted environments are NOT loaded (not the case before 13.6.6)
    assert(lazyUsageMultiplePaths.size() == 2);
    assert(lazyUsageMultiplePaths.stream().allMatch(accountLoaded()));
    assert(lazyUsageMultiplePaths.stream().filter(environmentLoaded()).count() == 1);

    // demonstrates workaround, use single path, all required properties are loaded
    // when: lazy fetching serviceAccount.environment
    List<UsageRaw> lazyUsageSinglePath = server.find(UsageRaw.class)
      .setIncludeSoftDeletes()
      .fetchLazy("serviceAccount.environment")
      .findList();
    // then: soft-deleted environments loaded
    assert(lazyUsageSinglePath.size() == 2);
    assert(lazyUsageSinglePath.stream().allMatch(accountLoaded()));
    assert(lazyUsageSinglePath.stream().allMatch(environmentLoaded()));

    // demonstrates that eager fetching works when multiple paths are used
    // when: eagerly fetching serviceAccount AND serviceAccount.environment
    List<UsageRaw> eagerUsage = server.find(UsageRaw.class)
      .setIncludeSoftDeletes()
      .fetch("serviceAccount")
      .fetch("serviceAccount.environment")
      .findList();
    // then: soft-deleted environments loaded
    assert(eagerUsage.size() == 2);
    assert(eagerUsage.stream().allMatch(accountLoaded()));
    assert(eagerUsage.stream().allMatch(environmentLoaded()));
  }

  private static Predicate<UsageRaw> accountLoaded() {
    return usage -> {
      boolean loaded = nonNull(usage.getServiceAccount().getName());
      // put breakpoint here to see post-load state
      return loaded;
    };
  }

  private static Predicate<UsageRaw> environmentLoaded() {
    return usage -> {
      boolean loaded = nonNull(usage.getServiceAccount())
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
