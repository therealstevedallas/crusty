package com.walterj.crusty.action;

import com.walterj.crusty.dao.Dao;
import com.walterj.crusty.dao.impl.EntityDaoImpl;
import com.walterj.crusty.model.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Walter Jordan
 */
public class SaveAccountAction extends BaseAction {
    private static final Logger LOG = LogManager.getLogger(SaveAccountAction.class.getName());

    Account accountForm;

    @Override public String perform(HttpSession session, HttpServletRequest request) {

        long id = accountForm.getId();
        Dao<Account> dao = new EntityDaoImpl<>();
        if (dao.exists(accountForm)) {

            dao.update(accountForm);
        }
        else {
            dao.add(accountForm);
        }
        return SUCCESS;
    }

    public Account getAccountForm() {
        return accountForm;
    }

    public void setAccountForm(Account accountForm) {
        this.accountForm = accountForm;
    }
}
