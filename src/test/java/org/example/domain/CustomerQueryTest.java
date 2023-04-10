package org.example.domain;

import io.ebean.DB;
import org.example.domain.query.QCustomer;
import org.junit.jupiter.api.Test;

class CustomerQueryTest {

  @Test
  void findAll() {

    DB.find(Customer.class)
        .findList();

    new QCustomer()
      .id.greaterOrEqualTo(1L)
      .findList();
  }
}
