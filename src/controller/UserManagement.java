package controller;

import common.UserCRUD;
import common.Library;
import view.Menu;

public class UserManagement extends Menu<String> {

    static String[] mc = {"Create a new account", "Login system", "Exit"};

    protected Library library;
    protected UserCRUD userCrud;

    public UserManagement() {
        super("===== USER MANAGEMENT SYSTEM=====", mc);
        library = new Library();
        userCrud = new UserCRUD();
    }

    @Override
    public void execute(int n) {
        userCrud.loadAccounts();
        switch (n) {
            case 1:
                userCrud.addNewAccount();
                break;
            case 2:
                userCrud.login();
                break;
            case 3:
                System.out.println("Exiting program.");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
                break;
        }
    }
}
