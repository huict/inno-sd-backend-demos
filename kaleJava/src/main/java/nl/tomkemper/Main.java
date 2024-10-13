package nl.tomkemper;

import com.github.ricksbrown.cowsay.Cowsay;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String message = System.getenv("MESSAGE");
        message = message != null ? message : "Hallo";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wat is je naam?");

        String naam = scanner.nextLine();

        String cowed = Cowsay.say(new String[]{message + " " + naam});
        System.out.println(cowed);

        if (naam.equals("Bob")) {
            throw new RuntimeException("Jammer Bob, echt jammer...");
        }
    }
}