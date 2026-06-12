package Controller;

import Model.Account;
import Model.Role;

public class AccountController {
    public Account login(String username, String password) {
        if(username.equals("anpt")&& password.equals("123")) return new Account(new Role("Customer"));
        return null;
    }
}
