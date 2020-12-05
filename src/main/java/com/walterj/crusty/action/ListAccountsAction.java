package com.walterj.crusty.action;

import com.walterj.crusty.dao.Dao;
import com.walterj.crusty.dao.impl.EntityDaoImpl;
import com.walterj.crusty.model.Account;
import com.walterj.util.web.ParamUtil;
import com.walterj.util.web.TablePager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Walter Jordan
 */
public class ListAccountsAction extends BaseAction {

    private static final Logger LOG = LogManager.getLogger(ListAccountsAction.class.getName());
    private static final int DEFAULT_ROWS_PER_PAGE = 10;

    int currentPage;
    int rowsPerPage = DEFAULT_ROWS_PER_PAGE;

    @Override public String perform(HttpSession session, HttpServletRequest request) {

        Dao<Account> dao = new EntityDaoImpl<>();
        long rows = dao.count(Account.class);
        TablePager<Account> pager = new TablePager<>(getRowsPerPage(), rows);
        pager.setCurrentPage(getCurrentPage());
        List<Account> items = dao.list(Account.class, pager.getRowsPerPage(), pager.getRowOffset());
        pager.setRows(items);
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
}
