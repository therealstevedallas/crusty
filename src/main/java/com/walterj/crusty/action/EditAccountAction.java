package com.walterj.crusty.action;

import antlr.DocBookCodeGenerator;
import com.opensymphony.xwork2.Action;
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
public class EditAccountAction extends BaseAction {
    private static final Logger LOG = LogManager.getLogger(EditAccountAction.class.getName());

    Account accountForm;

    @Override public String perform(HttpSession session, HttpServletRequest request) {

        long id = accountForm.getId();
        Dao<Account> dao = new EntityDaoImpl<>();
        if (id != -1) {
            LOG.debug("perform(): Loading account with id [" + id + "]");
            Account a = dao.get(Account.class, id);
            setAccountForm(a);
        }
        else {
            setAccountForm(new Account());
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
