package org.example.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jw_melding_aanvang_product")
public class MeldingAanvangProduct {

  @Id
  long id;

  final String map;

  @ManyToOne(optional = false)
  ProductToegewezen toegewezenProduct;

  public MeldingAanvangProduct(String map) {
    this.map = map;
  }

  public String map() {
    return map;
  }

  public ProductToegewezen toegewezenProduct() {
    return toegewezenProduct;
  }

  public void setToegewezenProduct(ProductToegewezen toegewezenProduct) {
    this.toegewezenProduct = toegewezenProduct;
  }
}
