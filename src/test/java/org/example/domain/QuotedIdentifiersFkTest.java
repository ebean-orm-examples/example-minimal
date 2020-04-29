package org.example.domain;

import io.ebean.Query;
import org.example.domain.query.QLabs;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuotedIdentifiersFkTest {

  @Test
  public void test() {

    final Query<Labs> query = new QLabs().query();
    query.findList();

    assertThat(query.getGeneratedSql()).contains("select t0.[LabID], t0.[Name], t0.[AddressID] from [Labs] t0");
  }
}
