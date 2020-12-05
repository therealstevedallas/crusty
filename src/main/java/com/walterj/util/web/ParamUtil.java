package com.walterj.util.web;

import com.walterj.crusty.action.LoginForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Walter Jordan
 */
public class ParamUtil {

    private static final Logger LOG = LogManager.getLogger(ParamUtil.class.getName());
    /**
     * Get a request or session param/attribute as an int
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

    /**
     * Useful for formatiing Timestamps between DAO objects and web output.
     * @param ts A {@link Timestamp}
     * @param format A {@link SimpleDateFormat} formatting pattern.
     * @return The date/timestamp as formatted string or if the pattern is no good
     *         the value will be whatever the toString of ts yields.
     */
    public static String formatTimestamp(Timestamp ts, String format) {

        boolean error  = false;
        DateFormat fmt = formats.get(format);
        if (fmt == null) {
            try {
                fmt = new SimpleDateFormat(format);
                formats.put(format, fmt);
            }
            catch (Throwable e) {
                LOG.error("formatTimestamp(): [" + format + "]: "
                    + e.getMessage(), e);
                error = true;
            }
        }
        return error ? ts.toString() : fmt.format(ts);
    }

    public static String formatMessage(String msg, Object... params) {
        boolean error  = false;
        MessageFormat fmt = new MessageFormat(msg);
        return fmt.format(params);
    }
}
