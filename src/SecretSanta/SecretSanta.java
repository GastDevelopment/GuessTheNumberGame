package SecretSanta;

import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SecretSanta {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> players = new ArrayList<>();

        System.out.println("Welcome to the Secret Santa Pair Generator!");
        System.out.println("Enter the names of the participants (type 'done' to finish):");
        Supplier<String> inputSupplier = scanner::nextLine;

        Stream.generate(inputSupplier)
                .takeWhile(input -> !input.equalsIgnoreCase("done"))
                .forEach(players::add);
        scanner.close();

        Collections.shuffle(players);

        try (FileWriter file = new FileWriter("Presents.txt")) {
            IntStream.range(0, players.size())
                    .forEach(i -> {
                        String giver = players.get(i);
                        String receiver = players.get((i + 1) % players.size());

                        String result = giver + " gives a gift to " + receiver;

                        System.out.println(result);

                        try {
                            file.write(result + "\n");
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        players.forEach(System.out::println);
    }
}
