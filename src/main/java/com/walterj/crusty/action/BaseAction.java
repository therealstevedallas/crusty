package com.walterj.crusty.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
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
        final HttpSession session = ServletActionContext.getRequest().getSession(true);
        final Object attribute = session.getAttribute(Constants.SESSION_KEY_ACCOUNT);
        if (checkLogin && attribute == null) {
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
        
        Map vars = ServletActionContext.getRequest().getParameterMap();
        StringBuilder sb = new StringBuilder("Request Params:");
        for (Object key : vars.keySet()) {
            
            Object v = vars.get(key);
            sb.append('\n').append(key).append(" = ").append(v);
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("logRequestParams(): " + sb.toString());
        }
        
    }
}
