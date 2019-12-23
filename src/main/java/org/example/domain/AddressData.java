package org.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Addresses")//, schema = "dev")
public class AddressData extends BaseModel<AddressData> {
  @Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "AddressID")
  private Long id;

  @Column(name = "Name", length = 50, nullable = false)
  private String name;

  @Column(name = "Address1", length = 75)
  private String address1;

  @Column(name = "Address2", length = 75)
  private String address2;

  @Column(name = "City", length = 50)
  private String city;

  @Column(name = "State", length = 50)
  private String state;

  @Column(name = "Zip", length = 50)
  private String zipCode;

  @Column(name = "Country", length = 50)
  private String country;

}
