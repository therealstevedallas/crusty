package com.walterj.crusty.action;

import com.walterj.crusty.dao.Dao;
import com.walterj.crusty.dao.impl.EntityDaoImpl;
import com.walterj.crusty.model.Account;
import com.walterj.util.Crypt;
import com.walterj.util.web.Strings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.MessageFormat;

import static com.walterj.crusty.dao.Identifiable.NEW_ID;

/**
 * @author Walter Jordan
 */
public class SaveAccountAction extends BaseAction {
    private static final Logger LOG = LogManager.getLogger(SaveAccountAction.class.getName());

    Account accountForm;

    @Override public String perform(HttpSession session, HttpServletRequest request) {

        long id = accountForm.getId();
        Dao<Account> dao = new EntityDaoImpl<>();
        boolean update = false;

        if (id != NEW_ID){

            update = true;

            LOG.debug("perform(): Updating account: " + accountForm);
            Account a = dao.get(Account.class, id);

            // we cannot decrypt - we can use the compare to see if it's a match
            if (!a.getPassword().equals(accountForm.getPassword())) {

                // it has been changed to a new clear text password, encrypt it
                accountForm.setPassword(Crypt.crypt(Constants.CRYPT_SALTSTRING,accountForm.getPassword()));
            }
            // set the values ve aren't carrying thru so they are not set to null
            accountForm.setLastLogin(a.getLastLogin());
            accountForm.setCreated(a.getCreated());
        }
        else {
            LOG.debug("perform(): Adding new account: " + accountForm);
            // generate an encrypted "one way" password to store
            accountForm.setPassword(Crypt.crypt(Constants.CRYPT_SALTSTRING,accountForm.getPassword()));
        }

        if (!validate(accountForm, getMessages(request))){
            LOG.debug("perform(): Validation failed for: " + accountForm);
            return INPUT;
        }

        if (update) {
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

    public void setAccountForm(Account accountForm){
        this.accountForm = accountForm;
    }

    final static String email_regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    //TODO: Break this out into a utility
    public boolean validate(Account a, Strings msg) {

        MessageFormat required = new MessageFormat(msg.get("format.error.required.field"));
        MessageFormat invalid = new MessageFormat(msg.get("format.error.invalid.email"));

        if (a.getName() == null || "".equals(a.getName())) {
            String err = required.format(new Object[] {msg.get("account.name"),});
            LOG.debug("validate(): ".concat(err));
            addActionError(err);
        }
        if (a.getEmail() == null || "".equals(a.getEmail())) {
            String err = required.format(new Object[] {msg.get("account.email"),});
            LOG.debug("validate(): ".concat(err));
            addActionError(err);
        }
        if(!a.getEmail().matches(email_regex)) {
            String err = invalid.format(new Object[] {msg.get("account.email"),});
            LOG.debug("validate(): ".concat(err));
            addActionError(err);
        }
        return getActionErrors().isEmpty();
    }
}
