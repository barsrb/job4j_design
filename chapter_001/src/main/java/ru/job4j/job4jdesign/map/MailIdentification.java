package ru.job4j.job4jdesign.map;

import java.util.*;

public class MailIdentification {
    public static Map<String, List<String>> groupUsers(Map<String, List<String>> usersWithMail) {
        Map<String, String> mailToUser = new HashMap<>();

        Map<String, Set<String>> userToUsers = new HashMap<>();

        for (Map.Entry<String, List<String>> userMails : usersWithMail.entrySet()) {

            String currentUser = checkUserIsUsersSet(userToUsers, userMails.getKey());

            for (String mail : userMails.getValue()) {
                if (mailToUser.containsKey(mail)) {
                    String existsUser = mailToUser.get(mail);
                    currentUser = addUserInUsersSet(userToUsers, currentUser, existsUser);
                    mailToUser.put(mail, existsUser);
                } else {
                    mailToUser.put(mail, currentUser);
                }
            }
        }



        Map<String, List<String>> groupedUsers = new HashMap<>();
        for (Map.Entry<String, Set<String>> users : userToUsers.entrySet()) {
            String mainUser = users.getKey();
            Set<String> emails = new HashSet<>();
            for (String user : users.getValue()) {
                emails.addAll(usersWithMail.get(user));
            }
            groupedUsers.put(mainUser, new ArrayList<>(emails));
        }
        return groupedUsers;
    }

    private static String checkUserIsUsersSet(Map<String, Set<String>> userToUsers, String currentUser) {
        if (userToUsers.containsKey(currentUser)) {
            return currentUser;
        }

        for (var entry : userToUsers.entrySet()) {
            if (entry.getValue().contains(currentUser)) {
                userToUsers.remove(currentUser);
                return entry.getKey();
            }
        }

        Set<String> users = new HashSet<>();
        users.add(currentUser);
        userToUsers.put(currentUser, users);
        return currentUser;
    }

    private static String addUserInUsersSet(Map<String, Set<String>> userToUsers, String currentUser, String originalUser) {
        for (var entry : userToUsers.entrySet()) {
            if (entry.getValue().contains(originalUser)) {
                entry.getValue().add(currentUser);
                userToUsers.remove(currentUser);
                return originalUser;
            }
            if (entry.getValue().contains(currentUser)) {
                entry.getValue().add(originalUser);
                userToUsers.remove(originalUser);
                return currentUser;
            }
        }
        return null;
    }
}
