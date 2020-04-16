package ru.job4j.job4jdesign.map;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;

public class MailIdentificationTest {

    @Test
    public void fiveUsersToTwo() {
        Map<String, List<String>> users = Map.of(
                "user1", List.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"),
                "user2", List.of("foo@gmail.com", "ups@pisem.net"),
                "user3", List.of("xyz@pisem.net", "vasya@pupkin.com"),
                "user4", List.of("ups@pisem.net", "aaa@bbb.ru"),
                "user5", List.of("xyz@pisem.net")
        );

        Map<Integer, String[]> expectedEmails = Map.of(
                5, new String[]{"aaa@bbb.ru", "foo@gmail.com", "lol@mail.ru", "ups@pisem.net", "xxx@ya.ru"},
                2, new String[] {"vasya@pupkin.com", "xyz@pisem.net"}
        );

        Map<Integer, String[]> expectedUsers = Map.of(
                5, new String[]{"user1", "user2", "user4"},
                2, new String[] {"user3", "user5"}
        );

        Map<String, List<String>> grouped = MailIdentification.groupUsers(users);

        assertThat(grouped.size(), is(2));
        Iterator<Map.Entry<String, List<String>>> resultIterator = grouped.entrySet().iterator();
        Map.Entry<String, List<String>> entry = resultIterator.next();
        assertThat(entry.getValue(), containsInAnyOrder(expectedEmails.get(entry.getValue().size())));
        assertThat(List.of(expectedUsers.get(entry.getValue().size())), hasItem(entry.getKey()));
        entry = resultIterator.next();
        assertThat(entry.getValue(), containsInAnyOrder(expectedEmails.get(entry.getValue().size())));
        assertThat(List.of(expectedUsers.get(entry.getValue().size())), hasItem(entry.getKey()));
    }

}