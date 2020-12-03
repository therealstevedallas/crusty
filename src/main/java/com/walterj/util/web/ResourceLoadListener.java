package com.walterj.util.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Walter Jordan
 */
public class ResourceLoadListener implements ServletContextListener {

    private static final Logger LOG = LogManager.getLogger(ResourceLoadListener.class.getName());
    private static final Map<String,String> strings = new ConcurrentHashMap<>();
    public static final String STRING_RESOURCES = "stringResources";

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        strings.clear();
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {

        final Properties props = new Properties();
        final ServletContext ctx = arg0.getServletContext();
        final String fname = ctx.getInitParameter(STRING_RESOURCES);
        final Path location = Paths.get(ctx.getRealPath("/") + fname);
        LOG.info("contextInitialized(): Loading strings from: " + location);

        try (InputStream inputStream = Files.newInputStream(location)) {
            props.load(inputStream);
            LOG.info("contextInitialized(): Loaded " + props.size() + " string resources");

            // copy to concurrent map so we have one for the whole application
            for (Map.Entry entry : props.entrySet()) {
                if (entry.getKey() == null) continue;
                String value = entry.getValue() == null
                    ? ""
                    : entry.getValue().toString();

                strings.put(entry.getKey().toString(), value);
            }
        } catch (IOException e) {
            LOG.error("contextInitialized(): " + e.getMessage(), e);
        }
        ctx.setAttribute(STRING_RESOURCES, strings);
    }
}