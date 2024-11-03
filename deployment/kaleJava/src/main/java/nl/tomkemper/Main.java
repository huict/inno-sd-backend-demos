package nl.tomkemper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String message = System.getenv("MESSAGE");
        message = message != null ? message : "Hallo";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wat is je naam?");

        String naam = scanner.nextLine();

//        String formatted = Cowsay.say(new String[]{message, naam});
        String formatted = message + " " + naam;
        System.out.println(formatted);

        if (naam.equals("Bob")) {
            throw new RuntimeException("Jammer Bob, echt jammer...");
        }
    }
}