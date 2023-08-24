package org.example.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "jw_beschikking")
public class Beschikking {

  @Id
  long id;

  final String b;
  @OneToMany(mappedBy = "beschikking", cascade = CascadeType.ALL)
  private Set<ProductToegewezen> toegewezenProducten;

  public Beschikking(String b) {
    this.b = b;
  }

  public String b() {
    return b;
  }

  public Set<ProductToegewezen> toegewezenProducten() {
    return toegewezenProducten;
  }
}
