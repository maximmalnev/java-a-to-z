package ru.kovladimir.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddValueToStoreThenContainsIt() {
        RoleStore<Role> store = new RoleStore<>();
        Role role = new Role();
        role.setId("100");

        store.add(role);
        Role result = store.get("100");

        assertThat(role, is(result));
    }

    @Test
    public void whenDeleteValueFromStoreThenDoesNotContainIt() {
        RoleStore<Role> store = new RoleStore<>();
        Role role = new Role();
        role.setId("100");
        store.add(role);

        store.delete("100");
        Role result = store.get("100");

        assertNull(result);
    }

    @Test
    public void WhenUpdateValueThenStoreContainsNewValue() {
        RoleStore<Role> store = new RoleStore<>();
        Role role1 = new Role();
        role1.setId("100");
        Role role2 = new Role();
        role2.setId("100");
        store.add(role1);

        store.update(role2);
        Role result = store.get("100");

        assertThat(result, is(role2));
    }

}