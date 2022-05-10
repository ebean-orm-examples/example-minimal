package org.example.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import io.ebean.DB;
import io.ebean.Database;

class UserTest {

  @Test
  void test_insert() {
      Database server = DB.getDefault();

    // setup test
    {
      Role role1 = new Role();
      role1.setName("admin");

      Role role2 = new Role();
      role2.setName("user");

      User user = new User();
      user.setName("rob");
      user.setRoles(List.of(role1, role2));

      server.save(user);
    }

    User fetchedUser = server.find(User.class).where().eq("name", "rob").findOne();
    Objects.requireNonNull(fetchedUser);

    {
      Role role = new Role();
      role.setName("superadmin");
      List<Role> superadmin = List.of(role);

      List<Role> updatedRoles = Stream.concat(
        fetchedUser.getRoles().stream().filter(r -> "admin".equals(r.getName())),
        superadmin.stream()
      ).collect(Collectors.toList());

      fetchedUser.setRoles(updatedRoles);
      server.save(fetchedUser);
    }

    User fetchedUser2 = server.find(User.class).where().eq("name", "rob").findOne();
    Objects.requireNonNull(fetchedUser2);
    assertEquals(2, fetchedUser2.getRoles().size());
  }

}
