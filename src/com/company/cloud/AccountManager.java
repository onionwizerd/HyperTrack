package com.company.cloud;

/**
 * Created by josh on 2016/07/30.
 */
public class AccountManager {

    private static AccountManager accountManager = new AccountManager();

    // User Information
    private String email = "";

    private AccountManager() {
    }

    public static AccountManager getInstance(){ return accountManager; }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }
}
