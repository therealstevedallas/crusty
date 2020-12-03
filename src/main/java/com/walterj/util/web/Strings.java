package com.walterj.util.web;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class is expected to be used with key/value pairs of strings using keys
 * with a prefix like "en" or "de" as the LANG abbv code for internationalization
 * {https://www.w3.org/International/O-charset-lang.html}
 * such that the "source" should have:
 *
 * en.title=Home Page
 * de.title=Startseite
 *
 * @author Walter Jordan
 */
public class Strings {

    final Map<String,String> strings;
    private String prefix;

    public Strings(Map<String,String> i18n, Locale lang)
        throws UnsupportedLanguageException {
        strings = i18n;
        prefix = lang.getLanguage().concat(".");
        if (!getKnownPrefixes().contains(lang.getLanguage()))
            throw new UnsupportedLanguageException(lang.getLanguage());
    }

    public Locale getPrefix() {
        // strip off the trailing dot
        return new Locale(prefix.replace(".", ""));
    }

    public void setPrefix(Locale locale) {
        this.prefix = locale.getLanguage().toLowerCase();
    }

    /**
     *
     * @param key The key for the value to return
     * @return The value mapped to [prefix].[key] or ?[key]?
     */
    public String get(String key) {

        String k = prefix.concat(key);
        String r = strings.get(k);
        return r != null ? r : "?".concat(k).concat("?");
    }

    /**
     * For testing language support dynamically.
     * @return A set of all prefixes, in lower case.
     */
    public Set<String> getKnownPrefixes() {

        Set<String> prefixes = new HashSet<>();
        for (String key : strings.keySet()) {
            prefixes.add(key.contains(".") ? key.substring(0, key.indexOf('.')) : "");
        }
        return prefixes;
    }

    public static class UnsupportedLanguageException extends Exception {

        public UnsupportedLanguageException(String message) {
            super(message);
        }
    }
}
