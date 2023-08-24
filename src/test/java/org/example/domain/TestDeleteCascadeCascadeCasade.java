package org.example.domain;

import io.ebean.DB;
import io.ebean.test.LoggedSql;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TestDeleteCascadeCascadeCasade {

  @Test
  void deleteCascade() {

    ProductToegewezen pt = new ProductToegewezen("pt");
    pt.meldingenAanvangProduct().add(new MeldingAanvangProduct("map"));
    pt.mutatiesProduct().add(new MutatieProduct("mp"));
    Beschikking b = new Beschikking("b");
    b.toegewezenProducten().add(pt);

    DB.save(b);

    LoggedSql.start();

    DB.delete(b);

    List<String> sql = LoggedSql.stop();

    assertThat(sql).hasSize(8);
    assertThat(sql.get(0)).contains("select t0.id from jw_product_toegewezen t0 where beschikking_id=?");
    assertThat(sql.get(1)).contains("delete from jw_melding_aanvang_product where (toegewezen_product_id) "); // in | any
    assertThat(sql.get(2)).contains(" -- bind(Array[1]");
    assertThat(sql.get(3)).contains("delete from jw_mutatie_product where (toegewezen_product_id)"); // in | any
    assertThat(sql.get(4)).contains(" -- bind(Array[1]");
    assertThat(sql.get(5)).contains("delete from jw_product_toegewezen where id "); // in | any
    assertThat(sql.get(6)).contains(" -- bind(Array[1]");
    assertThat(sql.get(7)).contains("delete from jw_beschikking where id=?");
  }
}
