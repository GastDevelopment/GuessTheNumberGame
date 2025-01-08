package SchoolMatchGame;

import java.util.*;

import static SchoolMatchGame.CC.*;

public class MatchManager {

    private final Check check = new Check();
    private int round;
    private int number;

    private final ArrayList<String> currentPlayers = new ArrayList<>();
    private final ArrayList<String> eliminatedPlayers = new ArrayList<>();
    private final HashMap<String, Integer> guesses = new HashMap<>();

    public void createMatch() {
        Scanner sc = new Scanner(System.in);
        round = 1;

        System.out.println(CYAN + "Welcome to Guess The Number Game!" + RESET);
        System.out.println(CYAN + "Usernames should be between 3-8 characters long and unique with no spaces.\n" + RESET);

        while (currentPlayers.size() != 5) {
            System.out.print(GREEN + "Enter username: " + RESET);
            String username = sc.nextLine();
            if (check.nameCheck(username, currentPlayers)) {
                currentPlayers.add(username);
                System.out.println(YELLOW + "Successfully added!\n" + RESET);
            }
        }

        displayPlayers();
        resume();
    }

    public void createRound() {
        while (round != 5) {
            Scanner sc = new Scanner(System.in);
            guesses.clear();
            CLEAR();

            System.out.format(BLUE + "\nRound " + "%d!\n" + RESET, round);
            genNum();
            System.out.println("\n" + CYAN + "The number has been chosen!");

            for (String u : currentPlayers) {
                boolean validGuess = false;

                while (!validGuess) {
                    System.out.format(YELLOW + "%s " + BLUE + "you must pick a number between 1-100. It should not be the same as anyone else's number.\n" + RESET, u);
                    System.out.print(BLUE + "Enter number: " + RESET);
                    int guess = sc.nextInt();

                    if (check.valueCheck(guesses, guess)) {
                        guesses.put(u, guess);
                        validGuess = true;
                    }
                }
            }

            eliminate();
            delay(1);
            System.out.format("\nThe number was %d.", number);
            round++;

            delay(1);
            displayPlayers();
            System.out.println();

            if (round < 5) {
                resume();
                createRound();
            }
        }
    }

    public void getWinner() {
        System.out.println(CYAN + "The winner of Guess The Number Game is..." + RESET);
        delay(2);
        currentPlayers.forEach(p -> System.out.println(GREEN + p + RESET + "!"));
    }

    private void displayPlayers() {
        System.out.println("\n----------------------");
        System.out.format(BLUE + "Current Players: [%d]" + RESET + "\n", currentPlayers.size());
        for (String n : currentPlayers) {
            System.out.println(GREEN + n + RESET);
        }
        System.out.println("----------------------");
        System.out.format(PURPLE + "Eliminated Players: [%d]" + RESET + "\n", eliminatedPlayers.size());
        for (String n : eliminatedPlayers) {
            System.out.println(RED + n);
        }
        System.out.println("----------------------");
    }
    private void genNum() {
        Random random = new Random();
        number = random.nextInt(99) + 1;
    }

    private void eliminate() {
        int maxDiff = 0;
        String furthestPlayer = "";

        for (Map.Entry<String, Integer> entry : guesses.entrySet()) {
            int diff = Math.abs(entry.getValue() - number);

            if (diff > maxDiff) {
                maxDiff = diff;
                furthestPlayer = entry.getKey();
            }
        }

        System.out.println(CYAN + "\nThe furthest player was..." + RESET);

        delay(2);

        System.out.format(RED + "%s!\nYou have now been eliminated." + RESET, furthestPlayer);

        currentPlayers.remove(furthestPlayer);
        eliminatedPlayers.add(furthestPlayer);
    }

    private void resume() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(WHITE + "\nIf you wish to continue press " + RED + "C" + "\n" + WHITE + "If not enter " + RED + "E" + WHITE + " to exit the program." + RESET);
            String inp = sc.nextLine();
            if (inp.equalsIgnoreCase("C")) {
                CLEAR();
                break;
            } else if (inp.equalsIgnoreCase("E")) {
                Runtime.getRuntime().exit(1);
            } else {
                System.out.println(RED + "That is not a valid option!");
            }
        }
    }

    private void delay(long seconds) {
        try {
            Thread.sleep((seconds * 1000));
        } catch (InterruptedException e) {
            System.out.println("Loading...");
        }
    }

}
