package com.walterj.util.web;

import com.walterj.crusty.action.LoginForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Walter Jordan
 */
public class ParamUtil {

    private static final Logger LOG = LogManager.getLogger(ParamUtil.class.getName());
    /**
     *
     * @param name The name of the parameter
     * @param req The request
     * @param search Look in the session if it's not in the request?
     * @return The parameter or zero
     */
    public static int getIntParam(String name, HttpServletRequest req, boolean search) {

        Object o = req.getAttribute(name);
        if (o == null && search) {
            o = req.getSession(true).getAttribute(name);
        }
        if  (o == null)
            return 0;

        int rVal = 0;
        if (o instanceof String) {
            try {rVal = Integer.parseInt(o.toString());} catch (Throwable e) {
                LOG.warn("getIntParam(): Value cannot be parsed as an integer: " + o);
            }
        }
        else try {rVal = (int)o;} catch (Throwable e){
            LOG.warn("getIntParam(): Cast to int failed: " + o);
        }
        return rVal;
    }

}
