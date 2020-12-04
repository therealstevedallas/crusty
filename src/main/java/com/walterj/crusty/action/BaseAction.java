package com.walterj.crusty.action;

import com.opensymphony.xwork2.ActionSupport;
import com.walterj.crusty.model.Account;
import com.walterj.util.web.ResourceLoadListener;
import com.walterj.util.web.Strings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * A base class for placing common attributes and behaviors we may want to carry
 * around.
 *
 * @author Walter Jordan
 * @version $Id: $Id
 */
public abstract class BaseAction
    extends ActionSupport {
    
    private static final Logger LOG = LogManager.getLogger(
            BaseAction.class.getName());

    private boolean checkLogin = true;
    private String contentSnippetJsp;

    public boolean isCheckLogin() {
        return checkLogin;
    }

    public void setCheckLogin(boolean checkLogin) {
        this.checkLogin = checkLogin;
    }

    public String getContentSnippetJsp() {
        return contentSnippetJsp;
    }

    public void setContentSnippetJsp(String contentSnippetJsp) {
        this.contentSnippetJsp = contentSnippetJsp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute()
            throws Exception {

        String rVal;
        final HttpServletRequest request = ServletActionContext.getRequest();
        final HttpSession session = request.getSession(true);
        final Object o = session.getServletContext().getAttribute(ResourceLoadListener.STRING_RESOURCES);
        if (o != null) {

            try {
                Strings strings = new Strings((Map<String,String>)o, request.getLocale());
                request.setAttribute(Constants.STRINGS, strings);
            }
            catch (Strings.UnsupportedLanguageException e) {
                LOG.error("execute(): " + e.getMessage(), e);
                addActionError("No i18n support is configured for: " + request.getLocale());
                return ERROR;
            }
        }
        else {
            LOG.warn("execute(): There is no STRINGS capability set in the context.");
        }

        final Account account = getCurrentLogin();
        if (isCheckLogin() && account == null) {
            LOG.debug("execute(): No active account is logged in, forwarding to Login page.");
            rVal = LOGIN;
        }
        else {
            try {
                LOG.debug("execute(): calling perform()");
                rVal = perform(session, ServletActionContext.getRequest());
            } catch (Throwable t) {
                LOG.error("execute(): " + t.getMessage(), t);
                rVal = ERROR;
            }
        }
        return rVal;
    }

    // helper for children
    protected static final Strings getMessages(HttpServletRequest req) {
        return (Strings)req.getAttribute(Constants.STRINGS);
    }

    protected Account getCurrentLogin() {
        Account rVal = null;
        HttpSession session = ServletActionContext.getRequest().getSession(true);
       if (session != null) {
           final Object o = session.getAttribute(Constants.SESSION_KEY_CURRENT_LOGIN);
           if (o instanceof Account) {
               rVal = (Account)o;
           }
       }
       return rVal;
    }

    public abstract String perform(HttpSession session, HttpServletRequest request);

    Connection getConnection() {

        Connection c = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/crusty");
            c = ds.getConnection();
        }
        catch(Exception e) {
            LOG.debug("getConnection(): " + e.getMessage());
        }
        return c;
    }
    
    void logRequestParams() {

        final HttpServletRequest req = ServletActionContext.getRequest();
        final Map<String,String[]> vars = req.getParameterMap();
        StringBuilder sb = new StringBuilder("Request Params:");
        for (Object key : vars.keySet()) {
            Object v = vars.get(key);
            sb.append('\n').append(key).append(" = ").append(v);
        }
        sb.append("\nRequest Attributes:");
        Enumeration<String> attrs = req.getAttributeNames();
        while(attrs.hasMoreElements()) {
            String key = attrs.nextElement();
            sb.append('\n').append(key).append(" = ").append(""+req.getAttribute(key));
        }
        sb.append("\nSession Attributes:");
        attrs = req.getSession().getAttributeNames();
        while(attrs.hasMoreElements()) {
            String key = attrs.nextElement();
            sb.append('\n').append(key).append(" = ").append(""+req.getAttribute(key));
        }
        LOG.debug("logRequestParams(): " + sb.toString());

    }
}
