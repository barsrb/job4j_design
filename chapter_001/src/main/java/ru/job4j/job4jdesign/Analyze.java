package ru.job4j.job4jdesign;

import java.util.*;

public class Analyze {

    public static Info diff(List<User> previous, List<User> current) {
        Info info = new Info();

        Map<Integer, User> previousMap = new HashMap<>();

        for (User user : previous) {
            previousMap.put(user.id, user);
        }

        for (User user : current) {
            if (!previousMap.containsKey(user.id)) {
                info.added++;
            } else if (!previousMap.get(user.id).equals(user)) {
                info.changed++;
            }
        }

        info.deleted = previous.size() - (current.size() - info.added);

        return info;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            User user = (User) o;

            if (id != user.id) {
                return false;
            }
            return name.equals(user.name);
        }

        @Override
        public int hashCode() {
            int result = id;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Info info = (Info) o;

            if (added != info.added) {
                return false;
            }
            if (changed != info.changed) {
                return false;
            }
            return deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            int result = added;
            result = 31 * result + changed;
            result = 31 * result + deleted;
            return result;
        }

        @Override
        public String toString() {
            return "Info{"
                    + "added=" + added
                    + ", changed=" + changed
                    + ", deleted=" + deleted + '}';
        }
    }

}
