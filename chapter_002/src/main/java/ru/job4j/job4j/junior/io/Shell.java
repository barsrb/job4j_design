package ru.job4j.job4j.junior.io;

import java.util.LinkedList;

class Shell {

    private final LinkedList<String> path = new LinkedList<>();

    Shell cd(String to) {

        if (to.startsWith("/")) {
            path.clear();
        }

        if (!to.contains("/")) {
            if (to.equals("..")) {
                path.removeLast();
            } else {
                path.add(to);
            }
            return this;
        }

        String[] cd = to.split("/");
        for (String dir : cd) {
            if (dir.equals(".") || dir.isBlank()) {
                continue;
            } else if (dir.equals("..")) {
                path.removeLast();
            } else {
                path.add(dir);
            }
        }
        return this;
    }

    public String path() {
        return "/" + String.join("/", path);
    }
}
