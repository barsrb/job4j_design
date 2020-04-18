package ru.job4j.job4j.junior.io;

public class ArgZip {

    private String exclude;
    private String directory;
    private boolean valid = true;
    private String output;

    public ArgZip(String[] args) {
        if (args.length < 6) {
            valid = false;
            return;
        }
        label:
        for (int i = 0; i < 6; i = i + 2) {
            switch (args[i]) {
                case "-d":
                    directory = args[i + 1];
                    break;
                case "-e":
                    exclude = args[i + 1];
                    break;
                case "-o":
                    output = args[i + 1];
                    break;
                default:
                    valid = false;
                    break label;
            }
        }

    }

    public boolean valid() {
        return valid;
    }

    public String directory() {
        return directory;
    }

    public String exclude() {
        return exclude;
    }

    public String output() {
        return output;
    }
}
