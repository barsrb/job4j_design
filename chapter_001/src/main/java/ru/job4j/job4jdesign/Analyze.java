package ru.job4j.job4jdesign;

import java.util.*;

public class Analyze {


    public static Info diff(List<User> previous, List<User> current) {
        Info info = new Info();

        List<Integer> currentUnicalIDs = new ArrayList<>();

        Iterator<User> currentIterator = current.iterator();
        while (currentIterator.hasNext()) {
            User user = currentIterator.next();
            if (previous.contains(user)) {
                currentIterator.remove();
                previous.remove(user);
            } else {
                currentUnicalIDs.add(user.id);
            }
        }

        Iterator<User> previousIterator = previous.iterator();
        while (previousIterator.hasNext()) {
            User previousUser = previousIterator.next();
            if (currentUnicalIDs.contains(previousUser.id)) {
                info.changed++;
                currentUnicalIDs.remove(Integer.valueOf(previousUser.id));
                previousIterator.remove();
            }
        }


        info.deleted = previous.size();
        info.added = currentUnicalIDs.size();
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
    }

}
