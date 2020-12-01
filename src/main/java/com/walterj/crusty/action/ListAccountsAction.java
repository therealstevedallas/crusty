package com.walterj.crusty.action;

import com.walterj.crusty.dao.Dao;
import com.walterj.crusty.dao.impl.EntityDaoImpl;
import com.walterj.crusty.model.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Walter Jordan
 */
public class ListAccountsAction extends BaseAction {

    @Override public String perform(HttpSession session, HttpServletRequest request) {

        Dao<Account> dao = new EntityDaoImpl<>();
        List<Account> items = dao.list(Account.class);
        request.setAttribute(Constants.REQUEST_KEY_ACCOUNT_LIST , items);
        return SUCCESS;
    }
}
