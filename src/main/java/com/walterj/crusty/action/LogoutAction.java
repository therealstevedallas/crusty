package com.walterj.crusty.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Carries login information during verify and login
 *
 * @author Walter Jordan
 * @version $Id: $Id
 */
public class LogoutAction extends BaseAction {

    private static final Logger LOG = LogManager.getLogger(LogoutAction.class.getName());

    @Override public String perform(HttpSession session, HttpServletRequest request) {

        LOG.debug("perform(): called");
        session.removeAttribute(Constants.SESSION_KEY_CURRENT_LOGIN);
        return SUCCESS;
    }
}
