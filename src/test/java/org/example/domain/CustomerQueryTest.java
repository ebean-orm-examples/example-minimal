package org.example.domain;

import io.ebean.Ebean;
import org.testng.annotations.Test;

public class CustomerQueryTest {

  @Test
  public void findAll() {

    Ebean.find(Customer.class)
        .findList();
  }
}
