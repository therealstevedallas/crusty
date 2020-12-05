package com.walterj.util.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.junit.Assert.fail;

/**
 * @author Walter Jordan
 */
public class StringsTest {
    private static final Logger LOG = LogManager.getLogger(StringsTest.class.getName());

    @Test
    public void testStrings() {

        Locale locale = new Locale("en");
        Map<String,String> m = new HashMap<>();
        m.put("en.test","Test!");

        Strings strings = null;
        try {
            strings = new Strings(m, locale);
            String t = strings.get("test");

            if (!t.equals("Test!"))
                fail("get() fails");
        }
        catch (Throwable t) {
            fail(t.getMessage());
        }
    }

    @Test
    public void testParamsUtil() {

        String fmt = "{0,number,integer} and {1,number,integer}";
        String res = ParamUtil.formatMessage(fmt, 1, 2);
        LOG.debug("testParamsUtil(): "+ res);
        assert("1 and 2".equals(res));
    }
}
