package org.example.domain;

//import org.example.domain.query.QCustomer;

import com.avaje.ebean.Ebean;
import org.testng.annotations.Test;

public class CustomerQueryTest {

  @Test
  public void findAll() {

    Ebean.find(Customer.class)
        .findList();

//    new QCustomer()
//        .name.istartsWith("Rob")
//        .findList();
  }
}
