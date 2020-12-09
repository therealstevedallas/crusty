package com.walterj.crusty.action;

import com.walterj.crusty.dao.Dao;
import com.walterj.crusty.dao.impl.EntityDaoImpl;
import com.walterj.crusty.dao.impl.EntityDaoSort;
import com.walterj.crusty.model.Account;
import com.walterj.util.web.Strings;
import com.walterj.util.web.TablePager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Walter Jordan
 */
public class ListAccountsAction extends BaseAction {

    private static final Logger LOG = LogManager.getLogger(ListAccountsAction.class.getName());
    private static final int DEFAULT_ROWS_PER_PAGE = 10;

    private static final String ACCOUNT_NAME = "accounts.header.name";
    private static final String ACCOUNT_LAST = "accounts.header.name.last";
    private static final String ACCOUNT_FIRST = "accounts.header.name.first";
    private static final String ACCOUNT_EMAIL = "accounts.header.email";
    private static final String ACCOUNT_MOBILE = "accounts.header.mobile";
    private static final String ACCOUNT_LOGIN = "accounts.header.login";
    private static final String ACCOUNT_ACTIVE = "accounts.header.active";

    private static final Map<Integer, String> accountPropertyMap
        = new ConcurrentHashMap<>();
    static {
        accountPropertyMap.put(0, "name");
        accountPropertyMap.put(1, "lastName");
        accountPropertyMap.put(2, "firstName");
        accountPropertyMap.put(3, "email");
        accountPropertyMap.put(4, "mobile");
        accountPropertyMap.put(5, "lastLogin");
        accountPropertyMap.put(6, "active");
    }

    int currentPage;
    int rowsPerPage = DEFAULT_ROWS_PER_PAGE;
    boolean sortAscending = true;
    int sortBy = -1;

    @Override public String perform(HttpSession session, HttpServletRequest request) {

        final Strings messages = getMessages(request);
        // per request in case i18n changes
        List<String> headers = new ArrayList<>();
        headers.add(messages.get(ACCOUNT_NAME));
        headers.add(messages.get(ACCOUNT_LAST));
        headers.add(messages.get(ACCOUNT_FIRST));
        headers.add(messages.get(ACCOUNT_EMAIL));
        headers.add(messages.get(ACCOUNT_MOBILE));
        headers.add(messages.get(ACCOUNT_LOGIN));
        headers.add(messages.get(ACCOUNT_ACTIVE));

        final Dao<Account> dao = new EntityDaoImpl<>();
        long rows = dao.count(Account.class);
        TablePager<Account> pager = new TablePager<>(getRowsPerPage(), rows);
        pager.setCurrentPage(getCurrentPage());

        List<EntityDaoSort> sorting = null; // can be null
        if (sortBy > -1) {

            sorting = new ArrayList<>();
            sorting.add(
                new EntityDaoSort(accountPropertyMap.get(sortBy), sortAscending));
            pager.setSortBy(sortBy);
            pager.setSortAscending(sortAscending);
        }

        List<Account> items = dao.list(Account.class, pager.getRowsPerPage(),
            pager.getRowOffset(), sorting);
        pager.setRows(items);
        pager.setHeaders(headers);

        LOG.debug("perform(): Setting table pager:" + pager);
        super.logRequestParams();
        request.setAttribute(Constants.REQUEST_KEY_ACCOUNT_LIST , pager);
        return SUCCESS;
    }



    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    public boolean isSortAscending() {
        return sortAscending;
    }

    public void setSortAscending(boolean sortAscending) {
        this.sortAscending = sortAscending;
    }

    public int getSortBy() {
        return sortBy;
    }

    public void setSortBy(int sortBy) {
        this.sortBy = sortBy;
    }
}
