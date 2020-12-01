package com.walterj.crusty.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Walter Jordan
 */
public class DefaultAction extends BaseAction{
    @Override public String perform(HttpSession session, HttpServletRequest request) {
        return SUCCESS;
    }
}
