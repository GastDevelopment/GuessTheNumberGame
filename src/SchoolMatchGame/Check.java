package SchoolMatchGame;

import java.util.ArrayList;
import java.util.HashMap;

import static SchoolMatchGame.CC.*;

public class Check {

    // Function nameCheck() is called inside of startMatch() to check whether the usernames meet the criteria or not and return the appropriate message
    // Returns True if the username meets the criteria and False if not
    public boolean nameCheck(String name, ArrayList<String> users) {
        if (name.length() > 8) {
            System.out.printf(PURPLE + "\n%s " + RED + "is too long!\nUsername must be between 3-8 characters long.\n" + RESET, name);
            return false;
        } else if (name.length() < 3) {
            System.out.printf(PURPLE + "%s " + RED + "is too short!\nUsername must be between 3-8 characters long.\n" + RESET, name);
            return false;
        }
        for (String n : users) {
            if (n.equals(name)) {
                System.out.printf(PURPLE + "%s " + RED + "is already taken! Username must be unique and not taken.\n" + RESET, name);
                return false;
            }
        }
        for (int i = 0; i < name.length(); i++) {
            char l = name.charAt(i);
            if (l == ' ') {
                System.out.printf(PURPLE + "%s " + RED + "contains a space! Username must not have a space.\n" + RESET, name);
                return false;
            }
        }
        return true;
    }

    public boolean valueCheck(HashMap<String, Integer> data, int value) {
        for (int num : data.values()) {
            if (value == num) {
                System.out.format(RED + "\n%d is already taken! Choose a different number.\n" + RESET, value);
                return false;
            }
        }
        if (value < 1) {
            System.out.format(RED + "\n%d is less than 1! Choose a number between 1-100\n" + RESET, value);
            return false;
        } else if (value > 100) {
            System.out.format(RED + "\n%d is more than 100! Choose a number between 1-100\n" + RESET, value);
            return false;
        }
        System.out.println(GREEN + "Successfully entered value!\n" + RESET);
        return true;
    }
}
