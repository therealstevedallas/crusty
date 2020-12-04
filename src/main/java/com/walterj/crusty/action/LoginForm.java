package com.walterj.crusty.action;

/**
 * A form bean used for both import and export
 *
 * @author Walter Jordan
 * @version $Id: $Id
 */
public class LoginForm {

    private String account;
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
