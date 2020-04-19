package ru.job4j.job4j.junior.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Chat {
    private final List<String> answers;
    private boolean pause = false;
    private boolean stop = false;
    private PrintWriter logWriter;

    public Chat(String answerFile, String logFilePath) {
        answers = getAnswersFromFile(answerFile);
        try {
            logWriter = new PrintWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(logFilePath)
                    ));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String messageFromUser(String message) {
        saveMessage(message);
        if (message.toLowerCase().equals("стоп")) {
            pause = true;
        } else if (message.toLowerCase().equals("продолжить")) {
            pause = false;
        } else if (message.toLowerCase().equals("закончить")) {
            pause = true;
            stop = true;
        }
        if (!pause && !stop) {
            String answer = answers.get(new Random().nextInt(answers.size()));
            saveMessage(answer);
            return answer;
        }
        return "";
    }

    private void saveMessage(String message) {
        logWriter.write(message);
        logWriter.write(System.lineSeparator());
    }

    private List<String> getAnswersFromFile(String answerFile) {
        List<String> answersList = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(answerFile))) {
            in.lines().forEach(answersList::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answersList;
    }

    public boolean isPause() {
        return pause;
    }

    public boolean isStop() {
        return stop;
    }

    public void close() {
        logWriter.close();
    }
}
