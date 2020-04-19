package ru.job4j.job4j.junior.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChatRunner {

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalStateException("Wrong arguments. Usage java -jar Zip.jar -d SOURCE_PATH -e *.java -o project.zip");
        }
        String answerFile = args[0];
        String logFile = args[1];
        Chat chat = new Chat(answerFile, logFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (!chat.isStop()) {
            System.out.print("You: ");
            String input = br.readLine();
            String answer = chat.messageFromUser(input);
            if (!chat.isPause()) {
                System.out.println("Bot: " + answer);
            }
        }
        br.close();
        chat.close();
    }
}
