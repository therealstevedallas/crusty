package com.walterj.util.web;

import com.walterj.crusty.action.LoginForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    // cache them so we aren't killing ourselves to have multiple users in different
    // locales writing out tables with 3 date fields in them
    private static final Map<String, DateFormat> formats = new ConcurrentHashMap<>();

    public static String formatTimestamp(Timestamp ts, String format) {

        DateFormat fmt = formats.get(format);
        if (fmt == null) {
            fmt = new SimpleDateFormat(format);
            formats.put(format,fmt);
        }
        return fmt.format(ts);
    }
}
