package com.codersbay;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Random Word
        String[] wordArray = {"Hangman", "Konsole", "Buchstabe", "Eingabe"};
        Random fortuna = new Random();
        int fortunaChoice = fortuna.nextInt(wordArray.length);
        String word = wordArray[fortunaChoice];
        String hiddenWord = "";
        for (int i = 0; i < word.length(); i++) {
            hiddenWord += "*";
        }
        String record = "";

        // Header
        System.out.println("   Its hangman time!!");
        System.out.println("------------------------");
        // Game
        boolean gameover = false;
        int state = 0;
        do {
            System.out.println("The Word is: " + hiddenWord);
            if (!record.isEmpty()) {
                System.out.println("Your tries: " + record);
            }

            // Input
            boolean inputTry = false;
            String input = "";
            do {
                System.out.print("chose a charakter: ");
                input = getSign();
                if (hangmanCheck(record, input)) {
                    System.out.println("All ready used ;)");
                    inputTry = true;
                } else {
                    if (record.isEmpty()) {
                        record += input;
                    } else {
                        record += ", " + input;
                    }
                    inputTry = false;
                }
            } while (inputTry);

            // correct or wrong
            if (hangmanCheck(word, input)) {
                System.out.println("Correct!");
                hiddenWord = changeHiddenWord(word, hiddenWord, input);
            } else {
                state++;
                System.out.println("Wrong!");
            }
            // Lose condition
            gameover = drawHangman(state);
            // Win condition
            if (!hiddenWord.contains("*")) {
                gameover = true;
            }
        } while (!gameover);

        if (state != 9) {
            System.out.println();
            System.out.println("Victory!");
            System.out.println("--------");
            System.out.println("failed attempts: " + state);
        } else {
            System.out.println("Defeat!\nMore Luck the next time ;)");
        }
    }

    public static String changeHiddenWord(String word, String hiddenWord, String sign) {
        if (!hiddenWord.isEmpty() && !word.isEmpty() && word.length() == hiddenWord.length() && !sign.isEmpty()) {
            String help = "";
            for (int i = 0; i < word.length(); i++) {
                if (word.toUpperCase().charAt(i) == sign.charAt(0) && hiddenWord.charAt(i) == '*') {
                    help += sign;
                } else {
                    help += hiddenWord.charAt(i);
                }
            }
            return help;
        }
        return hiddenWord;
    }

    public static String getSign() {
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        input = input.toUpperCase();

        if (!input.isEmpty()) {
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) >= 'A' && input.charAt(i) <= 'Z') {
                    return String.valueOf(input.charAt(i));
                } else if (input.charAt(i) >= 'a' && input.charAt(i) <= 'z') {
                    return String.valueOf(input.charAt(i));
                }
            }
        }
        return String.valueOf(' ');
    }

    public static boolean hangmanCheck(String word, String sign) {
        if (word.toUpperCase().contains(sign)) {
            return true;
        }
        return false;
    }

    // found in the web... don't work
    public final static void clearConsole() {
        try {
            Runtime.getRuntime().exec("cls");
        } catch (final Exception e) {
        }
    }

    public static boolean drawHangman(int input) {
        if (input < 0) {
            return false;
        }

        //clearConsole();
        //System.out.flush();
        // clear console and flush does not work ??? this is my solution
        System.out.println("\n".repeat(20));
        switch (input) {
            case 0 -> System.out.println();

            case 1 -> System.out.println("___|___");

            case 2 -> System.out.println("""
                       |
                       |
                       |
                    ___|___""");

            case 3 -> System.out.println("""
                       |
                       |                   
                       |
                       |
                       |
                    ___|___""");

            case 4 -> System.out.println("""
                       _______
                       |/     
                       |     
                       |
                       |
                       |
                       |
                       |
                    ___|___""");

            case 5 -> System.out.println("""
                       _______
                       |/     |
                       |     
                       |
                       |
                       |
                       |
                       |
                    ___|___""");

            case 6 -> System.out.println("""
                       _______
                       |/     |
                       |     (_)
                       |
                       |
                       |
                       |
                       |
                    ___|___""");

            case 7 -> System.out.println("""
                       _______
                       |/     |
                       |     (_)
                       |     \\|/
                       |      
                       |
                       |
                       |
                    ___|___""");

            case 8 -> System.out.println("""
                       _______
                       |/     |
                       |     (_)
                       |     \\|/
                       |      |
                       |
                       |
                       |
                    ___|___""");

            case 9 -> {
                System.out.println("""
                           _______
                           |/     |
                           |     (_)
                           |     \\|/
                           |      |
                           |     / \\ 
                           |
                           |
                        ___|___""");

                System.out.println("GAME OVER!");
                return true;
            }
            default -> System.out.println("Error: drawHangman; state is " + input);
        }
        return false;
    }
}
