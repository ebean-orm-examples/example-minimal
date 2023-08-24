package org.example.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jw_mutatie_product")
public class MutatieProduct {

  @Id
  long id;

  final String aprod;

  @ManyToOne(optional = false)
  ProductToegewezen toegewezenProduct;

  public MutatieProduct(String aprod) {
    this.aprod = aprod;
  }


}
