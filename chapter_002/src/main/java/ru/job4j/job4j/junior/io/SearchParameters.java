package ru.job4j.job4j.junior.io;

public class SearchParameters {
    private String directory;
    private String pattern;
    private String logFile;

    public SearchParameters(String[] args) {
        //-d c:/ -n *.txt -m -o log.txt
        String patternType = null;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-d":
                    directory = args[i + 1];
                    break;
                case "-n":
                    pattern = args[i + 1];
                    patternType = args[i + 2];
                    break;
                case "-o":
                    logFile = args[i + 1];
                    break;
                default:
                    break;
            }
        }
        if (directory == null || pattern == null || patternType == null || logFile == null) {
            throw new IllegalStateException("Wrong arguments. Usage java -jar FileLister.jar -d c:/ -n *.txt -m -o log.txt");
        }
        if (!patternType.equals("-r")) {
            convertPattern(patternType);
        }
    }

    private void convertPattern(String patternType) {
        pattern = "^" + pattern.replace(".", "\\.") + "$";
        if (patternType.equals("-m")) {
            pattern = pattern.replace("*", ".*")
                    .replace("?", ".?");
        }
    }

    public String getDirectory() {
        return directory;
    }

    public String getPattern() {
        return pattern;
    }

    public String getLogFile() {
        return logFile;
    }
}
