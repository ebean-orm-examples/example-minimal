package org.example.domain;

import org.example.domain.query.QCustomer;
import org.testng.annotations.Test;

public class CustomerQueryTest {

  @Test
  public void findAll() {

    new QCustomer()
        .name.istartsWith("Rob")
        .findList();
  }
}
