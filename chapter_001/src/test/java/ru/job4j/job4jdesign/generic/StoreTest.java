package ru.job4j.job4jdesign.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StoreTest {
    private UserStore userStore;
    private RoleStore roleStore;

    @Before
    public void createStores() {
        userStore = new UserStore(5);
        roleStore = new RoleStore(5);

        userStore.add(new User("user1"));
        userStore.add(new User("user2"));
        userStore.add(new User("user3"));

        roleStore.add(new Role("role1"));
        roleStore.add(new Role("role2"));
        roleStore.add(new Role("role3"));
    }

    @Test
    public void addToStores() {
        userStore.add(new User("testUser"));
        roleStore.add(new Role("testRole"));

        assertThat(userStore.findById("testUser").getId(), is("testUser"));
        assertThat(roleStore.findById("testRole").getId(), is("testRole"));
    }

    @Test
    public void replaceInStores() {
        boolean userReplaced = userStore.replace("user1", new User("testUser"));
        boolean roleReplaced = roleStore.replace("role1", new Role("testRole"));

        assertTrue(userReplaced);
        assertTrue(roleReplaced);
    }

    @Test
    public void wasNotReplaced() {
        boolean userReplaced = userStore.replace("userNone", new User("testUser"));
        boolean roleReplaced = roleStore.replace("roleNone", new Role("testRole"));

        assertFalse(userReplaced);
        assertFalse(roleReplaced);
    }

    @Test
    public void deleteFromStore() {
        boolean userReplaced = userStore.delete("user1");
        boolean roleReplaced = roleStore.delete("role1");

        assertTrue(userReplaced);
        assertTrue(roleReplaced);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenElementNotFound() {
        userStore.findById("NONE");
    }

    @Test (expected = NoSuchElementException.class)
    public void whenElementWasDeleted() {
        userStore.delete("user1");
        userStore.findById("user1");
    }

}
