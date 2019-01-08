package org.example.domain;

import io.ebean.Ebean;
import org.example.domain.query.QCustomer;
import org.junit.Test;

public class CustomerQueryTest {

  @Test
  public void findAll() {

    Ebean.find(Customer.class)
        .findList();

    new QCustomer()
      .id.greaterOrEqualTo(1L)
      .findList();
  }
}
