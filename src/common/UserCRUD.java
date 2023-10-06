package common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class UserCRUD extends ArrayList<String> {

    Library library = new Library();

    public void addNewAccount() {
        String username = "";
        while (true) {
            username = library.getString("Enter username: ").trim();

            if (username.length() < 5 || username.contains(" ")) {
                System.out.println("Username must be at least 5 characters and cannot contain spaces.");
            } else {
                break;
            }
        }

        if (isUsernameTaken(username)) {
            System.out.println("Username already exists. Please choose another username.");
            return;
        }

        String password = "";
        while (true) {
            password = library.getString("Enter password: ").trim();

            if (password.length() < 6 || password.contains(" ")) {
                System.out.println("Password must be at least 6 characters and cannot contain spaces.");
            } else {
                break;
            }
        }

        this.add(username);
        this.add(password);
        saveAccounts();

        System.out.println("Account created successfully!");
    }

    public void login() {
        String username = "";
        while (true) {
            username = library.getString("Enter username: ").trim();

            if (username.length() < 5 || username.contains(" ")) {
                System.out.println("Username must be at least 5 characters and cannot contain spaces.");
            } else {
                break;
            }
        }

        String password = "";
        while (true) {
            password = library.getString("Enter password: ").trim();

            if (password.length() < 6 || password.contains(" ")) {
                System.out.println("Password must be at least 6 characters and cannot contain spaces.");
            } else {
                break;
            }
        }
        if (isValidLogin(username, password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    public void loadAccounts() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("user.dat"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                this.add(line);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while loading accounts: " + e.getMessage());
        }
    }

    public void saveAccounts() {
        try (FileWriter fileWriter = new FileWriter("user.dat"); 
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter); 
                PrintWriter printWriter = new PrintWriter(bufferedWriter, true)) {

            for (String account : this) {
                printWriter.println(account);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while saving accounts: " + e.getMessage());
        }
    }

    private boolean isUsernameTaken(String username) {
        for (int i = 0; i < this.size(); i += 2) {
            if (username.equals(this.get(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidLogin(String username, String password) {
        for (int i = 0; i < this.size(); i += 2) {
            if (username.equals(this.get(i)) && password.equals(this.get(i + 1))) {

                return true;
            }
        }
        return false;
    }
}
