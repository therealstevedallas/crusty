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
public class DeleteAccountAction extends EditAccountAction {
    private static final Logger LOG = LogManager.getLogger(DeleteAccountAction.class.getName());

    @Override public String perform(HttpSession session, HttpServletRequest request) {
        long id = getAccountForm().getId();
        Account login = getCurrentLogin();
        if (login != null && id == login.getId()) {

            LOG.debug("perform(): Attempt to delete own account: " + login);
            addActionError("Users cannot delete their own account!");
            return ERROR;
        }

        Dao<Account> dao = new EntityDaoImpl<>();
        Account a = dao.get(Account.class, id);
        if(a != null) {
            LOG.debug("perform(): Removing account: " + a);
            dao.remove(a);
        }
        return SUCCESS;
    }
}
