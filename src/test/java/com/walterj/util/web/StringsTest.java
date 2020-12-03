package com.walterj.util.web;

import org.junit.Test;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.junit.Assert.fail;

/**
 * @author Walter Jordan
 */
public class StringsTest {

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
}
