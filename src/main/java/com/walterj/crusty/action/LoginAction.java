package com.walterj.crusty.action;

import com.walterj.crusty.dao.impl.EntityDaoImpl;
import com.walterj.crusty.model.Account;
import com.walterj.util.Crypt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.parser.Entity;
import java.sql.Timestamp;
import java.util.Map;

/**
 * Carries login information during verify and login
 *
 * @author Walter Jordan
 * @version $Id: $Id
 */
public class LoginAction extends BaseAction {

    private static final Logger LOG = LogManager.getLogger(LoginAction.class.getName());
    private LoginForm loginForm;

    /**
     * <p>Getter for the field <code>loginForm</code>.</p>
     *
     * @return a {@link LoginForm}
     * object.
     */
    public LoginForm getLoginForm() {
        return loginForm;
    }
    
    /**
     * <p>Setter for the field <code>loginForm</code>.</p>
     *
     * @param loginForm a
     * {@link LoginForm} object.
     */
    public void setLoginForm(LoginForm loginForm) {
        this.loginForm = loginForm;
    }

    @Override public String perform(HttpSession session, HttpServletRequest request) {

        LOG.debug("perform(): called");
        if (loginForm == null) {
            LOG.warn("perform(): No form present!");
            return ERROR;
        }
        session.removeAttribute(Constants.SESSION_KEY_ACCOUNT);
        Account a = new Account();
        a.setName(loginForm.getAccount());
        LOG.debug("perform(): looking for account [" + a.getName() + "]");
        EntityDaoImpl<Account> dao = new EntityDaoImpl();
        a = dao.get(a);
        String rVal = ERROR;
        if (a != null) {
            LOG.debug("perform(): Account [" + a + "] found, checking credentials.");
            if (Crypt.compare(loginForm.getPassword(), a.getPassword())) {
                session.setAttribute(Constants.SESSION_KEY_ACCOUNT, a);
                a.setLastLogin(new Timestamp(System.currentTimeMillis()));
                dao.update(a);
                rVal = SUCCESS;
            }
            else {
                LOG.debug("perform(): Account [" + a + "] passwords do not match.");
            }
        }
        else {
            LOG.debug("perform(): Account [" + a + "] not found.");
        }

        if (!SUCCESS.equals(rVal))
            addActionError("Login for account [" + loginForm.getAccount() + "] failed!");
        return rVal;
    }
}
