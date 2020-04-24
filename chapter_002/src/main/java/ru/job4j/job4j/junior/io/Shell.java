package ru.job4j.job4j.junior.io;

import java.util.ArrayList;

class Shell {

    private final ArrayList<String> currentPath;

    public Shell() {
        this.currentPath = new ArrayList<>();
    }

    Shell cd(final String path) {

        if (path.startsWith("/")) {
            currentPath.clear();
        }

        if (!path.contains("/")) {
            if (path.equals("..")) {
                currentPath.remove(currentPath.size() - 1);
            } else {
                currentPath.add(path);
            }
            return this;
        }

        String[] cd = path.split("/");
        for (String dir : cd) {
            if (dir.equals(".") || dir.isBlank()) {
                continue;
            } else if (dir.equals("..")) {
                currentPath.remove(currentPath.size() - 1);
            } else {
                currentPath.add(dir);
            }
        }
        return this;
    }

    public String path() {
        return "/" + String.join("/", currentPath);
    }
}
