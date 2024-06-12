package org.example.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "jw_product_toegewezen")
public class ProductToegewezen  {

  @Id
  long id;
  final String tg;

  @ManyToOne(optional = false)
  Beschikking beschikking;

  @OneToMany(mappedBy = "toegewezenProduct", cascade = CascadeType.ALL)
  private Set<MeldingAanvangProduct> meldingenAanvangProduct;

  @OneToMany(mappedBy = "toegewezenProduct", cascade = CascadeType.ALL)
  private Set<MutatieProduct> mutatiesProduct;

  public ProductToegewezen(String tg) {
    this.tg = tg;
  }

  public Beschikking beschikking() {
    return beschikking;
  }

  public void setBeschikking(Beschikking beschikking) {
    this.beschikking = beschikking;
  }

  public Set<MeldingAanvangProduct> meldingenAanvangProduct() {
    return meldingenAanvangProduct;
  }

  public Set<MutatieProduct> mutatiesProduct() {
    return mutatiesProduct;
  }

}
