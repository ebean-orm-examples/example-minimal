package org.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Labs")//, schema = "dev")
public class Labs extends BaseModel<Labs> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "LabID")
  private Integer id;

  @Column(name = "Name", length = 50, nullable = false)
  private String name;

  @ManyToOne //(optional = true, targetEntity = AddressData.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "AddressID", insertable = false)
  private AddressData address;

}
