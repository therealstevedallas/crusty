package com.walterj.util;

import org.hibernate.SessionFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class HibernateSessionFactoryListener implements ServletContextListener {

    private static final Logger LOG = LogManager.getLogger(HibernateSessionFactoryListener.class.getName());

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        SessionFactory sessionFactory = (SessionFactory) servletContextEvent.getServletContext().getAttribute("SessionFactory");
        if(sessionFactory != null && !sessionFactory.isClosed()){
            LOG.info("Closing sessionFactory");
            sessionFactory.close();
        }
        LOG.info("Released Hibernate sessionFactory resource");
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute("SessionFactory", HibernateUtil.getSessionFactory());
        LOG.info("Hibernate SessionFactory Configured successfully");
    }

}

