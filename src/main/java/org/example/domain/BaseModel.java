package org.example.domain;

import io.ebean.Model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseModel<SELF> extends Model {

//  SELF save() {
//    super.save();
//    return this;
//  }
}
